package com.okoho.okoho.service.dto;

import java.io.Serializable;
import java.util.Objects;


/**
 * A DTO for the {@link com.okoho.okoho.domain.Personnel} entity.
 */
public class PersonnelDDTO implements Serializable {

    private String id;
    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private String codePhone;

    private String password;
    private String address;

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCodePhone() {
        return this.codePhone;
    }

    public void setCodePhone(String codePhone) {
        this.codePhone = codePhone;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PersonnelDDTO)) {
            return false;
        }

        PersonnelDDTO personnelDTO = (PersonnelDDTO) o;
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
