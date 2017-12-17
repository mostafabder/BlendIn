package com.example.android.blendin.Fragments;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.blendin.Adapters.ProfileAdapter;
import com.example.android.blendin.Models.ProfileModel;
import com.example.android.blendin.R;

import java.util.ArrayList;
import java.util.List;


public class ProfileFragment extends Fragment implements AppBarLayout.OnOffsetChangedListener {
    private static final int PERCENTAGE_TO_ANIMATE_AVATAR = 20;
    RecyclerView recyclerView;
    ProfileAdapter adapter;
    List<ProfileModel> profileModelList;
    private ImageView mProfileImage;
    private boolean mIsAvatarShown = true;
    private int mMaxScrollSize;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_profile, container, false);
        recyclerView=(RecyclerView)v.findViewById(R.id.profile_recycler_view);
        recyclerView.setHasFixedSize(true);
       RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        AppBarLayout appbarLayout = (AppBarLayout) v.findViewById(R.id.profile_appbar);
        mProfileImage = (ImageView) v.findViewById(R.id.profile_user_image);
        appbarLayout.addOnOffsetChangedListener(this);
        mMaxScrollSize = appbarLayout.getTotalScrollRange();

        profileModelList=new ArrayList<>();
        for(int i=0; i<5; i++){
            ProfileModel profileModel=new ProfileModel(
                    "Hamda Helal is at Loca Loca Cafe", "900", "1000", R.drawable.kappa2, true);
            profileModelList.add(profileModel);
        }
        adapter = new ProfileAdapter(profileModelList,getActivity());
        recyclerView.setAdapter(adapter);


        return v;
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
        if (mMaxScrollSize == 0)
            mMaxScrollSize = appBarLayout.getTotalScrollRange();

        int percentage = (Math.abs(i)) * 100 / mMaxScrollSize;

        if (percentage >= PERCENTAGE_TO_ANIMATE_AVATAR && mIsAvatarShown) {
            mIsAvatarShown = false;

            mProfileImage.animate()
                    .scaleY(0).scaleX(0)
                    .setDuration(200)
                    .start();
        }

        if (percentage <= PERCENTAGE_TO_ANIMATE_AVATAR && !mIsAvatarShown) {
            mIsAvatarShown = true;

            mProfileImage.animate()
                    .scaleY(1).scaleX(1)
                    .start();
        }
    }

}
