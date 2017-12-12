package com.example.android.blendin.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.blendin.Models.SquadChatMessageModel;
import com.example.android.blendin.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by LeGenÐ on 12/10/2017.
 */

public class SquadChatMessageAdapter extends RecyclerView.Adapter<SquadChatMessageAdapter.ViewHolder> {
    List<SquadChatMessageModel> squadChatMessageModelList;
    SquadChatMessageModel squadChatMessageModel;
    private Context context;

    public SquadChatMessageAdapter(List<SquadChatMessageModel> squadChatMessageModelList, Context context) {
        this.squadChatMessageModelList = squadChatMessageModelList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_squad_chat_message, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        squadChatMessageModel = squadChatMessageModelList.get(position);
        holder.userImage.setImageResource(squadChatMessageModel.getImage());
        holder.userName.setText(squadChatMessageModel.getUserName());
        holder.text.setText(squadChatMessageModel.getText());
        holder.dateAgo.setText(squadChatMessageModel.getDate());
    }

    @Override
    public int getItemCount() {
        return squadChatMessageModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView userImage;
        TextView userName;
        TextView text;
        TextView dateAgo;

        public ViewHolder(View itemView) {
            super(itemView);
            userImage = (CircleImageView) itemView.findViewById(R.id.squad_chat_message_user_image);
            userName = (TextView) itemView.findViewById(R.id.squad_chat_message_username);
            text = (TextView) itemView.findViewById(R.id.squad_chat_message_text);
            dateAgo = (TextView) itemView.findViewById(R.id.squad_chat_message_date);
        }
    }
}
