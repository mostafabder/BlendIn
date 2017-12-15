package com.example.android.blendin.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.blendin.Adapters.ProfileAdapter;
import com.example.android.blendin.Models.ProfileModel;
import com.example.android.blendin.R;

import java.util.ArrayList;
import java.util.List;


public class ProfileFragment extends Fragment {
    RecyclerView recyclerView;
    ProfileAdapter adapter;
    List<ProfileModel> profileModelList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_profile, container, false);
        recyclerView=(RecyclerView)v.findViewById(R.id.profile_recycler_view);
        recyclerView.setHasFixedSize(true);
       RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
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

}
