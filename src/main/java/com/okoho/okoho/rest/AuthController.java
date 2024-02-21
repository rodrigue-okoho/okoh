package com.okoho.okoho.rest;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.okoho.okoho.service.dto.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.okoho.okoho.domain.UserAccount;
import com.okoho.okoho.rest.errors.TypeAccountExeption;
import com.okoho.okoho.security.JWTUtil;
import com.okoho.okoho.security.JwtUserDetailsService;
import com.okoho.okoho.security.UserDetailsImpl;
import com.okoho.okoho.service.MailService;
import com.okoho.okoho.service.UserAccountService;
import com.okoho.okoho.service.mapper.UserAccountMapper;
import org.springframework.web.server.ResponseStatusException;


@RestController
@CrossOrigin
public class AuthController {


    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private AuthenticationManagerBuilder authenticationManagerBuilder;
    @Autowired
    private MailService mailService;
    @Autowired
    private UserAccountService userServiceInterface;
    @Autowired
    private UserAccountMapper userAccountMapper;
    @Autowired
    private JWTUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @PostMapping("/auth/signin")
    public ResponseEntity<JwtResponse> authenticateUser(@Validated @RequestBody JwtRequest loginRequest) {
        System.out.println("******************************************");
        System.out.println(loginRequest.toString());
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtTokenUtil.generateToken(loginRequest.getEmail());
            System.out.println(jwt);
            var user = userServiceInterface
                    .getUserWithAuthorities()
                    .map(UserDTO::new)
                    .orElseThrow();
            /*	UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();*/
            Set<String> roles = new HashSet<>();
            if (user.getRoles() != null) {
                roles = new HashSet<>(user.getRoles());
            }

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Value token", "Bearer " + jwt);
            return new ResponseEntity<>(new JwtResponse(jwt,
                    user.getId(),
                    user.getFirstName(),
                    user.getEmail(), roles, user.getUserType()), httpHeaders, HttpStatus.OK);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage());
        }

    }

    @PostMapping("/auth/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerUser(@Validated @RequestBody RegisterRequest registerRequest) {
        HttpHeaders httpHeaders = new HttpHeaders();

        if (registerRequest.getUser_type() == null) {
            throw new TypeAccountExeption(new Exception("pb"));
        }
        var account = userServiceInterface.register(registerRequest);
        mailService.sendActivationEmail(userAccountMapper.toEntity(account));
    }

    @GetMapping("/auth/forgotpassword/{email}")
    public ResponseEntity<?> resetPassword(@PathVariable("email") String email) {
        var existingItemOptional = userServiceInterface.resetpassword(email);
        return ResponseEntity.ok(existingItemOptional);
    }

    /**
     * {@code POST  /account/change-password} : changes the current user's password.
     *
     * @param passwordChangeDto current and new password.
     */
    @PostMapping(path = "/v1/account/change-password")
    public void changePassword(@RequestBody PasswordChangeDTO passwordChangeDto) {
        if (isPasswordLengthInvalid(passwordChangeDto.getNewPassword())) {
            //  throw new InvalidPasswordException();
        }
        userServiceInterface.changePassword(passwordChangeDto.getCurrentPassword(), passwordChangeDto.getNewPassword());
    }

    @GetMapping(path = "/v1/account/email/{id}")
    public void sendMailTest(@PathVariable("id") String id) {
        var account = userServiceInterface.findOne(id).get();
        mailService.sendActivationEmail(userAccountMapper.toEntity(account));
    }

    /**
     * {@code GET  /activate} : activate the registered user.
     *
     * @param key the activation key.
     * @throws RuntimeException {@code 500 (Internal Server Error)} if the user couldn't be activated.
     */
    @GetMapping("/auth/activate")
    public void activateAccount(@RequestParam(value = "key") String key) {
        System.out.println("---------------------");
        Optional<UserAccount> user = userServiceInterface.activateRegistration(key);
        if (!user.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "No user was found for this activation key");
        }
    }

    /**
     * {@code POST   /account/reset-password/init} : Send an email to reset the password of the user.
     *
     * @param mail the mail of the user.
     */
    @PostMapping(path = "/auth/reset-password/init")
    public void requestPasswordReset(@RequestBody String mail) {
        Optional<UserAccount> user = userServiceInterface.requestPasswordReset(mail);
        if (user.isPresent()) {
            mailService.sendPasswordResetMail(user.get());
        } else {
            // Pretend the request has been successful to prevent checking which emails really exist
            // but log that an invalid attempt has been made
            //log.warn("Password reset requested for non existing mail");
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "Password reset requested for non existing mail");
        }
    }

    /**
     * {@code POST   /account/reset-password/finish} : Finish to reset the password of the user.
     *
     * @param keyAndPassword the generated key and the new password.
     * @throws RuntimeException {@code 500 (Internal Server Error)} if the password could not be reset.
     */
    @PostMapping(path = "/auth/reset-password/finish")
    public void finishPasswordReset(@RequestBody KeyAndPasswordDTO keyAndPassword) {
        if (isPasswordLengthInvalid(keyAndPassword.getNewPassword())) {
            // throw new InvalidPasswordException();
        }
        Optional<UserAccount> user = userServiceInterface.completePasswordReset(keyAndPassword.getNewPassword(), keyAndPassword.getKey());

        if (!user.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "No user was found for this reset key");
            // throw new AccountResourceException("No user was found for this reset key");
        }
    }

    private static boolean isPasswordLengthInvalid(String password) {
        return (
                StringUtils.isEmpty(password) ||
                        password.length() < 3 ||
                        password.length() > 20
        );
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}