package com.novikov.mobilehealth.domain.models;

public class BreathTechnic {
    private String Name;
    private String Content;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public BreathTechnic(String name, String content) {
        Name = name;
        Content = content;
    }
}
