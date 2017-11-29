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

import com.example.android.blendin.Adapters.NewsfeedAdapter;
import com.example.android.blendin.Adapters.RequestAdapter;
import com.example.android.blendin.Models.NewsFeedModel;
import com.example.android.blendin.Models.RequestModel;
import com.example.android.blendin.R;

import java.util.ArrayList;
import java.util.List;

public class RequestFragment extends Fragment {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    List<RequestModel> requestModelList;
    RecyclerView.LayoutManager layoutManager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_request, container, false);
        recyclerView = (RecyclerView)  rootView.findViewById(R.id.request_recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        requestModelList = new ArrayList<>();

        for(int i=0; i < 9; i++){
            RequestModel requestModel = new RequestModel(
                    R.drawable.user,
                    "Nadia",
                    "1 hour ago",
                    "ELHaram",
                    "sy3ne ya 3atef"
            );
            requestModelList.add(requestModel);
        }
        adapter = new RequestAdapter(requestModelList,getActivity());
        recyclerView.setAdapter(adapter);
        return rootView;
    }

}
