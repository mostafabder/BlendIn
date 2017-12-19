package com.example.android.blendin.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.android.blendin.Adapters.MySquadAdapter;
import com.example.android.blendin.Adapters.NewsfeedAdapter;
import com.example.android.blendin.Models.MySquadModel;
import com.example.android.blendin.Models.NewsFeedModel;
import com.example.android.blendin.R;
import com.example.android.blendin.RecyclerViewClickListener;
import com.example.android.blendin.Responses.LoginResponse;
import com.example.android.blendin.Responses.MySquadResponse;
import com.example.android.blendin.Responses.ProfileResponse;
import com.example.android.blendin.Retrofit.ApiClient;
import com.example.android.blendin.Retrofit.ApiInterface;
import com.example.android.blendin.Utility.AuthUser;
import com.github.clans.fab.FloatingActionButton;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.android.blendin.Utility.CommonMethods.retrieveDataFromSharedPref;
import static com.example.android.blendin.Utility.Constants.FLAG_SUCCESS;
import static com.example.android.blendin.Utility.Constants.KEY_USER_DATA;


public class MysquadFragment extends Fragment {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    List<MySquadModel> mySquadModelList;
    com.github.clans.fab.FloatingActionButton floatingActionButton;
    RecyclerView.LayoutManager layoutManager;
    View view;
    AuthUser authUser;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_mysquad, container, false);
        initAPI();
        floatingActionButton = (FloatingActionButton) view.findViewById(R.id.fab_squad);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new AddSquadFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.slide_in_from_bottom, R.anim.slide_out_to_bottom, R.anim.slide_out_from_bottom, R.anim.slide_in_to_bottom);
                fragmentTransaction.add(R.id.content_main, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        return view;
    }

    public void initAPI() {
        //LoginResponse loginResponse = new Gson().fromJson(retrieveDataFromSharedPref(getActivity(), KEY_USER_DATA), LoginResponse.class);
        authUser = AuthUser.getAuthData();
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<MySquadResponse> call = apiInterface.mysquads(authUser.getToken(), authUser.getSecret());
        Log.e("kkk", authUser.getToken());
        Log.e("kkk1", authUser.getSecret());
        call.enqueue(new Callback<MySquadResponse>() {
            @Override
            public void onResponse(Call<MySquadResponse> call, final Response<MySquadResponse> response) {
                if (response != null && response.body().getStatus().equals(FLAG_SUCCESS)) {
                    GridLayoutManager layoutManager = new GridLayoutManager(getActivity().getApplicationContext(), 2);
                    recyclerView = (RecyclerView) view.findViewById(R.id.mysquad_recyclerView);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setHasFixedSize(true);
                    RecyclerViewClickListener listener = new RecyclerViewClickListener() {
                        @Override
                        public void onClick(View view, int position) {
                            //TODO different fragment data depends on the post , waiting for the logic .-.
                            Fragment fragment = new SquadFragment();
                            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                            Bundle bundle = new Bundle();
                            Log.e("kappa5", response.body().getSquads().get(position).getId());
                            bundle.putString("squadID", response.body().getSquads().get(position).getId());
                            fragment.setArguments(bundle);
                            fragmentTransaction.setCustomAnimations(R.anim.slide_in_from_bottom, R.anim.slide_out_to_bottom, R.anim.slide_out_from_bottom, R.anim.slide_in_to_bottom);
                            fragmentTransaction.add(R.id.content_main, fragment);
                            fragmentTransaction.addToBackStack(null);
                            fragmentTransaction.commit();
                        }
                    };
                    adapter = new MySquadAdapter(response.body().getSquads(), getActivity(), listener);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<MySquadResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "failed", Toast.LENGTH_SHORT).show();
            }

        });
    }
}
