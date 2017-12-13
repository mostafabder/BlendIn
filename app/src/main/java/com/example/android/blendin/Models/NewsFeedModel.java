package com.example.android.blendin.Models;

/**
 * Created by Luffy on 11/28/2017.
 */

public class NewsFeedModel {

    int avatar;
    String name;
    String city;
    String timeAgo;
    int image;
    String activity;
    String disc;
    String likes;
    String comments;
    boolean like;

    public NewsFeedModel(int avatar, String name, String city, String timeAgo, int image, String activity, String disc, String likes, String comments, boolean like) {
        this.avatar = avatar;
        this.name = name;
        this.city = city;
        this.timeAgo = timeAgo;
        this.image = image;
        this.activity = activity;
        this.disc = disc;
        this.likes = likes;
        this.comments = comments;
        this.like = like;
    }

    public boolean isLike() {
        return like;
    }

    public void setLike(boolean like) {
        this.like = like;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTimeAgo() {
        return timeAgo;
    }

    public void setTimeAgo(String timeAgo) {
        this.timeAgo = timeAgo;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getDisc() {
        return disc;
    }

    public void setDisc(String disc) {
        this.disc = disc;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

}
