package com.novikov.mobilehealth.domain.models;

import java.time.LocalDate;
import java.time.LocalTime;

public class MedicineModel {

    private String name;
    private String time;
    private int duration;
    private String type;

    private String startDate;

    public MedicineModel(String name, String time, int duration, String type, String startDate) {
        this.name = name;
        this.time = time;
        this.duration = duration;
        this.type = type;
        this.startDate = startDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
}
