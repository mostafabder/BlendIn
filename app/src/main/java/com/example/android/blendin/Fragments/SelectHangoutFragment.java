package com.example.android.blendin.Fragments;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.blendin.Adapters.PreviousHangoutAdapter;
import com.example.android.blendin.Models.PreviousHangoutModel;
import com.example.android.blendin.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LeGenÐ on 12/15/2017.
 */


public class SelectHangoutFragment extends DialogFragment {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    List<PreviousHangoutModel> modelList;
    LinearLayoutManager layoutManager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.previous_hangouts_dialog, container);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.previous_hangout_recycler);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        modelList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            PreviousHangoutModel commentModel = new PreviousHangoutModel(
                    R.drawable.kappa2,
                    "Fifa 18 Tournment",
                    "Matador Gamin Cafe",
                    "10th December"
            );
            modelList.add(commentModel);
        }
        adapter = new PreviousHangoutAdapter(modelList, SelectHangoutFragment.this);
        recyclerView.setAdapter(adapter);
        return rootView;
    }
}
