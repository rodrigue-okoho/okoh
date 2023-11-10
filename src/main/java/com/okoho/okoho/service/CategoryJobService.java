package com.okoho.okoho.service;

import com.okoho.okoho.domain.CategoryJob;
import com.okoho.okoho.service.dto.CategoryJobDTO;
import com.okoho.okoho.service.dto.DomainActivityDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.okoho.okoho.domain.CategoryJob}.
 */
public interface CategoryJobService {
    /**
     * Save a categoryJob.
     *
     * @param CategoryJob the entity to save.
     * @return the persisted entity.
     */
    CategoryJob save(CategoryJobDTO categoryJob);

    /**
     * Partially updates a categoryJob.
     *
     * @param CategoryJob the entity to update partially.
     * @return the persisted entity.
     */
    Optional<CategoryJob> partialUpdate(CategoryJob categoryJob);

    /**
     * Get all the categoryJobs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<CategoryJob> findAll(Pageable pageable);

    /**
     * Get the "id" categoryJob.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CategoryJob> findOne(String id);

    /**
     * Delete the "id" categoryJob.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
