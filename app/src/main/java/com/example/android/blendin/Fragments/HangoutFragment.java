package com.example.android.blendin.Fragments;

import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.blendin.Adapters.HangoutAdapter;
import com.example.android.blendin.Models.User;
import com.example.android.blendin.Models.anActivity;
import com.example.android.blendin.Models.nearbyUsers;
import com.example.android.blendin.Responses.ActivitiesResponse;
import com.example.android.blendin.Responses.SearchPeople;
import com.example.android.blendin.Retrofit.ApiClient;
import com.example.android.blendin.Retrofit.ApiInterface;
import com.example.android.blendin.Utility.AuthUser;
import com.example.android.blendin.Utility.Constants;
import com.example.android.blendin.Models.HangoutModel;
import com.example.android.blendin.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
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
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.gson.Gson;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import mehdi.sakout.fancybuttons.FancyButton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.android.blendin.Utility.Constants.FLAG_SUCCESS;


public class HangoutFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener, GoogleMap.OnInfoWindowClickListener
        , GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {

    static boolean mLocationPermissionGranted, orgCreated;
    private final LatLng HAMBURG = new LatLng(53.558, 9.927);
    private final LatLng KIEL = new LatLng(53.551, 9.993);
    private final int LOCATION_REQUESTED_CODE = 2;
    ProgressDialog progressDialog;
    LatLng org;
    Boolean isPermissionGranted;
    LocationRequest mLocationRequest;
    Location mLastLocation;
    Boolean isBottom = false;
    LinearLayout linear;
    LinearLayout linear2;
    View v;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    List<HangoutModel> HangoutModelList;
    LinearLayoutManager layoutManager;
    Boolean y = true;
    String orgLat, orgLng;
    User now = null;
    List<anActivity> activites = new ArrayList<>();
    ArrayList<String> actStrings = new ArrayList<String>();
    List<User> listNearby = new ArrayList<>();
    @BindView(R.id.act_spinner)
    Spinner act;
    LocationManager mLocationManager;
    private GoogleApiClient mGoogleApiClient;
    private GoogleMap mMap;
    private Map<Marker, String> markerIds = new HashMap<>();
    private int LOCATION_SETTINGS_REQUEST_CODE = 1;
    private FusedLocationProviderClient mFusedLocationClient;

    public static boolean checkForLocationEnabled(Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        boolean isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        return isGPSEnabled;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_hangout, container, false);
        ButterKnife.bind(this, v);
        FancyButton proceed = (FancyButton) v.findViewById(R.id.btn_Proceed);
        FancyButton fancyButton = (FancyButton) v.findViewById(R.id.btn_LetsGo);
        linear = (LinearLayout) v.findViewById(R.id.linear);
        linear2 = (LinearLayout) v.findViewById(R.id.linear2);

        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_hangout);
        HangoutModelList = new ArrayList<>();
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new HangoutAdapter(getActivity(), HangoutModelList, false, linear2);
        recyclerView.setAdapter(adapter);
        fancyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SlideToDown();
                mMap.getUiSettings().setAllGesturesEnabled(true);
                getNearbyPeople();
            }
        });
        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                proceed();
            }
        });

        //setActivities
        getActivities();


        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>
                (getActivity(), android.R.layout.simple_spinner_item,
                        actStrings);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        act.setAdapter(spinnerArrayAdapter);
        initPermissionCheck();
        initMap();
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(getActivity());
        if (mLastLocation == null)
            mLastLocation = getLastKnownLocation();
        tryLocation();
        Constants.isBottom = true;
        return v;
    }

    public void proceed() {
        if (HangoutModelList.size() > 0) {
            Fragment fragment = new HangoutDetailsFragment();
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            Bundle bundle = new Bundle();
            bundle.putString("people", new Gson().toJson(HangoutModelList));
            bundle.putString("activity", "playing");
            fragment.setArguments(bundle);
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.setCustomAnimations(R.anim.slide_in_from_bottom, R.anim.slide_out_to_bottom, R.anim.slide_out_from_bottom, R.anim.slide_in_to_bottom);
            fragmentTransaction.add(R.id.content_main, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        } else
            Toast.makeText(getActivity(), "U should add a memeber", Toast.LENGTH_SHORT).show();
    }

    public void getActivities() {
        progressDialog = ProgressDialog.show(getActivity(), null, "Loading");
        progressDialog.setCancelable(false);
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Log.e("Token", AuthUser.getAuthData().getToken());
        Log.e("Token", AuthUser.getAuthData().getSecret());
        Call<ActivitiesResponse> call = apiService.getActivities(AuthUser.getAuthData().getToken(), AuthUser.getAuthData().getSecret());
        call.enqueue(new Callback<ActivitiesResponse>() {
            @Override
            public void onResponse(Call<ActivitiesResponse> call, Response<ActivitiesResponse> response) {
                progressDialog.cancel();
                if (response.body() != null) {
                    if (response.body().getStatus().equals(FLAG_SUCCESS)) {

                        for (int i = 0; i < response.body().getActivities().size(); i++)
                            activites.add(response.body().getActivities().get(i));
                        Log.e("size", String.valueOf(activites.size()));
                        for (int i = 0; i < activites.size(); i++) {
                            actStrings.add(activites.get(i).getTitle());
                            Log.e("title", activites.get(i).getTitle());
                        }
                        Log.e("ACT", activites.get(0).getTitle());
                    } else
                        Toast.makeText(getActivity(), response.body().getStatus(), Toast.LENGTH_SHORT).show();
                } else Toast.makeText(getActivity(), "null", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ActivitiesResponse> call, Throwable t) {
                progressDialog.cancel();
                Toast.makeText(getActivity(), "Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void settingRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(10000);    // 10 seconds, in milliseconds
        mLocationRequest.setFastestInterval(1000);   // 1 second, in milliseconds
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(mLocationRequest);

        PendingResult<LocationSettingsResult> result =
                LocationServices.SettingsApi.checkLocationSettings(mGoogleApiClient,
                        builder.build());

        result.setResultCallback(new ResultCallback<LocationSettingsResult>() {

            @Override
            public void onResult(@NonNull LocationSettingsResult result) {
                final Status status = result.getStatus();
                final LocationSettingsStates state = result.getLocationSettingsStates();
                switch (status.getStatusCode()) {
                    case LocationSettingsStatusCodes.SUCCESS:
                        // All location settings are satisfied. The client can
                        // initialize location requests here.
                        try {
                            getLocation();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        // Location settings are not satisfied, but this can be fixed
                        // by showing the user a dialog.
                        try {
                            // Show the dialog by calling startResolutionForResult(),
                            // and check the result in onActivityResult().
                            status.startResolutionForResult(getActivity(), 1000);
                        } catch (IntentSender.SendIntentException e) {
                            // Ignore the error.
                        }
                        break;
                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        // Location settings are not satisfied. However, we have no way
                        // to fix the settings so we won't show the dialog.
                        break;
                }
            }

        });
    }

    public void tryLocation() {
        initPermissionCheck();
        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        if (mGoogleApiClient != null) {
            mGoogleApiClient.connect();
        } else
            Toast.makeText(getActivity(), "Not Connected!", Toast.LENGTH_SHORT).show();
    }

    public void getLocation() throws IOException {
        if (ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        } else {
            /*Getting the location after aquiring location service*/
            mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                    mGoogleApiClient);

            if (mLastLocation != null) {

                orgLat = String.valueOf(mLastLocation.getLatitude());
                orgLng = String.valueOf(mLastLocation.getLongitude());

                //Toast.makeText(this, lat + " " + lng + " " + getAddress(Double.parseDouble(lat),Double.parseDouble(lng)), Toast.LENGTH_SHORT).show();
            } else {
                /*if there is no last known location. Which means the device has no data for the loction currently.
                * So we will get the current location.
                * For this we'll implement Location Listener and override onLocationChanged*/
                Log.i("Current Location", "No data for location found");

                if (!mGoogleApiClient.isConnected())
                    mGoogleApiClient.connect();

                LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
            }
        }
    }

    public void getNearbyPeople() {
        progressDialog = ProgressDialog.show(getActivity(), null, "Loading");
        progressDialog.setCancelable(false);
        Log.e("LAT  LNG", orgLat + " " + orgLng);
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Log.e("Token", AuthUser.getAuthData().getToken());
        Log.e("Secret", AuthUser.getAuthData().getSecret());
        Call<SearchPeople> call = apiService.searchPeople(AuthUser.getAuthData().getToken(),
                AuthUser.getAuthData().getSecret(), "1", orgLat, orgLng);
        Log.e("orglat", orgLat + "         ");
        Log.e("orglng", orgLng + "             ");
        call.enqueue(new Callback<SearchPeople>() {
            @Override
            public void onResponse(Call<SearchPeople> call, Response<SearchPeople> response) {
                progressDialog.cancel();
                if (response.body() != null) {
                    if (response.body().getStatus().equals(FLAG_SUCCESS)) {
                        for (int i = 0; i < response.body().getUsers().size(); i++) {
                            listNearby.add(response.body().getUsers().get(i));
                        }
                        createNearbyPins(response.body());
                    } else
                        Toast.makeText(getActivity(), response.body().getStatus(), Toast.LENGTH_SHORT).show();
                } else Toast.makeText(getActivity(), "null", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<SearchPeople> call, Throwable t) {
                progressDialog.cancel();
                Toast.makeText(getActivity(), "Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void createNearbyPins(SearchPeople searchPeople) {

        for (int i = 0; i < searchPeople.getUsers().size(); i++) {
            LatLng lt = new LatLng(Double.parseDouble(searchPeople.getUsers().get(i).getLat()), Double.parseDouble(searchPeople.getUsers().get(i).getLng()));
            Marker m = mMap.addMarker(new MarkerOptions()
                    .position(lt));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(m.getPosition(), 15));
            markerIds.put(m, searchPeople.getUsers().get(i).getUuid());
        }
    }

    public void initMap() {
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (checkForLocationEnabled(getActivity())) {
            buildGoogleApiClient();
            if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
            }
        }
//        Marker hamburg = mMap.addMarker(new MarkerOptions()
//                .position(HAMBURG));
//        Marker kiel = mMap.addMarker(new MarkerOptions()
//                .position(KIEL));
//        markerIds.put(hamburg, "1");
//        markerIds.put(kiel, "2");
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(HAMBURG, 15));
        mMap.getUiSettings().setAllGesturesEnabled(false);
        mMap.setOnMarkerClickListener(this);
        mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {

            @Override
            public View getInfoWindow(Marker marker) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {
                String id = markerIds.get(marker);
                User now = null;
                for (int i = 0; i < listNearby.size(); i++) {
                    if (listNearby.get(i).getUuid().equals(id)) {
                        now = listNearby.get(i);
                        break;
                    }
                }
                View infowindow;
                if (isBottom) {
                    infowindow = getActivity().getLayoutInflater().inflate(R.layout.marker_popup, null);
                    if (now != null) {

                        CircleImageView avatar = (CircleImageView) infowindow.findViewById(R.id.iv_avatar_popMarker);
                        TextView tvname = (TextView) infowindow.findViewById(R.id.tvName_popMarker);
                        TextView tvGender = (TextView) infowindow.findViewById(R.id.tvGenre_popMarker);
                        Picasso.with(getActivity())
                                .load(Constants.BASE_URL + now.getPic())
                                .error(R.drawable.kappa2)
                                .into(avatar);
                        tvname.setText(now.getName());
                        tvGender.setText(now.getGender());
                    }
                } else
                    infowindow = null;


                return infowindow;
            }
        });
        mMap.setOnInfoWindowClickListener(this);
    }

    @Override
    public boolean onMarkerClick(Marker marker) {

        return false;
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        String id = markerIds.get(marker);

        for (int i = 0; i < listNearby.size(); i++) {
            if (listNearby.get(i).getUuid().equals(id)) {
                now = listNearby.get(i);
                break;
            }
        }
        final Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.marker_dialog);

        FancyButton btnHang = (FancyButton) dialog.findViewById(R.id.btn_hang_dialog);
        FancyButton btnPreview = (FancyButton) dialog.findViewById(R.id.btn_preview_dialog);
        TextView tv_name = (TextView) dialog.findViewById(R.id.tvDialog_Name);
        TextView tv_gender = (TextView) dialog.findViewById(R.id.tvDialogGender);
        tv_name.setText(now.getName());
        tv_gender.setText(now.getGender_name());

        btnHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Constants.isBottom) {
                    linear2.setVisibility(View.VISIBLE);
                    Constants.isBottom = false;
                }
                HangoutModel hangoutModel;
                hangoutModel = new HangoutModel(now.getPic(), now.getName(), now.getUuid());
                HangoutModelList.add(hangoutModel);
                recyclerView.setAdapter(adapter);
                dialog.cancel();
            }
        });
        btnPreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new ProfileFragment();
                Bundle bundle = new Bundle();
                bundle.putBoolean("type", false);
                bundle.putString("uuid", now.getUuid());
                fragment.setArguments(bundle);
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.enter_left, R.anim.exit_right);
                fragmentTransaction.replace(R.id.content_main, fragment, "Profile");
                fragmentTransaction.commit();
                ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Profile");
                Constants.inFragment = "Profile";
                dialog.cancel();
            }
        });

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

        if (mGoogleApiClient.isConnected())
            if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION)
                            != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
        settingRequest();
        if (isPermissionGranted) {
            mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                    mGoogleApiClient);
            mMap.setMyLocationEnabled(true);
            if (mLastLocation != null) {
                orgLat = String.valueOf(mLastLocation.getLatitude());
                orgLng = String.valueOf(mLastLocation.getLongitude());
                mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude())));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude()), 15));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
            }
            if (mLastLocation == null)
                mLastLocation = getLastKnownLocation();
        }
    }


    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {

        if (isPermissionGranted) {

            if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            if (location != null) {
                orgLat = String.valueOf(location.getLatitude());
                orgLng = String.valueOf(location.getLongitude());
                Toast.makeText(getActivity(), orgLat + " " + orgLng, Toast.LENGTH_SHORT).show();
                mLastLocation = location;
            } else mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                    mGoogleApiClient);
        }
        if (mLastLocation == null)
            mLastLocation = getLastKnownLocation();
    }

    private synchronized void buildGoogleApiClient() {

        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    void initPermissionCheck() {

        PermissionListener permissionlistener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                isPermissionGranted = true;
            }

            @Override
            public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                isPermissionGranted = false;
            }
        };
        new TedPermission(getActivity())
                .setPermissionListener(permissionlistener)
                .setDeniedMessage("If you reject permission,you can not use this service")
                .setPermissions(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
                .check();
    }

    public void showWaringDialog() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
                alertDialog.setTitle("Location not enabled");
                alertDialog.setMessage("Please, enable location");
                alertDialog.setPositiveButton("Enable", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivityForResult(intent, LOCATION_SETTINGS_REQUEST_CODE);
                    }
                });
                alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                        dialog.dismiss();
                    }
                });
                alertDialog.show();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (checkForLocationEnabled(getActivity())) {
            buildGoogleApiClient();
        } else {
            showWaringDialog();
        }
        switch (requestCode) {
            case 1000:
                switch (resultCode) {
                    case Activity.RESULT_OK:

                        try {
                            getLocation();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    case Activity.RESULT_CANCELED:
                        // The user was asked to change settings, but chose not to
                        Toast.makeText(getActivity(), "Location Service not Enabled", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        mLocationPermissionGranted = false;
        switch (requestCode) {

            case LOCATION_REQUESTED_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mLocationPermissionGranted = true;
                    Toast.makeText(this.getActivity(), "Location permission granted", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(this.getActivity(), "Location permission Denied", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        int response = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(getActivity());
        if (response != ConnectionResult.SUCCESS)
            GoogleApiAvailability.getInstance().getErrorDialog(getActivity(), response, 1).show();
        if (ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.INTERNET},
                    LOCATION_REQUESTED_CODE);
        }
        mFusedLocationClient.getLastLocation().addOnSuccessListener(getActivity(), new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    org = new LatLng(location.getLatitude(), location.getLongitude());
                    CameraPosition cameraPosition = new CameraPosition.Builder()
                            .target(new LatLng(org.latitude, org.longitude)).zoom(12).build();
                    mMap.animateCamera(CameraUpdateFactory
                            .newCameraPosition(cameraPosition));
                    if (!orgCreated && org != null) {
                        mMap.addMarker(new MarkerOptions()
                                .position(org));
                        orgCreated = true;
                    }
                }
            }
        });
    }

    public void SlideToDown() {
        linear.setVisibility(View.VISIBLE);
        linear.setAlpha(0.0f);
// Start the animation
        linear.animate()
                .translationY(linear.getHeight())
                .alpha(1.0f)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        linear.setVisibility(View.GONE);
                    }
                });
        isBottom = true;
    }

    public void SlideToTop() {
        linear2.setVisibility(View.GONE);
        linear2.setAlpha(0.0f);
// Start the animation
        linear2.animate()
                .translationY(linear2.getHeight())
                .alpha(1.0f)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        linear2.setVisibility(View.VISIBLE);
                    }
                });
    }

    private Location getLastKnownLocation() {
        mLocationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        List<String> providers = mLocationManager.getProviders(true);
        Location bestLocation = null;
        for (String provider : providers) {
            if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                break;
            }
            Location l = mLocationManager.getLastKnownLocation(provider);
            if (l != null)
                Log.e("provider:, location:", provider + "  " + l.getLatitude() + "  " + l.getLongitude());
            else
                Log.e("provider:, location:", provider + "  " + null + "  " + null);
            if (l == null) {
                continue;
            }
            if (bestLocation == null
                    || l.getAccuracy() < bestLocation.getAccuracy()) {
                Log.e(" best last location: %s", l.getLatitude() + "  " + l.getLongitude());
                bestLocation = l;
            }
        }
        if (bestLocation == null) {
            return null;
        }
        return bestLocation;
    }
}