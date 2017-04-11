package com.mindlot.firebase.codelab.mindlot.code;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by real on 6/5/2016.
 */
public class SessionManager {


    public void setPreferences(Context context, String key, String value) {

        SharedPreferences.Editor editor = context.getSharedPreferences("Androidwarriors", Context.MODE_PRIVATE).edit();
        editor.putString(key, value);
        editor.commit();

    }



    public String getPreferences(Context context, String key) {

        SharedPreferences prefs = context.getSharedPreferences("Androidwarriors",	Context.MODE_PRIVATE);
        String position = prefs.getString(key, "");
        return position;
    }
}