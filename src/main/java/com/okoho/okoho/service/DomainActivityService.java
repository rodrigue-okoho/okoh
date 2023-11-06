package com.okoho.okoho.service;

import com.okoho.okoho.service.dto.DomainActivityDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.okoho.okoho.domain.DomainActivity}.
 */
public interface DomainActivityService {
    /**
     * Save a domainActivity.
     *
     * @param domainActivityDTO the entity to save.
     * @return the persisted entity.
     */
    DomainActivityDTO save(DomainActivityDTO domainActivityDTO);

    /**
     * Partially updates a domainActivity.
     *
     * @param domainActivityDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<DomainActivityDTO> partialUpdate(DomainActivityDTO domainActivityDTO);

    /**
     * Get all the domainActivities.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DomainActivityDTO> findAll(Pageable pageable);

    /**
     * Get the "id" domainActivity.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DomainActivityDTO> findOne(String id);

    /**
     * Delete the "id" domainActivity.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
