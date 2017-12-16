package com.example.android.blendin.Fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.android.blendin.R;

import java.io.IOException;

import static android.app.Activity.RESULT_OK;


public class CreatePostFragment extends Fragment {

    static final int REQUEST_IMAGE_SELECT = 0;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int REQUEST_HANGOUT_NAME = 2;

    ImageView cancelImageView;
    ImageView camera;
    ImageView gallary;
    ImageView hangouts;
    ImageView showImage;
    LinearLayout linearLayout;
    TextView hangoutTV;
    Button postButton;

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
        linearLayout = (LinearLayout) rootView.findViewById(R.id.createPost_ll_image);
        showImage = (ImageView) rootView.findViewById(R.id.createPost_image);
        postButton = (Button) rootView.findViewById(R.id.createPost_postBtn);
        camera = (ImageView) rootView.findViewById(R.id.createPost_AddPicCamera);
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCamera();
            }
        });
        gallary = (ImageView) rootView.findViewById(R.id.createPost_AddPicGallery);
        gallary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });
        hangoutTV = (TextView) rootView.findViewById(R.id.createPost_hangout);
        hangouts = (ImageView) rootView.findViewById(R.id.createPost_AddPreviousHangout);
        hangouts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO :: hangout name
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                SelectHangoutFragment fragment = new SelectHangoutFragment();
                fragment.setTargetFragment(CreatePostFragment.this, REQUEST_HANGOUT_NAME);
                fragment.show(fragmentManager, "Dialog");
            }
        });



        // Inflate the layout for this fragment
        return rootView;
    }

    public void openCamera() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    public void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), REQUEST_IMAGE_SELECT);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap = null;
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            bitmap = (Bitmap) extras.get("data");
            showImage.setImageBitmap(bitmap);
            linearLayout.setVisibility(View.VISIBLE);
            //img.setImageBitmap(imageBitmap);
        } else if (requestCode == REQUEST_IMAGE_SELECT && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri);
                // Log.d(TAG, String.valueOf(bitmap));
                showImage.setImageBitmap(bitmap);
                linearLayout.setVisibility(View.VISIBLE);

                //img.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (requestCode == REQUEST_HANGOUT_NAME && resultCode == RESULT_OK && data != null) {
            hangoutTV.setText(data.getStringExtra("hangout"));
            postButton.setEnabled(true);
        }
    }
}
