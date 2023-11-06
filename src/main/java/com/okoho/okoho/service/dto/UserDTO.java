package com.okoho.okoho.service.dto;

import com.okoho.okoho.domain.ERole;
import com.okoho.okoho.domain.Role;
import com.okoho.okoho.domain.UserAccount;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * A DTO for the {@link UserAccount} entity.
 */
public class UserDTO implements Serializable {
    private String id;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private String codePhone;

    private Boolean isVerify;
    private String password;
    private String langKey;
    private String imageUrl;
    private boolean activated;
    private String userType;
    private Set<String> roles;
    private String activationKey;
    private String resetKey;
    public UserDTO(UserAccount userAccount) {
        this.id = Optional.ofNullable(userAccount).map(UserAccount::getId).orElse("");
        this.firstName = Optional.ofNullable(userAccount).map(UserAccount::getFirstName).orElse("");
        this.lastName = Optional.ofNullable(userAccount).map(UserAccount::getLastName).orElse("");
        this.email = Optional.ofNullable(userAccount).map(UserAccount::getEmail).orElse("");
        this.phoneNumber = Optional.ofNullable(userAccount).map(UserAccount::getPhoneNumber).orElse("");
        this.codePhone = Optional.ofNullable(userAccount).map(UserAccount::getPhoneNumber).orElse("");
        this.isVerify = Optional.ofNullable(userAccount).map(UserAccount::getIsVerify).orElse(false);
        this.langKey = Optional.ofNullable(userAccount).map(UserAccount::getLangKey).orElse("");
        this.imageUrl = Optional.ofNullable(userAccount).map(UserAccount::getImageUrl).orElse("");
        this.activated = Optional.ofNullable(userAccount).map(UserAccount::getActivated).orElse(false);
        this.userType = Optional.ofNullable(userAccount).map(UserAccount::getUserType).orElse("");
        this.resetKey = Optional.ofNullable(userAccount).map(UserAccount::getResetKey).orElse("");
        this.activationKey = Optional.ofNullable(userAccount).map(UserAccount::getActivationKey).orElse("");
        this.roles = userAccount.getRoles().stream().map(Role::getName).map(ERole::name).collect(Collectors.toSet());
    }

    public String getActivationKey() {
        return activationKey;
    }

    public void setActivationKey(String activationKey) {
        this.activationKey = activationKey;
    }

    public String getResetKey() {
        return resetKey;
    }

    public void setResetKey(String resetKey) {
        this.resetKey = resetKey;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLangKey() {
        return this.langKey;
    }

    public void setLangKey(String langKey) {
        this.langKey = langKey;
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

    public String getUserType() {
        return this.userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Set<String> getRoles() {
        return this.roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Boolean isIsVerify() {
        return this.isVerify;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Boolean getIsVerify() {
        return isVerify;
    }

    public void setIsVerify(Boolean isVerify) {
        this.isVerify = isVerify;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserDTO)) {
            return false;
        }

        UserDTO userAccountDTO = (UserDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, userAccountDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UserAccountDTO{" +
            "id='" + getId() + "'" +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", email='" + getEmail() + "'" +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", codePhone='" + getCodePhone() + "'" +
            ", isVerify='" + getIsVerify() + "'" +
            "}";
    }
}
