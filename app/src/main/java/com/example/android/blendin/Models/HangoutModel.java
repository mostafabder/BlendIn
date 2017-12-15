package com.example.android.blendin.Models;

/**
 * Created by Luffy on 12/13/2017.
 */

public class HangoutModel {
    int avatar;
    String name;

    public HangoutModel(int avatar, String name) {
        this.avatar = avatar;
        this.name = name;
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
}
