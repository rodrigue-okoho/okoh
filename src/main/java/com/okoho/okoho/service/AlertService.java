package com.okoho.okoho.service;

import com.okoho.okoho.domain.Address;
import com.okoho.okoho.domain.AlertCandidat;
import com.okoho.okoho.domain.AlertJob;
import com.okoho.okoho.service.dto.AdressDTO;
import com.okoho.okoho.service.dto.AlertCandidatDTO;
import com.okoho.okoho.service.dto.AlertJobDTO;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface AlertService {
    AlertCandidatDTO save_alert_candidat(AlertCandidatDTO alertCandidatDTO);
    AlertJobDTO save_alert_job(AlertJobDTO alertJobDTO);
    Optional<AlertJob> findOneAlertJob(String id);
    Optional<AlertJob> findOneAlertCandidat(String id);
    Page<AlertJob> findByCandidatWithEagerRelationships(Pageable pageable, String id);
    Page<AlertCandidat> findByEntrepriseWithEagerRelationships(Pageable pageable,String id);
    void deleteAlertCandidat(String id);
    void deleteAlertJob(String id);
}
