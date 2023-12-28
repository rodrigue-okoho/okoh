package com.okoho.okoho.service.dto;

import org.springframework.data.mongodb.core.mapping.Field;

public class ItemCandidatDTO {
 private String id;


    private String libelle;

    private String itemType;

    private String location;

    private String begin;

    private String end;

    private String description;
    private String city;
    private String country;
    private String employer_name;
    private String traning_body;
    private String line2;
    private String line1;
    private String owner_id;
    private String postcode;
    private String website;

    public String getId() {
        return this.id;
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

    public void setId(String id) {
        this.id = id;
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

    public String getBegin() {
        return this.begin;
    }

    public void setBegin(String begin) {
        this.begin = begin;
    }

    public String getEnd() {
        return this.end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOwner_id() {
        return owner_id;
    }
    public void setOwner_id(String owner_id) {
        this.owner_id = owner_id;
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
