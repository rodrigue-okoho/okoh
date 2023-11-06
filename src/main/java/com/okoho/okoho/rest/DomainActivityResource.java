package com.okoho.okoho.rest;

import com.okoho.okoho.repository.DomainActivityRepository;
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
 * REST controller for managing {@link com.okoho.okoho.domain.DomainActivity}.
 */
@RestController
@RequestMapping("/v1")
public class DomainActivityResource {

    private final Logger log = LoggerFactory.getLogger(DomainActivityResource.class);

    private static final String ENTITY_NAME = "okohoDomainActivity";

    @Value("${okoho.clientApp.name}")
    private String applicationName;

    private final DomainActivityService domainActivityService;

    private final DomainActivityRepository domainActivityRepository;

    public DomainActivityResource(DomainActivityService domainActivityService, DomainActivityRepository domainActivityRepository) {
        this.domainActivityService = domainActivityService;
        this.domainActivityRepository = domainActivityRepository;
    }

    /**
     * {@code POST  /domain-activities} : Create a new domainActivity.
     *
     * @param domainActivityDTO the domainActivityDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new domainActivityDTO, or with status {@code 400 (Bad Request)} if the domainActivity has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     * @throws BadRequestAlertException
     */
    @PostMapping("/domain-activities")
    public ResponseEntity<DomainActivityDTO> createDomainActivity(@RequestBody DomainActivityDTO domainActivityDTO)
        throws URISyntaxException, BadRequestAlertException {
        log.debug("REST request to save DomainActivity : {}", domainActivityDTO);
        if (domainActivityDTO.getId() != null) {
            throw new BadRequestAlertException("A new domainActivity cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DomainActivityDTO result = domainActivityService.save(domainActivityDTO);
        return ResponseEntity
            .created(new URI("/api/domain-activities/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /domain-activities/:id} : Updates an existing domainActivity.
     *
     * @param id the id of the domainActivityDTO to save.
     * @param domainActivityDTO the domainActivityDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated domainActivityDTO,
     * or with status {@code 400 (Bad Request)} if the domainActivityDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the domainActivityDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     * @throws BadRequestAlertException
     */
    @PutMapping("/domain-activities/{id}")
    public ResponseEntity<DomainActivityDTO> updateDomainActivity(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody DomainActivityDTO domainActivityDTO
    ) throws URISyntaxException, BadRequestAlertException {
        log.debug("REST request to update DomainActivity : {}, {}", id, domainActivityDTO);
        if (domainActivityDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, domainActivityDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!domainActivityRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        DomainActivityDTO result = domainActivityService.save(domainActivityDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, domainActivityDTO.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /domain-activities/:id} : Partial updates given fields of an existing domainActivity, field will ignore if it is null
     *
     * @param id the id of the domainActivityDTO to save.
     * @param domainActivityDTO the domainActivityDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated domainActivityDTO,
     * or with status {@code 400 (Bad Request)} if the domainActivityDTO is not valid,
     * or with status {@code 404 (Not Found)} if the domainActivityDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the domainActivityDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     * @throws BadRequestAlertException
     */
    @PatchMapping(value = "/domain-activities/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<DomainActivityDTO> partialUpdateDomainActivity(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody DomainActivityDTO domainActivityDTO
    ) throws URISyntaxException, BadRequestAlertException {
        log.debug("REST request to partial update DomainActivity partially : {}, {}", id, domainActivityDTO);
        if (domainActivityDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, domainActivityDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!domainActivityRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DomainActivityDTO> result = domainActivityService.partialUpdate(domainActivityDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, domainActivityDTO.getId())
        );
    }

    /**
     * {@code GET  /domain-activities} : get all the domainActivities.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of domainActivities in body.
     */
    @GetMapping("/domain-activities")
    public ResponseEntity<List<DomainActivityDTO>> getAllDomainActivities(Pageable pageable) {
        log.debug("REST request to get a page of DomainActivities");
        Page<DomainActivityDTO> page = domainActivityService.findAll(pageable);
     //   HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().body(page.getContent());
    }

    /**
     * {@code GET  /domain-activities/:id} : get the "id" domainActivity.
     *
     * @param id the id of the domainActivityDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the domainActivityDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/domain-activities/{id}")
    public ResponseEntity<DomainActivityDTO> getDomainActivity(@PathVariable String id) {
        log.debug("REST request to get DomainActivity : {}", id);
        Optional<DomainActivityDTO> domainActivityDTO = domainActivityService.findOne(id);
        return ResponseUtil.wrapOrNotFound(domainActivityDTO);
    }

    /**
     * {@code DELETE  /domain-activities/:id} : delete the "id" domainActivity.
     *
     * @param id the id of the domainActivityDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/domain-activities/{id}")
    public ResponseEntity<Void> deleteDomainActivity(@PathVariable String id) {
        log.debug("REST request to delete DomainActivity : {}", id);
        domainActivityService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id)).build();
    }
}
