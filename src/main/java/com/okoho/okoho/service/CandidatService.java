package com.okoho.okoho.service;

import com.okoho.okoho.domain.Address;
import com.okoho.okoho.domain.Candidat;
import com.okoho.okoho.domain.FileUrl;
import com.okoho.okoho.domain.ItemCandidat;
import com.okoho.okoho.service.criteria.CandidatCriteria;
import com.okoho.okoho.service.dto.CandidatDTO;
import com.okoho.okoho.service.dto.FileUrlDTO;
import com.okoho.okoho.service.dto.ItemCandidatDTO;
import com.okoho.okoho.service.dto.RegisterRequest;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link Candidat}.
 */
public interface CandidatService {
    /**
     * Save a candidat.
     *
     * @param candidat the entity to save.
     * @return the persisted entity.
     */
    Candidat save(CandidatDTO registerRequest);
    FileUrl uploadCv(FileUrlDTO fileUrlDTO);
    void removeCv(String idFile);
    ItemCandidat addItemCandidat(ItemCandidatDTO itemCandidatDTO);
    Address addAddress(Address address);
    void removeItemCandidat(String idItem);
    void removeAddress(String idItem);
    /**
     * Partially updates a candidat.
     *
     * @param candidat the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Candidat> partialUpdate(Candidat candidat);

    /**
     * Get all the candidats.
     *
     * @return the list of entities.
     */
    List<Candidat> findAll();
    List<FileUrl> findCvs(String idcandidat);

    /**
     * Get all the candidats with eager load of many-to-many relationships.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Candidat> findAllWithEagerRelationships(CandidatCriteria candidatCriteria,Pageable pageable);
    Page<Candidat> findSearch(Pageable pageable,String keyword,String location,
    String category,String dateposted,String education,String experience);

    /**
     * Get the "id" candidat.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Candidat> findOne(String id);
    Optional<Candidat> findProfile(String id);

    /**
     * Delete the "id" candidat.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
