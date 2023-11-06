package com.okoho.okoho.service;

import com.okoho.okoho.domain.Experience;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link Experience}.
 */
public interface ExperienceService {
    /**
     * Save a experience.
     *
     * @param experience the entity to save.
     * @return the persisted entity.
     */
    Experience save(Experience experience);

    /**
     * Partially updates a experience.
     *
     * @param experience the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Experience> partialUpdate(Experience experience);

    /**
     * Get all the experiences.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Experience> findAll(Pageable pageable);

    /**
     * Get the "id" experience.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Experience> findOne(String id);

    /**
     * Delete the "id" experience.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
