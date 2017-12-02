package com.example.android.blendin.Models;

/**
 * Created by Luffy on 12/1/2017.
 */

public class ProfileModel {
    String topText;
    String likesCount;
    String commentsCount;
    int img;



    public String getTopText() {
        return topText;
    }

    public ProfileModel(String topText, String likesCount, String commentsCount, int img) {
        this.topText = topText;
        this.likesCount = likesCount;
        this.commentsCount = commentsCount;
        this.img = img;
    }

    public String getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(String likesCount) {
        this.likesCount = likesCount;
    }

    public String getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(String commentsCount) {
        this.commentsCount = commentsCount;
    }

    public void setTopText(String topText) {
        this.topText = topText;
    }


    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }





}
