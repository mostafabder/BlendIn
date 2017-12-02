package com.example.android.blendin.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.blendin.Models.ProfileModel;
import com.example.android.blendin.R;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Luffy on 12/1/2017.
 */

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ViewHolder> {
        List<ProfileModel> profileItemsList;
      Context context;

    public ProfileAdapter(List<ProfileModel> profileItemsList, Context context) {
        this.profileItemsList = profileItemsList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_profile,parent,false);
        return new ProfileAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvTop.setText(profileItemsList.get(position).getTopText());
        holder.iv.setImageResource(profileItemsList.get(position).getImg());
        holder.likes.setText(profileItemsList.get(position).getLikesCount());
        holder.comments.setText(profileItemsList.get(position).getCommentsCount());
    }

    @Override
    public int getItemCount() {
        return profileItemsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv;
        TextView tvTop;
        TextView likes;
        TextView comments;
        public ViewHolder(View itemView) {
            super(itemView);
            iv=(ImageView) itemView.findViewById(R.id.iv_profileImage);
            tvTop=(TextView)itemView.findViewById(R.id.tv_top);
            likes=(TextView) itemView.findViewById(R.id.tvItemLikesNumProfile);
            comments=(TextView) itemView.findViewById(R.id.tvItemCommentsNumProfile);
        }
    }
}
