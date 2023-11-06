package com.okoho.okoho.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
@Document(collection = "blog")
@TypeAlias("blog")
public class Blog {
    @Id
    private String id;
    @Field("title")
    private String title;
    @Field("description")
    private String description;
    @Field("created_at")
    private LocalDate createdAt;
    @DBRef
    @Field("image")
    private FileUrl image;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public FileUrl getImage() {
        return image;
    }

    public void setImage(FileUrl image) {
        this.image = image;
    }
}
