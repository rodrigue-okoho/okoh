package com.okoho.okoho.domain;

import java.io.Serializable;
import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

public class ItemCandidat  implements Serializable{
    
    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("libelle")
    private String libelle;
    @Field("item_type")
    private String itemType;
    @Field("location")
    private String location;
    @Field("employer_name")
    private String employer_name;
    @Field("traning_body")
    private String traning_body;
    @Field("line2")
    private String line2;
    @Field("line1")
    private String line1;
    @Field("begin")
    private LocalDate begin;
    @Field("end")
    private LocalDate end;
    @Field("city")
    private String city;
    @Field("country")
    private String country;
    @Field("description")
    private String description;

    @Field("postcode")
    private String postcode;
    @Field("website")
    private String website;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmployer_name() {
        return this.employer_name;
    }

    public void setEmployer_name(String employer_name) {
        this.employer_name = employer_name;
    }

    public String getTraning_body() {
        return this.traning_body;
    }

    public void setTraning_body(String traning_body) {
        this.traning_body = traning_body;
    }

    public String getLine2() {
        return this.line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public String getLine1() {
        return this.line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLibelle() {
        return this.libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getItemType() {
        return this.itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDate getBegin() {
        return this.begin;
    }

    public void setBegin(LocalDate begin) {
        this.begin = begin;
    }

    public LocalDate getEnd() {
        return this.end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPostcode() {
        return this.postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getWebsite() {
        return this.website;
    }

    public void setWebsite(String postcode) {
        this.website = postcode;
    }

}
