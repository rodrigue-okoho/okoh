package com.okoho.okoho.domain;

import com.okoho.okoho.service.dto.BranchDto;
import com.okoho.okoho.service.dto.LanguageDto;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "branch")
@TypeAlias("branch")
public class Branch {

    @Id
    private String id;

    @Field("branch")
    private String branch;

    @Field("experience")
    private String experience;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public static Branch build(Branch src , BranchDto dto) {
        src.setBranch(dto.getBranch());
        src.setExperience(dto.getExperience());
        return src;
    }

}
