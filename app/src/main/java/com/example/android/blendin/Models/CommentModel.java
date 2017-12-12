package com.example.android.blendin.Models;

/**
 * Created by LeGenÐ on 12/9/2017.
 */

public class CommentModel {
    // TODO :: it should have a userName object as a member to get his pic and link to his profile
    String name;
    String text;
    String date;
    int userImage;

    public CommentModel(String name, String text, String date, int userImage) {
        this.name = name;
        this.text = text;
        this.date = date;
        this.userImage = userImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getUserImage() {
        return userImage;
    }

    public void setUserImage(int userImage) {
        this.userImage = userImage;
    }
}
