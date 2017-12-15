package com.example.android.blendin.Fragments;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.blendin.Adapters.HangoutProfileMemeberAdapter;
import com.example.android.blendin.Models.HangoutProfileMemberModel;
import com.example.android.blendin.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HangoutProfileDetailsFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    List<HangoutProfileMemberModel> modelList;
    LinearLayoutManager layoutManager;
    private Bitmap b = null;

    public HangoutProfileDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_hangout_profile_details, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.hangoutDetails_recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        modelList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            HangoutProfileMemberModel memberModel = new HangoutProfileMemberModel(
                    R.drawable.user,
                    "Mostafa Amr"
            );
            modelList.add(memberModel);
        }
        adapter = new HangoutProfileMemeberAdapter(modelList, getActivity());
        recyclerView.setAdapter(adapter);
        return rootView;
    }
}
