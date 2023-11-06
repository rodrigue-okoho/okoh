package com.okoho.okoho.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A DTO for the {@link com.okoho.okoho.domain.OfferJob} entity.
 */
public class OfferJobDTO implements Serializable {

    private String id;

    private String title;

    private String description;
    private String id_recruteur;
    private String expiredAt;
    private String salaryType;
    private String jobApplyType;
    private String externUrlApply;
    private String applyEmail;
    private String minSalary;
    private String max_salary;
    private String careeLevel;
    private int experience;
    private String gender;
    private String industry;
    private String qualification;
    private String address;
    private String town;
    private String country;
    private String longitude;
    private String latitude;
    private Set<String> categoryjobs = new HashSet<>();

    public String getId_recruteur() {
        return this.id_recruteur;
    }

    public void setId_recruteur(String id_recruteur) {
        this.id_recruteur = id_recruteur;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Set<String> getCategoryjobs() {
        return this.categoryjobs;
    }

    public void setCategoryjobs(Set<String> categoryjobs) {
        this.categoryjobs = categoryjobs;
    }

    public String getMinSalary() {
        return this.minSalary;
    }

    public void setMinSalary(String minSalary) {
        this.minSalary = minSalary;
    }

    public String getMax_salary() {
        return this.max_salary;
    }

    public void setMax_salary(String max_salary) {
        this.max_salary = max_salary;
    }


    public String getSalaryType() {
        return this.salaryType;
    }

    public void setSalaryType(String salaryType) {
        this.salaryType = salaryType;
    }

    public String getJobApplyType() {
        return this.jobApplyType;
    }

    public void setJobApplyType(String jobApplyType) {
        this.jobApplyType = jobApplyType;
    }

    public String getExternUrlApply() {
        return this.externUrlApply;
    }

    public void setExternUrlApply(String externUrlApply) {
        this.externUrlApply = externUrlApply;
    }

    public String getApplyEmail() {
        return this.applyEmail;
    }

    public void setApplyEmail(String applyEmail) {
        this.applyEmail = applyEmail;
    }

    public String getCareeLevel() {
        return this.careeLevel;
    }

    public void setCareeLevel(String careeLevel) {
        this.careeLevel = careeLevel;
    }

    public int getExperience() {
        return this.experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIndustry() {
        return this.industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getQualification() {
        return this.qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTown() {
        return this.town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLongitude() {
        return this.longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return this.latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExpiredAt() {
        return expiredAt;
    }

    public void setExpiredAt(String expiredAt) {
        this.expiredAt = expiredAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OfferJobDTO)) {
            return false;
        }

        OfferJobDTO offerJobDTO = (OfferJobDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, offerJobDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OfferJobDTO{" +
            "id='" + getId() + "'" +
            ", title='" + getTitle() + "'" +
            ", description='" + getDescription() + "'" +
            ", expiredAt='" + getExpiredAt() + "'" +
            "}";
    }
}
