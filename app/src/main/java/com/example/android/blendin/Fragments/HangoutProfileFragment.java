package com.example.android.blendin.Fragments;


import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.blendin.Models.HangoutModel;
import com.example.android.blendin.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class HangoutProfileFragment extends Fragment {


    String title, disc, location, startDate, endDate, startTime, endTime, activity, ppl;
    @BindView(R.id.hangoutProfile_collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_hangout_profile, container, false);
        ButterKnife.bind(this, rootView);
        boolean authenticated;
        Bundle bundle = getArguments();
        authenticated = bundle.getBoolean("auth");
        ppl = bundle.getString("people");
        title = bundle.getString("title");
        disc = bundle.getString("disc");
        location = bundle.getString("location");
        startDate = bundle.getString("startDate");
        endDate = bundle.getString("endDate");
        startTime = bundle.getString("startTime");
        endTime = bundle.getString("endTime");
        activity = bundle.getString("activity");
        collapsingToolbarLayout.setTitle(title);
        ViewPager viewPager = (ViewPager) rootView.findViewById(R.id.hangoutProfile_viewpager);
        PagerAdapter pagerAdapter = new HangoutTabsAdapter(getChildFragmentManager(), authenticated);
        viewPager.setAdapter(pagerAdapter);
        TabLayout tableLayout = (TabLayout) rootView.findViewById(R.id.hangoutProfile_tabs);
        tableLayout.setupWithViewPager(viewPager);
        return rootView;
    }

    class HangoutTabsAdapter extends FragmentPagerAdapter {
        boolean auth;

        public HangoutTabsAdapter(FragmentManager fm, boolean auth) {
            super(fm);
            this.auth = auth;
        }
        @Override
        public int getCount() {
            if (auth)
                return 3;
            else
                return 2;
        }

        @Override
        public Fragment getItem(int position) {
            if (auth) {
                switch (position) {
                    case 0:
                        Bundle bundle = new Bundle();
                        bundle.putString("people", ppl);
                        bundle.putString("activity", activity);
                        bundle.putString("disc", disc);
                        bundle.putString("title", title);
                        bundle.putString("location", location);
                        bundle.putString("startDate", startDate);
                        bundle.putString("endDate", endDate);
                        bundle.putString("startTime", startTime);
                        bundle.putString("endTime", endTime);
                        Fragment fragment = new HangoutProfileDetailsFragment();
                        fragment.setArguments(bundle);
                        return fragment;
                    case 1:
                        return new HangoutProfileChatFragment();
                    case 2:
                        return new HangoutProfilePostsFragment();
                    default:
                        return null;
                }
            } else {
                switch (position) {
                    case 0:
                        return new HangoutProfileDetailsFragment();
                    case 1:
                        return new HangoutProfilePostsFragment();
                    default:
                        return null;
                }

            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if (auth) {
                switch (position) {
                    case 0:
                        return "Details";
                    case 1:
                        return "Chat";
                    case 2:
                        return "Posts";
                    default:
                        return "Blend In";
                }
            } else {
                switch (position) {
                    case 0:
                        return "Details";
                    case 1:
                        return "Posts";
                    default:
                        return "Blend In";
                }
            }
        }
    }

}
