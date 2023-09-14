package com.novikov.mobilehealth.domain.models;

public class WorkoutModel {

    private String title;
    private int imgRes;
    private String description;

    public WorkoutModel(String title, int imgRes, String description) {
        this.title = title;
        this.imgRes = imgRes;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImgRes() {
        return imgRes;
    }

    public void setImgRes(int imgRes) {
        this.imgRes = imgRes;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
