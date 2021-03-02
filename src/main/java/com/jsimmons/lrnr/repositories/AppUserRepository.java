package com.jsimmons.lrnr.repositories;

import com.jsimmons.lrnr.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
@EnableWebSecurity
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findByEmail(String email);
}
