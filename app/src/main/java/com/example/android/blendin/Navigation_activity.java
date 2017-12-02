package com.example.android.blendin;

import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
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
import android.view.MenuItem;

import com.example.android.blendin.Fragments.HangoutFragment;
import com.example.android.blendin.Fragments.MysquadFragment;
import com.example.android.blendin.Fragments.NewsfeedFragment;
import com.example.android.blendin.Fragments.ProfileFragment;
import com.example.android.blendin.Fragments.RequestFragment;

/**
 * Created by Luffy on 11/28/2017.
 */

public class Navigation_activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Fragment fragment;
    Toolbar toolbar;
    FragmentTransaction fragmentTransaction;
    NavigationView navigationView;
    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_activity);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,R.string.open_navigation,R.string.close_navigation);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        // Opens Default Fragment -> Newsfeed
        fragment = new NewsfeedFragment();
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_main, fragment, "newsfeed");
        fragmentTransaction.commit();
        //getSupportActionBar().setTitle("Newsfeed");

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.profile_nav_item) {
                    //TODO Profile Fragment
            fragment = new ProfileFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.content_main, fragment, "Profile");
            fragmentTransaction.commit();
            //getSupportActionBar().setTitle(getString(R.string.Profile));

                } else if (id == R.id.hangouts_nav_item) {
                    fragment = new HangoutFragment();
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.content_main, fragment, "hangout");
                    fragmentTransaction.commit();
                    //getSupportActionBar().setTitle(getString(R.string.Hangout));

                } else if (id == R.id.Newsfeed_nav_item) {
                    fragment = new NewsfeedFragment();
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.content_main, fragment, "news");
                    fragmentTransaction.commit();
                    //getSupportActionBar().setTitle(getString(R.string.Hangout));

                } else if (id == R.id.request_nav_item) {
                    fragment = new RequestFragment();
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.content_main, fragment, "req");
                    fragmentTransaction.commit();
                    //getSupportActionBar().setTitle(getString(R.string.Hangout));

                } else if (id == R.id.Mysquad_nav_item) {
                    fragment = new MysquadFragment();
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.content_main, fragment, "squad");
                    fragmentTransaction.commit();
                    //getSupportActionBar().setTitle(getString(R.string.Hangout));

                } else if (id == R.id.settings_nav_item) {
                    //TODO :: Settings Fragment
           /* fragment = new SettingsFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.content_main, fragment, "set");
            fragmentTransaction.commit();
            //getSupportActionBar().setTitle(getString(R.string.Hangout));
            */
                } else if (id == R.id.signout_nav_item) {
                    //TODO :: Sign out
                }


                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                return true;

            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        // ma 2na 3mlt breakpoint 2ho 2odamk , w never got excuted , nope
        // Handling navigation view item clicks.
        int id = item.getItemId();
        if (id == R.id.profile_nav_item) {
            //TODO Profile Fragment
            /*fragment = new ProfileFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.content_main, fragment, "Profile");
            fragmentTransaction.commit();
            //getSupportActionBar().setTitle(getString(R.string.Profile));*/

        } else if (id == R.id.hangouts_nav_item) {
            fragment = new HangoutFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.content_main, fragment, "hangout");
            fragmentTransaction.commit();
            //getSupportActionBar().setTitle(getString(R.string.Hangout));

        } else if (id == R.id.Newsfeed_nav_item) {
            fragment = new NewsfeedFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.content_main, fragment, "news");
            fragmentTransaction.commit();
            //getSupportActionBar().setTitle(getString(R.string.Hangout));

        } else if (id == R.id.request_nav_item) {
            fragment = new RequestFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.content_main, fragment, "req");
            fragmentTransaction.commit();
            //getSupportActionBar().setTitle(getString(R.string.Hangout));

        } else if (id == R.id.Mysquad_nav_item) {
            fragment = new MysquadFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.content_main, fragment, "squad");
            fragmentTransaction.commit();
            //getSupportActionBar().setTitle(getString(R.string.Hangout));

        } else if (id == R.id.settings_nav_item) {
            //TODO :: Settings Fragment
           /* fragment = new SettingsFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.content_main, fragment, "set");
            fragmentTransaction.commit();
            //getSupportActionBar().setTitle(getString(R.string.Hangout));
            */
        } else if (id == R.id.signout_nav_item) {
            //TODO :: Sign out
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
            super.onBackPressed();
        }
    }
}
