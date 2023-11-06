package com.okoho.okoho.domain;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Size;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * A UserAccount.
 */
@Document(collection = "user_account")
public class UserAccount implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("first_name")
    private String firstName;

    @Field("last_name")
    private String lastName;

    @Field("email")
    private String email;

    @Field("phone_number")
    private String phoneNumber;
    @Field("gender")
    private String gender;
    @Field("code_phone")
    private String codePhone;
    @Field("latitude")
    private String latitude;
    @Field("longitude")
    private String longitude;
    @Field("address")
    private String address;

    @Field("is_verify")
    private Boolean isVerify;
    @Field("show_profile")
    private Boolean show_profile;
    @Field("lang_key")
    private String langKey;
    @Field("user_type")
    private String userType;
    @Field("image_url")
    private String imageUrl;
    @DBRef
    @Field("file_url")
    private FileUrl fileUrl;
    @Field("activated")
    private boolean activated = false;
    @JsonIgnore
    private String password;
    @JsonIgnore
    private String activationKey;

    @Size(max = 30)
    @Field(name = "reset_key")
    private String resetKey;
    @CreatedDate
    @Field(name = "created_date")
    @JsonIgnore
    private Instant createdDate = Instant.now();
    @Field(name = "reset_date")
    private Instant resetDate = null;
    @DBRef
    private Set<Role> roles = new HashSet<>();
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLatitude() {
        return this.latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return this.longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean isShow_profile() {
        return this.show_profile;
    }

    public Boolean getShow_profile() {
        return show_profile;
    }

    public void setShow_profile(Boolean show_profile) {
        this.show_profile = show_profile;
    }

    public Instant getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public FileUrl getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(FileUrl fileUrl) {
        this.fileUrl = fileUrl;
    }

    public UserAccount id(String id) {
        this.id = id;
        return this;
    }

    public String getUserType() {
        return this.userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getActivationKey() {
        return this.activationKey;
    }

    public void setActivationKey(String activationKey) {
        this.activationKey = activationKey;
    }

    public String getResetKey() {
        return this.resetKey;
    }

    public void setResetKey(String resetKey) {
        this.resetKey = resetKey;
    }

    public Instant getResetDate() {
        return this.resetDate;
    }

    public void setResetDate(Instant resetDate) {
        this.resetDate = resetDate;
    }

    public boolean isActivated() {
        return this.activated;
    }

    public boolean getActivated() {
        return this.activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public UserAccount firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public UserAccount lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public UserAccount email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public UserAccount phoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCodePhone() {
        return this.codePhone;
    }

    public UserAccount codePhone(String codePhone) {
        this.codePhone = codePhone;
        return this;
    }

    public void setCodePhone(String codePhone) {
        this.codePhone = codePhone;
    }

    public Boolean getIsVerify() {
        return this.isVerify;
    }

    public UserAccount isVerify(Boolean isVerify) {
        this.isVerify = isVerify;
        return this;
    }

    public void setIsVerify(Boolean isVerify) {
        this.isVerify = isVerify;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserAccount)) {
            return false;
        }
        return id != null && id.equals(((UserAccount) o).id);
    }

    public Boolean isIsVerify() {
        return this.isVerify;
    }

    public String getLangKey() {
        return this.langKey;
    }

    public void setLangKey(String langKey) {
        this.langKey = langKey;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }
    public Set<Role> getRoles() {
        return roles;
      }
    
      public void setRoles(Set<Role> roles) {
        this.roles = roles;
      }
    // prettier-ignore
    @Override
    public String toString() {
        return "UserAccount{" +
            "id=" + getId() +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", email='" + getEmail() + "'" +
            ", user_type='" + getUserType() + "'" +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", codePhone='" + getCodePhone() + "'" +
            ", isVerify='" + getIsVerify() + "'" +
            ", reset='" + getResetKey() + "'" +
                ", activate='" + getActivationKey() + "'" +
            "}";
    }
}
