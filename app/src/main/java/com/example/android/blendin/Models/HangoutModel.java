package com.example.android.blendin.Models;

/**
 * Created by Luffy on 12/13/2017.
 */

public class HangoutModel {
    String pic;
    String name;
    String uuid;
    String first_name;
    String last_name;

    public HangoutModel(String avatar, String name, String uuid) {
        this.pic = avatar;
        this.name = name;
        this.uuid = uuid;
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

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
