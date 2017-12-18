package com.example.android.blendin.Adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.android.blendin.Fragments.CommentsFragment;
import com.example.android.blendin.Models.NewsFeedModel;
import com.example.android.blendin.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by LeGenÐ on 12/13/2017.
 */

public class HangoutProfilePostsAdapter extends RecyclerView.Adapter<HangoutProfilePostsAdapter.ViewHolder> {

    List<NewsFeedModel> newsfeedItemsList;
    NewsFeedModel newsFeedModel;
    private Context context;

    public HangoutProfilePostsAdapter(List<NewsFeedModel> newsfeedItemsList, Context context) {
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
                .inflate(R.layout.item_hangout_profile_post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

//        newsFeedModel = newsfeedItemsList.get(position);
//        holder.userProfileImage.setImageResource(newsFeedModel.getAvatar());
//        holder.userNameTxt.setContent(newsFeedModel.getName());
//        holder.userLocationTxt.setContent(newsFeedModel.getCity());
//        holder.postTimeTxt.setContent(newsFeedModel.getTimeAgo());
//        holder.postImage.setImageResource(newsFeedModel.getHangout_pic());
//        holder.postMainTxt.setContent(newsFeedModel.getActivity());
//        holder.postDescTxt.setContent(newsFeedModel.getDisc());
//        holder.postLikesCount.setContent(newsFeedModel.getLikes());
//        holder.postCommentsCount.setContent(newsFeedModel.getComments());
//        holder.likeImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (newsfeedItemsList.get(position).isLike()) {
//                    holder.likeImage.setImageResource(R.drawable.dislike);
//                    newsfeedItemsList.get(position).setLike(false);
//                } else {
//                    holder.likeImage.setImageResource(R.drawable.like);
//                    newsfeedItemsList.get(position).setLike(true);
//                }
//
//            }
//        });
//        holder.commentLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Fragment fragment = new CommentsFragment();
//                FragmentManager fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.setCustomAnimations(R.anim.slide_in_from_bottom, R.anim.slide_out_to_bottom, R.anim.slide_out_from_bottom, R.anim.slide_in_to_bottom);
//                fragmentTransaction.add(R.id.content_main, fragment);
//                fragmentTransaction.addToBackStack(null);
//                fragmentTransaction.commit();
//            }
//        });

        newsFeedModel = newsfeedItemsList.get(position);
        //
        holder.userNameTxt.setText(newsFeedModel.getName());
        holder.userLocationTxt.setText(newsFeedModel.getLocation());
        holder.postTimeTxt.setText(newsFeedModel.getCreated_at());
        //
        holder.postMainTxt.setText(newsFeedModel.getActivity());
        holder.postDescTxt.setText(newsFeedModel.getContent());
        holder.postLikesCount.setText(newsFeedModel.getLoves());
        holder.postCommentsCount.setText(newsFeedModel.getComments());
        holder.likeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (newsfeedItemsList.get(position).isLovedByThisUser()) {
                    holder.likeImage.setImageResource(R.drawable.dislike);
                    newsfeedItemsList.get(position).setLovedByThisUser(false);
                } else {
                    holder.likeImage.setImageResource(R.drawable.like);
                    newsfeedItemsList.get(position).setLovedByThisUser(true);
                }

            }
        });
        holder.commentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new CommentsFragment();
                FragmentManager fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.slide_in_from_bottom, R.anim.slide_out_to_bottom, R.anim.slide_out_from_bottom, R.anim.slide_in_to_bottom);
                fragmentTransaction.add(R.id.content_main, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

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
        public ImageView likeImage;
        public LinearLayout commentLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            userProfileImage = (CircleImageView) itemView.findViewById(R.id.hangoutPost_user_image);
            userNameTxt = (TextView) itemView.findViewById(R.id.hangoutPost_username);
            userLocationTxt = (TextView) itemView.findViewById(R.id.hangoutPost_location);
            postTimeTxt = (TextView) itemView.findViewById(R.id.hangoutPost_date);
            postImage = (ImageView) itemView.findViewById(R.id.hangoutPost_image);
            postMainTxt = (TextView) itemView.findViewById(R.id.hangoutPost_Title);
            postDescTxt = (TextView) itemView.findViewById(R.id.hangoutPost_Desc);
            postLikesCount = (TextView) itemView.findViewById(R.id.hangoutPost_likes_count);
            postCommentsCount = (TextView) itemView.findViewById(R.id.hangoutPost_comments_count);
            likeImage = (ImageView) itemView.findViewById(R.id.hangoutPost_like);
            commentLayout = (LinearLayout) itemView.findViewById(R.id.hangoutPost_ll_comment);

        }

       /* @Override
        public void onClick(View view) {
            mListener.onClick(view, getAdapterPosition());
        }*/
    }
}
