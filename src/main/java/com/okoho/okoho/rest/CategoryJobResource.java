package com.okoho.okoho.rest;

import com.okoho.okoho.domain.CategoryJob;
import com.okoho.okoho.repository.CategoryJobRepository;
import com.okoho.okoho.repository.DomainActivityRepository;
import com.okoho.okoho.service.CategoryJobService;
import com.okoho.okoho.service.DomainActivityService;
import com.okoho.okoho.service.dto.DomainActivityDTO;
import com.okoho.okoho.utils.HeaderUtil;
import com.okoho.okoho.utils.PaginationUtil;
import com.okoho.okoho.utils.ResponseUtil;
import com.okoho.okoho.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


/**
 * REST controller for managing {@link com.okoho.okoho.domain.CategoryJob}.
 */
@RestController
@RequestMapping("/v1")
public class CategoryJobResource {

    private final Logger log = LoggerFactory.getLogger(CategoryJobResource.class);

    private static final String ENTITY_NAME = "categoryJob";

    @Value("${okoho.clientApp.name}")
    private String applicationName;

    private final CategoryJobService categoryJobService;

    private final CategoryJobRepository categoryJobRepository;

    public CategoryJobResource(CategoryJobService categoryJobService, CategoryJobRepository categoryJobRepository) {
        this.categoryJobService = categoryJobService;
        this.categoryJobRepository = categoryJobRepository;
    }

    /**
     * {@code POST  /category-jobs} : Create a new categoryJob.
     *
     * @param categoryJob the categoryJob to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new categoryJob, or with status {@code 400 (Bad Request)} if the domainActivity has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     * @throws BadRequestAlertException
     */
    @PostMapping("/category-jobs")
    public ResponseEntity<CategoryJob> createCategoryJob(@RequestBody CategoryJob categoryJob)
        throws URISyntaxException, BadRequestAlertException {
        log.debug("REST request to save CategoryJob : {}", categoryJob);
        if (categoryJob.getId() != null) {
            throw new BadRequestAlertException("A new categoryJob cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CategoryJob result = categoryJobService.save(categoryJob);
        return ResponseEntity
            .created(new URI("/api/category-jobs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /category-jobs/:id} : Updates an existing categoryJob.
     *
     * @param id the id of the categoryJob to save.
     * @param categoryJob the categoryJob to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated categoryJob,
     * or with status {@code 400 (Bad Request)} if the categoryJob is not valid,
     * or with status {@code 500 (Internal Server Error)} if the categoryJob couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     * @throws BadRequestAlertException
     */
    @PutMapping("/category-jobs/{id}")
    public ResponseEntity<CategoryJob> updateDomainActivity(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody CategoryJob categoryJob
    ) throws URISyntaxException, BadRequestAlertException {
        log.debug("REST request to update categoryJob : {}, {}", id, categoryJob);
        if (categoryJob.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, categoryJob.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!categoryJobRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        CategoryJob result = categoryJobService.save(categoryJob);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, categoryJob.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /category-jobs/:id} : Partial updates given fields of an existing categoryJob, field will ignore if it is null
     *
     * @param id the id of the categoryJob to save.
     * @param categoryJob the categoryJob to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated categoryJob,
     * or with status {@code 400 (Bad Request)} if the categoryJob is not valid,
     * or with status {@code 404 (Not Found)} if the categoryJob is not found,
     * or with status {@code 500 (Internal Server Error)} if the categoryJob couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     * @throws BadRequestAlertException
     */
    @PatchMapping(value = "/category-jobs/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<CategoryJob> partialUpdateDomainActivity(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody CategoryJob categoryJob
    ) throws URISyntaxException, BadRequestAlertException {
        log.debug("REST request to partial update DomainActivity partially : {}, {}", id, categoryJob);
        if (categoryJob.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, categoryJob.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!categoryJobRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<CategoryJob> result = categoryJobService.partialUpdate(categoryJob);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, categoryJob.getId())
        );
    }

    /**
     * {@code GET  /category-jobs} : get all the categoryJobs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of categoryJobs in body.
     */
    @GetMapping("/category-jobs")
    public ResponseEntity<List<CategoryJob>> getAllCategoryJob(Pageable pageable) {
        log.debug("REST request to get a page of DomainActivities");
        Page<CategoryJob> page = categoryJobService.findAll(pageable);
     //   HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().body(page.getContent());
    }

    /**
     * {@code GET  /category-jobs/:id} : get the "id" categoryJob.
     *
     * @param id the id of the categoryJob to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the categoryJob, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/category-jobs/{id}")
    public ResponseEntity<CategoryJob> getCategoryJob(@PathVariable String id) {
        log.debug("REST request to get CategoryJob : {}", id);
        Optional<CategoryJob> categoryJob = categoryJobService.findOne(id);
        return ResponseUtil.wrapOrNotFound(categoryJob);
    }

    /**
     * {@code DELETE  /category-jobs/:id} : delete the "id" categoryJob.
     *
     * @param id the id of the categoryJob to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/category-jobs/{id}")
    public ResponseEntity<Void> deleteCategoryJob(@PathVariable String id) {
        log.debug("REST request to delete CategoryJob : {}", id);
        categoryJobService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id)).build();
    }
}
