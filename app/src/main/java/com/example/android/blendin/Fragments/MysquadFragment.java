package com.example.android.blendin.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.blendin.R;


public class MysquadFragment extends Fragment {
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
   View view=inflater.inflate(R.layout.fragment_mysquad, container, false);
        GridLayoutManager layoutManager=new GridLayoutManager(getActivity().getApplicationContext(),2);
        recyclerView=(RecyclerView)view.findViewById(R.id.recyclerTeam);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        return view;
    }

}
