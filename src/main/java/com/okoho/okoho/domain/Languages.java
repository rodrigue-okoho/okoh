package com.okoho.okoho.domain;

import com.okoho.okoho.service.dto.LanguageDto;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "languages")
@TypeAlias("languages")
public class Languages {

    @Id
    private String id;

    @Field("orther_lang")
    private String ortherLanguage;

    @Field("monther_lang")
    private String montherLanguage;

    @Field("reading_comprehension_1")
    private String readingComprehension1;

    @Field("reading_comprehension_2")
    private String readingComprehension2;

    @Field("oral_integration")
    private String oralIntegration;

    @Field("continuous_speaking")
    private String continuousSpeaking;

    @Field("written")
    private String written;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public static Languages build(Languages src , LanguageDto dto) {
        src.setWritten(dto.getWritten());
        src.setOrtherLanguage(dto.getOrtherLanguage());
        src.setMontherLanguage(dto.getMontherLanguage());
        src.setOralIntegration(dto.getOralIntegration());
        src.setContinuousSpeaking(dto.getContinuousSpeaking());
        src.setReadingComprehension1(dto.getReadingComprehension1());
        src.setReadingComprehension2(dto.getReadingComprehension2());
        return src;
    }

}
