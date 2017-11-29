package com.example.android.blendin.Models;

/**
 * Created by Luffy on 11/29/2017.
 */

public class RequestModel {
    private String avatar;
    private String name;
    private String timeAgo;
    private String Location;
    private String description;
    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTimeAgo() {
        return timeAgo;
    }

    public void setTimeAgo(String timeAgol) {
        this.timeAgo = timeAgol;
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


}
