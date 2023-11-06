package com.okoho.okoho.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A Experience.
 */
@Document(collection = "experience")
public class Experience implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("intitule")
    private String intitule;

    @Field("date_begin")
    private LocalDate dateBegin;

    @Field("date_end")
    private LocalDate dateEnd;

    @Field("poste")
    private String poste;

    @Field("description")
    private String description;

    @DBRef
    @Field("candidat")
    @JsonIgnoreProperties(value = { "userAccount", "offerJobs", "competences" }, allowSetters = true)
    private Candidat candidat;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Experience id(String id) {
        this.id = id;
        return this;
    }

    public String getIntitule() {
        return this.intitule;
    }

    public Experience intitule(String intitule) {
        this.intitule = intitule;
        return this;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public LocalDate getDateBegin() {
        return this.dateBegin;
    }

    public Experience dateBegin(LocalDate dateBegin) {
        this.dateBegin = dateBegin;
        return this;
    }

    public void setDateBegin(LocalDate dateBegin) {
        this.dateBegin = dateBegin;
    }

    public LocalDate getDateEnd() {
        return this.dateEnd;
    }

    public Experience dateEnd(LocalDate dateEnd) {
        this.dateEnd = dateEnd;
        return this;
    }

    public void setDateEnd(LocalDate dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getPoste() {
        return this.poste;
    }

    public Experience poste(String poste) {
        this.poste = poste;
        return this;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public String getDescription() {
        return this.description;
    }

    public Experience description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Candidat getCandidat() {
        return this.candidat;
    }

    public Experience candidat(Candidat candidat) {
        this.setCandidat(candidat);
        return this;
    }

    public void setCandidat(Candidat candidat) {
        this.candidat = candidat;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Experience)) {
            return false;
        }
        return id != null && id.equals(((Experience) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Experience{" +
            "id=" + getId() +
            ", intitule='" + getIntitule() + "'" +
            ", dateBegin='" + getDateBegin() + "'" +
            ", dateEnd='" + getDateEnd() + "'" +
            ", poste='" + getPoste() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
