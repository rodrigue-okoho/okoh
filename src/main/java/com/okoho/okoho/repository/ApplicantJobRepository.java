package com.okoho.okoho.repository;

import com.okoho.okoho.domain.ApplicantJob;
import com.okoho.okoho.domain.Candidat;
import com.okoho.okoho.domain.OfferJob;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data MongoDB repository for the Competence entity.
 */
@Repository
public interface ApplicantJobRepository extends MongoRepository<ApplicantJob, String> {
    @Query("{}")
    List<ApplicantJob> findAllWithEagerRelationships();
    @Query("{}")
    Page<ApplicantJob> findAllWithEagerRelationships(Pageable pageable);
    @Query("{}")
    Page<ApplicantJob> findByCandidat(Pageable pageable,Candidat candidat);
    
    Optional<ApplicantJob> findFirstByCandidatAndOfferJob(Candidat candidat, OfferJob offerJob);
    Optional<ApplicantJob> findFirstByCandidat(Candidat candidat);
}
