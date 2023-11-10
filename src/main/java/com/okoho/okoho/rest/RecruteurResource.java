package com.okoho.okoho.rest;

import com.okoho.okoho.domain.Candidat;
import com.okoho.okoho.domain.Recruteur;
import com.okoho.okoho.repository.RecruteurRepository;
import com.okoho.okoho.service.RecruteurService;
import com.okoho.okoho.service.dto.RecruteurDDTO;
import com.okoho.okoho.service.dto.RecruteurDTO;
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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * REST controller for managing {@link com.okoho.okoho.domain.Recruteur}.
 */
@RestController
@RequestMapping("/v1")
public class RecruteurResource {

    private final Logger log = LoggerFactory.getLogger(RecruteurResource.class);

    private static final String ENTITY_NAME = "okohoRecruteur";

    @Value("${okoho.clientApp.name}")
    private String applicationName;

    private final RecruteurService recruteurService;

    private final RecruteurRepository recruteurRepository;

    public RecruteurResource(RecruteurService recruteurService, RecruteurRepository recruteurRepository) {
        this.recruteurService = recruteurService;
        this.recruteurRepository = recruteurRepository;
    }

    /**
     * {@code POST  /recruteurs} : Create a new recruteur.
     *
     * @param recruteurDTO the recruteurDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new recruteurDTO, or with status {@code 400 (Bad Request)} if the recruteur has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     * @throws BadRequestAlertException
     */
    @PostMapping("/recruteurs")
    public ResponseEntity<Recruteur> createRecruteur(@RequestBody RecruteurDTO recruteurDTO) throws URISyntaxException, BadRequestAlertException {
        log.debug("REST request to save Recruteur : {}", recruteurDTO);
        Recruteur result = recruteurService.save(recruteurDTO);
        return ResponseEntity
            .created(new URI("/api/recruteurs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /recruteurs/:id} : Updates an existing recruteur.
     *
     * @param id the id of the recruteurDTO to save.
     * @param recruteurDTO the recruteurDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated recruteurDTO,
     * or with status {@code 400 (Bad Request)} if the recruteurDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the recruteurDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     * @throws BadRequestAlertException
     */
    @PutMapping("/recruteurs/{id}")
    public ResponseEntity<Recruteur> updateRecruteur(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody RecruteurDTO recruteurDTO
    ) throws URISyntaxException, BadRequestAlertException {
        log.debug("REST request to update Recruteur : {}, {}", id, recruteurDTO);
        if (recruteurDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, recruteurDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!recruteurRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Recruteur result = recruteurService.save(recruteurDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, recruteurDTO.getId()))
            .body(result);
    }
    @GetMapping("/recruteurs/profile/{id}")
    public ResponseEntity<Recruteur> getCandidatProfile(@PathVariable String id) {
        log.debug("REST request to get Candidat : {}", id);
        Optional<Recruteur> recruteur = recruteurService.findProfile(id);
        return ResponseUtil.wrapOrNotFound(recruteur);
    }
    /**
     * {@code PATCH  /recruteurs/:id} : Partial updates given fields of an existing recruteur, field will ignore if it is null
     *
     * @param id the id of the recruteurDTO to save.
     * @param recruteurDTO the recruteurDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated recruteurDTO,
     * or with status {@code 400 (Bad Request)} if the recruteurDTO is not valid,
     * or with status {@code 404 (Not Found)} if the recruteurDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the recruteurDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     * @throws BadRequestAlertException
     */
    @PatchMapping(value = "/recruteurs/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<RecruteurDDTO> partialUpdateRecruteur(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody RecruteurDDTO recruteurDTO
    ) throws URISyntaxException, BadRequestAlertException {
        log.debug("REST request to partial update Recruteur partially : {}, {}", id, recruteurDTO);
        if (recruteurDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, recruteurDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!recruteurRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<RecruteurDDTO> result = recruteurService.partialUpdate(recruteurDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, recruteurDTO.getId())
        );
    }

    /**
     * {@code GET  /recruteurs} : get all the recruteurs.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of recruteurs in body.
     */
    @GetMapping("/recruteurs")
    public ResponseEntity<List<Recruteur>> getAllRecruteurs(Pageable pageable) {
        log.debug("REST request to get all Recruteurs");
        //return recruteurService.findAll();
        Page<Recruteur> page = recruteurService.findAllWithEagerRelationships(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page,ServletUriComponentsBuilder.fromCurrentRequest().toUriString());
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
    @GetMapping("/recruteurs/search/live")
    public ResponseEntity<List<Recruteur>> searchRecruteurs(Pageable pageable,
    @RequestParam String query,
    @RequestParam String city,
    @RequestParam String category,
    @RequestParam String founded,
    @RequestParam String compagny_size
    ) {
        log.debug("REST request to get a page of Recruteur");
        Page<Recruteur> page = recruteurService.findSearch(pageable,query,city,
        category,founded,compagny_size);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page,ServletUriComponentsBuilder.fromCurrentRequest().toUriString());
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
    @GetMapping("/recruteurs/tops")
    public ResponseEntity<List<RecruteurDDTO>> getTopRecruteurs() {
        log.debug("REST request to get all Recruteurs");
        //return recruteurService.findAll();
        List<RecruteurDDTO> page = recruteurService.findAll();
       return ResponseEntity.ok().body(page);
    }

    /**
     * {@code GET  /recruteurs/:id} : get the "id" recruteur.
     *
     * @param id the id of the recruteurDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the recruteurDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/recruteurs/{id}")
    public ResponseEntity<RecruteurDDTO> getRecruteur(@PathVariable String id) {
        log.debug("REST request to get Recruteur : {}", id);
        Optional<RecruteurDDTO> recruteurDTO = recruteurService.findOne(id);
        return ResponseUtil.wrapOrNotFound(recruteurDTO);
    }

    /**
     * {@code DELETE  /recruteurs/:id} : delete the "id" recruteur.
     *
     * @param id the id of the recruteurDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/recruteurs/{id}")
    public ResponseEntity<Void> deleteRecruteur(@PathVariable String id) {
        log.debug("REST request to delete Recruteur : {}", id);
        recruteurService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id)).build();
    }
}
