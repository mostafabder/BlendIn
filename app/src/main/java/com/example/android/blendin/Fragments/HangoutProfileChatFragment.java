package com.example.android.blendin.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.blendin.Adapters.HangoutProfileChatAdapter;
import com.example.android.blendin.Models.SquadChatMessageModel;
import com.example.android.blendin.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HangoutProfileChatFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    List<SquadChatMessageModel> chatMessageModelList;
    RecyclerView.LayoutManager layoutManager;

    public HangoutProfileChatFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_hangout_profile_chat, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.hangoutChat_recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        //recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(layoutManager);

        chatMessageModelList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            SquadChatMessageModel model = new SquadChatMessageModel(
                    R.drawable.user,
                    "Omar ELRayes",
                    "Yesterday was AWESOME !",
                    "30m"
            );
            chatMessageModelList.add(model);
        }
        adapter = new HangoutProfileChatAdapter(chatMessageModelList, getActivity());
        recyclerView.setAdapter(adapter);


        return rootView;
    }

}
