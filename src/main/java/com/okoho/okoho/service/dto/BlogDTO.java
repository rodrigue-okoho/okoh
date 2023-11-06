package com.okoho.okoho.service.dto;

import com.okoho.okoho.domain.FileUrl;
import org.springframework.data.mongodb.core.mapping.Field;

public class BlogDTO {
    private String image;
    private String title;
    private String description;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
