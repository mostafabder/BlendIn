package com.example.android.blendin.Fragments;


import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.blendin.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HangoutProfileFragment extends Fragment {


    public HangoutProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_hangout_profile, container, false);
        boolean authenticated;
        Bundle bundle = getArguments();
        authenticated = bundle.getBoolean("auth");
        ViewPager viewPager = (ViewPager) rootView.findViewById(R.id.hangoutProfile_viewpager);
        PagerAdapter pagerAdapter = new HangoutTabsAdapter(getChildFragmentManager(), authenticated);
        viewPager.setAdapter(pagerAdapter);

        TabLayout tableLayout = (TabLayout) rootView.findViewById(R.id.hangoutProfile_tabs);
        tableLayout.setupWithViewPager(viewPager);


        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) rootView.findViewById(R.id.hangoutProfile_collapsing_toolbar);
        collapsingToolbar.setTitle("FIFA 18 Tournment");
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
                        return new HangoutProfileDetailsFragment();
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
