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

    @Transactional abstracts a db transaction (follows ACID principles, blocks changes to the data being operated on during the transaction,
    and ensures either all operations execute (commit) or the state of db will remain the same as before the transaction (rollback)

     */

    //TODO : this probably belongs in the service layer... see:  https://stackoverflow.com/questions/1079114/where-does-the-transactional-annotation-belong

    // query uses jpql - ?1 will correspond to first argument, if ?2 was there it would correspond to second method arg, and so on
    @Transactional
    @Modifying
    @Query("UPDATE AppUser a " +
            "SET a.enabled = TRUE WHERE a.email = ?1")
    int enableAppUser(String email);
}
