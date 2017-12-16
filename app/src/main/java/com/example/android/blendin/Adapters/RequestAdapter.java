package com.example.android.blendin.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.blendin.Fragments.HangoutProfileFragment;
import com.example.android.blendin.Fragments.ProfileFragment;
import com.example.android.blendin.Fragments.SquadFragment;
import com.example.android.blendin.Models.RequestModel;
import com.example.android.blendin.Navigation_activity;
import com.example.android.blendin.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by LeGenÐ on 11/29/2017.
 */

public class RequestAdapter extends RecyclerView.Adapter<RequestAdapter.ViewHolder> {
        List<RequestModel> requestModelList;
        RequestModel requestModel;
    boolean flag;
        private Context context;

    public RequestAdapter(List<RequestModel> requestModelList, Context context, boolean flag) {
        this.requestModelList = requestModelList;
        this.context = context;
        this.flag = flag;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_request,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        requestModel = requestModelList.get(position);
        holder.requestUserImage.setImageResource(requestModel.getAvatar());
        holder.requestUserName.setText(requestModel.getName());
        holder.requestUserLocation.setText(requestModel.getLocation());
        holder.requestTimeAgo.setText(requestModel.getTimeAgo());
        holder.requestDesc.setText(requestModel.getDescription());

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

        if (flag) {
            holder.requestDetails.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    transmission(new HangoutProfileFragment(), "Fifa 18 Tournement");
                }
            });
        } else {
            holder.requestDetails.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    transmission(new SquadFragment(), "Fifa 18 Players");
                }
            });
        }


        holder.requestAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Accepted", Toast.LENGTH_LONG).show();
            }
        });

        holder.requestDecline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Declined", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return requestModelList.size();
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

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView requestUserImage;
        TextView requestUserName;
        TextView requestUserLocation;
        TextView requestTimeAgo;
        TextView requestDesc;
        LinearLayout requestDetails;
        ImageButton requestAccept;
        ImageButton requestDecline;

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
        }
    }
}
