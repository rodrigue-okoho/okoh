package com.okoho.okoho.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A Candidat.
 */
@Document(collection = "candidat")
@TypeAlias("candidat")
public class Candidat {
    private static final long serialVersionUID = 1L;
    @Id
    private String id;
    @DBRef
    @Field("userAccount")
    private UserAccount userAccount;
    @Field("country")
    private String country;
    @Field("town")
    private String town;
    @Field("placeofborn")
    private String placeofborn;
    @Field("current_salary")
    private float currentSalary;
    @Field("expected_salary")
    private float expectedSalary;
    @Field("education_level")
    private String educationLevel;
    @Field("salary_type")
    private String salaryType;
    @Field("experience_time")
    private String experienceTime;
    @Field("jobtitle")
    private String jobTitle;
    @Field("qualification")
    private String qualification;
    @Field("age")
    private String age;
    @Field("description")
    private String description;
    @Field("facebook")
    private String facebook;
    @Field("twitter")
    private String twitter;
    @Field("linkedin")
    private String linkedin;
    @Field("googleplus")
    private String googleplus;
    @Field("dob")
    private LocalDate dob;
    @DBRef
    @Field("offerJobs")
    @JsonIgnoreProperties(value = { "recruteur" }, allowSetters = true)
    private Set<OfferJob> offerJobs = new HashSet<>();
    @DBRef
    @Field("categoryJob")
    private Set<CategoryJob> categoryJobs= new HashSet<>();
    @DBRef
    @Field("competences")
    private Set<Competence> competences = new HashSet<>();
    @DBRef
    @Field("cvs")
    private Set<FileUrl> cvs = new HashSet<>();
    @DBRef
    @Field("educations")
    private Set<ItemCandidat> educations = new HashSet<>();
    @DBRef
    @Field("works")
    private Set<ItemCandidat> works = new HashSet<>();
    @DBRef
    @Field("awards")
    private Set<ItemCandidat> awards = new HashSet<>();
    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getPlaceofborn() {
        return placeofborn;
    }

    public void setPlaceofborn(String placeofborn) {
        this.placeofborn = placeofborn;
    }

    public String getFacebook() {
        return this.facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getTwitter() {
        return this.twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getLinkedin() {
        return this.linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public String getGoogleplus() {
        return this.googleplus;
    }

    public void setGoogleplus(String googleplus) {
        this.googleplus = googleplus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Candidat id(String id) {
        this.id = id;
        return this;
    }

    public String getCountry() {
        return this.country;
    }

    public Candidat country(String country) {
        this.country = country;
        return this;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public LocalDate getDob() {
        return this.dob;
    }

    public Candidat dob(LocalDate dob) {
        this.dob = dob;
        return this;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public float getCurrentSalary() {
        return currentSalary;
    }

    public void setCurrentSalary(float currentSalary) {
        this.currentSalary = currentSalary;
    }

    public float getExpectedSalary() {
        return expectedSalary;
    }

    public void setExpectedSalary(float expectedSalary) {
        this.expectedSalary = expectedSalary;
    }

    public String getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(String educationLevel) {
        this.educationLevel = educationLevel;
    }

    public Set<CategoryJob> getCategoryJobs() {
        return categoryJobs;
    }

    public void setCategoryJobs(Set<CategoryJob> categoryJobs) {
        this.categoryJobs = categoryJobs;
    }
    public Candidat addCategoryJobs(CategoryJob categoryJob) {
        this.categoryJobs.add(categoryJob);
        return this;
    }

    public Candidat removeCategoryJobs(CategoryJob categoryJob) {
        this.categoryJobs.remove(categoryJob);
        return this;
    }

    public String getSalaryType() {
        return salaryType;
    }

    public void setSalaryType(String salaryType) {
        this.salaryType = salaryType;
    }

    public String getExperienceTime() {
        return experienceTime;
    }

    public void setExperienceTime(String experienceTime) {
        this.experienceTime = experienceTime;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserAccount getUserAccount() {
        return this.userAccount;
    }

    public Candidat userAccount(UserAccount userAccount) {
        this.setUserAccount(userAccount);
        return this;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public Set<OfferJob> getOfferJobs() {
        return this.offerJobs;
    }

    public Candidat offerJobs(Set<OfferJob> offerJobs) {
        this.setOfferJobs(offerJobs);
        return this;
    }

    public Candidat addOfferJob(OfferJob offerJob) {
        this.offerJobs.add(offerJob);
        return this;
    }

    public Candidat removeOfferJob(OfferJob offerJob) {
        this.offerJobs.remove(offerJob);
        return this;
    }

    public void setOfferJobs(Set<OfferJob> offerJobs) {
        this.offerJobs = offerJobs;
    }

    public Set<Competence> getCompetences() {
        return this.competences;
    }

    public Candidat competences(Set<Competence> competences) {
        this.setCompetences(competences);
        return this;
    }

    public Candidat addCompetences(Competence competence) {
        this.competences.add(competence);
        return this;
    }

    public Candidat removeCompetences(Competence competence) {
        this.competences.remove(competence);
        return this;
    }

    public void setCompetences(Set<Competence> competences) {
        this.competences = competences;
    }
    
    public Set<FileUrl> getCvs() {
        return this.cvs;
    }
    public Candidat cvs(Set<FileUrl> cvs) {
        this.cvs(cvs);
        return this;
    }

    public Candidat addCvs(FileUrl cv) {
        this.cvs.add(cv);
        return this;
    }

    public Candidat removeCvs(FileUrl cv) {
        this.cvs.remove(cv);
        return this;
    }

    public void setCvs(Set<FileUrl> cvs) {
        this.cvs = cvs;
    }

    public Set<ItemCandidat> getEducations() {
        return this.educations;
    }

    public void setEducations(Set<ItemCandidat> educations) {
        this.educations = educations;
    }
    public Candidat addEducation(ItemCandidat education) {
        this.educations.add(education);
        return this;
    }

    public Candidat removeEducation(ItemCandidat education) {
        this.educations.remove(education);
        return this;
    }
    public Set<ItemCandidat> getWorks() {
        return this.works;
    }

    public void setWorks(Set<ItemCandidat> works) {
        this.works = works;
    }
    public Candidat addWork(ItemCandidat work) {
        this.works.add(work);
        return this;
    }

    public Candidat removeWork(ItemCandidat work) {
        this.works.remove(work);
        return this;
    }
    public Set<ItemCandidat> getAwards() {
        return this.awards;
    }

    public void setAwards(Set<ItemCandidat> awards) {
        this.awards = awards;
    }
    public Candidat addAwards(ItemCandidat award) {
        this.awards.add(award);
        return this;
    }

    public Candidat removeAwards(ItemCandidat award) {
        this.awards.remove(award);
        return this;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Candidat)) {
            return false;
        }
        return id != null && id.equals(((Candidat) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Candidat{" +
            "id=" + getId() +
            ", country='" + getCountry() + "'" +
            ", dob='" + getDob() + "'" +
            "}";
    }
}
