package com.example.android.blendin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ThemedSpinnerAdapter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.blendin.Responses.LoginResponse;
import com.example.android.blendin.Retrofit.ApiClient;
import com.example.android.blendin.Retrofit.ApiInterface;
import com.example.android.blendin.Utility.AuthUser;
import com.example.android.blendin.Utility.CommonMethods;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

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
    CallbackManager FBCallbackManager;
    LoginResponse loginResponse;
    private ProgressDialog progressDialog;
    private String fbID, fbEmail;
    private String fname, lname, gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        ButterKnife.bind(this);
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
                                String json = new Gson().toJson(response.body());
                                storeDataToSharedPref(SignInActivity.this, json, KEY_USER_DATA);
                                AuthUser authUser = AuthUser.getAuthUser(response.body());
                                Log.e("kappa", response.body().getToken());
                                Log.e("kappa1", response.body().getSecret());

                                Intent intent = new Intent(SignInActivity.this, Navigation_activity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
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
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginWithFacebook();
                LoginManager.getInstance().logInWithReadPermissions(SignInActivity.this, Arrays.asList("email", "public_profile"));
            }
        });
        initFacebookLogin();
    }

    public void backbtn(View view) {
        this.finish();
    }

    void initFacebookLogin() {
        FacebookSdk.sdkInitialize(this);
        AppEventsLogger.activateApp(this);
        loginWithFacebook();
    }

    private void loginWithFacebook() {
        FBCallbackManager = CallbackManager.Factory.create();
//        Log.e("LOGIN", "withfacebook");
        LoginManager.getInstance().registerCallback(FBCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                AccessToken accessToken = AccessToken.getCurrentAccessToken();
                String token = accessToken.getToken();
                int expire = (int) (accessToken.getExpires().getTime() / 1000);
//                Log.e("LOGIN", "Token here");
//
//                Log.e("LOGIN", "RequestBundel");
                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        Log.e("LOGIN", response.toString());
                        // Get facebook data from login
                        Bundle bFacebookData = getFacebookData(object);
                        extractFaceBookUserData(bFacebookData);
                    }
                });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id, first_name, last_name, email,gender, birthday, location");
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onCancel() {
                Log.e("LOGIN", "Cancel");

                Toast.makeText(getApplicationContext(), "تم الغاء تسجيل الدخول!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException exception) {
                Log.e("LOGIN", "Error");

                Toast.makeText(getApplicationContext(), "خطا فى تسجيل الدخول! " + exception.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        
    }

    private Bundle getFacebookData(JSONObject object) {
//        Log.e("LOGIN", "First GetFacebook");

        Bundle bundle = new Bundle();
        try {
            String id = object.getString("id");

            try {
                URL profile_pic = new URL("https://graph.facebook.com/" + id + "/picture?width=300&height=200");
                Log.i("profile_pic", profile_pic + "");
                bundle.putString("profile_pic", profile_pic.toString());
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return null;
            }
            if (id != null)
                bundle.putString("idFacebook", id);
            if (object.has("first_name"))
                bundle.putString("first_name", object.getString("first_name"));
            if (object.has("last_name"))
                bundle.putString("last_name", object.getString("last_name"));
            if (object.has("email"))
                bundle.putString("email", object.getString("email"));
            if (object.has("gender"))
                bundle.putString("gender", object.getString("gender"));

            return bundle;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bundle;
    }

    private void extractFaceBookUserData(Bundle bFacebookData) {
        Log.e("LOGIN", "First GetFacebook");

        fname = bFacebookData.getString("last_name");
        lname = bFacebookData.getString("last_name");
        fbEmail = bFacebookData.getString("email");
        gender = bFacebookData.getString("gender");
        fbID = bFacebookData.getString("idFacebook");
        loginWithFacebookAPI();
    }

    void loginWithFacebookAPI() {
        progressDialog = ProgressDialog.show(SignInActivity.this, null, "Loading");
        progressDialog.setCancelable(false);

        Log.e("loginWith", fbID + " " + fbEmail);

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<LoginResponse> call = apiService.loginFb(fbID, "1", fname, lname, fbEmail, gender);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                progressDialog.dismiss();
                Log.e("loginWithFacebookAPI", new Gson().toJson(response.body()));
                loginResponse = response.body();
                if (loginResponse != null) {
                    if (FLAG_SUCCESS.matches(loginResponse.getStatus())) {
                        saveAndTransformScreen();
                        startActivity(new Intent(SignInActivity.this, Navigation_activity.class));
                        finish();
                    } else {
                        Toast.makeText(SignInActivity.this
                                , "error", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(SignInActivity.this
                            , "error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(SignInActivity.this
                        , "error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        FBCallbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    void saveAndTransformScreen() {
        AuthUser.getAuthUser(loginResponse);
        CommonMethods.storeDataToSharedPref(SignInActivity.this,
                new Gson().toJson(loginResponse),
                KEY_USER_DATA);
    }


    public boolean valid() {
        /*String e = email.getContent().toString();
        String p = password.getContent().toString();
        if(e.contains("@") && e.contains(".") && p.length() > 9)
            return true;*/
        return true;
    }
//    public void openNav(View view){
//        Intent intent = new Intent(this, Navigation_activity.class);
//        startActivity(intent);
//    }
}
