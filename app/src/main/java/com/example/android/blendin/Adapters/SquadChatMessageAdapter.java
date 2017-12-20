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
 * Created by LeGenÐ on 12/10/2017.
 */

public class SquadChatMessageAdapter extends RecyclerView.Adapter<SquadChatMessageAdapter.ViewHolder> {
    List<CommentModel> squadChatMessageModelList;
    CommentModel squadChatMessageModel;
    private Context context;

    public SquadChatMessageAdapter(List<CommentModel> squadChatMessageModelList, Context context) {
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
        //holder.userImage.setImageResource(squadChatMessageModel.getPic());
        getPicasso(squadChatMessageModel.getPic(), holder.userImage);
        holder.userName.setText(squadChatMessageModel.getFirst_name() + " " + squadChatMessageModel.getLast_name());
        holder.text.setText(squadChatMessageModel.getMessage());
        holder.dateAgo.setText(squadChatMessageModel.getCreated_at());
    }

    @Override
    public int getItemCount() {
        return squadChatMessageModelList.size();
    }

    void getPicasso(String temp, ImageView img) {
        Picasso.with(context)
                .load(Constants.BASE_URL + temp)
                .error(R.drawable.kappa2)
                .into(img);
    }

    void getPicasso(String temp, CircleImageView img) {
        Picasso.with(context)
                .load(Constants.BASE_URL + temp)
                .error(R.drawable.kappa2)
                .into(img);
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
