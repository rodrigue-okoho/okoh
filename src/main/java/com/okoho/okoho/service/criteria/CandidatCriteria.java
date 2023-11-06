package com.okoho.okoho.service.criteria;

public class CandidatCriteria {
    private String experience;

    public CandidatCriteria() {
    }

    public CandidatCriteria(String experience) {
        this.experience = experience;
    }

    public CandidatCriteria(CandidatCriteria other) {
        this.experience = other.experience == null ? null : other.experience;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }
}
