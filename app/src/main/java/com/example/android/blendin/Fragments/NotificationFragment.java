package com.example.android.blendin.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.blendin.Adapters.NewsfeedAdapter;
import com.example.android.blendin.Adapters.NotificationAdapter;
import com.example.android.blendin.Models.NewsFeedModel;
import com.example.android.blendin.Models.NotificationModel;
import com.example.android.blendin.R;
import com.example.android.blendin.RecyclerViewClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationFragment extends Fragment {


    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    List<NotificationModel> notificationModelList;
    LinearLayoutManager layoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_notification, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_notification);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setLayoutManager(layoutManager);
        notificationModelList = new ArrayList<>();
        //Test Data
        for (int i = 0; i < 5; i++) {
            NotificationModel notificationModel = new NotificationModel(
                    R.drawable.user,
                    "Hamada helal just like your photo",
                    "just now");
            notificationModelList.add(notificationModel);
        }
        RecyclerViewClickListener listener = new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
            }
        };
        adapter = new NotificationAdapter(getActivity(), notificationModelList);
        recyclerView.setAdapter(adapter);
        return v;
    }
}
