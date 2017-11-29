package com.example.android.blendin.Models;

/**
 * Created by Luffy on 11/28/2017.
 */

public class MySquadModel {
   private int avatar;
   private String name;
   private String count;

    public MySquadModel(int avatar, String name, String count) {
        this.avatar = avatar;
        this.name = name;
        this.count = count;
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

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

}
