package com.example.android.blendin.Models;

/**
 * Created by LeGenÐ on 12/15/2017.
 */

public class PreviousHangoutModel {
    int image;
    String title;
    String location;
    String date;

    public PreviousHangoutModel(int image, String title, String location, String date) {
        this.image = image;
        this.title = title;
        this.location = location;
        this.date = date;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
