package com.example.ammar.materialme;

/**
 * Created by Ammar on 8/28/2017.
 */

public class Sport {
    private String title;
    private String info;
    private String description;
    private int imageResource;

    public Sport(String title, String info, int imageResource, String description) {
        this.title = title;
        this.info = info;
        this.imageResource = imageResource;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getInfo() {
        return info;
    }

    public int getImageResource() {
        return imageResource;
    }

    public String getDescription() {
        return description;
    }
}
