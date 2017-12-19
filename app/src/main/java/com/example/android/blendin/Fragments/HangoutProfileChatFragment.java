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
import com.example.android.blendin.Models.CommentModel;
import com.example.android.blendin.Models.SquadChatMessageModel;
import com.example.android.blendin.R;
import com.example.android.blendin.Responses.CommentsResponse;
import com.example.android.blendin.Responses.PushHangoutResponse;
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
public class HangoutProfileChatFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    List<CommentModel> chatMessageModelList;
    RecyclerView.LayoutManager layoutManager;
    ProgressDialog progressDialog;
    String hangout_id;
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
        Bundle bundle = getArguments();
        hangout_id = bundle.getString("hangout_id");
        getChat();

        return rootView;
    }

    public void getChat() {
        progressDialog = ProgressDialog.show(getActivity(), null, "Loading");
        progressDialog.setCancelable(false);
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Log.e("Token", AuthUser.getAuthData().getToken());
        Log.e("Secret", AuthUser.getAuthData().getSecret());
        Log.e("id", String.valueOf(hangout_id));
        Call<CommentsResponse> call = apiService.getCommentsHangout(AuthUser.getAuthData().getToken(), AuthUser.getAuthData().getSecret(), hangout_id);

        call.enqueue(new Callback<CommentsResponse>() {
            @Override
            public void onResponse(Call<CommentsResponse> call, Response<CommentsResponse> response) {
                progressDialog.cancel();
                if (response.body() != null) {
                    if (response.body().getStatus().equals(FLAG_SUCCESS)) {
                        chatMessageModelList = new ArrayList<CommentModel>(response.body().getMessages());
                        adapter = new HangoutProfileChatAdapter(chatMessageModelList, getActivity());
                        recyclerView.setAdapter(adapter);
                    } else
                        Toast.makeText(getActivity(), response.body().getStatus(), Toast.LENGTH_SHORT).show();

                } else Toast.makeText(getActivity(), "null", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<CommentsResponse> call, Throwable t) {
                progressDialog.cancel();
                Toast.makeText(getActivity(), "Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
