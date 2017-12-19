package com.example.android.blendin.Models;

/**
 * Created by Luffy on 11/29/2017.
 */

public class RequestModel {
    private int avatar;
    private String name;
    private String created_at;
    private String Location;
    private String description;
    private String title;

    public RequestModel(int avatar, String name, String timeAgo, String location, String description) {
        this.avatar = avatar;
        this.name = name;
        this.created_at = timeAgo;
        Location = location;
        this.description = description;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String timeAgol) {
        this.created_at = timeAgol;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
