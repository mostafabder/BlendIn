package com.example.android.blendin.Models;

/**
 * Created by Luffy on 11/28/2017.
 */

public class MySquadModel {

    private String id;
    private String title;
    private String pic;
    private String members;

    public String getMembers() {
        return members;
    }

    public void setMembers(String members) {
        this.members = members;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

}
