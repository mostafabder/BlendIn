package com.example.android.blendin.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.blendin.Models.NewsFeedModel;
import com.example.android.blendin.R;
import com.example.android.blendin.RecyclerViewClickListener;

import de.hdodenhof.circleimageview.CircleImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LeGenÐ on 11/29/2017.
 */

public class NewsfeedAdapter extends RecyclerView.Adapter<NewsfeedAdapter.ViewHolder> {
    List<NewsFeedModel> newsfeedItemsList;
    NewsFeedModel newsFeedModel;
    private Context context;
    private RecyclerViewClickListener recyclerViewClickListener;


    public NewsfeedAdapter(List<NewsFeedModel> newsfeedItemsList, Context context, RecyclerViewClickListener recyclerViewClickListener) {
        this.recyclerViewClickListener = recyclerViewClickListener;
        this.newsfeedItemsList = newsfeedItemsList;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return newsfeedItemsList.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_newsfeed,parent,false);
        return new ViewHolder(view, recyclerViewClickListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        newsFeedModel = newsfeedItemsList.get(position);
        holder.userProfileImage.setImageResource(newsFeedModel.getAvatar());
        holder.userNameTxt.setText(newsFeedModel.getName());
        holder.userLocationTxt.setText(newsFeedModel.getCity());
        holder.postTimeTxt.setText(newsFeedModel.getTimeAgo());
        holder.postImage.setImageResource(newsFeedModel.getImage());
        holder.postMainTxt.setText(newsFeedModel.getActivity());
        holder.postDescTxt.setText(newsFeedModel.getDisc());
        holder.postLikesCount.setText(newsFeedModel.getLikes());
        holder.postCommentsCount.setText(newsFeedModel.getComments());


    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        // each data item is just a string in this case
        public CircleImageView userProfileImage;
        public TextView userNameTxt;
        public TextView userLocationTxt;
        public TextView postTimeTxt;
        public ImageView postImage;
        public TextView postMainTxt;
        public TextView postDescTxt;
        public TextView postLikesCount;
        public TextView postCommentsCount;
        private RecyclerViewClickListener mListener;

        public ViewHolder(View itemView, RecyclerViewClickListener listener) {
            super(itemView);
            userProfileImage = (CircleImageView) itemView.findViewById(R.id.profile_image);
            userNameTxt = (TextView) itemView.findViewById(R.id.tvItemNameNews);
            userLocationTxt = (TextView) itemView.findViewById(R.id.tvItemLocNews);
            postTimeTxt = (TextView) itemView.findViewById(R.id.tvItemTimeNews);
            postImage = (ImageView) itemView.findViewById(R.id.post_image);
            postMainTxt = (TextView) itemView.findViewById(R.id.tvItemActivityNews);
            postDescTxt = (TextView) itemView.findViewById(R.id.tvItemDiscNews);
            postLikesCount = (TextView) itemView.findViewById(R.id.tvItemLikesNumNews);
            postCommentsCount = (TextView) itemView.findViewById(R.id.tvItemCommentsNumNews);
            mListener = listener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mListener.onClick(view, getAdapterPosition());
        }
    }
}
