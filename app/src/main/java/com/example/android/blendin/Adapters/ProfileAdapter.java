package com.example.android.blendin.Adapters;

import android.content.Context;
import android.media.Image;
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
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.tvTop.setText(profileItemsList.get(position).getTopText());
        holder.iv.setImageResource(profileItemsList.get(position).getImg());
        holder.likes.setText(profileItemsList.get(position).getLikesCount());
        holder.comments.setText(profileItemsList.get(position).getCommentsCount());
        holder.likeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (profileItemsList.get(position).isLike()) {
                    holder.likeImage.setImageResource(R.drawable.dislike);
                    profileItemsList.get(position).setLike(false);
                } else {
                    holder.likeImage.setImageResource(R.drawable.like);
                    profileItemsList.get(position).setLike(true);
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

    @Override
    public int getItemCount() {
        return profileItemsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv;
        TextView tvTop;
        TextView likes;
        TextView comments;
        ImageView likeImage;
        LinearLayout commentLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            iv=(ImageView) itemView.findViewById(R.id.iv_profileImage);
            tvTop=(TextView)itemView.findViewById(R.id.tv_top);
            likes=(TextView) itemView.findViewById(R.id.tvItemLikesNumProfile);
            comments=(TextView) itemView.findViewById(R.id.tvItemCommentsNumProfile);
            likeImage = (ImageView) itemView.findViewById(R.id.iv_profile_like);
            commentLayout = (LinearLayout) itemView.findViewById(R.id.ll_profile_comments);
        }
    }
}
