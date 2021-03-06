package com.example.android.blendin.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.blendin.Models.NotificationModel;
import com.example.android.blendin.Models.NotificationModell;
import com.example.android.blendin.R;

import org.w3c.dom.Text;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Luffy on 12/15/2017.
 */

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {

    Context context;
    List<NotificationModell> notificationModelList;

    public NotificationAdapter(Context context, List<NotificationModell> notificationModelList) {
        this.context = context;
        this.notificationModelList = notificationModelList;
    }

    @Override
    public int getItemCount() {
        return notificationModelList.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_notification, parent, false);
        return new NotificationAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.disc.setText(notificationModelList.get(position).getMessage());
        holder.time.setText(notificationModelList.get(position).getCreated_at());
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView disc;
        TextView time;

        public ViewHolder(View itemView) {
            super(itemView);

            disc = (TextView) itemView.findViewById(R.id.tv_details_notification);
            time = (TextView) itemView.findViewById(R.id.tv_time_notification);
        }
    }
}
