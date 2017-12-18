package com.example.android.blendin.Utility;

import com.example.android.blendin.Responses.LoginResponse;

/**
 * Created by Luffy on 12/16/2017.
 */

public class AuthUser extends LoginResponse {

    private static AuthUser authUser;

    private AuthUser() {

    }

    public static AuthUser getAuthUser(LoginResponse loginResponse) {
        if (authUser == null) {
            authUser = new AuthUser();
            authUser.setSecret(loginResponse.getSecret());
            authUser.setToken(loginResponse.getToken());
            authUser.setStatus(loginResponse.getStatus());
        }
        return authUser;
    }

    public static AuthUser getAuthData() {
        return authUser;
    }
}
