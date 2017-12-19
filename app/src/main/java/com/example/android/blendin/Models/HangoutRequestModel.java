package com.example.android.blendin.Models;

/**
 * Created by LeGenÐ on 12/19/2017.
 */

public class HangoutRequestModel {

    private String uuid;
    private String first_name;
    private String last_name;
    private String pic;
    private String title;
    private String hangout_id;
    private String created_at;
    private String location;
    private String invite_id;

    public String getInvite_id() {
        return invite_id;
    }

    public void setInvite_id(String invite_id) {
        this.invite_id = invite_id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHangout_id() {
        return hangout_id;
    }

    public void setHangout_id(String hangout_id) {
        this.hangout_id = hangout_id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
