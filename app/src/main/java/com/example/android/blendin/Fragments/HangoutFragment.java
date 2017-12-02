package com.example.android.blendin.Fragments;

import android.animation.ValueAnimator;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Transformation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.android.blendin.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import mehdi.sakout.fancybuttons.FancyButton;


public class HangoutFragment extends Fragment implements OnMapReadyCallback {
    private GoogleMap mMap;
    ValueAnimator va;
    RelativeLayout hidden;
    LinearLayout linear;
    boolean isBottom;
    View v;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v=inflater.inflate(R.layout.fragment_hangout, container, false);
        FancyButton fancyButton=(FancyButton)v.findViewById(R.id.btn_LetsGo);
        hidden=(RelativeLayout) v.findViewById(R.id.hiddenView);
        linear=(LinearLayout)v.findViewById(R.id.linear);
        fancyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               expandView();
                mMap.getUiSettings().setScrollGesturesEnabled(true);
            }
        });
        initMap();
        return v;
    }



    public void initMap(){
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney, Australia, and move the camera.
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.getUiSettings().setScrollGesturesEnabled(false);
    }
    private void expandView() {

        hidden.clearAnimation();
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        int maxWidth = display.getWidth();
        int maxHeight = display.getHeight();
        ExpandCollapseViewAnimation animation = new ExpandCollapseViewAnimation(hidden, maxWidth,maxHeight, true);
        animation.setDuration(400);
        animation.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                linear.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
            }
        });
        hidden.startAnimation(animation);
        hidden.invalidate();
    }
    public class ExpandCollapseViewAnimation extends Animation {
        int targetWidth;
        int targetHeight;
        int initialWidth;
        int initialHeight;
        boolean expand;
        View view;

        public ExpandCollapseViewAnimation(View view, int targetWidth, int targetHeight ,boolean expand) {
            this.view = view;
            this.targetWidth = targetWidth;
            this.targetHeight = targetHeight;
            this.initialWidth = view.getWidth();
            this.initialHeight = view.getHeight();
            this.expand = expand;
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            int newWidth, newHeight;
            if (expand) {
                newWidth = this.initialWidth
                        + (int) ((this.targetWidth - this.initialWidth) * interpolatedTime);
                newHeight = this.initialHeight
                        + (int) ((this.targetHeight - this.initialHeight) * interpolatedTime);
            } else {
                newWidth = this.initialWidth
                        - (int) ((this.initialWidth - this.targetWidth) * interpolatedTime);
                newHeight = this.initialHeight
                        - (int) ((this.initialHeight - this.targetHeight) * interpolatedTime);
            }

            view.getLayoutParams().width = newWidth;
            view.getLayoutParams().height = newHeight;
            view.requestLayout();
        }

        @Override
        public void initialize(int width, int height, int parentWidth,
                               int parentHeight) {
            super.initialize(width, height, parentWidth, parentHeight);
        }

        @Override
        public boolean willChangeBounds() {
            return true;
        }

    }
}
