package com.okoho.okoho.service.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

public class LanguageDto {
    private String id;
    private String owner_id;
    private String ortherLanguage;
    private String montherLanguage;
    private String readingComprehension1;
    private String readingComprehension2;
    private String oralIntegration;
    private String continuousSpeaking;
    private String written;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(String owner_id) {
        this.owner_id = owner_id;
    }

    public String getOrtherLanguage() {
        return ortherLanguage;
    }

    public void setOrtherLanguage(String ortherLanguage) {
        this.ortherLanguage = ortherLanguage;
    }

    public String getMontherLanguage() {
        return montherLanguage;
    }

    public void setMontherLanguage(String montherLanguage) {
        this.montherLanguage = montherLanguage;
    }

    public String getReadingComprehension1() {
        return readingComprehension1;
    }

    public void setReadingComprehension1(String readingComprehension1) {
        this.readingComprehension1 = readingComprehension1;
    }

    public String getReadingComprehension2() {
        return readingComprehension2;
    }

    public void setReadingComprehension2(String readingComprehension2) {
        this.readingComprehension2 = readingComprehension2;
    }

    public String getOralIntegration() {
        return oralIntegration;
    }

    public void setOralIntegration(String oralIntegration) {
        this.oralIntegration = oralIntegration;
    }

    public String getContinuousSpeaking() {
        return continuousSpeaking;
    }

    public void setContinuousSpeaking(String continuousSpeaking) {
        this.continuousSpeaking = continuousSpeaking;
    }

    public String getWritten() {
        return written;
    }

    public void setWritten(String written) {
        this.written = written;
    }
}
