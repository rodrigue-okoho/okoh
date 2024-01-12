package com.okoho.okoho.repository;

import com.okoho.okoho.domain.OfferJob;
import com.okoho.okoho.domain.Recruteur;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;


/**
 * Spring Data MongoDB repository for the OfferJob entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OfferJobRepository extends MongoRepository<OfferJob, String> {
    @Query("{}")
    Page<OfferJob>findByRecruteur(Pageable pageable,Recruteur recruteur);
    List<OfferJob>findByRecruteur(Recruteur recruteur);

    @Query("{'id': ?0}")
    Optional<OfferJob> findOneWithEagerRelationships(String id);
}
