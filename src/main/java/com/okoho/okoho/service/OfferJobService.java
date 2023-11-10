package com.okoho.okoho.service;

import com.okoho.okoho.domain.OfferJob;
import com.okoho.okoho.service.dto.OfferJobDTO;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.okoho.okoho.domain.OfferJob}.
 */
public interface OfferJobService {
    /**
     * Save a offerJob.
     *
     * @param offerJobDTO the entity to save.
     * @return the persisted entity.
     */
    OfferJob save(OfferJobDTO offerJobDTO);

    /**
     * Partially updates a offerJob.
     *
     * @param offerJobDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<OfferJobDTO> partialUpdate(OfferJobDTO offerJobDTO);

    /**
     * Get all the offerJobs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<OfferJob> findAll(Pageable pageable);
    Page<OfferJob> findLastJobs(Pageable pageable);
    Page<OfferJob> findByEntreprise(Pageable pageable,String entreprise);
    List<OfferJob> findByEntreprise(String entreprise);
    Page<OfferJob> findAlertJobByCandidat(Pageable pageable,String candidat);
    Page<OfferJob> findSearch(Pageable pageable,
    String query,
    String location,
    String category,
    String experience,
    String dateposted,
    String salary,
    String type);
    /**
     * Get the "id" offerJob.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<OfferJob> findOne(String id);

    /**
     * Delete the "id" offerJob.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
