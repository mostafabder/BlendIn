package com.example.android.blendin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import mehdi.sakout.fancybuttons.FancyButton;

public class SignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        FancyButton fancyButton=(FancyButton)findViewById(R.id.btn_detail);
        fancyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignInActivity.this, Navigation_activity.class);
                startActivity(intent);
            }
        });
    }
//    public void openNav(View view){
//        Intent intent = new Intent(this, Navigation_activity.class);
//        startActivity(intent);
//    }
}
