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


    public int authi;
    String title, disc, location, startDate, endDate, startTime, endTime, activity, ppl, hangout_id;
    @BindView(R.id.hangoutProfile_collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_hangout_profile, container, false);
        ButterKnife.bind(this, rootView);

        Bundle bundle = getArguments();
        authi = bundle.getInt("auth");
        if (authi == 1) {
            ppl = bundle.getString("people");
            title = bundle.getString("title");
            disc = bundle.getString("disc");
            location = bundle.getString("location");
            startDate = bundle.getString("startDate");
            endDate = bundle.getString("endDate");
            startTime = bundle.getString("startTime");
            endTime = bundle.getString("endTime");
            activity = bundle.getString("activity");
            hangout_id = bundle.getString("hangout_id");
            collapsingToolbarLayout.setTitle(title);
        } else if (authi == 2) {
            hangout_id = bundle.getString("hangout_id");
        }




        ViewPager viewPager = (ViewPager) rootView.findViewById(R.id.hangoutProfile_viewpager);
        PagerAdapter pagerAdapter = new HangoutTabsAdapter(getChildFragmentManager(), authi);
        viewPager.setAdapter(pagerAdapter);
        TabLayout tableLayout = (TabLayout) rootView.findViewById(R.id.hangoutProfile_tabs);
        tableLayout.setupWithViewPager(viewPager);
        return rootView;
    }

    class HangoutTabsAdapter extends FragmentPagerAdapter {


        public HangoutTabsAdapter(FragmentManager fm, int auth) {
            super(fm);
            authi = auth;
        }
        @Override
        public int getCount() {
            if (authi == 1)
                return 3;
            else
                return 2;
        }

        @Override
        public Fragment getItem(int position) {
            if (authi == 1 || authi == 2) {
                Bundle bundle = new Bundle();
                switch (position) {
                    case 0:
                        bundle.putInt("auth", authi);
                        if (authi == 1) {
                            bundle.putString("people", ppl);
                            bundle.putString("activity", activity);
                            bundle.putString("disc", disc);
                            bundle.putString("title", title);
                            bundle.putString("location", location);
                            bundle.putString("startDate", startDate);
                            bundle.putString("endDate", endDate);
                            bundle.putString("startTime", startTime);
                            bundle.putString("endTime", endTime);
                        }
                        if (authi == 2)
                            bundle.putString("hangout_id", hangout_id);
                        Fragment fragment = new HangoutProfileDetailsFragment();
                        fragment.setArguments(bundle);
                        return fragment;
                    case 1:
                        Fragment fragment2 = new HangoutProfileChatFragment();
                        bundle.putString("hangout_id", hangout_id);
                        fragment2.setArguments(bundle);
                        return fragment2;
                    case 2:
                        Fragment fragment3 = new HangoutProfilePostsFragment();
                        bundle.putString("hangout_id", hangout_id);
                        fragment3.setArguments(bundle);
                        return fragment3;
                    default:
                        return null;
                }
            } else {
                Bundle bundle = new Bundle();
                switch (position) {

                    case 0:
                        return new HangoutProfileDetailsFragment();
                    case 1:
                        Fragment fragment2 = new HangoutProfileChatFragment();
                        bundle.putString("hangout_id", hangout_id);
                        fragment2.setArguments(bundle);
                        return fragment2;
                    default:
                        return null;
                }
            }
        }
        @Override
        public CharSequence getPageTitle(int position) {
            if (authi == 1 || authi == 2) {
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
