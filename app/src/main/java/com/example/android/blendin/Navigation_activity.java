package com.example.android.blendin;


import android.os.Build;
import android.os.Bundle;
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
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;

import com.example.android.blendin.Fragments.HangoutFragment;
import com.example.android.blendin.Fragments.HangoutProfileFragment;
import com.example.android.blendin.Fragments.MysquadFragment;
import com.example.android.blendin.Fragments.NewsfeedFragment;
import com.example.android.blendin.Fragments.NotificationFragment;
import com.example.android.blendin.Fragments.ProfileFragment;
import com.example.android.blendin.Fragments.RequestFragment;
import com.example.android.blendin.Fragments.SettingsFragment;

import org.apmem.tools.layouts.FlowLayout;
 
/**
 * Created by Luffy on 11/28/2017.
 */
 
public class Navigation_activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
 
    Fragment fragment;
    Toolbar toolbar;
    FragmentTransaction fragmentTransaction;
    NavigationView navigationView;
    Boolean notificationClicked = false;
    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_activity);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.open_navigation, R.string.close_navigation);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        // Opens Default Fragment -> Newsfeed
        // transmission(new NewsfeedFragment(),"news");
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_main, new NewsfeedFragment(), "news");
        fragmentTransaction.commit();
        getSupportActionBar().setTitle("Newsfeed");

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handling navigation view item clicks.
        int id = item.getItemId();
        if (id == R.id.profile_nav_item) {
            transmission(new ProfileFragment(), "Profile");

        } else if (id == R.id.hangouts_nav_item) {
            transmission(new HangoutFragment(), "Hangouts");
 
        } else if (id == R.id.Newsfeed_nav_item) {
            transmission(new NewsfeedFragment(), "Newsfeed");
 
        } else if (id == R.id.request_nav_item) {
            transmission(new RequestFragment(), "Requests");
 
        } else if (id == R.id.Mysquad_nav_item) {
            transmission(new MysquadFragment(), "My Squads", true);
 
        } else if (id == R.id.settings_nav_item) {
            //TODO :: Settings Fragment
            transmission(new SettingsFragment(), "set");
            getSupportActionBar().setTitle("Settings");
        } else if (id == R.id.signout_nav_item) {
            //TODO :: Sign out
            transmission(new HangoutProfileFragment(), "Hangout Profile", true);
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
        } else {
            notificationClicked = false;
            super.onBackPressed();
        }
    }
 
    public void transmission(Fragment nxtfragment, String key) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_left, R.anim.exit_right);
        fragmentTransaction.replace(R.id.content_main, nxtfragment, key);
        fragmentTransaction.commit();
        getSupportActionBar().setTitle(key);
    }

    public void transmission(Fragment nxtfragment, String key, boolean flag) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_left, R.anim.exit_right);
        Bundle bundle = new Bundle();
        bundle.putBoolean("auth", flag);
        nxtfragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.content_main, nxtfragment, key);
        fragmentTransaction.commit();
        getSupportActionBar().setTitle(key);
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
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.setCustomAnimations(R.anim.slide_out_from_bottom, R.anim.slide_in_to_bottom, R.anim.slide_in_from_bottom, R.anim.slide_out_to_bottom);
                    fragmentTransaction.replace(R.id.content_main, new NotificationFragment(), "Notifications");
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                    getSupportActionBar().setTitle("Notifications");
                    notificationClicked = true;
                }
                break;
        }
        return true;
    }


}