package com.example.android.blendin.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.blendin.Adapters.NewsfeedAdapter;
import com.example.android.blendin.Models.NewsFeedModel;
import com.example.android.blendin.R;

import java.util.ArrayList;
import java.util.List;


public class NewsfeedFragment extends Fragment {


    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    List<NewsFeedModel> newsFeedsList;
    RecyclerView.LayoutManager layoutManager;


    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    List<NewsFeedModel> newsFeedsList;
    RecyclerView.LayoutManager layoutManager;
    com.github.clans.fab.FloatingActionButton floatingActionButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //return inflater.inflate(R.layout.fragment_newsfeed, container, false);
        final View rootView = inflater.inflate(R.layout.fragment_newsfeed, container, false);
        recyclerView = (RecyclerView)  rootView.findViewById(R.id.newsfeed_recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        newsFeedsList = new ArrayList<>();
        //Test Data
        for(int i=0; i<5; i++){
            NewsFeedModel newsFeedModel = new NewsFeedModel(
                    R.drawable.user,
                    "Mostafa Waleed",
                    "Mansoura",
                    "1 hour ago",
                    R.drawable.kappa2,
                    "Eating at Bremer",
                    "is this is the best hangout or what ? ",
                    "5555",
                    "6969" );
            newsFeedsList.add(newsFeedModel);
        }

        adapter = new NewsfeedAdapter(newsFeedsList,getActivity());
        recyclerView.setAdapter(adapter);
        floatingActionButton = (com.github.clans.fab.FloatingActionButton) rootView.findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new CreatePostFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_main, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        return rootView;
    }
}
