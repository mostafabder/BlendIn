package com.example.android.blendin.Models;

/**
 * Created by Luffy on 12/1/2017.
 */

public class ProfileModel {
    String topText;
    String likesCount;
    String commentsCount;
    int img;
    boolean like;

    public ProfileModel(String topText, String likesCount, String commentsCount, int img, boolean like) {
        this.topText = topText;
        this.likesCount = likesCount;
        this.commentsCount = commentsCount;
        this.img = img;
        this.like = like;
    }

    public boolean isLike() {
        return like;
    }

    public void setLike(boolean like) {
        this.like = like;
    }

    public String getTopText() {
        return topText;
    }

    public void setTopText(String topText) {
        this.topText = topText;
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

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }





}
