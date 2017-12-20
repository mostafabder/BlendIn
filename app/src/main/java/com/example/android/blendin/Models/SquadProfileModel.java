package com.example.android.blendin.Models;

import java.util.List;

/**
 * Created by LeGenÐ on 12/18/2017.
 */

public class SquadProfileModel {
    private String title;
    private String description;
    private String squad_pic;
    private String admin_uuid;
    private String admin_pic;
    private String created_at;
    private String squad_count;
    private String hangouts_count;
    private String admin_first_name;
    private String admin_last_name;
    private List<CommentModel> chat;

    public List<CommentModel> getChat() {
        return chat;
    }

    public void setChat(List<CommentModel> chat) {
        this.chat = chat;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSquad_pic() {
        return squad_pic;
    }

    public void setSquad_pic(String squad_pic) {
        this.squad_pic = squad_pic;
    }

    public String getAdmin_uuid() {
        return admin_uuid;
    }

    public void setAdmin_uuid(String admin_uuid) {
        this.admin_uuid = admin_uuid;
    }

    public String getAdmin_pic() {
        return admin_pic;
    }

    public void setAdmin_pic(String admin_pic) {
        this.admin_pic = admin_pic;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getSquad_count() {
        return squad_count;
    }

    public void setSquad_count(String squad_count) {
        this.squad_count = squad_count;
    }

    public String getHangouts_count() {
        return hangouts_count;
    }

    public void setHangouts_count(String hangouts_count) {
        this.hangouts_count = hangouts_count;
    }

    public String getAdmin_first_name() {
        return admin_first_name;
    }

    public void setAdmin_first_name(String admin_first_name) {
        this.admin_first_name = admin_first_name;
    }

    public String getAdmin_last_name() {
        return admin_last_name;
    }

    public void setAdmin_last_name(String admin_last_name) {
        this.admin_last_name = admin_last_name;
    }
}
