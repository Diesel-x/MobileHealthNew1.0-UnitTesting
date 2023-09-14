package com.novikov.mobilehealth.domain.models;

import java.time.LocalDate;

public class PFCModel {

    private int protein;
    private int fats;
    private int carbs;
    private String date;

    public PFCModel(int protein, int fats, int carbs, String date) {
        this.protein = protein;
        this.fats = fats;
        this.carbs = carbs;
        this.date = date;
    }

    public int getProtein() {
        return protein;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public int getFats() {
        return fats;
    }

    public void setFats(int fats) {
        this.fats = fats;
    }

    public int getCarbs() {
        return carbs;
    }

    public void setCarbs(int carbs) {
        this.carbs = carbs;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
