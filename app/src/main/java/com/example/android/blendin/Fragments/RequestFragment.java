package com.example.android.blendin.Fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.blendin.Models.RequestModel;
import com.example.android.blendin.R;

import java.util.List;

public class RequestFragment extends Fragment {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    List<RequestModel> requestModelList;
    RecyclerView.LayoutManager layoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_request, container, false);

        ViewPager viewPager = (ViewPager) rootView.findViewById(R.id.requests_viewpager);
        PagerAdapter pagerAdapter = new RequestsTabsAdapter(getChildFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        TabLayout tableLayout = (TabLayout) rootView.findViewById(R.id.requests_tabs);
        tableLayout.setupWithViewPager(viewPager);

        return rootView;
    }

    class RequestsTabsAdapter extends FragmentPagerAdapter {

        public RequestsTabsAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new RequestsHangoutsFragment();
                case 1:
                    return new RequestsSquadsFragment();
                default:
                    return null;
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Hangouts";
                case 1:
                    return "Squads";
                default:
                    return "Blend In";
            }
        }

    }
}
