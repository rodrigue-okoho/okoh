package com.okoho.okoho.service;

import com.okoho.okoho.domain.Address;
import com.okoho.okoho.domain.Candidat;
import com.okoho.okoho.domain.FileUrl;
import com.okoho.okoho.domain.ItemCandidat;
import com.okoho.okoho.service.criteria.CandidatCriteria;
import com.okoho.okoho.service.dto.*;

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
     * @param CandidatDTO the entity to save.
     * @return the persisted entity.
     */
    Candidat save(CandidatDTO registerRequest);
    FileUrl uploadCv(FileUrlDTO fileUrlDTO);
    void removeCv(String idFile);
    ItemCandidatDTO addItemCandidat(ItemCandidatDTO itemCandidatDTO);
    AdressDTO addAddress(AdressDTO address);
    void removeItemCandidat(String idItem);
    void removeAddress(String idItem);
    void removeLanguage(String langId);

    LanguageDto saveLanguage(LanguageDto languageDto);
    BranchDto saveBranch(BranchDto branchDto);
    void removeBranch(String langId);
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
    FileUrl findCv(String idcandidat,int typecv);

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
