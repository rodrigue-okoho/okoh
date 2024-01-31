package com.okoho.okoho.service.impl;

import com.okoho.okoho.domain.AlertCandidat;
import com.okoho.okoho.domain.AlertJob;
import com.okoho.okoho.repository.AlertCandidatRepository;
import com.okoho.okoho.repository.AlertJobRepository;
import com.okoho.okoho.repository.UserAccountRepository;
import com.okoho.okoho.service.AlertService;
import com.okoho.okoho.service.dto.AlertCandidatDTO;
import com.okoho.okoho.service.dto.AlertJobDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class AlertServiceimpl implements AlertService {
    @Autowired
    private AlertJobRepository alertJobRepository;
    @Autowired
    private AlertCandidatRepository alertCandidatRepository;
    @Autowired
    private UserAccountRepository userAccountRepository;
    @Override
    public AlertCandidatDTO save_alert_candidat(AlertCandidatDTO alertCandidatDTO) {
        var user=userAccountRepository.findById(alertCandidatDTO.getOwner_id()).get();
        AlertCandidat alertCandidat=new AlertCandidat();
        alertCandidat.setTitle(alertCandidatDTO.getTitle());
        alertCandidat.setCategory(alertCandidatDTO.getCategory());
        alertCandidat.setExperience(alertCandidatDTO.getExperience());
        alertCandidat.setFrequency(alertCandidatDTO.getFrequency());
        alertCandidat.setIntitule(alertCandidatDTO.getIntitule());
        alertCandidat.setDatePosted(alertCandidatDTO.getDatePosted());
        alertCandidat.setEducation(alertCandidatDTO.getEducation());
        alertCandidat.setGender(alertCandidatDTO.getGender());
        alertCandidat.setLocation(alertCandidatDTO.getLocation());
        alertCandidat.setUserAccount(user);
        alertCandidatRepository.save(alertCandidat);
        return alertCandidatDTO;
    }

    @Override
    public AlertJobDTO save_alert_job(AlertJobDTO alertJobDTO) {
        var user=userAccountRepository.findById(alertJobDTO.getOwner_id()).get();
        AlertJob alertJob=new AlertJob();
        alertJob.setTitle(alertJobDTO.getTitle());
        alertJob.setCategory(alertJobDTO.getCategory());
        alertJob.setExperience(alertJobDTO.getExperience());
        alertJob.setFrequency(alertJobDTO.getFrequency());
        alertJob.setIntitule(alertJobDTO.getIntitule());
        alertJob.setDatePosted(alertJobDTO.getDatePosted());
        alertJob.setLocation(alertJobDTO.getLocation());
        alertJob.setUserAccount(user);
        alertJobRepository.save(alertJob);
        return alertJobDTO;
    }

    @Override
    public Optional<AlertJob> findOneAlertJob(String id) {
        return Optional.empty();
    }

    @Override
    public Optional<AlertJob> findOneAlertCandidat(String id) {
        return Optional.empty();
    }

    @Override
    public Page<AlertJob> findByCandidatWithEagerRelationships(Pageable pageable, String id) {
        var user=userAccountRepository.findById(id).get();
        var alerts=alertJobRepository.findByUserAccount(user);
        return new PageImpl<>(alerts,pageable, alerts.size());
       // return alertJobRepository.findByUserAccount(pageable,user);
    }

    @Override
    public Page<AlertCandidat> findByEntrepriseWithEagerRelationships(Pageable pageable, String id) {
       var user=userAccountRepository.findById(id).get();
        var alerts=alertCandidatRepository.findByUserAccount(user);
        return new PageImpl<>(alerts,pageable, alerts.size());
       // return alertCandidatRepository.findByUserAccount(pageable,user);
    }

    @Override
    public void deleteAlertCandidat(String id) {
        alertCandidatRepository.deleteById(id);
    }

    @Override
    public void deleteAlertJob(String id) {
        alertJobRepository.deleteById(id);
    }
}
