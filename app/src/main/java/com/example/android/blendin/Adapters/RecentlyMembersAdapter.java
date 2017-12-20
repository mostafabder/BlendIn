package com.example.android.blendin.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.android.blendin.Models.HangoutModel;
import com.example.android.blendin.Models.RecentlyHangwithModel;
import com.example.android.blendin.R;
import com.example.android.blendin.Utility.Constants;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Luffy on 12/20/2017.
 */

public class RecentlyMembersAdapter extends RecyclerView.Adapter<RecentlyMembersAdapter.ViewHolder> {
    Context context;
    List<RecentlyHangwithModel> hangoutModels;
    List<String> members;


    public RecentlyMembersAdapter(Context context, List<RecentlyHangwithModel> hangoutModels, List<String> members) {
        this.context = context;
        this.hangoutModels = hangoutModels;
        this.members = members;
    }


    @Override
    public RecentlyMembersAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_map, parent, false);
        return new RecentlyMembersAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecentlyMembersAdapter.ViewHolder holder, final int position) {

        Picasso.with(context)
                .load(Constants.BASE_URL + hangoutModels.get(position).getPic())
                .error(R.drawable.kappa2)
                .into(holder.avatar);
        holder.userName.setText(hangoutModels.get(position).getName());
        Log.e("NAME", hangoutModels.get(position).getFirst_name());

        holder.close.setImageResource(R.drawable.add);

        holder.close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                members.add(hangoutModels.get(position).getUuid());
                hangoutModels.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, hangoutModels.size());

            }
        });
    }

    @Override
    public int getItemCount() {
        return hangoutModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView close;
        CircleImageView avatar;
        TextView userName;

        public ViewHolder(View itemView) {
            super(itemView);
            close = (ImageView) itemView.findViewById(R.id.iv_close);
            avatar = (CircleImageView) itemView.findViewById(R.id.iv_hangout_avatar);
            userName = (TextView) itemView.findViewById(R.id.tv_hangout_userName);
        }
    }
}
