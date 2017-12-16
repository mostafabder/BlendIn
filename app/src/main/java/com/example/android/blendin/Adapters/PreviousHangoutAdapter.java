package com.example.android.blendin.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.android.blendin.Fragments.SelectHangoutFragment;
import com.example.android.blendin.Models.PreviousHangoutModel;
import com.example.android.blendin.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by LeGenÐ on 12/15/2017.
 */

public class PreviousHangoutAdapter extends RecyclerView.Adapter<PreviousHangoutAdapter.ViewHolder> {

    List<PreviousHangoutModel> hangoutModelList;
    PreviousHangoutModel hangoutModel;
    SelectHangoutFragment selectHangoutFragment;
    private Context context;

    public PreviousHangoutAdapter(List<PreviousHangoutModel> hangoutModelList, SelectHangoutFragment selectHangoutFragment) {
        this.hangoutModelList = hangoutModelList;
        this.selectHangoutFragment = selectHangoutFragment;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_previous_hangout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        hangoutModel = hangoutModelList.get(position);
        holder.image.setImageResource(hangoutModel.getImage());
        holder.title.setText(hangoutModel.getTitle());
        holder.location.setText(hangoutModel.getLocation());
        holder.date.setText(hangoutModel.getDate());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectHangoutFragment.dismiss();
                Intent intent = new Intent();
                intent.putExtra("hangout", hangoutModel.getTitle());
                selectHangoutFragment.getTargetFragment().onActivityResult(selectHangoutFragment.getTargetRequestCode(), Activity.RESULT_OK, intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return hangoutModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView image;
        TextView title;
        TextView location;
        TextView date;
        LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            image = (CircleImageView) itemView.findViewById(R.id.previous_hangout_image);
            title = (TextView) itemView.findViewById(R.id.previous_hangout_title);
            location = (TextView) itemView.findViewById(R.id.previous_hangout_location);
            date = (TextView) itemView.findViewById(R.id.previous_hangout_date);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.previous_hangout_item);
        }
    }
}