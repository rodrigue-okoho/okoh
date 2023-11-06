package com.okoho.okoho.service.dto;

import java.io.Serializable;
import java.util.Objects;

import com.okoho.okoho.domain.UserAccount;

/**
 * A DTO for the {@link com.okoho.okoho.domain.Personnel} entity.
 */
public class PersonnelDTO implements Serializable {

    private String id;
    private UserAccount userAccount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UserAccount getUserAccount() {
        return this.userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PersonnelDTO)) {
            return false;
        }

        PersonnelDTO personnelDTO = (PersonnelDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, personnelDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PersonnelDTO{" +
            "id='" + getId() + "'" +
            ", userAccount="+
            "}";
    }
}
