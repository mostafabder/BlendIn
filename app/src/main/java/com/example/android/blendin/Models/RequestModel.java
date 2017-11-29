package com.example.android.blendin.Models;

/**
 * Created by Luffy on 11/29/2017.
 */

public class RequestModel {
    private int avatar;
    private String name;
    private String timeAgo;
    private String Location;
    private String description;

    public RequestModel(int avatar, String name, String timeAgo, String location, String description) {
        this.avatar = avatar;
        this.name = name;
        this.timeAgo = timeAgo;
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
