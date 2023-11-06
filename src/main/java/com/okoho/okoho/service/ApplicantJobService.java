package com.okoho.okoho.service;

import com.okoho.okoho.domain.ApplicantJob;
import com.okoho.okoho.domain.Candidat;
import com.okoho.okoho.domain.Competence;
import com.okoho.okoho.service.criteria.CandidatCriteria;
import com.okoho.okoho.service.dto.ApplicantJobDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ApplicantJobService {
    /**
     * Save a competence.
     *
     * @param applicantJobDTO the entity to save.
     * @return the persisted entity.
     */
    ApplicantJob save(ApplicantJobDTO applicantJobDTO);

    /**
     * Partially updates a competence.
     *
     * @param applicantJobDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ApplicantJob> partialUpdate(ApplicantJobDTO applicantJobDTO);

    /**
     * Get all the competences.
     *
     * @return the list of entities.
     */
    List<ApplicantJob> findAll();
    Page<ApplicantJob> findAllWithEagerRelationships(Pageable pageable);
    Page<ApplicantJob> findAllByCandidat(Pageable pageable,String candidatID);
    Page<ApplicantJob> findAllByEntreprise(Pageable pageable,String job_id);

    /**
     * Get the "id" competence.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ApplicantJob> findOne(String id);

    /**
     * Delete the "id" competence.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
