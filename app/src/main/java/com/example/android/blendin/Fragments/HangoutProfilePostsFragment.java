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

import com.example.android.blendin.Adapters.HangoutProfileChatAdapter;
import com.example.android.blendin.Adapters.HangoutProfilePostsAdapter;
import com.example.android.blendin.Models.CommentModel;
import com.example.android.blendin.Models.NewsFeedModel;
import com.example.android.blendin.R;
import com.example.android.blendin.Responses.CommentsResponse;
import com.example.android.blendin.Responses.NewsfeedResponse;
import com.example.android.blendin.Retrofit.ApiClient;
import com.example.android.blendin.Retrofit.ApiInterface;
import com.example.android.blendin.Utility.AuthUser;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.android.blendin.Utility.Constants.FLAG_SUCCESS;

/**
 * A simple {@link Fragment} subclass.
 */
public class HangoutProfilePostsFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    List<NewsFeedModel> newsFeedsList;
    RecyclerView.LayoutManager layoutManager;
    String hangout_id;
    ProgressDialog progressDialog;
    public HangoutProfilePostsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_hangout_profile_posts, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.hangoutPosts_recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        Bundle bundle = getArguments();
        hangout_id = bundle.getString("hangout_id");

        newsFeedsList = new ArrayList<>();
        //Test Data

        getPosts();
        return rootView;
    }

    public void getPosts() {
        progressDialog = ProgressDialog.show(getActivity(), null, "Loading");
        progressDialog.setCancelable(false);
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Log.e("Token", AuthUser.getAuthData().getToken());
        Log.e("Secret", AuthUser.getAuthData().getSecret());
        Log.e("id", String.valueOf(hangout_id));
        Call<NewsfeedResponse> call = apiService.getPostsHangout(AuthUser.getAuthData().getToken(), AuthUser.getAuthData().getSecret(), hangout_id);

        call.enqueue(new Callback<NewsfeedResponse>() {
            @Override
            public void onResponse(Call<NewsfeedResponse> call, Response<NewsfeedResponse> response) {
                progressDialog.cancel();
                if (response.body() != null) {
                    if (response.body().getFlag().equals(FLAG_SUCCESS)) {
                        newsFeedsList = new ArrayList<NewsFeedModel>(response.body().getPosts());
                        adapter = new HangoutProfilePostsAdapter(newsFeedsList, getActivity());
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
}
