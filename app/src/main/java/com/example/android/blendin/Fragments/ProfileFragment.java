package com.example.android.blendin.Fragments;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.blendin.Adapters.ProfileAdapter;
import com.example.android.blendin.Models.NewsFeedModel;
import com.example.android.blendin.Models.ProfileModel;
import com.example.android.blendin.Models.User;
import com.example.android.blendin.R;
import com.example.android.blendin.Responses.LoginResponse;
import com.example.android.blendin.Responses.ProfileResponse;
import com.example.android.blendin.Responses.ViewProfileResponse;
import com.example.android.blendin.Retrofit.ApiClient;
import com.example.android.blendin.Retrofit.ApiInterface;
import com.example.android.blendin.Utility.AuthUser;
import com.example.android.blendin.Utility.Constants;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.R.attr.key;
import static com.example.android.blendin.Utility.CommonMethods.*;
import static com.example.android.blendin.Utility.Constants.*;


import static android.R.attr.password;


public class ProfileFragment extends Fragment implements AppBarLayout.OnOffsetChangedListener {
    private static final int PERCENTAGE_TO_ANIMATE_AVATAR = 20;
    RecyclerView recyclerView;
    ProfileAdapter adapter;
    List<NewsFeedModel> profileModelList;
    AuthUser authUser;
    User user;
    View v;
    @BindView(R.id.profile_user_image)
    CircleImageView img;
    @BindView(R.id.profile_user_name)
    TextView userName;
    @BindView(R.id.profile_user_address)
    TextView address;
    @BindView(R.id.profile_user_email)
    TextView email;
    @BindView(R.id.profile_user_interests)
    TextView interests;
    private ImageView mProfileImage;
    private boolean mIsAvatarShown = true;
    private int mMaxScrollSize;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, v);
        Bundle bundle = getArguments();
        Boolean userType = bundle.getBoolean("type");
        if (userType)
        initAPI();
        else {
            String uuid = bundle.getString("uuid");
            viewProfile(uuid);
        }

        return v;
    }

    private void viewProfile(String uuid) {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ViewProfileResponse> call = apiInterface.viewUserProfile(AuthUser.getAuthData().getToken(), AuthUser.getAuthData().getSecret(), uuid);
        Log.e("Token", AuthUser.getAuthData().getToken());
        Log.e("Secret", AuthUser.getAuthData().getSecret());
        call.enqueue(new Callback<ViewProfileResponse>() {
            @Override
            public void onResponse(Call<ViewProfileResponse> call, Response<ViewProfileResponse> response) {
                if (response != null && response.body().getStatus().equals(FLAG_SUCCESS)) {
                    user = response.body().getUser();
                    updateView();
                }
            }

            @Override
            public void onFailure(Call<ViewProfileResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "failed", Toast.LENGTH_SHORT).show();
            }

        });
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
        if (mMaxScrollSize == 0)
            mMaxScrollSize = appBarLayout.getTotalScrollRange();

        int percentage = (Math.abs(i)) * 100 / mMaxScrollSize;

        if (percentage >= PERCENTAGE_TO_ANIMATE_AVATAR && mIsAvatarShown) {
            mIsAvatarShown = false;

            mProfileImage.animate()
                    .scaleY(0).scaleX(0)
                    .setDuration(200)
                    .start();
        }
        if (percentage <= PERCENTAGE_TO_ANIMATE_AVATAR && !mIsAvatarShown) {
            mIsAvatarShown = true;

            mProfileImage.animate()
                    .scaleY(1).scaleX(1)
                    .start();
        }
    }

    public void initAPI() {
        LoginResponse loginResponse = new Gson().fromJson(retrieveDataFromSharedPref(getActivity(), KEY_USER_DATA), LoginResponse.class);
        authUser = AuthUser.getAuthUser(loginResponse);
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ProfileResponse> call = apiInterface.getProfile(authUser.getToken(), authUser.getSecret());
        Log.e("kkk", authUser.getToken());
        Log.e("kkk1", authUser.getSecret());
        call.enqueue(new Callback<ProfileResponse>() {

            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {

                if (response != null && response.body().getStatus().equals(FLAG_SUCCESS)) {
                    user = response.body().getUser();
                    updateView();
                }
            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "failed", Toast.LENGTH_SHORT).show();
            }

        });
    }

    public void updateView() {

        userName.setText(user.getFirst_name() + " " + user.getLast_name());
        address.setText(user.getAddress());
        email.setText(user.getEmail());
        getPicasso(user.getPic(), img);
        //Log.e("kappa",user.getInterests()[0]);
        StringBuilder stringBuilder = new StringBuilder();
        for (String x : user.getInterests()) {
            stringBuilder.append(x).append(" , ");
        }
        interests.setText(stringBuilder.toString());

        recyclerView = (RecyclerView) v.findViewById(R.id.profile_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        AppBarLayout appbarLayout = (AppBarLayout) v.findViewById(R.id.profile_appbar);
        mProfileImage = (ImageView) v.findViewById(R.id.profile_user_image);
        appbarLayout.addOnOffsetChangedListener(this);
        mMaxScrollSize = appbarLayout.getTotalScrollRange();
        adapter = new ProfileAdapter(user.getPosts(), getActivity());
        recyclerView.setAdapter(adapter);

    }

    void getPicasso(String temp, CircleImageView img) {
        Picasso.with(getActivity())
                .load(Constants.BASE_URL_FOR_IMAGE + temp)
                .error(R.drawable.kappa2)
                .into(img);
    }
}
