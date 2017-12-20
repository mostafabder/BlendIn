package com.example.android.blendin.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.blendin.Models.CommentModel;
import com.example.android.blendin.Models.SquadChatMessageModel;
import com.example.android.blendin.R;
import com.example.android.blendin.Utility.Constants;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by LeGenÐ on 12/13/2017.
 */

public class HangoutProfileChatAdapter extends RecyclerView.Adapter<HangoutProfileChatAdapter.ViewHolder> {

    List<CommentModel> chatMessageModelList;
    CommentModel chatMessageModel;
    private Context context;

    public HangoutProfileChatAdapter(List<CommentModel> chatMessageModelList, Context context) {
        this.chatMessageModelList = chatMessageModelList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_hangout_profile_chat_message, parent, false);
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
        chatMessageModel = chatMessageModelList.get(position);
        getPicasso(chatMessageModel.getPic(), holder.userImage);
        holder.userName.setText(chatMessageModel.getName());
        holder.text.setText(chatMessageModel.getMessage());
        holder.dateAgo.setText(chatMessageModel.getCreated_at());
    }

    @Override
    public int getItemCount() {
        return chatMessageModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView userImage;
        TextView userName;
        TextView text;
        TextView dateAgo;

        public ViewHolder(View itemView) {
            super(itemView);
            userImage = (CircleImageView) itemView.findViewById(R.id.hangoutChat_chat_message_user_image);
            userName = (TextView) itemView.findViewById(R.id.hangoutChat_chat_message_username);
            text = (TextView) itemView.findViewById(R.id.hangoutChat_message_text);
            dateAgo = (TextView) itemView.findViewById(R.id.hangoutChat_chat_message_date);
        }
    }
}
