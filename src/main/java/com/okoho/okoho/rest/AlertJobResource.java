package com.okoho.okoho.rest;

import com.okoho.okoho.domain.AlertCandidat;
import com.okoho.okoho.domain.AlertJob;
import com.okoho.okoho.domain.ApplicantJob;
import com.okoho.okoho.rest.errors.BadRequestAlertException;
import com.okoho.okoho.service.AlertService;
import com.okoho.okoho.service.dto.AlertCandidatDTO;
import com.okoho.okoho.service.dto.AlertJobDTO;
import com.okoho.okoho.service.dto.ApplicantJobDTO;
import com.okoho.okoho.utils.HeaderUtil;
import com.okoho.okoho.utils.PaginationUtil;
import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping("/v1")
@CrossOrigin
public class AlertJobResource {
    @Autowired
    private AlertService alertService;
    @Value("${okoho.clientApp.name}")
    private String applicationName;
    private static final String ENTITY_NAME = "okohoCompetence";
    /**
     * {@code POST  /alerts/save_candidat} : Create a new applicantJob.
     *
     * @param alertCandidatDTO the competence to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new competence, or with status {@code 400 (Bad Request)} if the competence has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/alerts/save_candidat")
    public ResponseEntity<AlertCandidatDTO> createAlertCandidat(@RequestBody AlertCandidatDTO alertCandidatDTO) throws URISyntaxException, BadRequestAlertException {

        AlertCandidatDTO result = alertService.save_alert_candidat(alertCandidatDTO);
        return ResponseEntity
                .created(new URI("/v1/alerts/save_candidat/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId()))
                .body(result);
    }
    @PostMapping("/alerts/save_job")
    public ResponseEntity<AlertJobDTO> createAlertJobs(@RequestBody AlertJobDTO alertJobDTO) throws URISyntaxException, BadRequestAlertException {

        AlertJobDTO result = alertService.save_alert_job(alertJobDTO);
        return ResponseEntity
                .created(new URI("/v1/alerts/save_job/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId()))
                .body(result);
    }
    @GetMapping("/alerts/candidat/{candidat}")
    public ResponseEntity<List<AlertJob>> getAllAlertCandidat(@PathVariable String candidat, Pageable pageable) {
        Page<AlertJob> page = alertService.findByCandidatWithEagerRelationships(pageable,candidat);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, ServletUriComponentsBuilder.fromCurrentRequest().toUriString());
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
    @GetMapping("/alerts/employer/{employer}")
    public ResponseEntity<List<AlertCandidat>> getAllAlertEntreprise(@PathVariable String employer, Pageable pageable) {
        Page<AlertCandidat> page = alertService.findByEntrepriseWithEagerRelationships(pageable,employer);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, ServletUriComponentsBuilder.fromCurrentRequest().toUriString());
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
}
