package com.example.android.blendin.Models;

import java.util.List;

/**
 * Created by Luffy on 12/18/2017.
 */

public class nearbyUsers {
    String uuid;
    String token;
    String secret;
    String status;
    String lat;
    String lng;
    String adress;
    String mobile;
    String created_at;
    String updated_at;
    String distance;
    NearbyUsersInfo info;
    List<Interests> interests;
    List<PostsNearby> posts;

    public NearbyUsersInfo getInfo() {
        return info;
    }

    public void setInfo(NearbyUsersInfo info) {
        this.info = info;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public List<Interests> getInterests() {
        return interests;
    }

    public void setInterests(List<Interests> interests) {
        this.interests = interests;
    }

    public List<PostsNearby> getPosts() {
        return posts;
    }

    public void setPosts(List<PostsNearby> posts) {
        this.posts = posts;
    }
}
