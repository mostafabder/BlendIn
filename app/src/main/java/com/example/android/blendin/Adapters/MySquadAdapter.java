package com.example.android.blendin.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.blendin.Models.MySquadModel;
import com.example.android.blendin.Models.NewsFeedModel;
import com.example.android.blendin.R;

import de.hdodenhof.circleimageview.CircleImageView;
import java.util.List;

/**
 * Created by LeGenÐ on 11/29/2017.
 */

public class MySquadAdapter extends RecyclerView.Adapter<MySquadAdapter.ViewHolder> {
    List<MySquadModel> mySquadItemsList;
    MySquadModel mySquadModel;
    private Context context;


    public MySquadAdapter(List<MySquadModel> mySquadItemsList, Context context) {
        this.mySquadItemsList = mySquadItemsList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_mysquad,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        mySquadModel = mySquadItemsList.get(position);
        holder.squadImage.setImageResource(mySquadModel.getAvatar());
        holder.squadName.setText(mySquadModel.getName());
        holder.squadMembersCount.setText(mySquadModel.getCount());
    }

    @Override
    public int getItemCount() {
        return mySquadItemsList.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView squadImage;
        TextView squadName;
        TextView squadMembersCount;

        public ViewHolder(View itemView) {
            super(itemView);
            squadImage = (CircleImageView) itemView.findViewById(R.id.squadImage);
            squadName = (TextView) itemView.findViewById(R.id.squadName);
            squadMembersCount = (TextView) itemView.findViewById(R.id.squadMembersCount);
        }
    }
}

