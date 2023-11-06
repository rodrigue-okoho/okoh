package com.okoho.okoho.rest;

import com.okoho.okoho.rest.errors.BadRequestAlertException;
import com.okoho.okoho.repository.UserAccountRepository;
import com.okoho.okoho.service.UserAccountService;
import com.okoho.okoho.service.dto.RegisterRequest;
import com.okoho.okoho.service.dto.UserAccountDTO;
import com.okoho.okoho.service.dto.UserDTO;
import com.okoho.okoho.utils.HeaderUtil;
import com.okoho.okoho.utils.ResponseUtil;

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
 * REST controller for managing {@link com.okoho.okoho.domain.UserAccount}.
 */
@RestController
@RequestMapping("/v1")
public class UserAccountResource {

    private final Logger log = LoggerFactory.getLogger(UserAccountResource.class);

    private static final String ENTITY_NAME = "okohoUserAccount";

    @Value("${okoho.clientApp.name}")
    private String applicationName;

    private final UserAccountService userAccountService;

    private final UserAccountRepository userAccountRepository;

    public UserAccountResource(UserAccountService userAccountService, UserAccountRepository userAccountRepository) {
        this.userAccountService = userAccountService;
        this.userAccountRepository = userAccountRepository;
    }

    /**
     * {@code POST  /user-accounts} : Create a new userAccount.
     *
     * @param userAccountDTO the userAccountDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new userAccountDTO, or with status {@code 400 (Bad Request)} if the userAccount has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     * @throws BadRequestAlertException
     */
    @PostMapping("/user-accounts")
    public ResponseEntity<UserAccountDTO> createUserAccount(@RequestBody RegisterRequest userAccountDTO) throws URISyntaxException, BadRequestAlertException {
        log.debug("REST request to save UserAccount : {}", userAccountDTO);
    
        UserAccountDTO result = userAccountService.save(userAccountDTO);
        return ResponseEntity
            .created(new URI("/api/user-accounts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /user-accounts/:id} : Updates an existing userAccount.
     *
     * @param id the id of the userAccountDTO to save.
     * @param userAccountDTO the userAccountDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated userAccountDTO,
     * or with status {@code 400 (Bad Request)} if the userAccountDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the userAccountDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     * @throws BadRequestAlertException
     */
    @PutMapping("/user-accounts/{id}")
    public ResponseEntity<UserAccountDTO> updateUserAccount(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody UserAccountDTO userAccountDTO
    ) throws URISyntaxException, BadRequestAlertException {
        log.debug("REST request to update UserAccount : {}, {}", id, userAccountDTO);
        if (userAccountDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, userAccountDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        // if (!userAccountRepository.existsById(id)) {
        //     throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        // }

        UserAccountDTO result = userAccountService.partialUpdate(userAccountDTO).get();
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, userAccountDTO.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /user-accounts/:id} : Partial updates given fields of an existing userAccount, field will ignore if it is null
     *
     * @param id the id of the userAccountDTO to save.
     * @param userAccountDTO the userAccountDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated userAccountDTO,
     * or with status {@code 400 (Bad Request)} if the userAccountDTO is not valid,
     * or with status {@code 404 (Not Found)} if the userAccountDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the userAccountDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     * @throws BadRequestAlertException
     */
    @PatchMapping(value = "/useraccounts/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<UserAccountDTO> partialUpdateUserAccount(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody UserAccountDTO userAccountDTO
    ) throws URISyntaxException, BadRequestAlertException {
        log.debug("REST request to partial update UserAccount partially : {}, {}", id, userAccountDTO);
        if (userAccountDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, userAccountDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

       /*  if (!userAccountRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        } */

        Optional<UserAccountDTO> result = userAccountService.partialUpdate(userAccountDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, userAccountDTO.getId())
        );
    }

    /**
     * {@code GET  /useraccounts} : get all the userAccounts.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of userAccounts in body.
     */
    @GetMapping("/user-accounts")
    public UserDTO getUserAccounts() {
        log.debug("REST request to get all UserAccounts");
        return userAccountService
        .getUserWithAuthorities()
        .map(UserDTO::new)
        .orElseThrow();
};

    /**
     * {@code GET  /user-accounts/:id} : get the "id" userAccount.
     *
     * @param id the id of the userAccountDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the userAccountDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/user-accounts/{id}")
    public ResponseEntity<UserAccountDTO> getUserAccount(@PathVariable String id) {
        log.debug("REST request to get UserAccount : {}", id);
        Optional<UserAccountDTO> userAccountDTO = userAccountService.findOne(id);
        return ResponseUtil.wrapOrNotFound(userAccountDTO);
    }

    /**
     * {@code DELETE  /user-accounts/:id} : delete the "id" userAccount.
     *
     * @param id the id of the userAccountDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/user-accounts/{id}")
    public ResponseEntity<Void> deleteUserAccount(@PathVariable String id) {
        log.debug("REST request to delete UserAccount : {}", id);
        userAccountService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id)).build();
    }
}
