package com.example.android.blendin.Models;

/**
 * Created by Luffy on 12/20/2017.
 */

public class MyHangoutsModel {
    String created_at;
    String title;
    String hangout_pic;
    String hangout_id;
    String location;
    String activity;

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHangout_pic() {
        return hangout_pic;
    }

    public void setHangout_pic(String hangout_pic) {
        this.hangout_pic = hangout_pic;
    }

    public String getHangout_id() {
        return hangout_id;
    }

    public void setHangout_id(String hangout_id) {
        this.hangout_id = hangout_id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
