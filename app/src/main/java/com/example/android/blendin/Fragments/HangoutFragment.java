package com.example.android.blendin.Fragments;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.android.blendin.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashMap;
import java.util.Map;

import mehdi.sakout.fancybuttons.FancyButton;


public class HangoutFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener, GoogleMap.OnInfoWindowClickListener {
    private final LatLng HAMBURG = new LatLng(53.558, 9.927);
    private final LatLng KIEL = new LatLng(53.551, 9.993);
    LinearLayout linear;
    View v;
    Spinner act;
    private GoogleMap mMap;
    private Marker mMarker;
    private PopupWindow mPopupWindow;
    private int mWidth;
    private int mHeight;
    private Map<Marker, String> markerIds = new HashMap<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v=inflater.inflate(R.layout.fragment_hangout, container, false);


        FancyButton fancyButton=(FancyButton)v.findViewById(R.id.btn_LetsGo);
        linear=(LinearLayout)v.findViewById(R.id.linear);
        act = (Spinner) v.findViewById(R.id.act_spinner);

        fancyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SlideToDown();
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
        final Marker hamburg = googleMap.addMarker(new MarkerOptions().position(HAMBURG)
                .title("Hamburg"));
        markerIds.put(hamburg, "1");
        final Marker kiel = googleMap.addMarker(new MarkerOptions()
                .position(KIEL)
                .title("Kiel"));
        markerIds.put(kiel, "2");
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(HAMBURG, 15));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
        mMap.getUiSettings().setScrollGesturesEnabled(false);
        mMap.setOnMarkerClickListener(this);
        mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker marker) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {
                View infowindow = getActivity().getLayoutInflater().inflate(R.layout.marker_popup, null);
                return infowindow;
            }
        });
        mMap.setOnInfoWindowClickListener(this);
    }

    public void SlideToDown() {
        linear.setVisibility(View.VISIBLE);
        linear.setAlpha(0.0f);

// Start the animation
        linear.animate()
                .translationY(linear.getHeight())
                .alpha(1.0f)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        linear.setVisibility(View.GONE);
                    }
                });
    }

    @Override
    public boolean onMarkerClick(Marker marker) {

        return false;
    }


    @Override
    public void onInfoWindowClick(Marker marker) {

        Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.marker_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
        Toast.makeText(getActivity(), markerIds.get(marker), Toast.LENGTH_SHORT).show();
    }
}

