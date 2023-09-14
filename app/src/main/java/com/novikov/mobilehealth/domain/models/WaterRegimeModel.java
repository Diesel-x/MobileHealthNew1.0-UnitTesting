package com.novikov.mobilehealth.domain.models;

public class WaterRegimeModel {

    private int waterCurrentCount;
    private String date;

    private int goalRow;

    public WaterRegimeModel(int waterCurrentCount, int goalRow, String date) {
        this.waterCurrentCount = waterCurrentCount;
        this.goalRow = goalRow;
        this.date = date;
    }

    public int getWaterCurrentCount() {
        return waterCurrentCount;
    }

    public void setWaterCurrentCount(int waterCurrentCount) {
        this.waterCurrentCount = waterCurrentCount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getGoalRow() {
        return goalRow;
    }

    public void setGoalRow(int goalRow) {
        this.goalRow = goalRow;
    }
}
