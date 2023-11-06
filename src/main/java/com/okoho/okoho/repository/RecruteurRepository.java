package com.okoho.okoho.repository;

import com.okoho.okoho.domain.Recruteur;

import com.okoho.okoho.domain.UserAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data MongoDB repository for the Recruteur entity.
 */
@Repository
public interface RecruteurRepository extends MongoRepository<Recruteur, String> {
    @Query("{}")
    Page<Recruteur> findAllWithEagerRelationships(Pageable pageable);
    @Query("{}")
    Page<Recruteur> findAll(Pageable pageable);

    Optional<Recruteur> findFirstByUserAccount(UserAccount userAccount);
}
