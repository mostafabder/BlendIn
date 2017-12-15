package com.example.android.blendin.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.android.blendin.Adapters.HangoutAdapter;
import com.example.android.blendin.Models.HangoutModel;
import com.example.android.blendin.R;

import java.util.ArrayList;
import java.util.List;

public class AddSquadFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    LinearLayoutManager linearLayoutManager;
    List<HangoutModel> hangoutModelList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_add_squad, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_addSquad);
        hangoutModelList = new ArrayList<>();
        linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        for (int i = 0; i < 12; i++) {
            HangoutModel hangoutModel = new HangoutModel(R.drawable.user, "Bondo2");
            hangoutModelList.add(hangoutModel);
        }
        adapter = new HangoutAdapter(getActivity(), hangoutModelList, true);
        recyclerView.setAdapter(adapter);
        return v;
    }

}
