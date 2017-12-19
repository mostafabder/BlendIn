package com.example.android.blendin.Responses;

import com.example.android.blendin.Models.Interests;
import com.example.android.blendin.Models.User;

import java.util.List;

public class ViewProfileResponse {
    String status;
    User user;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
