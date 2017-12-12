package com.example.android.blendin.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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
import com.example.android.blendin.RecyclerViewClickListener;

import java.util.ArrayList;
import java.util.List;


public class MysquadFragment extends Fragment {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    List<MySquadModel> mySquadModelList;
    RecyclerView.LayoutManager layoutManager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
   View view=inflater.inflate(R.layout.fragment_mysquad, container, false);
        GridLayoutManager layoutManager=new GridLayoutManager(getActivity().getApplicationContext(),2);
        recyclerView=(RecyclerView)view.findViewById(R.id.mysquad_recyclerView);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        mySquadModelList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            MySquadModel mySquadModel = new MySquadModel(
                    R.drawable.user,
                    "FIFA 17",
                    "30 Member");
            mySquadModelList.add(mySquadModel);
        }
        RecyclerViewClickListener listener = new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                //TODO different fragment data depends on the post , waiting for the logic .-.
                Fragment fragment = new SquadFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.slide_in_from_bottom, R.anim.slide_out_to_bottom, R.anim.slide_out_from_bottom, R.anim.slide_in_to_bottom);
                fragmentTransaction.add(R.id.content_main, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        };
        adapter = new MySquadAdapter(mySquadModelList, getActivity(), listener);
        recyclerView.setAdapter(adapter);
        return view;
    }

}
