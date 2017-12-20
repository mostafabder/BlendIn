package com.example.android.blendin.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.android.blendin.Fragments.HangoutProfileFragment;
import com.example.android.blendin.Models.MyHangoutsModel;
import com.example.android.blendin.R;
import com.example.android.blendin.Utility.Constants;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Luffy on 12/20/2017.
 */

public class MyHangoutsAdapter extends RecyclerView.Adapter<MyHangoutsAdapter.ViewHolder> {

    List<MyHangoutsModel> myHangoutsModelList;
    Context context;

    public MyHangoutsAdapter(List<MyHangoutsModel> myHangoutsModelList, Context context) {
        this.myHangoutsModelList = myHangoutsModelList;
        this.context = context;
    }

    @Override
    public MyHangoutsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_myhangouts, parent, false);
        return new MyHangoutsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.title.setText(myHangoutsModelList.get(position).getTitle());
        holder.location.setText(myHangoutsModelList.get(position).getLocation());
        holder.created_at.setText(myHangoutsModelList.get(position).getCreated_at());
        holder.activty.setText(myHangoutsModelList.get(position).getActivity());
        getPicasso(myHangoutsModelList.get(position).getHangout_pic(), holder.pic);
        holder.main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("auth", 2);
                bundle.putString("hangout_id", myHangoutsModelList.get(position).getHangout_id());
                Fragment fragment = new HangoutProfileFragment();
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.slide_in_from_bottom, R.anim.slide_out_to_bottom, R.anim.slide_out_from_bottom, R.anim.slide_in_to_bottom);
                fragmentTransaction.add(R.id.content_main, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
    }

    void getPicasso(String temp, ImageView img) {
        Picasso.with(context)
                .load(Constants.BASE_URL + temp)
                .error(R.drawable.kappa2)
                .into(img);
    }

    @Override
    public int getItemCount() {
        return myHangoutsModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, location, activty, created_at;
        ImageView pic;
        LinearLayout main;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.tvItemMyhangoutTitle);
            location = (TextView) itemView.findViewById(R.id.tvItemMyhangoutLoc);
            activty = (TextView) itemView.findViewById(R.id.tvItemMyhangoutAct);
            created_at = (TextView) itemView.findViewById(R.id.tvItemMyhangoutDate);
            pic = (ImageView) itemView.findViewById(R.id.ivItemMyhangoutpic);
            main = (LinearLayout) itemView.findViewById(R.id.linearMyhangout);
        }
    }
}
