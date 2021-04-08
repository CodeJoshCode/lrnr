package com.jsimmons.lrnr.repositories;

import com.jsimmons.lrnr.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
@EnableWebSecurity
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findByEmail(String email);

    /*
    ON @Transactional:

    Jpa itself does not provide any kind of declarative transaction management (it just does magic on your @entity obj's)
    When using jpa outside of a dependency injection container i.e. springs ioc container, transactions need to be handled
    programmatically by the developer.

    it looks like @Transactional abstracts having to create a db connection, carrying out a single db transaction,
    and then finally closing that connection.

     */

    //TODO : this probably belongs in the service layer... see:  https://stackoverflow.com/questions/1079114/where-does-the-transactional-annotation-belong

    // this query uses a "prepared statement" (maybe?),  (separates query logic from data) safer against sql injection
    @Transactional
    @Modifying
    @Query("UPDATE AppUser a " +
            "SET a.enabled = TRUE WHERE a.email = ?1")
    int enableAppUser(String email);
}
