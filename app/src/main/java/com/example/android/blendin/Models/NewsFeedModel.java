package com.example.android.blendin.Models;

/**
 * Created by Luffy on 11/28/2017.
 */

public class NewsFeedModel {


    String avatar;
    String name;
    String location;
    String created_at;
    String hangout_pic;
    String activity;
    String content;
    String loves;
    String comments;
    boolean isLovedByThisUser;
    String uuid;
    String first_name;
    String last_name;
    String id;
    String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public boolean isLovedByThisUser() {
        return isLovedByThisUser;
    }

    public void setLovedByThisUser(boolean lovedByThisUser) {
        this.isLovedByThisUser = lovedByThisUser;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return first_name + " " + last_name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getHangout_pic() {
        return hangout_pic;
    }

    public void setHangout_pic(String hangout_pic) {
        this.hangout_pic = hangout_pic;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLoves() {
        return loves;
    }

    public void setLoves(String loves) {
        this.loves = loves;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

}
