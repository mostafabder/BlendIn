package com.example.android.blendin;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

import gr.net.maroulis.library.EasySplashScreen;

public class SplachActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EasySplashScreen config = new EasySplashScreen(SplachActivity.this)
                .withFullScreen()
                .withTargetActivity(IntroActivity.class)
                .withSplashTimeOut(4000)
                .withBackgroundResource(R.color.dark_blue)
                .withLogo(R.drawable.logo);

        //customize all TextViews


        //create the view
        View easySplashScreenView = config.create();

        setContentView(easySplashScreenView);
    }

}
