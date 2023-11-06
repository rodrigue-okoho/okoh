package com.okoho.okoho.service.dto;

import com.okoho.okoho.domain.Candidat;

public class ApplicantJobDTO {
    private String candidat;
    private String offerJob;

    public String getCandidat() {
        return candidat;
    }

    public void setCandidat(String candidat) {
        this.candidat = candidat;
    }

    public String getOfferJob() {
        return offerJob;
    }

    public void setOfferJob(String offerJob) {
        this.offerJob = offerJob;
    }
}
