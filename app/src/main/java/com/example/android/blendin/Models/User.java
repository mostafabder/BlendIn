package com.example.android.blendin.Models;

import java.util.ArrayList;

/**
 * Created by LeGenÐ on 12/17/2017.
 */

public class User {
    private String uuid;
    private String email;
    private String gender;
    private String pic;
    private String first_name;
    private String last_name;
    private String address;
    private String mobile;
    private String[] interests;
    private ArrayList<NewsFeedModel> posts;
    private String lat;
    private String lng;
    private String name;
    private String gender_name;

    public String getGender_name() {
        if (getGender().equals("0"))
            return "male";
        else return "female";
    }

    public void setGender_name(String gender_name) {
        this.gender_name = gender_name;
    }

    public String getName() {
        return getFirst_name() + " " + getLast_name();
    }

    public void setName(String name) {
        this.name = first_name + " " + last_name;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String[] getInterests() {
        return interests;
    }

    public void setInterests(String[] interests) {
        this.interests = interests;
    }

    public ArrayList<NewsFeedModel> getPosts() {
        return posts;
    }

    public void setPosts(ArrayList<NewsFeedModel> posts) {
        this.posts = posts;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }
}
