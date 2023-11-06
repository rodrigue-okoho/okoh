package com.okoho.okoho.service.dto;

public class StatisticResponse {
    private int allJobs;
    private int allCandidats;
    private int allEmployers;
    private int jobPosted;
    private int application;
    private int shortList;
    private int message;
    private int jobAlert;

    public int getJobPosted() {
        return this.jobPosted;
    }

    public void setJobPosted(int jobPosted) {
        this.jobPosted = jobPosted;
    }

    public int getApplication() {
        return this.application;
    }

    public void setApplication(int application) {
        this.application = application;
    }

    public int getShortList() {
        return this.shortList;
    }

    public void setShortList(int shortList) {
        this.shortList = shortList;
    }

    public int getMessage() {
        return this.message;
    }

    public void setMessage(int message) {
        this.message = message;
    }

    public int getJobAlert() {
        return this.jobAlert;
    }

    public void setJobAlert(int jobAlert) {
        this.jobAlert = jobAlert;
    }

    public int getAllJobs() {
        return allJobs;
    }

    public void setAllJobs(int allJobs) {
        this.allJobs = allJobs;
    }

    public int getAllCandidats() {
        return allCandidats;
    }

    public void setAllCandidats(int allCandidats) {
        this.allCandidats = allCandidats;
    }

    public int getAllEmployers() {
        return allEmployers;
    }

    public void setAllEmployers(int allEmployers) {
        this.allEmployers = allEmployers;
    }
}
