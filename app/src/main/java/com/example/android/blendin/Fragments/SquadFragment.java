package com.example.android.blendin.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.blendin.Adapters.SquadChatMessageAdapter;
import com.example.android.blendin.Models.SquadChatMessageModel;
import com.example.android.blendin.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SquadFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    List<SquadChatMessageModel> squadChatMessageModelList;
    RecyclerView.LayoutManager layoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_squad, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.groupPage_recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        squadChatMessageModelList = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            SquadChatMessageModel squadChatMessageModel = new SquadChatMessageModel(
                    R.drawable.user,
                    "Omar ELRayes",
                    "Yesterday was AWESOME !",
                    "30m"
            );
            squadChatMessageModelList.add(squadChatMessageModel);
        }
        adapter = new SquadChatMessageAdapter(squadChatMessageModelList, getActivity());
        recyclerView.setAdapter(adapter);
        return rootView;
    }

}
