package com.novikov.mobilehealth.domain.models;

public class ProfileInfoModel {

    private int height;
    private int weight;
    private Long birthDate;

    public ProfileInfoModel(int height, int weight, Long birthDate) {
        this.height = height;
        this.weight = weight;
        this.birthDate = birthDate;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Long getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Long birthDate) {
        this.birthDate = birthDate;
    }
}
