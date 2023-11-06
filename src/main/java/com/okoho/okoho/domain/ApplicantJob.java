package com.okoho.okoho.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;

@Document(collection = "applicant_job")
@TypeAlias("applicant_job")
public class ApplicantJob {
    @Id
    private String id;
    @DBRef
    @Field("candidat")
    private Candidat candidat;
    @DBRef
    @Field("offerJob")
    private OfferJob offerJob;
    @Field("status")
    private String status;
    @Field("created_at")
    private LocalDate createdAt;
    @Field("update_at")
    private LocalDate updateAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Candidat getCandidat() {
        return this.candidat;
    }
    public ApplicantJob candidat(Candidat candidat) {
        this.setCandidat(candidat);
        return this;
    }

    public void setCandidat(Candidat candidat) {
        this.candidat = candidat;
    }

    public OfferJob getOfferJob() {
        return this.offerJob;
    }

    public void setOfferJob(OfferJob offerJob) {
        this.offerJob = offerJob;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDate updateAt) {
        this.updateAt = updateAt;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", candidat='" + getCandidat() + "'" +
            ", offerJob='" + getOfferJob() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updateAt='" + getUpdateAt() + "'" +
            "}";
    }

}
