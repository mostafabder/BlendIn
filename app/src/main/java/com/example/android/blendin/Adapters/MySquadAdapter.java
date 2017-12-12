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
import com.example.android.blendin.RecyclerViewClickListener;

import de.hdodenhof.circleimageview.CircleImageView;
import java.util.List;

/**
 * Created by LeGenÐ on 11/29/2017.
 */

public class MySquadAdapter extends RecyclerView.Adapter<MySquadAdapter.ViewHolder> {
    List<MySquadModel> mySquadItemsList;
    MySquadModel mySquadModel;
    private Context context;
    private RecyclerViewClickListener recyclerViewClickListener;


    public MySquadAdapter(List<MySquadModel> mySquadItemsList, Context context, RecyclerViewClickListener listener) {
        this.mySquadItemsList = mySquadItemsList;
        this.context = context;
        recyclerViewClickListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_mysquad,parent,false);
        return new ViewHolder(view, recyclerViewClickListener);
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

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        CircleImageView squadImage;
        TextView squadName;
        TextView squadMembersCount;
        private RecyclerViewClickListener mListener;

        public ViewHolder(View itemView, RecyclerViewClickListener listener) {
            super(itemView);
            squadImage = (CircleImageView) itemView.findViewById(R.id.squadImage);
            squadName = (TextView) itemView.findViewById(R.id.squadName);
            squadMembersCount = (TextView) itemView.findViewById(R.id.squadMembersCount);
            mListener = listener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mListener.onClick(view, getAdapterPosition());
        }
    }
}

