package com.example.android.blendin.Models;

/**
 * Created by LeGenÐ on 12/13/2017.
 */

public class HangoutProfileMemberModel {
    //Todo : user object or profile url :: waiting for logic .-.
    int image;
    String name;

    public HangoutProfileMemberModel(int image, String name) {
        this.image = image;
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
