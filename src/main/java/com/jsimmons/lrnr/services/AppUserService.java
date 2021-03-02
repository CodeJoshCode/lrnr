package com.jsimmons.lrnr.services;

import com.jsimmons.lrnr.entities.AppUser;
import com.jsimmons.lrnr.repositories.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

    private static final String USER_NOT_FOUND_MSG =
            "user with email %s not found";
    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return appUserRepository
                .findByEmail(email)
                .orElseThrow(
                        () -> new UsernameNotFoundException
                                (String.format(USER_NOT_FOUND_MSG, email)));
    }

    public String signUpUser(AppUser appUser) {

        boolean userExists = appUserRepository
                .findByEmail(appUser.getEmail())
                .isPresent();

        if (userExists) {
            throw  new IllegalStateException("email already taken");
        }

        String encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());

        appUser.setPassword(encodedPassword);

        appUserRepository.save(appUser);

        //Todo : send confirmation token

        return "it works";
    }
}
