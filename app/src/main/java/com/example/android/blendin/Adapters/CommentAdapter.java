package com.example.android.blendin.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.blendin.Models.CommentModel;
import com.example.android.blendin.R;
import com.example.android.blendin.Utility.Constants;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by LeGenÐ on 12/9/2017.
 */

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {
    List<CommentModel> commentModelList;
    CommentModel commentModel;
    private Context context;

    public CommentAdapter(List<CommentModel> commentModelList, Context context) {
        this.commentModelList = commentModelList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_comment, parent, false);
        return new ViewHolder(view);
    }

    void getPicasso(String temp, CircleImageView img) {
        Picasso.with(context)
                .load(Constants.BASE_URL + temp)
                .error(R.drawable.kappa2)
                .into(img);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        commentModel = commentModelList.get(position);
        getPicasso(commentModel.getPic(), holder.userImage);
        holder.userName.setText(commentModel.getName());
        holder.text.setText(commentModel.getMessage());
        holder.dateAgo.setText(commentModel.getCreated_at());
    }

    @Override
    public int getItemCount() {
        return commentModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView userImage;
        TextView userName;
        TextView text;
        TextView dateAgo;

        public ViewHolder(View itemView) {
            super(itemView);
            userImage = (CircleImageView) itemView.findViewById(R.id.comment_image);
            userName = (TextView) itemView.findViewById(R.id.comment_username);
            text = (TextView) itemView.findViewById(R.id.comment_text);
            dateAgo = (TextView) itemView.findViewById(R.id.comment_date);

        }
    }
}
