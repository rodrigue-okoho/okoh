package com.okoho.okoho.service;

import com.okoho.okoho.domain.Candidat;
import com.okoho.okoho.domain.Recruteur;
import com.okoho.okoho.service.dto.RecruteurDDTO;
import com.okoho.okoho.service.dto.RecruteurDTO;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.okoho.okoho.domain.Recruteur}.
 */
public interface RecruteurService {
    /**
     * Save a recruteur.
     *
     * @param recruteurDTO the entity to save.
     * @return the persisted entity.
     */
    Recruteur save(RecruteurDTO recruteurDTO);

    /**
     * Partially updates a recruteur.
     *
     * @param recruteurDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<RecruteurDDTO> partialUpdate(RecruteurDDTO recruteurDTO);

    /**
     * Get all the recruteurs.
     *
     * @return the list of entities.
     */
    List<RecruteurDDTO> findAll();
    /**
     * Get all the candidats with eager load of many-to-many relationships.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Recruteur> findAllWithEagerRelationships(Pageable pageable);
    Page<Recruteur> findSearch(Pageable pageable,String query,String city,String category,String founded,String compagny_size);
    /**
     * Get the "id" recruteur.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<RecruteurDDTO> findOne(String id);
    Optional<Recruteur> findProfile(String id);
    /**
     * Delete the "id" recruteur.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
