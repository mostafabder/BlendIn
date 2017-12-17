package com.example.android.blendin.Utility;

import com.example.android.blendin.Responses.LoginResponse;

/**
 * Created by Luffy on 12/16/2017.
 */

public class AuthUser extends LoginResponse {

    private static AuthUser authUser;

    private AuthUser() {

    }

    public static AuthUser getAuthUser() {
        if (authUser == null)
            authUser = (AuthUser) new LoginResponse();
        return authUser;
    }

    public static void setAuthUser(LoginResponse loginResponse) {
        authUser = (AuthUser) loginResponse;
    }
}
