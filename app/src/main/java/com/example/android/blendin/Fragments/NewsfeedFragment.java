package com.example.android.blendin.Fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.android.blendin.Adapters.NewsfeedAdapter;
import com.example.android.blendin.Models.NewsFeedModel;
import com.example.android.blendin.R;
import com.example.android.blendin.RecyclerViewClickListener;
import com.example.android.blendin.Responses.LoginResponse;
import com.example.android.blendin.Responses.NewsfeedResponse;
import com.example.android.blendin.Retrofit.ApiClient;
import com.example.android.blendin.Retrofit.ApiInterface;
import com.example.android.blendin.SignUpActivity;
import com.example.android.blendin.Utility.AuthUser;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.android.blendin.Utility.Constants.FLAG_SUCCESS;


public class NewsfeedFragment extends Fragment {


    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    List<NewsFeedModel> newsFeedsList;
    RecyclerView.LayoutManager layoutManager;
    com.github.clans.fab.FloatingActionButton floatingActionButton;
    ProgressDialog progressDialog;
    NewsfeedResponse newsfeedResponse;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //return inflater.inflate(R.layout.fragment_newsfeed, container, false);
        final View rootView = inflater.inflate(R.layout.fragment_newsfeed, container, false);
        recyclerView = (RecyclerView)  rootView.findViewById(R.id.newsfeed_recyclerView);

        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        newsFeedsList = new ArrayList<>();


        //Test Data

        RecyclerViewClickListener listener = new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
            }
        };
        getPosts();

        floatingActionButton = (com.github.clans.fab.FloatingActionButton) rootView.findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new CreatePostFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.slide_in_from_bottom, R.anim.slide_out_to_bottom, R.anim.slide_out_from_bottom, R.anim.slide_in_to_bottom);
                fragmentTransaction.add(R.id.content_main, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });


        return rootView;
    }

    public void getPosts() {
        progressDialog = ProgressDialog.show(getActivity(), null, "Loading");
        progressDialog.setCancelable(false);
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Log.e("Token", AuthUser.getAuthData().getToken());
        Log.e("Token", AuthUser.getAuthData().getSecret());
        Call<NewsfeedResponse> call = apiService.newsfeed(AuthUser.getAuthData().getToken(), AuthUser.getAuthData().getSecret());
        call.enqueue(new Callback<NewsfeedResponse>() {
            @Override
            public void onResponse(Call<NewsfeedResponse> call, Response<NewsfeedResponse> response) {
                progressDialog.cancel();
                if (response.body() != null) {
                    if (response.body().getFlag().equals(FLAG_SUCCESS)) {
                        newsfeedResponse = response.body();
                        adapter = new NewsfeedAdapter(newsfeedResponse.getPosts(), getActivity());
                        recyclerView.setAdapter(adapter);
                    } else
                        Toast.makeText(getActivity(), response.body().getFlag(), Toast.LENGTH_SHORT).show();
                } else Toast.makeText(getActivity(), "null", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<NewsfeedResponse> call, Throwable t) {
                progressDialog.cancel();
                Toast.makeText(getActivity(), "Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void onResume() {
        super.onResume();
    }
}
