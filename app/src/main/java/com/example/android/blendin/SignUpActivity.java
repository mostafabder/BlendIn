package com.example.android.blendin;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.blendin.Responses.LoginResponse;
import com.example.android.blendin.Responses.SignUpResponse;
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
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStates;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.gson.Gson;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.android.blendin.Utility.Constants.FLAG_SUCCESS;
import static com.example.android.blendin.Utility.Constants.KEY_USER_DATA;

public class SignUpActivity extends AppCompatActivity {

    @BindView(R.id.signup_email_input)
    TextView email;
    @BindView(R.id.signup_password_input)
    TextView password;
    @BindView(R.id.signup_confirmpassword_input)
    TextView cpassword;
    @BindView(R.id.signup_firstname_input)
    TextView firstname;
    @BindView(R.id.signup_lastname_input)
    TextView lastname;
    @BindView(R.id.signup_female_radio_input)
    RadioButton female;
    @BindView(R.id.signup_male_radio_input)
    RadioButton male;
    @BindView(R.id.signup_fb)
    LinearLayout fb;
    @BindView(R.id.signup_tw)
    LinearLayout tw;

    @BindView(R.id.signup_submit)
    Button submit;
    boolean isPermissionGranted;
    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;
    LocationRequest mLocationRequest;
    CallbackManager FBCallbackManager;
    LoginResponse loginResponse;
    private String fbID, fbEmail;
    private String fname, lname, gender;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);

        //   initPermissionCheck();
//        mGoogleApiClient = new GoogleApiClient.Builder(this)
//                .addConnectionCallbacks(this)
//                .addOnConnectionFailedListener(this)
//                .addApi(LocationServices.API)
//                .build();
//
//        if (mGoogleApiClient != null) {
//            mGoogleApiClient.connect();
//        } else
//            Toast.makeText(this, "Not Connected!", Toast.LENGTH_SHORT).show();


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (valid()) {
                    if (female.isChecked())
                        gender = "1";
                    else
                        gender = "0";
                    ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
                    Call<SignUpResponse> call = null;
                        call = apiInterface.signupRegular(
                                email.getText().toString(),
                                password.getText().toString(),
                                "0",
                                firstname.getText().toString(),
                                lastname.getText().toString(),
                                gender
                        );
                    call.enqueue(new Callback<SignUpResponse>() {
                        @Override
                        public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                            if (response != null && response.body().getStatus().equals(FLAG_SUCCESS)) {
                                Intent intent = new Intent(SignUpActivity.this, Navigation_activity.class);
                                startActivity(intent);
                                finish();
                            }
                        }

                        @Override
                        public void onFailure(Call<SignUpResponse> call, Throwable t) {
                            Toast.makeText(SignUpActivity.this, "something went wrong", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Toast.makeText(SignUpActivity.this, "Input Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
        initFacebookLogin();
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginWithFacebook();
                LoginManager.getInstance().logInWithReadPermissions(SignUpActivity.this, Arrays.asList("email", "public_profile"));
            }
        });
    }

    public void backbtn(View view) {
        this.finish();
    }

    public boolean valid() {
        return true;
    }

    //    void initPermissionCheck() {
//
//        PermissionListener permissionlistener = new PermissionListener() {
//            @Override
//            public void onPermissionGranted() {
//                isPermissionGranted = true;
//            }
//
//            @Override
//            public void onPermissionDenied(ArrayList<String> deniedPermissions) {
//                isPermissionGranted = false;
//            }
//        };
//        new TedPermission(this)
//                .setPermissionListener(permissionlistener)
//                .setDeniedMessage("If you reject permission,you can not use this service")
//                .setPermissions(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
//                .check();
//    }
//
//    @Override
//    public void onConnected(@Nullable Bundle bundle) {
//        settingRequest();
//    }
//
//    @Override
//    public void onConnectionSuspended(int i) {
//
//    }
//
//    @Override
//    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
//
//    }
//
//    @Override
//    public void onLocationChanged(Location location) {
//        if (location != null) {
//            lat = String.valueOf(location.getLatitude());
//            lng = String.valueOf(location.getLongitude());
//            Toast.makeText(this, lat + " " + lng, Toast.LENGTH_SHORT).show();
//            mLastLocation = location;
//        }
//    }
//
//    public void settingRequest() {
//        mLocationRequest = new LocationRequest();
//        mLocationRequest.setInterval(10000);    // 10 seconds, in milliseconds
//        mLocationRequest.setFastestInterval(1000);   // 1 second, in milliseconds
//        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
//
//        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
//                .addLocationRequest(mLocationRequest);
//
//        PendingResult<LocationSettingsResult> result =
//                LocationServices.SettingsApi.checkLocationSettings(mGoogleApiClient,
//                        builder.build());
//
//        result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
//
//            @Override
//            public void onResult(@NonNull LocationSettingsResult result) {
//                final Status status = result.getStatus();
//                final LocationSettingsStates state = result.getLocationSettingsStates();
//                switch (status.getStatusCode()) {
//                    case LocationSettingsStatusCodes.SUCCESS:
//                        // All location settings are satisfied. The client can
//                        // initialize location requests here.
//                        try {
//                            getLocation();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                        break;
//                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
//                        // Location settings are not satisfied, but this can be fixed
//                        // by showing the user a dialog.
//                        try {
//                            // Show the dialog by calling startResolutionForResult(),
//                            // and check the result in onActivityResult().
//                            status.startResolutionForResult(SignUpActivity.this, 1000);
//                        } catch (IntentSender.SendIntentException e) {
//                            // Ignore the error.
//                        }
//                        break;
//                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
//                        // Location settings are not satisfied. However, we have no way
//                        // to fix the settings so we won't show the dialog.
//                        break;
//                }
//            }
//
//        });
//    }
//
//    public void getLocation() throws IOException {
//        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return;
//        } else {
//            /*Getting the location after aquiring location service*/
//            mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
//                    mGoogleApiClient);
//
//            if (mLastLocation != null) {
//
//                lat = String.valueOf(mLastLocation.getLatitude());
//                lng = String.valueOf(mLastLocation.getLongitude());
//
//                //Toast.makeText(this, lat + " " + lng + " " + getAddress(Double.parseDouble(lat),Double.parseDouble(lng)), Toast.LENGTH_SHORT).show();
//            } else {
//                /*if there is no last known location. Which means the device has no data for the loction currently.
//                * So we will get the current location.
//                * For this we'll implement Location Listener and override onLocationChanged*/
//                Log.i("Current Location", "No data for location found");
//
//                if (!mGoogleApiClient.isConnected())
//                    mGoogleApiClient.connect();
//
//                LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, SignUpActivity.this);
//            }
//        }
//    }
//
//
//
//    public String getAddress(double latitude, double longitude) throws IOException {
//        Geocoder geocoder;
//        List<Address> addresses;
//        geocoder = new Geocoder(this, Locale.getDefault());
//        addresses = geocoder.getFromLocation(latitude, longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
//        return addresses.get(0).getAddressLine(0);
//    }
//fb
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        FBCallbackManager.onActivityResult(requestCode, resultCode, data);
        final LocationSettingsStates states = LocationSettingsStates.fromIntent(data);
        switch (requestCode) {
            case 1000:
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        // All required changes were successfully made
//                        try {
//                           // getLocation();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
                        break;
                    case Activity.RESULT_CANCELED:
                        // The user was asked to change settings, but chose not to
                        Toast.makeText(this, "Location Service not Enabled", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
                break;
        }
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
        progressDialog = ProgressDialog.show(SignUpActivity.this, null, "Loading");
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
                        startActivity(new Intent(SignUpActivity.this, Navigation_activity.class));
                        finish();
                    } else {
                        Toast.makeText(SignUpActivity.this
                                , "error", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(SignUpActivity.this
                            , "error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(SignUpActivity.this
                        , "error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //save
    void saveAndTransformScreen() {
        AuthUser.getAuthUser(loginResponse);
        CommonMethods.storeDataToSharedPref(SignUpActivity.this,
                new Gson().toJson(loginResponse),
                KEY_USER_DATA);

    }

}
