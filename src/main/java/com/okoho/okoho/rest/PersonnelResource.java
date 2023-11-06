package com.okoho.okoho.rest;

import com.okoho.okoho.repository.PersonnelRepository;
import com.okoho.okoho.service.PersonnelService;
import com.okoho.okoho.service.dto.PersonnelDTO;
import com.okoho.okoho.utils.HeaderUtil;
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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing {@link com.okoho.okoho.domain.Personnel}.
 */
@RestController
@RequestMapping("/v1")
public class PersonnelResource {

    private final Logger log = LoggerFactory.getLogger(PersonnelResource.class);

    private static final String ENTITY_NAME = "okohoPersonnel";

    @Value("${okoho.clientApp.name}")
    private String applicationName;

    private final PersonnelService personnelService;

    private final PersonnelRepository personnelRepository;

    public PersonnelResource(PersonnelService personnelService, PersonnelRepository personnelRepository) {
        this.personnelService = personnelService;
        this.personnelRepository = personnelRepository;
    }

    /**
     * {@code POST  /personnel} : Create a new personnel.
     *
     * @param personnelDTO the personnelDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new personnelDTO, or with status {@code 400 (Bad Request)} if the personnel has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     * @throws BadRequestAlertException
     */
    @PostMapping("/personnel")
    public ResponseEntity<PersonnelDTO> createPersonnel(@RequestBody PersonnelDTO personnelDTO) throws URISyntaxException, BadRequestAlertException {
        log.debug("REST request to save Personnel : {}", personnelDTO);
        if (personnelDTO.getId() != null) {
            throw new BadRequestAlertException("A new personnel cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PersonnelDTO result = personnelService.save(personnelDTO);
        return ResponseEntity
            .created(new URI("/api/personnel/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /personnel/:id} : Updates an existing personnel.
     *
     * @param id the id of the personnelDTO to save.
     * @param personnelDTO the personnelDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated personnelDTO,
     * or with status {@code 400 (Bad Request)} if the personnelDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the personnelDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     * @throws BadRequestAlertException
     */
    @PutMapping("/personnel/{id}")
    public ResponseEntity<PersonnelDTO> updatePersonnel(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody PersonnelDTO personnelDTO
    ) throws URISyntaxException, BadRequestAlertException {
        log.debug("REST request to update Personnel : {}, {}", id, personnelDTO);
        if (personnelDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, personnelDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!personnelRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        PersonnelDTO result = personnelService.save(personnelDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, personnelDTO.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /personnel/:id} : Partial updates given fields of an existing personnel, field will ignore if it is null
     *
     * @param id the id of the personnelDTO to save.
     * @param personnelDTO the personnelDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated personnelDTO,
     * or with status {@code 400 (Bad Request)} if the personnelDTO is not valid,
     * or with status {@code 404 (Not Found)} if the personnelDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the personnelDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     * @throws BadRequestAlertException
     */
    @PatchMapping(value = "/personnel/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<PersonnelDTO> partialUpdatePersonnel(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody PersonnelDTO personnelDTO
    ) throws URISyntaxException, BadRequestAlertException {
        log.debug("REST request to partial update Personnel partially : {}, {}", id, personnelDTO);
        if (personnelDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, personnelDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!personnelRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<PersonnelDTO> result = personnelService.partialUpdate(personnelDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, personnelDTO.getId())
        );
    }

    /**
     * {@code GET  /personnel} : get all the personnel.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of personnel in body.
     */
    @GetMapping("/personnel")
    public List<PersonnelDTO> getAllPersonnel() {
        log.debug("REST request to get all Personnel");
        return personnelService.findAll();
    }

    /**
     * {@code GET  /personnel/:id} : get the "id" personnel.
     *
     * @param id the id of the personnelDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the personnelDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/personnel/{id}")
    public ResponseEntity<PersonnelDTO> getPersonnel(@PathVariable String id) {
        log.debug("REST request to get Personnel : {}", id);
        Optional<PersonnelDTO> personnelDTO = personnelService.findOne(id);
        return ResponseUtil.wrapOrNotFound(personnelDTO);
    }

    /**
     * {@code DELETE  /personnel/:id} : delete the "id" personnel.
     *
     * @param id the id of the personnelDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/personnel/{id}")
    public ResponseEntity<Void> deletePersonnel(@PathVariable String id) {
        log.debug("REST request to delete Personnel : {}", id);
        personnelService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id)).build();
    }
}
