package com.example.android.blendin.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.blendin.Models.HangoutProfileMemberModel;
import com.example.android.blendin.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by LeGenÐ on 12/13/2017.
 */

public class HangoutProfileMemeberAdapter extends RecyclerView.Adapter<HangoutProfileMemeberAdapter.ViewHolder> {
    List<HangoutProfileMemberModel> memberModelList;
    HangoutProfileMemberModel memberModel;
    private Context context;

    public HangoutProfileMemeberAdapter(List<HangoutProfileMemberModel> memberModelList, Context context) {
        this.memberModelList = memberModelList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_hangout_profile_member, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        memberModel = memberModelList.get(position);
        holder.userImage.setImageResource(memberModel.getImage());
        holder.userName.setText(memberModel.getName());
    }

    @Override
    public int getItemCount() {
        return memberModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView userImage;
        TextView userName;

        public ViewHolder(View itemView) {
            super(itemView);
            userImage = (CircleImageView) itemView.findViewById(R.id.hangoutDetails_member_image);
            userName = (TextView) itemView.findViewById(R.id.hangoutDetails_member_name);
        }
    }

}
