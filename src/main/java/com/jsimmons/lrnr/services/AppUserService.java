package com.jsimmons.lrnr.services;

import com.jsimmons.lrnr.entities.AppUser;
import com.jsimmons.lrnr.registration.token.ConfirmationToken;
import com.jsimmons.lrnr.registration.token.ConfirmationTokenService;
import com.jsimmons.lrnr.repositories.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

    private static final String USER_NOT_FOUND_MSG =
            "user with email %s not found";
    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

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
        if ( userExists ) {
           if ( appUserRepository.findByEmail(appUser.getEmail()).get().isEnabled()) {
               throw new IllegalStateException("email already taken");
           }
           //user exists but not enabled below
           else {

           }

        } else {

            String encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());

            appUser.setPassword(encodedPassword);

            appUserRepository.save(appUser);
        }

        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                appUser
        );

        confirmationTokenService.saveConfirmationToken(
                confirmationToken);

        //TODO : send email

        return token;
    }

    public int enableAppUser(String email) {
        return appUserRepository.enableAppUser(email);
    }

    public boolean appUserExistsAndEnabled(String email) {
        boolean userExists =  appUserExists(email);

        if ( userExists) {
            if ( appUserRepository.findByEmail(email).get().isEnabled()) {
                return true;
            }

        }
        return false;
    }

    public boolean appUserExists(String email) {
        return appUserRepository
                .findByEmail(email)
                .isPresent();
    }

    public Optional<AppUser> findByEmail(String email) {
        return appUserRepository.findByEmail(email);
    }

}
