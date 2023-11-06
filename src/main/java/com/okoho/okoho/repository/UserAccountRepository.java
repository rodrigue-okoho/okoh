package com.okoho.okoho.repository;

import com.okoho.okoho.domain.UserAccount;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the UserAccount entity.
 */

public interface UserAccountRepository extends MongoRepository<UserAccount, String> {
    Optional<UserAccount> findOneByEmail(String username);
    Optional<UserAccount> findOneByEmailIgnoreCase(String email);
    Optional<UserAccount> findOneByActivationKey(String activationKey);

    List<UserAccount> findAllByActivatedIsFalseAndActivationKeyIsNotNullAndCreatedDateBefore(Instant dateTime);
    Optional<UserAccount> findOneWithRolesByEmail(String login);
    Optional<UserAccount> findOneByResetKey(String resetKey);
    Optional<UserAccount> findOneWithRolesByEmailIgnoreCase(String email);
}
