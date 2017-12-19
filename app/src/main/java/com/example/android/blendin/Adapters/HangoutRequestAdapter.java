package com.example.android.blendin.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.blendin.Fragments.HangoutProfileFragment;
import com.example.android.blendin.Fragments.ProfileFragment;
import com.example.android.blendin.Fragments.SquadFragment;
import com.example.android.blendin.Models.HangoutRequestModel;
import com.example.android.blendin.Models.RequestModel;
import com.example.android.blendin.Navigation_activity;
import com.example.android.blendin.R;
import com.example.android.blendin.Responses.HangoutRequestAcceptResponse;
import com.example.android.blendin.Responses.HangoutRequestRejectResponse;
import com.example.android.blendin.Responses.HangoutRequestResponse;
import com.example.android.blendin.Retrofit.ApiClient;
import com.example.android.blendin.Retrofit.ApiInterface;
import com.example.android.blendin.Utility.AuthUser;
import com.example.android.blendin.Utility.Constants;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by LeGenÐ on 12/19/2017.
 */

public class HangoutRequestAdapter extends RecyclerView.Adapter<HangoutRequestAdapter.ViewHolder> {

    List<HangoutRequestModel> hangoutList;
    HangoutRequestModel hangout;
    private Context context;

    public HangoutRequestAdapter(List<HangoutRequestModel> hangoutList, Context context) {
        this.hangoutList = hangoutList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_request, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        hangout = hangoutList.get(position);
        getPicasso(hangout.getPic(), holder.requestUserImage);
        holder.requestUserName.setText(hangout.getFirst_name() + " " + hangout.getLast_name());
        holder.requestUserLocation.setText(hangout.getLocation());
        holder.requestTimeAgo.setText(hangout.getCreated_at());
        holder.requestDesc.setText(hangout.getFirst_name() + " Invited you to hangout.");
        holder.requestTitle.setText(hangout.getTitle());


        holder.requestUserImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO : get user
                transmission(new ProfileFragment(), "Hamada Helal");
            }
        });
        holder.requestUserName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO : get user
                transmission(new ProfileFragment(), "Hamada Helal");
            }
        });

        holder.requestDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transmission(new HangoutProfileFragment(), "Fifa 18 Tournement");
            }
        });


        holder.requestAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
                Call<HangoutRequestAcceptResponse> call = apiService.acceptHangoutRequest(AuthUser.getAuthData().getToken(), AuthUser.getAuthData().getSecret(), hangout.getInvite_id());
                call.enqueue(new Callback<HangoutRequestAcceptResponse>() {
                    @Override
                    public void onResponse(Call<HangoutRequestAcceptResponse> call, Response<HangoutRequestAcceptResponse> response) {
                        if (response.body() != null) {
                            if (response.body().getStatus().equals(Constants.FLAG_SUCCESS)) {
                                hangoutList.remove(position);
                                notifyDataSetChanged();
                            } else
                                Toast.makeText(context, response.body().getStatus(), Toast.LENGTH_SHORT).show();
                        } else Toast.makeText(context, "null", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<HangoutRequestAcceptResponse> call, Throwable t) {
                        Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        holder.requestDecline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
                Call<HangoutRequestRejectResponse> call = apiService.rejectHangoutRequest(AuthUser.getAuthData().getToken(), AuthUser.getAuthData().getSecret(), hangout.getInvite_id());
                call.enqueue(new Callback<HangoutRequestRejectResponse>() {
                    @Override
                    public void onResponse(Call<HangoutRequestRejectResponse> call, Response<HangoutRequestRejectResponse> response) {
                        if (response.body() != null) {
                            if (response.body().getStatus().equals(Constants.FLAG_SUCCESS)) {
                                hangoutList.remove(position);
                                notifyDataSetChanged();
                            } else
                                Toast.makeText(context, response.body().getStatus(), Toast.LENGTH_SHORT).show();
                        } else Toast.makeText(context, "null", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<HangoutRequestRejectResponse> call, Throwable t) {
                        Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return hangoutList.size();
    }

    public void transmission(Fragment nxtfragment, String key) {
        FragmentTransaction fragmentTransaction = ((FragmentActivity) context).getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_in_from_bottom, R.anim.slide_out_to_bottom, R.anim.slide_out_from_bottom, R.anim.slide_in_to_bottom);
        fragmentTransaction.add(R.id.content_main, nxtfragment, key);
        Bundle bundle = new Bundle();
        bundle.putBoolean("auth", false);
        nxtfragment.setArguments(bundle);
        fragmentTransaction.commit();
        fragmentTransaction.addToBackStack("wtf");
        ((Navigation_activity) context).getSupportActionBar().setTitle(key);
    }

    void getPicasso(String temp, ImageView img) {
        Picasso.with(context)
                .load(Constants.BASE_URL + temp)
                .error(R.drawable.kappa2)
                .into(img);
    }

    void getPicasso(String temp, CircleImageView img) {
        Picasso.with(context)
                .load(Constants.BASE_URL + temp)
                .error(R.drawable.kappa2)
                .into(img);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView requestUserImage;
        TextView requestUserName;
        TextView requestUserLocation;
        TextView requestTimeAgo;
        TextView requestDesc;
        LinearLayout requestDetails;
        ImageButton requestAccept;
        ImageButton requestDecline;
        TextView requestTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            requestUserImage = (CircleImageView) itemView.findViewById(R.id.ivItemImageRequest);
            requestUserName = (TextView) itemView.findViewById(R.id.tvItemNameRequest);
            requestUserLocation = (TextView) itemView.findViewById(R.id.tvItemLocRequest);
            requestTimeAgo = (TextView) itemView.findViewById(R.id.tvItemTimeRequest);
            requestDesc = (TextView) itemView.findViewById(R.id.tvItemDiscRequest);
            requestDetails = (LinearLayout) itemView.findViewById(R.id.tvItemDetailsRequest);
            requestAccept = (ImageButton) itemView.findViewById(R.id.request_Accept);
            requestDecline = (ImageButton) itemView.findViewById(R.id.request_Decline);
            requestTitle = (TextView) itemView.findViewById(R.id.tvItemTitleRequest);
        }
    }
}
