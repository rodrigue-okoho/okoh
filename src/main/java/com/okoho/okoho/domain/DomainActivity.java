package com.okoho.okoho.domain;
import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A DomainActivity.
 */
@Document(collection = "domain_activity")
public class DomainActivity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("tilte")
    private String tilte;

    @Field("description")
    private String description;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DomainActivity id(String id) {
        this.id = id;
        return this;
    }

    public String getTilte() {
        return this.tilte;
    }

    public DomainActivity tilte(String tilte) {
        this.tilte = tilte;
        return this;
    }

    public void setTilte(String tilte) {
        this.tilte = tilte;
    }

    public String getDescription() {
        return this.description;
    }

    public DomainActivity description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DomainActivity)) {
            return false;
        }
        return id != null && id.equals(((DomainActivity) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DomainActivity{" +
            "id=" + getId() +
            ", tilte='" + getTilte() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
