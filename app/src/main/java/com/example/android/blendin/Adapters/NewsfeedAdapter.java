package com.example.android.blendin.Adapters;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.blendin.Fragments.CommentsFragment;
import com.example.android.blendin.Models.NewsFeedModel;
import com.example.android.blendin.R;
import com.example.android.blendin.Responses.LoveResponse;
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
 * Created by LeGenÐ on 11/29/2017.
 */

public class NewsfeedAdapter extends RecyclerView.Adapter<NewsfeedAdapter.ViewHolder> {
    List<NewsFeedModel> newsfeedItemsList;
    NewsFeedModel newsFeedModel;
    private Context context;


    public NewsfeedAdapter(List<NewsFeedModel> newsfeedItemsList, Context context) {
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
        return new ViewHolder(view);
    }

    void getPicasso(String temp, ImageView img) {
        Picasso.with(context)
                .load(Constants.BASE_URL_FOR_IMAGE + temp)
                .error(R.drawable.kappa2)
                .into(img);
    }

    void getPicasso(String temp, CircleImageView img) {
        Picasso.with(context)
                .load(Constants.BASE_URL_FOR_IMAGE + temp)
                .error(R.drawable.kappa2)
                .into(img);
    }

    public void setLike(String id) {
        final ProgressDialog progressDialog = ProgressDialog.show(context, null, "Loading");
        progressDialog.setCancelable(false);
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Log.e("Token", AuthUser.getAuthData().getToken());
        Log.e("Token", AuthUser.getAuthData().getSecret());
        Call<LoveResponse> call = apiService.love(AuthUser.getAuthData().getToken(), AuthUser.getAuthData().getSecret(), id);
        call.enqueue(new Callback<LoveResponse>() {
            @Override
            public void onResponse(Call<LoveResponse> call, Response<LoveResponse> response) {
                progressDialog.cancel();
                if (response.body() != null) {
                    if (response.body().getStatues().equals(Constants.FLAG_SUCCESS)) {

                    } else
                        Toast.makeText(context, response.body().getStatues(), Toast.LENGTH_SHORT).show();
                } else Toast.makeText(context, "null", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<LoveResponse> call, Throwable t) {
                progressDialog.cancel();
                Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        newsFeedModel = newsfeedItemsList.get(position);

        getPicasso(newsfeedItemsList.get(position).getAvatar(), holder.userProfileImage);

        getPicasso(newsfeedItemsList.get(position).getHangout_pic(), holder.postImage);
        holder.userNameTxt.setText(newsFeedModel.getName());
        holder.userLocationTxt.setText(newsFeedModel.getLocation());

        holder.postTimeTxt.setText(newsFeedModel.getCreated_at());
        holder.titletxt.setText(newsFeedModel.getTitle());

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
                    setLike(newsfeedItemsList.get(position).getId());
                } else {
                    holder.likeImage.setImageResource(R.drawable.like);
                    newsfeedItemsList.get(position).setLovedByThisUser(true);
                    setLike(newsfeedItemsList.get(position).getId());
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
