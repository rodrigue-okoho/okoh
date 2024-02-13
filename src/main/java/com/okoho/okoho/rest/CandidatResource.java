package com.okoho.okoho.rest;

import com.lowagie.text.DocumentException;
import com.okoho.okoho.domain.Address;
import com.okoho.okoho.domain.Candidat;
import com.okoho.okoho.domain.FileUrl;
import com.okoho.okoho.domain.ItemCandidat;
import com.okoho.okoho.repository.CandidatRepository;
import com.okoho.okoho.service.CandidatService;
import com.okoho.okoho.service.CvService;
import com.okoho.okoho.service.criteria.CandidatCriteria;
import com.okoho.okoho.service.dto.*;
import com.okoho.okoho.utils.HeaderUtil;
import com.okoho.okoho.utils.PaginationUtil;
import com.okoho.okoho.utils.ResponseUtil;
import com.okoho.okoho.rest.errors.BadRequestAlertException;

import java.io.IOException;
import java.net.MalformedURLException;
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
 * REST controller for managing {@link com.okoho.okoho.domain.Candidat}.
 */
@RestController
@RequestMapping("/v1")
@CrossOrigin
public class CandidatResource {

    private final Logger log = LoggerFactory.getLogger(CandidatResource.class);

    private static final String ENTITY_NAME = "okohoCandidat";

    @Value("${okoho.clientApp.name}")
    private String applicationName;

    private final CandidatService candidatService;
    private final CvService cvService;

    private final CandidatRepository candidatRepository;

    public CandidatResource(CandidatService candidatService, CvService cvService,
    CandidatRepository candidatRepository) {
        this.candidatService = candidatService;
        this.candidatRepository = candidatRepository;
        this.cvService=cvService;
    }

    /**
     * {@code POST  /candidats} : Create a new candidat.
     *
     * @param candidat the candidat to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new candidat, or with status {@code 400 (Bad Request)} if the candidat has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     * @throws BadRequestAlertException
     */
    @PostMapping("/candidats")
    public ResponseEntity<Candidat> createCandidat(@RequestBody CandidatDTO candidat) throws URISyntaxException {
      //  log.debug("REST request to save Candidat : {}", candidat);
        System.out.println("test candidats");
       /*  if (candidat.getId() != null) {
            throw new BadRequestAlertException("A new candidat cannot already have an ID", ENTITY_NAME, "idexists");
        } */
        Candidat result = candidatService.save(candidat);
        return ResponseEntity
            .created(new URI("/v1/candidats/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId()))
            .body(result);
    }
    @GetMapping("/candidats/search/live")
    public ResponseEntity<List<Candidat>> searchCandidats(Pageable pageable,
    @RequestParam String keyword,
    @RequestParam String location,
    @RequestParam String type,
    @RequestParam String category,
    @RequestParam String dateposted,
    @RequestParam String education,
    @RequestParam String experience
    ) {
        log.debug("REST request to get a page of Candidat");
        Page<Candidat> page = candidatService.findSearch(pageable,keyword,location,type,
        category,dateposted,education,experience);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page,ServletUriComponentsBuilder.fromCurrentRequest().toUriString());
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
    @PostMapping("/candidats/addcv")
    public ResponseEntity<FileUrl> addCv(@RequestBody FileUrlDTO fileUrlDTO) throws URISyntaxException {

        FileUrl result = candidatService.uploadCv(fileUrlDTO);
        return ResponseEntity
                .created(new URI("/v1/candidats/"))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, null))
                .body(result);
    }
    @PostMapping("/candidats/address")
    public ResponseEntity<AdressDTO> addAddress(@RequestBody AdressDTO address) throws URISyntaxException {

        AdressDTO result = candidatService.addAddress(address);
        return ResponseEntity
                .created(new URI("/v1/candidats/address"))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, null))
                .body(result);
    }
    @PostMapping("/candidats/languages")
    public ResponseEntity<LanguageDto> addLanguage(@RequestBody LanguageDto language) throws URISyntaxException {

        LanguageDto result = candidatService.saveLanguage(language);
        return ResponseEntity
                .created(new URI("/v1/candidats/languages"))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, null))
                .body(result);
    }
    @PostMapping("/candidats/branches")
    public ResponseEntity<BranchDto> addBranch(@RequestBody BranchDto branchDto) throws URISyntaxException {

        BranchDto result = candidatService.saveBranch(branchDto);
        return ResponseEntity
                .created(new URI("/v1/candidats/branches"))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, null))
                .body(result);
    }
  
    @PostMapping("/candidats/additem")
    public ResponseEntity<ItemCandidatDTO> addItem(@RequestBody ItemCandidatDTO itemCandidatDTO) throws URISyntaxException {

        ItemCandidatDTO result = candidatService.addItemCandidat(itemCandidatDTO);
        return ResponseEntity
                .created(new URI("/v1/candidats/"))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, null))
                .body(result);
    }
    /**
     * {@code PUT  /candidats/:id} : Updates an existing candidat.
     *
     * @param id the id of the candidat to save.
     * @param candidat the candidat to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated candidat,
     * or with status {@code 400 (Bad Request)} if the candidat is not valid,
     * or with status {@code 500 (Internal Server Error)} if the candidat couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     * @throws BadRequestAlertException
     */
    @PutMapping("/candidats/{id}")
    public ResponseEntity<Candidat> updateCandidat(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody Candidat candidat
    ) throws URISyntaxException, BadRequestAlertException {
        log.debug("REST request to update Candidat : {}, {}", id, candidat);
        if (candidat.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, candidat.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!candidatRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Candidat result = candidatService.partialUpdate(candidat).get();
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, candidat.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /candidats/:id} : Partial updates given fields of an existing candidat, field will ignore if it is null
     *
     * @param id the id of the candidat to save.
     * @param candidat the candidat to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated candidat,
     * or with status {@code 400 (Bad Request)} if the candidat is not valid,
     * or with status {@code 404 (Not Found)} if the candidat is not found,
     * or with status {@code 500 (Internal Server Error)} if the candidat couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     * @throws BadRequestAlertException
     */
    @PatchMapping(value = "/candidats/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<Candidat> partialUpdateCandidat(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody Candidat candidat
    ) throws URISyntaxException, BadRequestAlertException {
        log.debug("REST request to partial update Candidat partially : {}, {}", id, candidat);
        if (candidat.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, candidat.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!candidatRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Candidat> result = candidatService.partialUpdate(candidat);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, candidat.getId())
        );
    }

    /**
     * {@code GET  /candidats} : get all the candidats.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of candidats in body.
     */
    @GetMapping("/candidats")
    public ResponseEntity<List<Candidat>> getAllCandidats(Pageable pageable) {
        log.debug("REST request to get all Candidats");
        CandidatCriteria candidatCriteria=new CandidatCriteria();
        Page<Candidat> page = candidatService.findAllWithEagerRelationships(candidatCriteria,pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page,ServletUriComponentsBuilder.fromCurrentRequest().toUriString());
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
    @GetMapping("/candidats/search")
    public ResponseEntity<List<Candidat>> getAllCandidatsearch(@RequestParam String byexperience,Pageable pageable) {
        log.debug("REST request to get all Candidats");
        System.out.println(byexperience);
        CandidatCriteria candidatCriteria=new CandidatCriteria(byexperience);
        Page<Candidat> page = candidatService.findAllWithEagerRelationships(candidatCriteria,pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page,ServletUriComponentsBuilder.fromCurrentRequest().toUriString());
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
    /**
     * {@code GET  /candidats/:id} : get the "id" candidat.
     *
     * @param id the id of the candidat to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the candidat, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/candidats/{id}")
    public ResponseEntity<Candidat> getCandidat(@PathVariable String id) {
        log.debug("REST request to get Candidat : {}", id);
        Optional<Candidat> candidat = candidatService.findOne(id);
        return ResponseUtil.wrapOrNotFound(candidat);
    }
    @GetMapping("/candidats/profile/{id}")
    public ResponseEntity<Candidat> getCandidatProfile(@PathVariable String id) {
        log.debug("REST request to get Candidat : {}", id);
        Optional<Candidat> candidat = candidatService.findProfile(id);
        return ResponseUtil.wrapOrNotFound(candidat);
    }

    /**
     * {@code DELETE  /candidats/:id} : delete the "id" candidat.
     *
     * @param id the id of the candidat to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/candidats/{id}")
    public ResponseEntity<Void> deleteCandidat(@PathVariable String id) {
        log.debug("REST request to delete Candidat : {}", id);
        candidatService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id)).build();
    }
    @DeleteMapping("/candidats/removecv/{id}")
    public ResponseEntity<Void> deleteCV(@PathVariable String id) {
        candidatService.removeCv(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id)).build();
    }
    @DeleteMapping("/candidats/removeitem/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable String id) {
        candidatService.removeItemCandidat(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id)).build();
    }
    @DeleteMapping("/candidats/removeaddress/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable String id) {
        candidatService.removeAddress(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id)).build();
    }
    @DeleteMapping("/candidats/remove-language/{id}")
    public ResponseEntity<Void> deleteLanguage(@PathVariable String id) {
        candidatService.removeLanguage(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id)).build();
    }
    @DeleteMapping("/candidats/remove-branch/{id}")
    public ResponseEntity<Void> deleteBranch(@PathVariable String id) {
        candidatService.removeBranch(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id)).build();
    }
    @GetMapping("/candidats/cv_S/{id}")
    public ResponseEntity<List<FileUrl>> getCvForCandidat(@PathVariable String id) {
        log.debug("REST request to get all Candidats");
        List<FileUrl> page = candidatService.findCvs(id);
       
        return ResponseEntity.ok().body(page);
    }
    @GetMapping("/candidats/cvs/{id}")
    public ResponseEntity<FileUrl> getOneCvForCandidat(@PathVariable String id) throws DocumentException, MalformedURLException, IOException {
        log.debug("REST request to get all Candidats");
        FileUrl page = cvService.generateCV(id);
      
        return ResponseUtil.wrapOrNotFound( Optional.of(page));
    }
}
