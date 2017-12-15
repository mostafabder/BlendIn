package com.example.android.blendin.Models;

/**
 * Created by Luffy on 12/15/2017.
 */

public class NotificationModel {
    int avatar;
    String disc;
    String time;

    public NotificationModel(int avatar, String disc, String time) {
        this.avatar = avatar;
        this.disc = disc;
        this.time = time;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public String getDisc() {
        return disc;
    }

    public void setDisc(String disc) {
        this.disc = disc;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
