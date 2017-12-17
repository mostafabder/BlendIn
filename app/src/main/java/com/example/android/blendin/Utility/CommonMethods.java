package com.example.android.blendin.Utility;

import android.content.Context;
import android.content.SharedPreferences;

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

}
