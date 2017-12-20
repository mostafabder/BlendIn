package com.example.android.blendin.Responses;

import com.example.android.blendin.Models.NewsFeedModel;
import com.example.android.blendin.Models.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LeGenÐ on 12/17/2017.
 */

public class ProfileResponse {

    private String status;
    private User user;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

   /* public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }*/
}
