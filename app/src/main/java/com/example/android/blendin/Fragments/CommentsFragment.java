package com.example.android.blendin.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.blendin.Adapters.CommentAdapter;
import com.example.android.blendin.Models.CommentModel;
import com.example.android.blendin.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CommentsFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    List<CommentModel> commentModelList;
    LinearLayoutManager layoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_comments, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.comments_rectclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        commentModelList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            CommentModel commentModel = new CommentModel(
                    "Hamada Helal",
                    "a7la regalaaaaaaaaaaa <3",
                    "20h",
                    R.drawable.user
            );
            commentModelList.add(commentModel);
        }
        adapter = new CommentAdapter(commentModelList, getActivity());
        recyclerView.setAdapter(adapter);
        return rootView;
    }

}
