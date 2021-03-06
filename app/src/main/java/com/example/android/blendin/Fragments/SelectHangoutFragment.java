package com.example.android.blendin.Fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.android.blendin.Adapters.PreviousHangoutAdapter;
import com.example.android.blendin.Models.MyHangoutsModel;
import com.example.android.blendin.Models.PreviousHangoutModel;
import com.example.android.blendin.R;
import com.example.android.blendin.RecyclerViewClickListener;
import com.example.android.blendin.Responses.CommentResponse;
import com.example.android.blendin.Responses.MyHangoutsResponse;
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
 * Created by LeGenÐ on 12/15/2017.
 */


public class SelectHangoutFragment extends DialogFragment {
    ProgressDialog progressDialog;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    List<MyHangoutsModel> modelList;
    LinearLayoutManager layoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.previous_hangouts_dialog, container);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.previous_hangout_recycler);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        getHangouts();
        return rootView;
    }

    void getHangouts() {
        progressDialog = ProgressDialog.show(getActivity(), null, "Loading");
        progressDialog.setCancelable(false);
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Log.e("Token", AuthUser.getAuthData().getToken());
        Log.e("Secret", AuthUser.getAuthData().getSecret());
        Call<MyHangoutsResponse> call = apiService.getMyHangouts(AuthUser.getAuthData().getToken(), AuthUser.getAuthData().getSecret());
        call.enqueue(new Callback<MyHangoutsResponse>() {
            @Override
            public void onResponse(Call<MyHangoutsResponse> call, Response<MyHangoutsResponse> response) {
                progressDialog.cancel();
                if (response.body() != null) {
                    if (response.body().getStatus().equals(Constants.FLAG_SUCCESS)) {
                        modelList = new ArrayList<MyHangoutsModel>(response.body().getHangouts());
                        adapter = new PreviousHangoutAdapter(modelList, SelectHangoutFragment.this);
                        recyclerView.setAdapter(adapter);
                    } else
                        Toast.makeText(getActivity(), response.body().getStatus(), Toast.LENGTH_SHORT).show();
                } else Toast.makeText(getActivity(), "null", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<MyHangoutsResponse> call, Throwable t) {
                progressDialog.cancel();
                Toast.makeText(getActivity(), "Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
