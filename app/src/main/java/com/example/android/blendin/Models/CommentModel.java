package com.example.android.blendin.Models;

/**
 * Created by LeGenÐ on 12/9/2017.
 */

public class CommentModel {
    // TODO :: it should have a userName object as a member to get his pic and link to his profile

    String name;
    String message;
    String created_at;
    String pic;
    String first_name;
    String last_name;
    String uuid;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
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

    public String getName() {
        return first_name + " " + last_name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return message;
    }

    public void setContent(String content) {
        this.message = content;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}
