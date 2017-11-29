package com.example.android.blendin.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.android.blendin.Models.MySquadModel;
import com.example.android.blendin.Models.RequestModel;
import com.example.android.blendin.R;
import de.hdodenhof.circleimageview.CircleImageView;
import java.util.List;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by LeGenÐ on 11/29/2017.
 */

public class RequestAdapter extends RecyclerView.Adapter<RequestAdapter.ViewHolder> {
        List<RequestModel> requestModelList;
        RequestModel requestModel;
        private Context context;

    public RequestAdapter(List<RequestModel> requestModelList, Context context) {
        this.requestModelList = requestModelList;
        this.context = context;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView requestUserImage;
        TextView requestUserName;
        TextView requestUserLocation;
        TextView requestTimeAgo;
        TextView requestDesc;


        public ViewHolder(View itemView) {
            super(itemView);
            requestUserImage = (CircleImageView) itemView.findViewById(R.id.ivItemImageRequest);
            requestUserName = (TextView) itemView.findViewById(R.id.tvItemNameRequest);
            requestUserLocation = (TextView) itemView.findViewById(R.id.tvItemLocRequest);
            requestTimeAgo = (TextView) itemView.findViewById(R.id.tvItemTimeRequest);
            requestDesc = (TextView) itemView.findViewById(R.id.tvItemDiscRequest);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_request,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        requestModel = requestModelList.get(position);
        holder.requestUserImage.setImageResource(requestModel.getAvatar());
        holder.requestUserName.setText(requestModel.getName());
        holder.requestUserLocation.setText(requestModel.getLocation());
        holder.requestTimeAgo.setText(requestModel.getTimeAgo());
        holder.requestDesc.setText(requestModel.getDescription());
    }

    @Override
    public int getItemCount() {
        return requestModelList.size();
    }
}
