package com.example.android.blendin.Fragments;


import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.blendin.Adapters.RecentlyMembersAdapter;
import com.example.android.blendin.Adapters.SquadChatMessageAdapter;
import com.example.android.blendin.Models.CommentModel;
import com.example.android.blendin.Models.RecentlyHangwithModel;
import com.example.android.blendin.Models.SquadChatMessageModel;
import com.example.android.blendin.Models.SquadProfileModel;
import com.example.android.blendin.R;
import com.example.android.blendin.Responses.CommentResponse;
import com.example.android.blendin.Responses.CommentsResponse;
import com.example.android.blendin.Responses.RecentlyHangwithResponse;
import com.example.android.blendin.Responses.SquadProfileResponse;
import com.example.android.blendin.Retrofit.ApiClient;
import com.example.android.blendin.Retrofit.ApiInterface;
import com.example.android.blendin.Utility.AuthUser;
import com.example.android.blendin.Utility.Constants;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
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
    List<RecentlyHangwithModel> hangoutModelList;
    List<CommentModel> commentModels;
    RecyclerView.LayoutManager layoutManager;
    String id;
    View rootView;
    AuthUser authUser;
    Bundle bundle;
    String squad_id;
    List<String> recetMembers;
    ProgressDialog progressDialog;
    ArrayList<Integer> userItems = new ArrayList<>();
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

    @BindView(R.id.et_squad_sendMessage)
    EditText et_send;

    @BindView(R.id.iv_squad_sendMessage)
    ImageView iv_send;

    @BindView(R.id.iv_addMember_squad)
    CircleImageView iv_addMember;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_squad, container, false);
        ButterKnife.bind(this, rootView);
        Bundle bundle = getArguments();
        squad_id = bundle.getString("squad_id");
        iv_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addChat();
            }
        });

        iv_addMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        recyclerView = (RecyclerView) rootView.findViewById(R.id.groupPage_recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        initAPI();
        return rootView;
    }

    public void getChat() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Log.e("kappa4", squad_id);
        Call<CommentsResponse> call = apiInterface.getSquadChat(authUser.getToken(), authUser.getSecret(), squad_id);
        Log.e("Token", authUser.getToken());
        Log.e("Secret", authUser.getSecret());
        call.enqueue(new Callback<CommentsResponse>() {
            @Override
            public void onResponse(Call<CommentsResponse> call, final Response<CommentsResponse> response) {
                if (response != null && response.body().getStatus().equals(FLAG_SUCCESS)) {

                    commentModels = new ArrayList<CommentModel>(response.body().getMessages());
                    adapter = new SquadChatMessageAdapter(commentModels, getActivity());
                    recyclerView.setAdapter(adapter);
                }

            }

            @Override
            public void onFailure(Call<CommentsResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "failed", Toast.LENGTH_SHORT).show();
            }

        });

    }

    public void addChat() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Log.e("kappa4", squad_id + "   ");
        Call<CommentResponse> call = apiInterface.addCommentSquad(authUser.getToken(), authUser.getSecret(), squad_id, et_send.getText().toString());
        Log.e("Token", authUser.getToken() + "   ");
        Log.e("Secret", authUser.getSecret() + "   ");
        call.enqueue(new Callback<CommentResponse>() {
            @Override
            public void onResponse(Call<CommentResponse> call, final Response<CommentResponse> response) {
                if (response != null && response.body().getStatus().equals(FLAG_SUCCESS)) {
                    getChat();
                }
            }

            @Override
            public void onFailure(Call<CommentResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "failed", Toast.LENGTH_SHORT).show();
            }

        });

    }

    public void initAPI() {
        //LoginResponse loginResponse = new Gson().fromJson(retrieveDataFromSharedPref(getActivity(), KEY_USER_DATA), LoginResponse.class);
        authUser = AuthUser.getAuthData();
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Log.e("kappa4", squad_id + "    ");
        Call<SquadProfileResponse> call = apiInterface.squadProfile(authUser.getToken(), authUser.getSecret(), squad_id);
        Log.e("Token", authUser.getToken() + "      ");
        Log.e("Secret", authUser.getSecret() + "      ");
        call.enqueue(new Callback<SquadProfileResponse>() {
            @Override
            public void onResponse(Call<SquadProfileResponse> call, final Response<SquadProfileResponse> response) {
                if (response != null && response.body().getStatus().equals(FLAG_SUCCESS)) {
                    SquadProfileModel squad = response.body().getSquad();
                    Log.e("BODY", response.body().toString());
                    getPicasso(squad.getSquad_pic(), squadImg);
                    collapsingToolbarLayout.setTitle(squad.getTitle());
                    desc.setText(squad.getDescription());
                    membersCount.setText(squad.getSquad_count());
                    hangoutsCount.setText(squad.getHangouts_count());
                    getPicasso(squad.getAdmin_pic(), creator_image);
                    creator_name.setText(squad.getAdmin_first_name() + " " + squad.getAdmin_last_name());
                    date_created.setText(squad.getCreated_at());
                    commentModels = new ArrayList<CommentModel>(response.body().getSquad().getChat());
                    adapter = new SquadChatMessageAdapter(commentModels, getActivity());
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
                        for (int i = 0; i < hangoutModelList.size(); i++)
                            recetMembers.add(hangoutModelList.get(i).getUuid());
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

//    public void dialogMembers(){
//        AlertDialog.Builder mBuilder=new AlertDialog.Builder(getActivity());
//        mBuilder.setTitle("members");
//        Boolean[]checkedItems=new Boolean[recetMembers.size()];
//
//        mBuilder.setMultiChoiceItems(recetMembers, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
//
//            }
//        })
//        mBuilder.setCancelable(false);
//        mBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                String item="";
//                for(int i=0;i<userItems.size();i++) {
//                    item += listItems[userItems.get(i)];
//                    if (i != userItems.size() - 1)
//                        item += ", ";
//                }
//                selectedItems.setText(item);
//            }
//        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.cancel();
//            }
//        }).setNeutralButton("Clear all", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                for(int i=0;i<checkedItems.length;i++)
//                {
//                    checkedItems[i]=false;
//                    userItems.clear();
//                    selectedItems.setText("");
//                }
//            }
//        });
//        AlertDialog alertDialog=mBuilder.create();
//        alertDialog.show();
//    }
}
