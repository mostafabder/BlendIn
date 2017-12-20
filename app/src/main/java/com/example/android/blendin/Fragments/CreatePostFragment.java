package com.example.android.blendin.Fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.blendin.Adapters.PreviousHangoutAdapter;
import com.example.android.blendin.Models.MyHangoutsModel;
import com.example.android.blendin.R;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.example.android.blendin.Responses.CommentResponse;
import com.example.android.blendin.Responses.MyHangoutsResponse;
import com.example.android.blendin.Retrofit.ApiClient;
import com.example.android.blendin.Retrofit.ApiInterface;
import com.example.android.blendin.Utility.*;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;
import static android.graphics.Bitmap.createBitmap;


public class CreatePostFragment extends Fragment {

    static final int REQUEST_IMAGE_SELECT = 0;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int REQUEST_HANGOUT_NAME = 2;
    static String mCurrentPhotoPath;
    ProgressDialog progressDialog;
    ImageView cancelImageView;
    ImageView camera;
    ImageView gallary;
    ImageView hangouts;
    ImageView showImage;
    LinearLayout linearLayout;
    EditText et_desc;
    TextView hangoutTV;
    Button postButton;
    String hangout_id;
    public static Bitmap rotateImage(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        Bitmap b = Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(),
                matrix, true);
        return b;
    }

    public static Bitmap orientationFix(Bitmap wrongBitmap) {
        ExifInterface ei = null;
        try {
            ei = new ExifInterface(mCurrentPhotoPath);
        } catch (IOException e) {
            //e.printStackTrace();
        }
        int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                ExifInterface.ORIENTATION_UNDEFINED);

        Bitmap rotatedBitmap = null;
        switch (orientation) {

            case ExifInterface.ORIENTATION_ROTATE_90:
                rotatedBitmap = rotateImage(wrongBitmap, 90);
                break;

            case ExifInterface.ORIENTATION_ROTATE_180:
                rotatedBitmap = rotateImage(wrongBitmap, 180);
                break;

            case ExifInterface.ORIENTATION_ROTATE_270:
                rotatedBitmap = rotateImage(wrongBitmap, 270);
                break;

            case ExifInterface.ORIENTATION_NORMAL:
            default:
                rotatedBitmap = wrongBitmap;
        }
        return rotatedBitmap;
    }

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
        et_desc = (EditText) rootView.findViewById(R.id.createPost_PostText);
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
        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createPost();
            }
        });


        // Inflate the layout for this fragment
        return rootView;
    }

    public void openCamera() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
                Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(getActivity().getApplicationContext(),
                        "com.example.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
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

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            linearLayout.setVisibility(View.VISIBLE);
            setPic();
        } else if (requestCode == REQUEST_IMAGE_SELECT && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Bitmap bitmap = null;
            Uri uri = data.getData();
            File actualImage = null;
            //try {
            //bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri);
            try {
                actualImage = FileUtil.from(getActivity(), data.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
            mCurrentPhotoPath = actualImage.getAbsolutePath();
            linearLayout.setVisibility(View.VISIBLE);
            setPic();
            //showImage.setImageBitmap(bitmap);
            //} catch (IOException e) {
            //  e.printStackTrace();
            //}
        } else if (requestCode == REQUEST_HANGOUT_NAME && resultCode == RESULT_OK && data != null) {
            hangoutTV.setText(data.getStringExtra("hangout"));
            hangout_id = data.getStringExtra("hangout_id");
            postButton.setEnabled(true);
        }
    }

    private File createImageFile() throws IOException {

        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    private void setPic() {
        // Get the dimensions of the View
        int targetW = 700;
        int targetH = 700;

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW / targetW, photoH / targetH);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;
        Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
        showImage.setImageBitmap(orientationFix(bitmap));
    }

    void createPost() {
        progressDialog = ProgressDialog.show(getActivity(), null, "Loading");
        progressDialog.setCancelable(false);
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Log.e("Token", AuthUser.getAuthData().getToken());
        Log.e("Secret", AuthUser.getAuthData().getSecret());
        Call<CommentResponse> call = apiService.addPost(AuthUser.getAuthData().getToken(), AuthUser.getAuthData().getSecret(), hangout_id, et_desc.getText().toString(), hangoutTV.getText().toString());
        call.enqueue(new Callback<CommentResponse>() {
            @Override
            public void onResponse(Call<CommentResponse> call, Response<CommentResponse> response) {
                progressDialog.cancel();
                if (response.body() != null) {
                    if (response.body().getStatus().equals(Constants.FLAG_SUCCESS)) {
                        Toast.makeText(getActivity(), "Created", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(getActivity(), response.body().getStatus(), Toast.LENGTH_SHORT).show();
                } else Toast.makeText(getActivity(), "null", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<CommentResponse> call, Throwable t) {
                progressDialog.cancel();
                Toast.makeText(getActivity(), "Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
