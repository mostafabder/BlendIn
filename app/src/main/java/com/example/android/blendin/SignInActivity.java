package com.example.android.blendin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import static com.example.android.blendin.Utility.Constants.FLAG_SUCCESS;

public class SignInActivity extends AppCompatActivity {
    @BindView(R.id.signin_email_input)
    TextView email;

    @BindView(R.id.signin_password_input)
    TextView password;

    @BindView(R.id.signin_fb)
    LinearLayout fb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        ButterKnife.bind(this);
        Button fancyButton = (Button) findViewById(R.id.btn_signin);
        fancyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(SignInActivity.this, Navigation_activity.class);
                startActivity(intent);
                finish();
            }
        });

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
    }

    public void backbtn(View view) {
        this.finish();
    }
//    public void openNav(View view){
//        Intent intent = new Intent(this, Navigation_activity.class);
//        startActivity(intent);
//    }
}
