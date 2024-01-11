package com.okoho.okoho.service.impl;

import com.okoho.okoho.domain.CategoryJob;
import com.okoho.okoho.domain.OfferJob;
import com.okoho.okoho.repository.CandidatRepository;
import com.okoho.okoho.repository.CategoryJobRepository;
import com.okoho.okoho.repository.OfferJobRepository;
import com.okoho.okoho.repository.RecruteurRepository;
import com.okoho.okoho.service.OfferJobService;
import com.okoho.okoho.service.dto.OfferJobDTO;
import com.okoho.okoho.service.mapper.OfferJobMapper;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Service Implementation for managing {@link OfferJob}.
 */
@Service
public class OfferJobServiceImpl implements OfferJobService {

    private final Logger log = LoggerFactory.getLogger(OfferJobServiceImpl.class);

    private final OfferJobRepository offerJobRepository;
    private final RecruteurRepository recruteurRepository;
    private final CategoryJobRepository categoryJobRepository;
    private final CandidatRepository candidatRepository;

    private final OfferJobMapper offerJobMapper;

    public OfferJobServiceImpl(OfferJobRepository offerJobRepository, OfferJobMapper offerJobMapper
    , CategoryJobRepository categoryJobRepository,
                               RecruteurRepository recruteurRepository, CandidatRepository candidatRepository) {
        this.offerJobRepository = offerJobRepository;
        this.offerJobMapper = offerJobMapper;
        this.recruteurRepository=recruteurRepository;
        this.categoryJobRepository = categoryJobRepository;
        this.candidatRepository = candidatRepository;
    }

    @Override
    public OfferJob save(OfferJobDTO offerJobDTO) {
        log.debug("Request to save OfferJob : {}", offerJobDTO);
        OfferJob offerJob = offerJobMapper.toEntity(offerJobDTO);
   
         for (String s : offerJobDTO.getCategoryjobs()) {
               offerJob.addCategoryJob(categoryJobRepository.findById(s).get());
        }
         if(!StringUtils.hasLength(offerJobDTO.getId())) {
             offerJob.setExpiredAt(LocalDate.parse(offerJobDTO.getExpiredAt()));
             offerJob.setCreatedAt(LocalDate.now());
             offerJob.setRecruteur(recruteurRepository.findById(offerJobDTO.getId_recruteur()).get());
         }
        offerJob = offerJobRepository.save(offerJob);
        return offerJob;
    }

    @Override
    public Optional<OfferJobDTO> partialUpdate(OfferJobDTO offerJobDTO) {
        log.debug("Request to partially update OfferJob : {}", offerJobDTO);

        return offerJobRepository
            .findById(offerJobDTO.getId())
            .map(
                existingOfferJob -> {
                    offerJobMapper.partialUpdate(existingOfferJob, offerJobDTO);

                    return existingOfferJob;
                }
            )
            .map(offerJobRepository::save)
            .map(offerJobMapper::toDto);
    }

    @Override
    public Page<OfferJob> findAll(Pageable pageable) {
        log.debug("Request to get all OfferJobs");
        return offerJobRepository.findAll(pageable);
    }

    @Override
    public Page<OfferJob> findLastJobs(Pageable pageable) {
        var list=offerJobRepository.findAll(pageable).stream().sorted(Comparator.comparing(OfferJob::getCreatedAt)).collect(Collectors.toList());

        return new PageImpl<>(list,pageable,list.size());
    }

    @Override
    public Optional<OfferJob> findOne(String id) {
        log.debug("Request to get OfferJob : {}", id);
        var fer=offerJobRepository.findOneWithEagerRelationships(id);
        System.out.println(fer.get());
        return fer;
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete OfferJob : {}", id);
        offerJobRepository.deleteById(id);
    }

    @Override
    public Page<OfferJob> findByEntreprise(Pageable pageable, String entreprise) {
        return offerJobRepository.findByRecruteur(pageable,recruteurRepository.findById(entreprise).get());
    }
    @Override
    public List<OfferJob> findByEntreprise(String entreprise) {
        return offerJobRepository.findAll().stream().filter(e->e.getRecruteur().getId().equals(entreprise)).collect(Collectors.toList());
    }
    @Override
    public Page<OfferJob> findAlertJobByCandidat(Pageable pageable, String candidatID) {
        var candidat=candidatRepository.findById(candidatID).orElseThrow();
        var jobs= offerJobRepository.findAll()
                .stream()
                .filter(offerJob -> {
                    var val=false;
                    for (CategoryJob cat: candidat.getCategoryJobs()) {
                        if (offerJob.getCategoryJobs().stream().map(CategoryJob::getId).collect(Collectors.toList()).contains(cat.getId())) {
                            System.out.println("########################");
                            val = true;
                            break;
                        }
                    }
                    return val;
                })
                .collect(Collectors.toList());
        return new PageImpl<>(jobs,pageable, jobs.size());
    }

    @Override
    public Page<OfferJob> findSearch(Pageable pageable, String query, String location, String category,
            String experience, String dateposted, String salary, String type) {
                var jobs= offerJobRepository.findAll();
                if(!query.isBlank()){
                    System.out.print(query);
                  jobs= jobs.stream()
                          .filter(e->e.getTitle().toLowerCase().contains(query.toLowerCase()))
                          .collect(Collectors.toList());

                }
                if(!location.isEmpty()){
                    System.out.print("***************location"+location);
                   jobs=jobs.stream().filter(e -> {
                        var res= Objects.equals(e.getTown(), location) || Objects.equals(e.getRecruteur().getBp(), location);
                       return res;
                    }).collect(Collectors.toList());
                }
                if(!category.isEmpty()){
                    System.out.print("***************category"+category);
                    jobs.stream().filter(e -> {
                        var res= e.getJobType().contains(category);
                        return res;
                    }).collect(Collectors.toList());
                }
                return new PageImpl<>(jobs,pageable, jobs.size());
    }
}
