package com.okoho.okoho.service.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class RegisterRequest  implements Serializable{
    
    private String firstName;

    private String lastName;

    private String email;
    private String user_type; //1=candidate 2=recruteur 3=personnel

    private String phoneNumber;

    private String codePhone;

    private String password;

    private Boolean isVerify;
    private String langKey;
    private String imageUrl;
    private String country;

    private LocalDate dob;

    private String entreprise;

    private String bp;

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public LocalDate getDob() {
        return this.dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getEntreprise() {
        return this.entreprise;
    }

    public void setEntreprise(String entreprise) {
        this.entreprise = entreprise;
    }

    public String getBp() {
        return this.bp;
    }

    public void setBp(String bp) {
        this.bp = bp;
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

    public String getUser_type() {
        return this.user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

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

    public Boolean isIsVerify() {
        return this.isVerify;
    }

    public Boolean getIsVerify() {
        return this.isVerify;
    }

    public void setIsVerify(Boolean isVerify) {
        this.isVerify = isVerify;
    }

}
