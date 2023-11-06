package com.okoho.okoho.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.okoho.okoho.domain.UserAccount;
import com.okoho.okoho.repository.UserAccountRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Component
public class JwtUserDetailsService  implements UserDetailsService {

    @Autowired
    private UserAccountRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserAccount> userRes = userRepo.findOneByEmailIgnoreCase(username);
        if(userRes.isEmpty())
            throw new UsernameNotFoundException("Could not findUser with email = " + username);
        if(!userRes.get().isActivated())
            throw new UsernameNotFoundException("Could not findUser with email = " + username);
            UserAccount user1 = userRes.get();
        List<GrantedAuthority> grantedAuthorities = user1.getRoles()
        .stream()
        .map(authority -> new SimpleGrantedAuthority(authority.getName().name()))
        .collect(Collectors.toList()); 
       return new org.springframework.security.core.userdetails.User(
        username, user1.getPassword(), grantedAuthorities);
    }
    private org.springframework.security.core.userdetails.User createSpringSecurityUser(String lowercaseLogin, UserAccount user) {
        if (!user.isActivated()) {
            throw new UsernameNotFoundException("User " + lowercaseLogin + " was not activated");
        }
       
         List<GrantedAuthority> grantedAuthorities = user
                .getRoles()
                .stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getName().name()))
                .collect(Collectors.toList()); 
                
                
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), grantedAuthorities);
    }

}