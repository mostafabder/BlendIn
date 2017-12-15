package com.example.android.blendin.Adapters;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.blendin.Constants;
import com.example.android.blendin.Models.HangoutModel;
import com.example.android.blendin.R;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Luffy on 12/13/2017.
 */

public class HangoutAdapter extends RecyclerView.Adapter<HangoutAdapter.ViewHolder> {

    Context context;
    List<HangoutModel> hangoutModels;
    Boolean flag;

    public HangoutAdapter(Context context, List<HangoutModel> hangoutModels, Boolean flag) {
        this.context = context;
        this.hangoutModels = hangoutModels;
        this.flag = flag;
    }


    @Override
    public HangoutAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_map, parent, false);
        return new HangoutAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HangoutAdapter.ViewHolder holder, final int position) {
        holder.avatar.setImageResource(hangoutModels.get(position).getAvatar());
        holder.userName.setText(hangoutModels.get(position).getName());
        if (flag) {
            holder.close.setImageResource(R.drawable.add);
        }
        holder.close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hangoutModels.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, hangoutModels.size());
                if (hangoutModels.size() == 0) {
                    Toast.makeText(context, "Empty", Toast.LENGTH_SHORT).show();
                }
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
