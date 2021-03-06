package com.example.android.blendin;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.blendin.Fragments.HangoutFragment;
import com.example.android.blendin.Fragments.HangoutProfileFragment;
import com.example.android.blendin.Fragments.MyHangoutsFragment;
import com.example.android.blendin.Fragments.MysquadFragment;
import com.example.android.blendin.Fragments.NewsfeedFragment;
import com.example.android.blendin.Fragments.NotificationFragment;
import com.example.android.blendin.Fragments.ProfileFragment;
import com.example.android.blendin.Fragments.RequestFragment;
import com.example.android.blendin.Fragments.SettingsFragment;
import com.example.android.blendin.Responses.CreateSquadResponse;
import com.example.android.blendin.Responses.NotifyResponse;
import com.example.android.blendin.Retrofit.ApiClient;
import com.example.android.blendin.Retrofit.ApiInterface;
import com.example.android.blendin.Utility.AuthUser;
import com.example.android.blendin.Utility.CommonMethods;
import com.example.android.blendin.Utility.Constants;

import org.apmem.tools.layouts.FlowLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.android.blendin.Utility.Constants.FLAG_SUCCESS;
import static com.example.android.blendin.Utility.Constants.PREF_KEY;

/**
 * Created by Luffy on 11/28/2017.
 */

public class Navigation_activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    String curFragmentKey;
    Fragment fragment;
    Toolbar toolbar;
    FragmentTransaction fragmentTransaction;
    NavigationView navigationView;
    Boolean notificationClicked = false;
    Boolean isMain;
    Toast toast;
    RelativeLayout relativeLayout;
    TextView noti;
    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_activity);
        noti = (TextView) findViewById(R.id.tv_noticationMessage);
        relativeLayout = (RelativeLayout) findViewById(R.id.relative_notification);
        startNotify();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.open_navigation, R.string.close_navigation);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        // Opens Default Fragment -> Newsfeed
        // transmission(new NewsfeedFragment(),"news");
        curFragmentKey = "Newsfeed";
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_main, new NewsfeedFragment(), "news");
        fragmentTransaction.commit();
        getSupportActionBar().setTitle("Newsfeed");
        Constants.inFragment = "Newsfeed";
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handling navigation view item clicks.
        int id = item.getItemId();
        if (id == R.id.profile_nav_item) {
            Fragment fragment = new ProfileFragment();
            Bundle bundle = new Bundle();
            bundle.putBoolean("type", true);
            fragment.setArguments(bundle);
            transmission(fragment, "Profile");
        } else if (id == R.id.hangouts_nav_item) {
            transmission(new HangoutFragment(), "Hangouts");
        } else if (id == R.id.Newsfeed_nav_item) {
            transmission(new NewsfeedFragment(), "Newsfeed");
        } else if (id == R.id.request_nav_item) {
            transmission(new RequestFragment(), "Requests");
        } else if (id == R.id.Mysquad_nav_item) {
            transmission(new MysquadFragment(), "My Squads", true);
        } else if (id == R.id.MyHangouts_nav_item) {
            transmission(new MyHangoutsFragment(), "My Hangouts");
        } else if (id == R.id.settings_nav_item) {
            //TODO :: Settings Fragment
            transmission(new SettingsFragment(), "Setting");
            getSupportActionBar().setTitle("Settings");
        } else if (id == R.id.signout_nav_item) {
            //TODO :: Sign out
            Intent intent = new Intent(Navigation_activity.this, LandingPageActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            CommonMethods.clearAllSavedSharedData(this);
            startActivity(intent);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (notificationClicked) {
            super.onBackPressed();
            getSupportActionBar().setTitle(Constants.inFragment);
        } else {
            this.finish();
            moveTaskToBack(true);
        }
    }

    public void transmission(Fragment nxtfragment, String key) {
        if (!key.equals(curFragmentKey)) {
            curFragmentKey = key;
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.setCustomAnimations(R.anim.enter_left, R.anim.exit_right);
            fragmentTransaction.replace(R.id.content_main, nxtfragment, key);
            getSupportFragmentManager().popBackStack();
            fragmentTransaction.commit();
            getSupportActionBar().setTitle(key);
            Constants.inFragment = key;
            notificationClicked = false;
        }
    }

    public void transmission(Fragment nxtfragment, String key, boolean flag) {
        if (!key.equals(curFragmentKey)) {
            curFragmentKey = key;
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.setCustomAnimations(R.anim.enter_left, R.anim.exit_right);
            Bundle bundle = new Bundle();
            bundle.putBoolean("auth", flag);
            nxtfragment.setArguments(bundle);
            fragmentTransaction.replace(R.id.content_main, nxtfragment, key);
           getSupportFragmentManager().popBackStack();
            fragmentTransaction.commit();
            getSupportActionBar().setTitle(key);
          Constants.inFragment = key;
        notificationClicked = false;
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        switch (itemId) {
            case R.id.notification_item_bar:
                if (!notificationClicked) {
                    fragment = new NotificationFragment();
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.setCustomAnimations(R.anim.slide_out_from_bottom, R.anim.slide_in_to_bottom, R.anim.slide_in_from_bottom, R.anim.slide_out_to_bottom);
                    fragmentTransaction.replace(R.id.content_main, fragment, "Notifications");
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                    getSupportActionBar().setTitle("Notifications");
                    notificationClicked = true;
                } else {
                    super.onBackPressed();
                    getSupportActionBar().setTitle(Constants.inFragment);
                    notificationClicked = false;
                }
                break;
        }
        return true;
    }

    public void startNotify() {

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Log.e("Token", AuthUser.getAuthData().getToken());
        Log.e("Secret", AuthUser.getAuthData().getSecret());
        Call<NotifyResponse> call = apiService.sendNotify(AuthUser.getAuthData().getToken(), AuthUser.getAuthData().getSecret());
        call.enqueue(new Callback<NotifyResponse>() {
            @Override
            public void onResponse(Call<NotifyResponse> call, Response<NotifyResponse> response) {
                if (response.body() != null) {
                    if (response.body().getStatus().equals(FLAG_SUCCESS)) {
                        relativeLayout.setVisibility(View.VISIBLE);
                        noti.setText(response.body().getMessage());
                        new CountDownTimer(3000, 1000) {
                            public void onTick(long millisUntilFinished) {
                            }

                            public void onFinish() {
                                relativeLayout.setVisibility(View.INVISIBLE);
                            }
                        }.start();
                        new CountDownTimer(7000, 1000) {
                            public void onTick(long millisUntilFinished) {
                            }

                            public void onFinish() {
                                startNotify();
                            }
                        }.start();

                    } else {
                        startNotify();
                    }
                } else Toast.makeText(Navigation_activity.this, "null", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<NotifyResponse> call, Throwable t) {

                Toast.makeText(Navigation_activity.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

}