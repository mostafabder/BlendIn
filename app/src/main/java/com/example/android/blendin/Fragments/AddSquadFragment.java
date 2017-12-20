package com.example.android.blendin.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.android.blendin.Adapters.HangoutAdapter;
import com.example.android.blendin.Adapters.RecentlyMembersAdapter;
import com.example.android.blendin.Models.HangoutModel;
import com.example.android.blendin.Models.RecentlyHangwithModel;
import com.example.android.blendin.R;
import com.example.android.blendin.Responses.CreateSquadResponse;
import com.example.android.blendin.Responses.PushHangoutResponse;
import com.example.android.blendin.Responses.RecentlyHangwithResponse;
import com.example.android.blendin.Retrofit.ApiClient;
import com.example.android.blendin.Retrofit.ApiInterface;
import com.example.android.blendin.Utility.AuthUser;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import mehdi.sakout.fancybuttons.FancyButton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.android.blendin.Utility.Constants.FLAG_SUCCESS;

public class AddSquadFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    LinearLayoutManager linearLayoutManager;
    List<RecentlyHangwithModel> hangoutModelList;
    List<String> recetMembers;
    ProgressDialog progressDialog;
    Bundle bundle;
    @BindView(R.id.et_addSquad_desc)
    EditText et_desc;
    @BindView(R.id.et_addSquad_title)
    EditText et_title;
    @BindView(R.id.btn_createSquad)
    FancyButton fancyButton;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_add_squad, container, false);
        ButterKnife.bind(this, v);
        bundle = new Bundle();
        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_addSquad);
        linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        getPeopleRecently();
        fancyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pushSquad();
                transmition(new SquadFragment());
            }
        });
        return v;
    }

    public StringBuilder getMemberFromJson() {
        StringBuilder members = new StringBuilder();
        Log.e("size", String.valueOf(recetMembers.size()));
        for (int i = 0; i < recetMembers.size(); i++) {
            if (i == recetMembers.size() - 1)
                members.append(recetMembers.get(i));
            else members.append(recetMembers.get(i)).append(",");
        }
        return members;
    }

    void pushSquad() {
        progressDialog = ProgressDialog.show(getActivity(), null, "Loading");
        progressDialog.setCancelable(false);
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Log.e("Token", AuthUser.getAuthData().getToken());
        Log.e("Secret", AuthUser.getAuthData().getSecret());
        Log.e("title", et_title.getText().toString());
        Log.e("desc", et_desc.getText().toString());
        Call<CreateSquadResponse> call = apiService.createSquad(AuthUser.getAuthData().getToken(), AuthUser.getAuthData().getSecret(), et_title.getText().toString(),
                et_desc.getText().toString(), "/upload/1.jpg", getMemberFromJson().toString());

        call.enqueue(new Callback<CreateSquadResponse>() {
            @Override
            public void onResponse(Call<CreateSquadResponse> call, Response<CreateSquadResponse> response) {
                progressDialog.cancel();
                if (response.body() != null) {
                    if (response.body().getStatus().equals(FLAG_SUCCESS)) {
                        Log.e("id", response.body().getSquad_id());
                        bundle.putString("squad_id", response.body().getSquad_id());
                        Toast.makeText(getActivity(), "Created", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(getActivity(), response.body().getStatus(), Toast.LENGTH_SHORT).show();
                } else Toast.makeText(getActivity(), "null", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<CreateSquadResponse> call, Throwable t) {
                progressDialog.cancel();
                Toast.makeText(getActivity(), "Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    void transmition(Fragment nxt) {
        Fragment fragment = nxt;
        fragment.setArguments(bundle);
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_left, R.anim.exit_right);
        fragmentTransaction.add(R.id.content_main, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    void getPeopleRecently() {
        recetMembers = new ArrayList<>();
        progressDialog = ProgressDialog.show(getActivity(), null, "Loading");
        progressDialog.setCancelable(false);
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Log.e("Token", AuthUser.getAuthData().getToken());
        Log.e("Secret", AuthUser.getAuthData().getSecret());
        Call<RecentlyHangwithResponse> call = apiService.getRecentMembers(AuthUser.getAuthData().getToken(), AuthUser.getAuthData().getSecret());
        call.enqueue(new Callback<RecentlyHangwithResponse>() {
            @Override
            public void onResponse(Call<RecentlyHangwithResponse> call, Response<RecentlyHangwithResponse> response) {
                progressDialog.cancel();
                if (response.body() != null) {
                    if (response.body().getStatus().equals(FLAG_SUCCESS)) {
                        hangoutModelList = new ArrayList<RecentlyHangwithModel>(response.body().getMembers());
                        adapter = new RecentlyMembersAdapter(getActivity(), hangoutModelList, recetMembers);
                        recyclerView.setAdapter(adapter);
                    } else
                        Toast.makeText(getActivity(), response.body().getStatus(), Toast.LENGTH_SHORT).show();
                } else Toast.makeText(getActivity(), "null", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<RecentlyHangwithResponse> call, Throwable t) {
                progressDialog.cancel();
                Toast.makeText(getActivity(), "Failed", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
