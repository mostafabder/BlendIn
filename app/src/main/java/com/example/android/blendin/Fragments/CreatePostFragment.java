package com.example.android.blendin.Fragments;

import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.blendin.R;


public class CreatePostFragment extends Fragment {

    ImageView cancelImageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_post, container, false);
        cancelImageView = (ImageView) rootView.findViewById(R.id.createPost_cancel);
        cancelImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO :: Transaction back to
                getActivity().onBackPressed();
                // do nothing
            }
        });
        // Inflate the layout for this fragment
        return rootView;
    }

}
