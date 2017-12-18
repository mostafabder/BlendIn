package com.example.android.blendin.Fragments;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.blendin.Adapters.HangoutAdapter;
import com.example.android.blendin.Models.HangoutModel;
import com.example.android.blendin.Models.HangoutProfileMemberModel;
import com.example.android.blendin.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class HangoutProfileDetailsFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    List<HangoutProfileMemberModel> modelList;
    LinearLayoutManager layoutManager;
    @BindView(R.id.hangoutDetails_desc)
    TextView tv_disc;
    @BindView(R.id.hangoutDetails_activity)
    TextView tv_activity;
    @BindView(R.id.hangoutDetails_startTime)
    TextView tv_startTime;
    @BindView(R.id.hangoutDetails_endTime)
    TextView tv_endTime;
    @BindView(R.id.hangoutDetails_locationName)
    TextView tv_location;
    List<HangoutModel> hangoutModelList;
    String title, disc, location, startDate, endDate, startTime, endTime, activity;
    private Bitmap b = null;
    public HangoutProfileDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_hangout_profile_details, container, false);
        ButterKnife.bind(this, rootView);
        Bundle bundle = getArguments();
        String ppl = bundle.getString("people");
        title = bundle.getString("title");
        disc = bundle.getString("disc");
        location = bundle.getString("location");
        startDate = bundle.getString("startDate");
        endDate = bundle.getString("endDate");
        startTime = bundle.getString("startTime");
        endTime = bundle.getString("endTime");
        activity = bundle.getString("activity");
        Type listType = new TypeToken<ArrayList<HangoutModel>>() {
        }.getType();
        hangoutModelList = new Gson().fromJson(ppl, listType);
        Log.e("size", String.valueOf(hangoutModelList.size()) + "           ");
        tv_disc.setText(disc);
        tv_activity.setText(activity);
        tv_location.setText(location);
        tv_startTime.setText(startDate + "  " + startTime);
        tv_endTime.setText(endDate + "  " + endTime);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.hangoutDetails_recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        modelList = new ArrayList<>();
        adapter = new HangoutAdapter(getActivity(), hangoutModelList, null, null);
        recyclerView.setAdapter(adapter);
        return rootView;
    }
}
