package com.example.android.blendin.Fragments;


import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
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

import com.example.android.blendin.Adapters.SquadChatMessageAdapter;
import com.example.android.blendin.Models.SquadChatMessageModel;
import com.example.android.blendin.Models.SquadProfileModel;
import com.example.android.blendin.R;
import com.example.android.blendin.Responses.SquadProfileResponse;
import com.example.android.blendin.Retrofit.ApiClient;
import com.example.android.blendin.Retrofit.ApiInterface;
import com.example.android.blendin.Utility.AuthUser;
import com.example.android.blendin.Utility.Constants;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.android.blendin.Utility.Constants.FLAG_SUCCESS;

/**
 * A simple {@link Fragment} subclass.
 */

public class SquadFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    List<SquadChatMessageModel> squadChatMessageModelList;
    RecyclerView.LayoutManager layoutManager;
    String id;
    View rootView;
    AuthUser authUser;

    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;

    @BindView(R.id.squadPage_Image)
    ImageView squadImg;

    @BindView(R.id.groupPage_Desc)
    TextView desc;

    @BindView(R.id.groupPage_creator_name)
    TextView creator_name;

    @BindView(R.id.groupPage_creator_image)
    CircleImageView creator_image;

    @BindView(R.id.groupPage_date_created)
    TextView date_created;

    @BindView(R.id.groupPage_membersCount)
    TextView membersCount;

    @BindView(R.id.groupPage_hangoutsCount)
    TextView hangoutsCount;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_squad, container, false);
        ButterKnife.bind(this, rootView);
        initAPI();

        return rootView;
    }

    public void initAPI() {
        //LoginResponse loginResponse = new Gson().fromJson(retrieveDataFromSharedPref(getActivity(), KEY_USER_DATA), LoginResponse.class);
        Bundle bundle = getArguments();
        authUser = AuthUser.getAuthData();
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        String s = bundle.getString("squadID");
        Log.e("kappa4", s);
        Call<SquadProfileResponse> call = apiInterface.squadProfile(authUser.getToken(), authUser.getSecret(), s);
        Log.e("kkk", authUser.getToken());
        Log.e("kkk1", authUser.getSecret());
        call.enqueue(new Callback<SquadProfileResponse>() {
            @Override
            public void onResponse(Call<SquadProfileResponse> call, final Response<SquadProfileResponse> response) {
                if (response != null && response.body().getStatus().equals(FLAG_SUCCESS)) {
                    SquadProfileModel squad = response.body().getSquad();
                    getPicasso(squad.getSquad_pic(), squadImg);
                    collapsingToolbarLayout.setTitle(squad.getTitle());
                    desc.setText(squad.getDescription());
                    membersCount.setText(squad.getSquad_count());
                    hangoutsCount.setText(squad.getHangouts_count());
                    getPicasso(squad.getAdmin_pic(), creator_image);
                    creator_name.setText(squad.getAdmin_first_name() + " " + squad.getAdmin_last_name());
                    date_created.setText(squad.getCreated_at());

                    recyclerView = (RecyclerView) rootView.findViewById(R.id.groupPage_recyclerView);
                    recyclerView.setHasFixedSize(true);
                    layoutManager = new LinearLayoutManager(getActivity());
                    recyclerView.setLayoutManager(layoutManager);
                    adapter = new SquadChatMessageAdapter(response.body().getSquad().getChat(), getActivity());
                    recyclerView.setAdapter(adapter);

                }
            }

            @Override
            public void onFailure(Call<SquadProfileResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "failed", Toast.LENGTH_SHORT).show();
            }

        });
    }

    void getPicasso(String temp, ImageView img) {
        Picasso.with(getActivity())
                .load(Constants.BASE_URL + temp)
                .error(R.drawable.kappa2)
                .into(img);
    }

    void getPicasso(String temp, CircleImageView img) {
        Picasso.with(getActivity())
                .load(Constants.BASE_URL + temp)
                .error(R.drawable.kappa2)
                .into(img);
    }

}
