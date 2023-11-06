package com.okoho.okoho.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.okoho.okoho.domain.UserAccount;

/**
 * A DTO for the {@link com.okoho.okoho.domain.Recruteur} entity.
 */
public class RecruteurDTO implements Serializable {

    private String id;
    private String firstName;
    private String lastName;
    private String country;
    private String email;
    private String phoneNumber;
    private String codePhone;
    private LocalDate dob;
    private String entreprise;
    private String description;
    private String location;
    private String companySize;
    private String foundedDate;
    private String bp;
    private String mode;
    private String website;
    private String user_type; //1=candidate 2=recruteur 3=personnel
    private String town;
    private String facebook;
    private String twitter;
    private String googleplus;
    private String linkedin;
    private String latitude;
    private String longitude;
    private String address;
    private String imageUrl;
    private String coverUrl;
    private UserAccount userAccount;
    private Set<String> categoryJobs= new HashSet<>();
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Set<String> getCategoryJobs() {
        return categoryJobs;
    }

    public void setCategoryJobs(Set<String> categoryJobs) {
        this.categoryJobs = categoryJobs;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCodePhone() {
        return codePhone;
    }

    public void setCodePhone(String codePhone) {
        this.codePhone = codePhone;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
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

    public String getGoogleplus() {
        return googleplus;
    }

    public void setGoogleplus(String googleplus) {
        this.googleplus = googleplus;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getFoundedDate() {
        return this.foundedDate;
    }

    public void setFoundedDate(String foundedDate) {
        this.foundedDate = foundedDate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public UserAccount getUserAccount() {
        return this.userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public String getEntreprise() {
        return entreprise;
    }

    public void setEntreprise(String entreprise) {
        this.entreprise = entreprise;
    }

    public String getBp() {
        return bp;
    }

    public void setBp(String bp) {
        this.bp = bp;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RecruteurDTO)) {
            return false;
        }

        RecruteurDTO recruteurDTO = (RecruteurDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, recruteurDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RecruteurDTO{" +
            "id='" + getId() + "'" +
            ", country='" + getCountry() + "'" +
            ", dob='" + getDob() + "'" +
            ", entreprise='" + getEntreprise() + "'" +
            ", bp='" + getBp() + "'" +
            ", userAccount=" +
            "}";
    }
}
