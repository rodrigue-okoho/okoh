package com.okoho.okoho.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.okoho.okoho.domain.DomainActivity} entity.
 */
public class DomainActivityDTO implements Serializable {

    private String id;

    private String tilte;

    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTilte() {
        return tilte;
    }

    public void setTilte(String tilte) {
        this.tilte = tilte;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DomainActivityDTO)) {
            return false;
        }

        DomainActivityDTO domainActivityDTO = (DomainActivityDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, domainActivityDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DomainActivityDTO{" +
            "id='" + getId() + "'" +
            ", tilte='" + getTilte() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
