package com.example.android.blendin.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.blendin.Adapters.MySquadAdapter;
import com.example.android.blendin.Adapters.NewsfeedAdapter;
import com.example.android.blendin.Models.MySquadModel;
import com.example.android.blendin.Models.NewsFeedModel;
import com.example.android.blendin.R;

import java.util.ArrayList;
import java.util.List;


public class MysquadFragment extends Fragment {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    List<MySquadModel> mySquadModelList;
    //RecyclerView.LayoutManager layoutManager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_mysquad, container, false);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity().getApplicationContext(),2);
        recyclerView=(RecyclerView)rootView.findViewById(R.id.mysquad_recyclerView);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        mySquadModelList = new ArrayList<>();
        for(int i=0; i<9; i++){
            MySquadModel mySquadModel = new MySquadModel(
                    R.drawable.user,
                    "Fifa Players",
                    "69"
            );
            mySquadModelList.add(mySquadModel);
        }
        adapter = new MySquadAdapter(mySquadModelList,getActivity());
        recyclerView.setAdapter(adapter);
        return rootView;
    }

}
