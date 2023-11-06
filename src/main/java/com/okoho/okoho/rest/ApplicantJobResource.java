package com.okoho.okoho.rest;

import com.okoho.okoho.domain.ApplicantJob;
import com.okoho.okoho.domain.CategoryJob;
import com.okoho.okoho.domain.Competence;
import com.okoho.okoho.rest.errors.BadRequestAlertException;
import com.okoho.okoho.service.ApplicantJobService;
import com.okoho.okoho.service.dto.ApplicantJobDTO;
import com.okoho.okoho.utils.HeaderUtil;
import com.okoho.okoho.utils.PaginationUtil;
import com.okoho.okoho.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/v1")
@CrossOrigin
public class ApplicantJobResource {
    private final ApplicantJobService applicantJobService;
    @Value("${okoho.clientApp.name}")
    private String applicationName;
    private static final String ENTITY_NAME = "okohoCompetence";
    public ApplicantJobResource(ApplicantJobService applicantJobService) {
        this.applicantJobService = applicantJobService;
    }

    /**
     * {@code POST  /applicant-jobs} : Create a new applicantJob.
     *
     * @param applicantJobDTO the competence to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new competence, or with status {@code 400 (Bad Request)} if the competence has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/applicant-jobs")
    public ResponseEntity<ApplicantJob> createApplicant(@RequestBody ApplicantJobDTO applicantJobDTO) throws URISyntaxException, BadRequestAlertException {

        ApplicantJob result = applicantJobService.save(applicantJobDTO);
        return ResponseEntity
                .created(new URI("/v1/applicant-jobs/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId()))
                .body(result);
    }

    /**
     * {@code PUT  /applicant-jobs/:id} : Updates an existing competence.
     *
     * @param id the id of the competence to save.
     * @param competence the competence to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated competence,
     * or with status {@code 400 (Bad Request)} if the competence is not valid,
     * or with status {@code 500 (Internal Server Error)} if the competence couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     * @throws BadRequestAlertException
     */
    @PutMapping("/applicant-jobs/{id}")
    public ResponseEntity<ApplicantJob> updateCompetence(
            @PathVariable(value = "id", required = false) final String id,
            @RequestBody ApplicantJobDTO competence
    ) throws URISyntaxException, BadRequestAlertException {



        ApplicantJob result = applicantJobService.save(competence);
        return ResponseEntity
                .ok()
                .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, null))
                .body(result);
    }

    /**
     * {@code PATCH  /applicant-jobs/:id} : Partial updates given fields of an existing competence, field will ignore if it is null
     *
     * @param id the id of the competence to save.
     * @param applicantJobDTO the competence to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated competence,
     * or with status {@code 400 (Bad Request)} if the competence is not valid,
     * or with status {@code 404 (Not Found)} if the competence is not found,
     * or with status {@code 500 (Internal Server Error)} if the competence couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     * @throws BadRequestAlertException
     */
    @PatchMapping(value = "/applicant-jobs/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<ApplicantJob> partialUpdateCompetence(
            @PathVariable(value = "id", required = false) final String id,
            @RequestBody ApplicantJobDTO applicantJobDTO
    ) throws URISyntaxException, BadRequestAlertException {



        Optional<ApplicantJob> result = applicantJobService.partialUpdate(applicantJobDTO);

        return ResponseUtil.wrapOrNotFound(
                result,
                HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, null)
        );
    }

    /**
     * {@code GET  /applicant-jobs} : get all the competences.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of competences in body.
     */
    @GetMapping("/applicant-jobs")
    public ResponseEntity<List<ApplicantJob>> getAllApplicantJob(Pageable pageable) {
        Page<ApplicantJob> page = applicantJobService.findAllWithEagerRelationships(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page,ServletUriComponentsBuilder.fromCurrentRequest().toUriString());
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
    @GetMapping("/applicant-jobs/candidat/{candidat}")
    public ResponseEntity<List<ApplicantJob>> getAllApplicantJobCandidat(@PathVariable String candidat, Pageable pageable) {
        Page<ApplicantJob> page = applicantJobService.findAllByCandidat(pageable,candidat);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page,ServletUriComponentsBuilder.fromCurrentRequest().toUriString());
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
    @GetMapping("/applicant-jobs/entreprise/{job_id}")
    public ResponseEntity<List<ApplicantJob>> getAllApplicantJobEntreprise(@PathVariable String job_id, Pageable pageable) {
        Page<ApplicantJob> page = applicantJobService.findAllByEntreprise(pageable,job_id);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page,ServletUriComponentsBuilder.fromCurrentRequest().toUriString());
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /applicant-jobs/:id} : get the "id" competence.
     *
     * @param id the id of the competence to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the competence, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/applicant-jobs/{id}")
    public ResponseEntity<ApplicantJob> getCompetence(@PathVariable String id) {
        Optional<ApplicantJob> competence = applicantJobService.findOne(id);
        return ResponseUtil.wrapOrNotFound(competence);
    }

    /**
     * {@code DELETE  /applicant-jobs/:id} : delete the "id" competence.
     *
     * @param id the id of the competence to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/applicant-jobs/{id}")
    public ResponseEntity<Void> deleteCompetence(@PathVariable String id) {
        applicantJobService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id)).build();
    }
}
