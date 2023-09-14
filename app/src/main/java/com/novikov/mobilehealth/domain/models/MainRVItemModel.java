package com.novikov.mobilehealth.domain.models;

public class MainRVItemModel {

    private int imgRes;
    private String name;
    private int colorRes;

    public MainRVItemModel(int imgRes, String name, int colorRes) {
        this.imgRes = imgRes;
        this.name = name;
        this.colorRes = colorRes;
    }

    public int getImgRes() {
        return imgRes;
    }

    public void setImgRes(int imgRes) {
        this.imgRes = imgRes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getColorRes() {
        return colorRes;
    }

    public void setColorRes(int colorRes) {
        this.colorRes = colorRes;
    }
}
