package com.example.android.blendin.Models;

/**
 * Created by Luffy on 12/20/2017.
 */

public class NotificationModell {
    String status;
    String message;
    String created_at;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
