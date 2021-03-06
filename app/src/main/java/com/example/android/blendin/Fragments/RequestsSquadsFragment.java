package com.example.android.blendin.Fragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.android.blendin.Adapters.HangoutRequestAdapter;
import com.example.android.blendin.Adapters.RequestAdapter;
import com.example.android.blendin.Adapters.SquadRequestAdapter;
import com.example.android.blendin.Models.RequestModel;
import com.example.android.blendin.R;
import com.example.android.blendin.Responses.HangoutRequestResponse;
import com.example.android.blendin.Responses.SquadRequestResponse;
import com.example.android.blendin.Retrofit.ApiClient;
import com.example.android.blendin.Retrofit.ApiInterface;
import com.example.android.blendin.Utility.AuthUser;
import com.example.android.blendin.Utility.Constants;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class RequestsSquadsFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;

    public RequestsSquadsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_requests_squads, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.requests_squads_recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        initAPI();
        return rootView;
    }

    void initAPI() {
        final ProgressDialog progressDialog = ProgressDialog.show(getActivity(), null, "Loading");
        progressDialog.setCancelable(false);
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<SquadRequestResponse> call = apiService.getSquadRequests(AuthUser.getAuthData().getToken(), AuthUser.getAuthData().getSecret());
        call.enqueue(new Callback<SquadRequestResponse>() {
            @Override
            public void onResponse(Call<SquadRequestResponse> call, Response<SquadRequestResponse> response) {
                progressDialog.cancel();
                if (response.body() != null) {
                    if (response.body().getStatus().equals(Constants.FLAG_SUCCESS)) {
                        adapter = new SquadRequestAdapter(response.body().getSquad_requests(), getActivity());
                        recyclerView.setAdapter(adapter);
                    } else
                        Toast.makeText(getActivity(), response.body().getStatus(), Toast.LENGTH_SHORT).show();
                } else Toast.makeText(getActivity(), "null", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<SquadRequestResponse> call, Throwable t) {
                progressDialog.cancel();
                Toast.makeText(getActivity(), "Failed", Toast.LENGTH_SHORT).show();
            }
        });


    }

}
