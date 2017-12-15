package com.example.android.blendin.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.blendin.Adapters.RequestAdapter;
import com.example.android.blendin.Models.RequestModel;
import com.example.android.blendin.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class RequestsHangoutsFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    List<RequestModel> requestModelList;
    RecyclerView.LayoutManager layoutManager;

    public RequestsHangoutsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_requests_hangouts, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.requests_hangouts_recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        requestModelList = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            RequestModel requestModel = new RequestModel(
                    R.drawable.user,
                    "Nadia",
                    "1 hour ago",
                    "ELHaram",
                    "Invited you to hangout"
            );
            requestModelList.add(requestModel);
        }
        adapter = new RequestAdapter(requestModelList, getActivity(), true);
        recyclerView.setAdapter(adapter);


        return rootView;
    }

}
