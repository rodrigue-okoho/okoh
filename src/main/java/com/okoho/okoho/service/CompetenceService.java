package com.okoho.okoho.service;

import com.okoho.okoho.domain.Competence;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Competence}.
 */
public interface CompetenceService {
    /**
     * Save a competence.
     *
     * @param competence the entity to save.
     * @return the persisted entity.
     */
    Competence save(Competence competence);

    /**
     * Partially updates a competence.
     *
     * @param competence the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Competence> partialUpdate(Competence competence);

    /**
     * Get all the competences.
     *
     * @return the list of entities.
     */
    List<Competence> findAll();

    /**
     * Get the "id" competence.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Competence> findOne(String id);

    /**
     * Delete the "id" competence.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
