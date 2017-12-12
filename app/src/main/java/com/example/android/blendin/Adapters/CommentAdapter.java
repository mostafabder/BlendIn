package com.example.android.blendin.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.blendin.Models.CommentModel;
import com.example.android.blendin.Models.RequestModel;
import com.example.android.blendin.R;

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

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        commentModel = commentModelList.get(position);
        holder.userImage.setImageResource(commentModel.getUserImage());
        holder.userName.setText(commentModel.getName());
        holder.text.setText(commentModel.getText());
        holder.dateAgo.setText(commentModel.getDate());
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
