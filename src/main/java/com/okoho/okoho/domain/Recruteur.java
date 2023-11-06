package com.okoho.okoho.domain;


import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A Recruteur.
 */
@Document(collection = "recruteur")
@TypeAlias("recruteur")
public class Recruteur {

    private static final long serialVersionUID = 1L;
    @Id
    private String id;

    @DBRef
    @Field("userAccount")
    private UserAccount userAccount;
    
    @Field("country")
    private String country;

    @Field("dob")
    private LocalDate dob;
    @DBRef
    @Field("categoryJob")
    private Set<CategoryJob> categoryJobs= new HashSet<>();
    @Field("entreprise")
    private String entreprise;
    @Field("description")
    private String description;
    @Field("location")
    private String location;
    @Field("company_size")
    private String companySize;
    @Field("founded_date")
    private LocalDate foundedDate;
    @Field("bp")
    private String bp;
    @Field("website")
    private String website;
    @Field("town")
    private String town;
    @Field("facebook")
    private String facebook;
    @Field("twitter")
    private String twitter;
    @Field("linkedin")
    private String linkedin;
    @Field("googleplus")
    private String googleplus;
    @Field("cover_image_url")
    private String cover_image_url;
    @DBRef
    @Field("cover_url")
    private FileUrl cover_url;

    public String getCover_image_url() {
        return cover_image_url;
    }

    public void setCover_image_url(String cover_image_url) {
        this.cover_image_url = cover_image_url;
    }

    public FileUrl getCover_url() {
        return cover_url;
    }

    public void setCover_url(FileUrl cover_url) {
        this.cover_url = cover_url;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public Set<CategoryJob> getCategoryJobs() {
        return categoryJobs;
    }

    public void setCategoryJobs(Set<CategoryJob> categoryJobs) {
        this.categoryJobs = categoryJobs;
    }
    public Recruteur addCategoryJobs(CategoryJob categoryJob) {
        this.categoryJobs.add(categoryJob);
        return this;
    }

    public Recruteur removeCategoryJobs(CategoryJob categoryJob) {
        this.categoryJobs.remove(categoryJob);
        return this;
    }
    public String getGoogleplus() {
        return googleplus;
    }

    public void setGoogleplus(String googleplus) {
        this.googleplus = googleplus;
    }

    public String getWebsite() {
        return this.website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCompanySize() {
        return this.companySize;
    }

    public void setCompanySize(String companySize) {
        this.companySize = companySize;
    }

    public LocalDate getFoundedDate() {
        return this.foundedDate;
    }

    public void setFoundedDate(LocalDate foundedDate) {
        this.foundedDate = foundedDate;
    }

   public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Recruteur id(String id) {
        this.id = id;
        return this;
    }

    public UserAccount getUserAccount() {
        return this.userAccount;
    }

    public Recruteur userAccount(UserAccount userAccount) {
        this.setUserAccount(userAccount);
        return this;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }
    public String getCountry() {
        return this.country;
    }

    public Recruteur country(String country) {
        this.country = country;
        return this;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public LocalDate getDob() {
        return this.dob;
    }

    public Recruteur dob(LocalDate dob) {
        this.dob = dob;
        return this;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getEntreprise() {
        return this.entreprise;
    }

    public Recruteur entreprise(String entreprise) {
        this.entreprise = entreprise;
        return this;
    }

    public void setEntreprise(String entreprise) {
        this.entreprise = entreprise;
    }

    public String getBp() {
        return this.bp;
    }

    public Recruteur bp(String bp) {
        this.bp = bp;
        return this;
    }

    public void setBp(String bp) {
        this.bp = bp;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Recruteur)) {
            return false;
        }
        return id != null && id.equals(((Recruteur) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Recruteur{" +
            "id=" + getId() +
            ", country='" + getCountry() + "'" +
            ", dob='" + getDob() + "'" +
            ", entreprise='" + getEntreprise() + "'" +
            ", bp='" + getBp() + "'" +
            "}";
    }
}
