package com.example.android.blendin.Responses;

/**
 * Created by Luffy on 12/15/2017.
 */

public class SignUpResponse {

    private String status;
    private String token;
    private String secret;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getToken() {
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
    }
}
