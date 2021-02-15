package com.jsimmons.lrnr.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    //TODO : figure out exactly why constuctor DI is better than field DI
    @Autowired
    SecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/")
                .permitAll()
                // TODO: check back and figure these out maybe?
                .anyRequest()
                .authenticated()
                .and()
                // TODO : eventually blast some oauth 2 not basic auth
                .httpBasic();
    }


    // UserDetailsService is used here to authenticate a user the is pre-defined by us in the code, in order to authenticate
    //from outside of our database/data model, use the AuthenticationProvider interface. https://stackoverflow.com/questions/31630818/spring-security-custom-authentication-authenticationprovider-vs-userdetailsser
    //the common implementation is through one of the overloaded configuration methods configure() methods belonging to
    //WebSecurityConfigurationAdapter as we've done above. just find the one that takes an AuthenticationManagerBuilder

    //also note, at the end of the day, UserDetailsService is (according to the Stack overflow post above) simply a DAO for user details
    //the bean is handled under the hood by the AuthenticationProvider (AuthenticationManager). UserDetailsService does not provide authentication by itself.
    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails Admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("password"))
                .roles("ADMIN")
                .build();
        //TODO : eventually configure to store credentials in database ie not returning inmemoryuserdetailsmanager
        return new InMemoryUserDetailsManager(
                Admin
        );
    }
}
