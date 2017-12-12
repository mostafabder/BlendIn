package com.example.android.blendin.Models;

/**
 * Created by LeGenÐ on 12/10/2017.
 */

public class SquadChatMessageModel {

    //TODO :: userName object memeber :: waiting for logic .-.

    int image;
    String userName;
    String text;
    String date;

    public SquadChatMessageModel(int image, String userName, String text, String date) {
        this.image = image;
        this.userName = userName;
        this.text = text;
        this.date = date;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
}