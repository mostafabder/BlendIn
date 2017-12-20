package com.example.android.blendin.Fragments;


import android.app.ProgressDialog;
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
import android.widget.Toast;

import com.example.android.blendin.Adapters.HangoutAdapter;
import com.example.android.blendin.Adapters.HangoutProfileChatAdapter;
import com.example.android.blendin.Models.CommentModel;
import com.example.android.blendin.Models.HangoutModel;
import com.example.android.blendin.Models.HangoutProfileMemberModel;
import com.example.android.blendin.Models.HangoutProfileModel;
import com.example.android.blendin.R;
import com.example.android.blendin.Responses.CommentsResponse;
import com.example.android.blendin.Responses.HangoutProfileResponse;
import com.example.android.blendin.Responses.MemberResponse;
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
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.android.blendin.Utility.Constants.FLAG_SUCCESS;

/**
 * A simple {@link Fragment} subclass.
 */
public class HangoutProfileDetailsFragment extends Fragment {

    ProgressDialog progressDialog;
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
    String title, disc, location, startDate, endDate, startTime, endTime, activity, ppl, hangout_id;
    int auth;
    Bundle bundle;
    private Bitmap b = null;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_hangout_profile_details, container, false);
        ButterKnife.bind(this, rootView);
        bundle = getArguments();
        recyclerView = (RecyclerView) rootView.findViewById(R.id.hangoutDetails_recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        inti();
        return rootView;
    }

    public void update(String desc, String activity, String location, String startTime, String endTime, List<HangoutModel> hangoutModels) {
        tv_disc.setText(desc);
        tv_activity.setText(activity);
        tv_location.setText(location);
        tv_startTime.setText(startTime);
        tv_endTime.setText(endTime);
        adapter = new HangoutAdapter(getActivity(), hangoutModels, null, null);
        recyclerView.setAdapter(adapter);
    }

    public void inti() {
        auth = bundle.getInt("auth");
        Log.e("auth", String.valueOf(auth) + "     ");
        if (auth == 1) {
            ppl = bundle.getString("people");
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

            update(disc, activity, location, startDate + "  " + startTime, endDate + " " + endTime, hangoutModelList);
        } else if (auth == 2 || auth == 3) {
            getMembers();
            hangout_id = bundle.getString("hangout_id");
            progressDialog = ProgressDialog.show(getActivity(), null, "Loading");
            progressDialog.setCancelable(false);
            ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
            Log.e("Token", AuthUser.getAuthData().getToken());
            Log.e("Secret", AuthUser.getAuthData().getSecret());
            Log.e("IDH", hangout_id + "     ");
            Call<HangoutProfileResponse> call = apiService.viewHangout(AuthUser.getAuthData().getToken(), AuthUser.getAuthData().getSecret(), hangout_id);
            call.enqueue(new Callback<HangoutProfileResponse>() {
                @Override
                public void onResponse(Call<HangoutProfileResponse> call, Response<HangoutProfileResponse> response) {
                    progressDialog.cancel();
                    if (response.body() != null) {
                        if (response.body().getStatus().equals(FLAG_SUCCESS)) {
                            progressDialog.cancel();
                            //TODO 10
                            //activity,location,date,time,members
                            HangoutProfileModel x = response.body().getHangout();
                            hangoutModelList = new ArrayList<HangoutModel>(response.body().getMembers());
                            Log.e("size", String.valueOf(hangoutModelList.size()) + "           ");
                            update(x.getDescription(), x.getActivity(), x.getLocation(), x.getCreated_at(), x.getEnd_at(), hangoutModelList);
                        } else
                            Toast.makeText(getActivity(), response.body().getStatus(), Toast.LENGTH_SHORT).show();

                    } else Toast.makeText(getActivity(), "null", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<HangoutProfileResponse> call, Throwable t) {
                    progressDialog.cancel();
                    Toast.makeText(getActivity(), "Failed", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void getMembers() {
        hangout_id = bundle.getString("hangout_id");
        progressDialog = ProgressDialog.show(getActivity(), null, "Loading");
        progressDialog.setCancelable(false);
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Log.e("Token", AuthUser.getAuthData().getToken());
        Log.e("Secret", AuthUser.getAuthData().getSecret());
        Log.e("iddh", hangout_id + "   ");
        Call<MemberResponse> call = apiService.getHangoutMembers(AuthUser.getAuthData().getToken(), AuthUser.getAuthData().getSecret(), hangout_id);
        call.enqueue(new Callback<MemberResponse>() {
            @Override
            public void onResponse(Call<MemberResponse> call, Response<MemberResponse> response) {
                progressDialog.cancel();
                if (response.body() != null) {
                    if (response.body().getStatus().equals(FLAG_SUCCESS)) {
                        modelList = new ArrayList<HangoutProfileMemberModel>(response.body().getMembers());
                    } else
                        Toast.makeText(getActivity(), response.body().getStatus(), Toast.LENGTH_SHORT).show();

                } else Toast.makeText(getActivity(), "null", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<MemberResponse> call, Throwable t) {
                progressDialog.cancel();
                Toast.makeText(getActivity(), "Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
