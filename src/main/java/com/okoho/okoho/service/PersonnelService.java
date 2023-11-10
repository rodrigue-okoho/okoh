package com.okoho.okoho.service;

import com.okoho.okoho.domain.Personnel;
import com.okoho.okoho.service.dto.PersonnelDDTO;
import com.okoho.okoho.service.dto.PersonnelDTO;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.okoho.okoho.domain.Personnel}.
 */
public interface PersonnelService {
    /**
     * Save a personnel.
     *
     * @param personnelDTO the entity to save.
     * @return the persisted entity.
     */
    PersonnelDTO save(PersonnelDDTO personnelDTO);

    /**
     * Partially updates a personnel.
     *
     * @param personnelDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<PersonnelDTO> partialUpdate(PersonnelDTO personnelDTO);

    /**
     * Get all the personnel.
     *
     * @return the list of entities.
     */
    List<PersonnelDTO> findAll();
    Page<Personnel> findAllWithEagerRelationships(Pageable pageable);
    /**
     * Get the "id" personnel.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PersonnelDTO> findOne(String id);

    /**
     * Delete the "id" personnel.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
