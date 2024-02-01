package com.okoho.okoho.service.impl;

import java.time.Instant;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.okoho.okoho.domain.Candidat;
import com.okoho.okoho.domain.ERole;
import com.okoho.okoho.domain.Recruteur;
import com.okoho.okoho.domain.UserAccount;
import com.okoho.okoho.repository.CandidatRepository;
import com.okoho.okoho.repository.RecruteurRepository;
import com.okoho.okoho.repository.RoleRepository;
import com.okoho.okoho.repository.UserAccountRepository;
import com.okoho.okoho.rest.errors.BadRequestAlertException;
import com.okoho.okoho.rest.errors.EmailAlreadyUsedException;
import com.okoho.okoho.rest.errors.TypeAccountExeption;
import com.okoho.okoho.security.SecurityUtils;
import com.okoho.okoho.service.CandidatService;
import com.okoho.okoho.service.RecruteurService;
import com.okoho.okoho.service.UserAccountService;
import com.okoho.okoho.service.dto.CandidatDTO;
import com.okoho.okoho.service.dto.RecruteurDTO;
import com.okoho.okoho.service.dto.RegisterRequest;
import com.okoho.okoho.service.dto.UserAccountDTO;
import com.okoho.okoho.service.mapper.UserAccountMapper;
import com.okoho.okoho.utils.Constant;
import com.okoho.okoho.utils.KeyUtil;

/**
 * Service Implementation for managing {@link UserAccount}.
 */
@Service
public class UserAccountServiceImpl implements UserAccountService {

    private final Logger log = LoggerFactory.getLogger(UserAccountServiceImpl.class);

    private final UserAccountRepository userAccountRepository;
    private final RoleRepository roleRepository;
    private final CandidatRepository candidatService;
    private final RecruteurRepository recruteurService;
    private final UserAccountMapper userAccountMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserAccountServiceImpl(UserAccountRepository userAccountRepository, CandidatRepository candidatService,
            RecruteurRepository recruteurService, UserAccountMapper userAccountMapper,RoleRepository repository) {
        this.userAccountRepository = userAccountRepository;
        this.candidatService = candidatService;
        this.recruteurService = recruteurService;
        this.userAccountMapper = userAccountMapper;
        this.roleRepository=repository;
    }

    @Override
    public UserAccountDTO register(RegisterRequest registerRequest) {
        List<String> arrays = new ArrayList<>();
        arrays.add(Constant.CANDIDATACCOUNT);
        arrays.add(Constant.ENTREPRISEACCOUNT);
        arrays.add(Constant.PERSONNALACCOUNT);
        if (!arrays.contains(registerRequest.getUser_type())) {
            throw new BadRequestAlertException("Invalid user", "Candidate", "id null");
        }

        userAccountRepository
                .findOneByEmailIgnoreCase(registerRequest.getEmail())
                .ifPresent(
                        existingUser -> {
                            boolean removed = removeNonActivatedUser(existingUser);
                            if (!removed) {
                                throw new EmailAlreadyUsedException();
                            }
                        });
        UserAccount account = new UserAccount();
        account.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        account.setEmail(registerRequest.getEmail());
        account.setUserType(registerRequest.getUser_type());
        var key = KeyUtil.randonKey(29);
        account.setResetKey(key);
        account.setActivationKey(key);
        System.out.println("---------------------------");
        if (registerRequest.getLangKey() == null) {
            account.setLangKey("en"); // default language
        } else {
            account.setLangKey(registerRequest.getLangKey());
        }
        if(registerRequest.getUser_type().equals(Constant.CANDIDATACCOUNT)){
            account.getRoles().add(roleRepository.findByName(ERole.ROLE_CANDIDATE).get());
        }else if(registerRequest.getUser_type().equals(Constant.ENTREPRISEACCOUNT)){
            account.getRoles().add(roleRepository.findByName(ERole.ROLE_ENTREPRISE).get());
        }
        
        var acc1 = userAccountRepository.save(account);
        System.out.println(acc1.getResetKey());
        return userAccountMapper.toDto(acc1);
    }

    @Override
    @Transactional
    public UserAccountDTO save(RegisterRequest registerRequest) {

        if (registerRequest.getUser_type() == null) {
            // throw new TypeAccountExeption();
        }
        userAccountRepository
                .findOneByEmailIgnoreCase(registerRequest.getEmail())
                .ifPresent(
                        existingUser -> {
                            boolean removed = removeNonActivatedUser(existingUser);
                            if (!removed) {
                                throw new EmailAlreadyUsedException();
                            }
                        });
        UserAccount account = new UserAccount();
        // account.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        // account.setEmail(registerRequest.getEmail());
        account.setFirstName(registerRequest.getFirstName());
        account.setLastName(registerRequest.getLastName());
        account.setImageUrl(registerRequest.getImageUrl());
        userAccountRepository.save(account);
        if (registerRequest.getLangKey() == null) {
            account.setLangKey("de"); // default language
        } else {
            account.setLangKey(registerRequest.getLangKey());
        }
        if (registerRequest.getUser_type() == Constant.CANDIDATACCOUNT) {
            var candidat = new Candidat();
            candidat.setCountry(registerRequest.getCountry());
            candidat.setDob(registerRequest.getDob());
            candidat.setUserAccount(account);
            // candidatService.save(candidat);
        } else if (registerRequest.getUser_type() == Constant.ENTREPRISEACCOUNT) {
            var recruteur = new RecruteurDTO();
            recruteur.setCountry(registerRequest.getCountry());
            recruteur.setDob(registerRequest.getDob());
            recruteur.setBp(registerRequest.getBp());
            recruteur.setEntreprise(registerRequest.getEntreprise());
            recruteur.setUserAccount(account);
            // recruteurService.save(recruteur);
        } else {

        }
        return userAccountMapper.toDto(account);
    }

    private boolean removeNonActivatedUser(UserAccount existingUser) {
        if (existingUser.isActivated()) {
            return false;
        }
        userAccountRepository.delete(existingUser);
        this.clearUserCaches(existingUser);
        return true;
    }

    @Override
    public Optional<UserAccountDTO> partialUpdate(UserAccountDTO userAccountDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'partialUpdate'");
    }

    @Override
    public List<UserAccountDTO> findAll() {
       return userAccountRepository.findAll().stream().filter(e->e.getUserType()==Constant.CANDIDATACCOUNT).map(userAccountMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<UserAccountDTO> findIsMessage(String id) {
        var account=userAccountRepository.findById(id).get();
        if (account.getUserType().equals(Constant.CANDIDATACCOUNT)){
            return userAccountRepository.findAll().stream().filter(e-> Objects.equals(e.getUserType(), Constant.ENTREPRISEACCOUNT))
                    .map(userAccountMapper::toDto).collect(Collectors.toList());
        }else {
            return userAccountRepository.findAll().stream().filter(e-> Objects.equals(e.getUserType(), Constant.CANDIDATACCOUNT))
                    .map(userAccountMapper::toDto).collect(Collectors.toList());
        }

    }

    public List<UserAccountDTO> findIsRecruteur() {
        return userAccountRepository.findAll().stream().filter(e-> Objects.equals(e.getUserType(), Constant.ENTREPRISEACCOUNT)).map(userAccountMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public Optional<UserAccountDTO> findOne(String id) {
    
       var account = userAccountRepository.findById(id);
        //return userAccountMapper.toDto(account);
        Optional<UserAccountDTO> opt = Optional.ofNullable(userAccountMapper.toDto(account));
       return opt;
    }

    @Override
    public void delete(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public Object resetpassword(String email) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'resetpassword'");
    }

    @Transactional(readOnly = true)
    public Optional<UserAccount> getUserWithAuthorities() {
        return SecurityUtils.getCurrentUserLogin().flatMap(userAccountRepository::findOneWithRolesByEmail);
    }

    public Optional<UserAccount> activateRegistration(String key) {
        log.debug("Activating user for activation key {}", key);
        System.out.println("---------------------");
        System.out.println(key);
        Optional<UserAccount> user2= userAccountRepository
                .findOneByActivationKey(key)
                .map(
                        user -> {
                            // activate given user for the registration key.
                            user.setActivated(true);
                            user.setActivationKey(null);
                            userAccountRepository.save(user);
                             this.clearUserCaches(user);
                            log.debug("Activated user: {}", user);
                            return user;
                        });
                      if(user2.get().getActivated()){ 
                        if (user2.get().getUserType().equals(Constant.CANDIDATACCOUNT)) {
                           System.out.println("---------------------"+user2.get().getUserType());
                            var candidat = new Candidat();
                            candidat.setUserAccount(user2.get());
                            candidatService.save(candidat);
                        } else if (user2.get().getUserType().equals(Constant.ENTREPRISEACCOUNT) ) {
                            var recruteur = new Recruteur();
                            recruteur.setUserAccount(user2.get());
                            recruteurService.save(recruteur);
                        } 
                      }
                                         
                        return user2;
    }

    @Override
    public boolean isEnabledUser(String username) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isEnabledUser'");
    }

    private void clearUserCaches(UserAccount user) {

    }

    public Optional<UserAccount> completePasswordReset(String newPassword, String key) {
        log.debug("Reset user password for reset key {}", key);
        return userAccountRepository
                .findOneByResetKey(key)
                .filter(user -> user.getResetDate().isAfter(Instant.now().minusSeconds(86400)))
                .map(
                        user -> {
                            user.setPassword(passwordEncoder.encode(newPassword));
                            user.setResetKey(null);
                            user.setResetDate(null);
                            this.clearUserCaches(user);
                            return user;
                        });
    }

    public Optional<UserAccount> requestPasswordReset(String mail) {
        return userAccountRepository
                .findOneByEmailIgnoreCase(mail)
                .filter(UserAccount::isActivated)
                .map(
                        user -> {
                            user.setResetKey(KeyUtil.randonKey(18));
                            user.setResetDate(Instant.now());
                            this.clearUserCaches(user);
                            return user;
                        });
    }

    @Transactional
    public void changePassword(String currentClearTextPassword, String newPassword) {
        SecurityUtils
                .getCurrentUserLogin()
                .flatMap(userAccountRepository::findOneByEmail)
                .ifPresent(
                        user -> {
                            String currentEncryptedPassword = user.getPassword();
                            if (!passwordEncoder.matches(currentClearTextPassword, currentEncryptedPassword)) {
                                // throw new InvalidPasswordException();
                            }
                            String encryptedPassword = passwordEncoder.encode(newPassword);
                            user.setPassword(encryptedPassword);
                            this.clearUserCaches(user);
                            log.debug("Changed password for User: {}", user);
                        });
    }
}
