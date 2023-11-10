package com.okoho.okoho.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A OfferJob.
 */
@Document(collection = "offer_job")
public class OfferJob implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("title")
    private String title;
    @Field("job_type")
    private String jobType;
    @Field("description")
    private String description;
    @Field("min_salary")
    private String minSalary;
    @Field("max_salary")
    private String max_salary;
    @Field("created_at")
    private LocalDate createdAt;
    @DBRef
    @Field("competences")
    private Set<Competence> competences = new HashSet<>();
    @Field("expired_at")
    private LocalDate expiredAt;
    @Field("is_active")
    private Boolean isActive=false;
    @Field("salary_type")
    private String salaryType;
    @Field("job_apply_type")
    private String jobApplyType;
    @Field("exter_url_apply")
    private String externUrlApply;
    @Field("apply_email")
    private String applyEmail;
    @Field("caree_level")
    private String careeLevel;
    @Field("experience")
    private int experience;
    @Field("gender")
    private String gender;
    @Field("industry")
    private String industry;
    @Field("qualification")
    private String qualification;
    @Field("address")
    private String address;
    @Field("town")
    private String town;
    @Field("country")
    private String country;
    @Field("longitude")
    private String longitude;
    @Field("latitude")
    private String latitude;
    @DBRef
    @Field("recruteur")
    private Recruteur recruteur;

    @DBRef
    @Field("categoryJobs")
    private Set<CategoryJob> categoryJobs = new HashSet<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public OfferJob id(String id) {
        this.id = id;
        return this;
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
        return this.title;
    }

    public OfferJob title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public OfferJob description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getCreatedAt() {
        return this.createdAt;
    }

    public OfferJob createdAt(LocalDate createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getExpiredAt() {
        return this.expiredAt;
    }

    public OfferJob expiredAt(LocalDate expiredAt) {
        this.expiredAt = expiredAt;
        return this;
    }

    public void setExpiredAt(LocalDate expiredAt) {
        this.expiredAt = expiredAt;
    }

    public Boolean getIsActive() {
        return this.isActive;
    }

    public OfferJob isActive(Boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Recruteur getRecruteur() {
        return this.recruteur;
    }

    public OfferJob recruteur(Recruteur recruteur) {
        this.setRecruteur(recruteur);
        return this;
    }

    public void setRecruteur(Recruteur recruteur) {
        this.recruteur = recruteur;
    }

    public Set<Competence> getCompetences() {
        return this.competences;
    }

    public OfferJob competences(Set<Competence> competences) {
        this.setCompetences(competences);
        return this;
    }

    public OfferJob addCompetences(Competence competence) {
        this.competences.add(competence);
        return this;
    }

    public OfferJob removeCompetences(Competence competence) {
        this.competences.remove(competence);
        return this;
    }

    public void setCompetences(Set<Competence> competences) {
        this.competences = competences;
    }


    public OfferJob categoryJob(Set<CategoryJob> categoryJobs) {
        this.setCategoryJobs(categoryJobs);
        return this;
    }

    public OfferJob addCategoryJob(CategoryJob categoryJob) {
        this.categoryJobs.add(categoryJob);
        return this;
    }

    public OfferJob removeCategoryJobs(CategoryJob categoryJob) {
        this.categoryJobs.remove(categoryJob);
        return this;
    }

    public Set<CategoryJob> getCategoryJobs() {
        return this.categoryJobs;
    }

    public void setCategoryJobs(Set<CategoryJob> categoryJobs) {
        this.categoryJobs = categoryJobs;
    }

    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OfferJob)) {
            return false;
        }
        return id != null && id.equals(((OfferJob) o).id);
    }

    public String getJobType() {
        return this.jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
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

    public Boolean isIsActive() {
        return this.isActive;
    }


    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OfferJob{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", description='" + getDescription() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", expiredAt='" + getExpiredAt() + "'" +
            ", isActive='" + getIsActive() + "'" +
            "}";
    }
}
