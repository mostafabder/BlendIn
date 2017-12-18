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
import com.example.android.blendin.Utility.Constants;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Luffy on 12/1/2017.
 */

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ViewHolder> {
    List<NewsFeedModel> newsfeedItemsList;
    NewsFeedModel newsFeedModel;
      Context context;

    public ProfileAdapter(List<NewsFeedModel> newsfeedItemsList, Context context) {
        this.newsfeedItemsList = newsfeedItemsList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_newsfeed, parent, false);
        return new ProfileAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        newsFeedModel = newsfeedItemsList.get(position);
        getPicasso(newsFeedModel.getAvatar(), holder.userProfileImage);
        holder.titletxt.setText(newsFeedModel.getTitle());
        holder.userNameTxt.setText(newsFeedModel.getName());
        holder.userLocationTxt.setText(newsFeedModel.getLocation());
        holder.postTimeTxt.setText(newsFeedModel.getCreated_at());
        getPicasso(newsFeedModel.getHangout_pic(), holder.postImage);
        holder.postMainTxt.setText(newsFeedModel.getActivity());
        holder.postDescTxt.setText(newsFeedModel.getContent());
        holder.postLikesCount.setText(newsFeedModel.getLoves());
        holder.postCommentsCount.setText(newsFeedModel.getComments());
        holder.likeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (newsfeedItemsList.get(position).isLovedByThisUser()) {
                    holder.likeImage.setImageResource(R.drawable.dislike);
                    //newsfeedItemsList.get(position).setLike(false);
                } else {
                    holder.likeImage.setImageResource(R.drawable.like);
                    //newsfeedItemsList.get(position).setLike(true);
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

    @Override
    public int getItemCount() {
        return newsfeedItemsList.size();
    }

    void getPicasso(String temp, CircleImageView img) {
        Picasso.with(context)
                .load(Constants.BASE_URL_FOR_IMAGE + temp)
                .error(R.drawable.kappa2)
                .into(img);
    }

    void getPicasso(String temp, ImageView img) {
        Picasso.with(context)
                .load(Constants.BASE_URL_FOR_IMAGE + temp)
                .error(R.drawable.kappa2)
                .into(img);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
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
        TextView titletxt;
        public ViewHolder(View itemView) {
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
            likeImage = (ImageView) itemView.findViewById(R.id.iv_newsfeed_like);
            commentLayout = (LinearLayout) itemView.findViewById(R.id.ll_newsfeed_comment);
            titletxt = (TextView) itemView.findViewById(R.id.tvItemTitleNews);
        }
    }
}
