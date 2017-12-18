package com.example.android.blendin;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

import com.example.android.blendin.Responses.LoginResponse;
import com.example.android.blendin.Utility.AuthUser;
import com.example.android.blendin.Utility.CommonMethods;
import com.google.gson.Gson;

import gr.net.maroulis.library.EasySplashScreen;

import static com.example.android.blendin.Utility.Constants.KEY_USER_DATA;

public class SplachActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Class nxt;
        String json = CommonMethods.retrieveDataFromSharedPref(SplachActivity.this, KEY_USER_DATA);
        LoginResponse loginResponse = new Gson().fromJson(json, LoginResponse.class);
        if (loginResponse != null && loginResponse.getToken() != null && loginResponse.getSecret() != null) {
            nxt = Navigation_activity.class;
            AuthUser.getAuthUser(loginResponse);
        } else nxt = IntroActivity.class;
        EasySplashScreen config = new EasySplashScreen(SplachActivity.this)
                .withFullScreen()
                .withTargetActivity(nxt)
                .withSplashTimeOut(4000)
                .withBackgroundResource(R.color.dark_blue)
                .withLogo(R.drawable.logo);

        //customize all TextViews


        //create the view
        View easySplashScreenView = config.create();

        setContentView(easySplashScreenView);
    }
}
