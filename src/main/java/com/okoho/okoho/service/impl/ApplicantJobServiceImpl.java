package com.okoho.okoho.service.impl;

import com.okoho.okoho.domain.ApplicantJob;
import com.okoho.okoho.repository.ApplicantJobRepository;
import com.okoho.okoho.repository.CandidatRepository;
import com.okoho.okoho.repository.OfferJobRepository;
import com.okoho.okoho.repository.RecruteurRepository;
import com.okoho.okoho.service.ApplicantJobService;
import com.okoho.okoho.service.dto.ApplicantJobDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class ApplicantJobServiceImpl implements ApplicantJobService {

private final ApplicantJobRepository applicantJobRepository;
private final CandidatRepository candidatRepository;
private final OfferJobRepository offerJobRepository;
private final RecruteurRepository recruteurRepository;

    public ApplicantJobServiceImpl(ApplicantJobRepository applicantJobRepository, CandidatRepository candidatRepository, OfferJobRepository offerJobRepository, RecruteurRepository recruteurRepository) {
        this.applicantJobRepository = applicantJobRepository;
        this.candidatRepository = candidatRepository;
        this.offerJobRepository = offerJobRepository;
        this.recruteurRepository = recruteurRepository;
    }

    @Override
    public ApplicantJob save(ApplicantJobDTO applicantJobDTO) {
        var candidat=candidatRepository.findById(applicantJobDTO.getCandidat()).get();
        var offerJob=offerJobRepository.findById(applicantJobDTO.getOfferJob()).get();
        var appl=applicantJobRepository.findFirstByCandidatAndOfferJob(candidat,offerJob);
        if (!appl.isPresent()){
           var applicant=new ApplicantJob();
           applicant.setCandidat(candidat);
           applicant.setOfferJob(offerJob);
           applicant.setCreatedAt(LocalDate.now());
           applicantJobRepository.save(applicant);
           return applicant;
        }
        return appl.get();
    }

    @Override
    public Optional<ApplicantJob> partialUpdate(ApplicantJobDTO applicantJobDTO) {
        return Optional.empty();
    }

    @Override
    public List<ApplicantJob> findAll() {
        return applicantJobRepository.findAll();
    }

    @Override
    public Page<ApplicantJob> findAllWithEagerRelationships(Pageable pageable) {
        return findAllWithEagerRelationships(pageable);
    }

    @Override
    public Page<ApplicantJob> findAllByCandidat(Pageable pageable, String candidatID) {
        var candidat=candidatRepository.findById(candidatID).orElseThrow();
        var offers=applicantJobRepository.findAllWithEagerRelationships().stream()
        .filter(applicantJob->applicantJob.getCandidat() !=null)
                .filter(applicantJob -> applicantJob.getCandidat().getId().equals(candidatID))
                .collect(Collectors.toList());
             
        return new PageImpl<>(offers,pageable,offers.size());
    }

    @Override
    public Page<ApplicantJob> findAllByEntreprise(Pageable pageable,String job_id) {
        var offers=applicantJobRepository.findAllWithEagerRelationships().stream()
        .filter(applicantJob->applicantJob.getOfferJob() !=null)
                .filter(applicantJob -> applicantJob.getOfferJob().getId().equals(job_id))
                .collect(Collectors.toList());

        return new PageImpl<>(offers,pageable,offers.size());
    }

    @Override
    public Optional<ApplicantJob> findOne(String id) {
        return Optional.empty();
    }

    @Override
    public void delete(String id) {

    }
}
