package com.example.android.blendin.Responses;

import com.example.android.blendin.Models.NotificationModell;

import java.util.List;

/**
 * Created by Luffy on 12/20/2017.
 */

public class NotificationResponse {
    String status;
    List<NotificationModell> notifications;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<NotificationModell> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<NotificationModell> notifications) {
        this.notifications = notifications;
    }
}
