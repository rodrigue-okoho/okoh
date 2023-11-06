package com.okoho.okoho.repository;

import com.okoho.okoho.domain.Candidat;
import com.okoho.okoho.domain.UserAccount;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the Candidat entity.
 */
@Repository
public interface CandidatRepository extends MongoRepository<Candidat, String> {
    @Query("{}")
    Page<Candidat> findAllWithEagerRelationships(Pageable pageable);

    @Query("{}")
    List<Candidat> findAllWithEagerRelationships();

    @Query("{'id': ?0}")
    Optional<Candidat> findOneWithEagerRelationships(String id);
    //@Query("{'user_account': ?0}")
    Optional<Candidat> findFirstByUserAccount(UserAccount userAccount);
}
