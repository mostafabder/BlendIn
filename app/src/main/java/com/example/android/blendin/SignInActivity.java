package com.example.android.blendin;

import android.content.Intent;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.blendin.Responses.LoginResponse;
import com.example.android.blendin.Retrofit.ApiClient;
import com.example.android.blendin.Retrofit.ApiInterface;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.android.blendin.Utility.CommonMethods.*;
import static com.example.android.blendin.Utility.Constants.*;

import com.example.android.blendin.Utility.AuthUser;
import com.google.gson.Gson;

public class SignInActivity extends AppCompatActivity {
    @BindView(R.id.signin_email_input)
    TextView email;

    @BindView(R.id.signin_password_input)
    TextView password;

    @BindView(R.id.signin_fb)
    LinearLayout fb;

    @BindView(R.id.signin_submit)
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        ButterKnife.bind(this);

        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
                Call<LoginResponse> call = apiInterface.loginFb("11", "1");
                call.enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        if (response != null && response.body().getStatus().equals(FLAG_SUCCESS)) {
                            Intent intent = new Intent(SignInActivity.this, Navigation_activity.class);
                            startActivity(intent);
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        Toast.makeText(SignInActivity.this, "something went wrong", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (valid()) {
                    ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
                    Call<LoginResponse> call = apiInterface.loginRegular(
                            email.getText().toString(),
                            password.getText().toString(),
                            "0"
                    );
                    Log.e("kappa3", password.getText().toString());
                    call.enqueue(new Callback<LoginResponse>() {
                        @Override
                        public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                            if (response != null && response.body().getStatus().equals(FLAG_SUCCESS)) {
                                String json = new Gson().toJson(response);
                                storeDataToSharedPref(SignInActivity.this, json, KEY_USER_DATA);
                                AuthUser authUser = AuthUser.getAuthUser(response.body());
                                Intent intent = new Intent(SignInActivity.this, Navigation_activity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                Log.e("kappa", response.body().getStatus());
                                Toast.makeText(SignInActivity.this, "flag error", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<LoginResponse> call, Throwable t) {
                            Toast.makeText(SignInActivity.this, "something went wrong", Toast.LENGTH_SHORT).show();
                        }
                    });

                }

            }
        });
    }

    public void backbtn(View view) {
        this.finish();
    }

    public boolean valid() {
        /*String e = email.getText().toString();
        String p = password.getText().toString();
        if(e.contains("@") && e.contains(".") && p.length() > 9)
            return true;*/
        return true;
    }
//    public void openNav(View view){
//        Intent intent = new Intent(this, Navigation_activity.class);
//        startActivity(intent);
//    }
}
