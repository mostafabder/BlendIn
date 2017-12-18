package com.example.android.blendin.Models;

/**
 * Created by Luffy on 12/13/2017.
 */

public class HangoutModel {
    String avatar;
    String name;

    public HangoutModel(String avatar, String name) {
        this.avatar = avatar;
        this.name = name;
    }

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
}
