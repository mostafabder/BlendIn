package com.example.android.blendin.Utility;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

import static com.example.android.blendin.Utility.Constants.PREF_KEY;

/**
 * Created by Luffy on 12/16/2017.
 */

public class CommonMethods {

    public static void storeDataToSharedPref(Context context, String data, String key) {
        SharedPreferences sharedpreferences = context.getSharedPreferences(PREF_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = sharedpreferences.edit();
        prefsEditor.putString(key, data);
        prefsEditor.apply();
    }

    public static String retrieveDataFromSharedPref(Context context, String key) {
        SharedPreferences sharedpreferences = context.getSharedPreferences(PREF_KEY, Context.MODE_PRIVATE);
        String json = sharedpreferences.getString(key, null);
        return json;
    }

    public static String encodeTobase64(Bitmap image) {
        Bitmap immagex = image;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        immagex.compress(Bitmap.CompressFormat.JPEG, 50, baos);
        byte[] b = baos.toByteArray();
        String imageEncoded = Base64.encodeToString(b, Base64.NO_WRAP);
        return imageEncoded;
    }
}
