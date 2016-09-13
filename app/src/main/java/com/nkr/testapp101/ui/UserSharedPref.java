package com.nkr.testapp101.ui;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by neel on 07/09/2016.
 */
public class UserSharedPref {
    SharedPreferences preferences;
    SharedPreferences.Editor prefEditor;
    Context mContext;

    public UserSharedPref(Context mContext) {
        this.mContext = mContext;
        preferences=mContext.getSharedPreferences("testapp",Context.MODE_PRIVATE);
        prefEditor=preferences.edit();
    }

    public void setLoggedIn(boolean loggedIn){
        prefEditor.putBoolean("loggedMode",loggedIn);
        prefEditor.commit();
    }

    public boolean loggedIn(){
        return preferences.getBoolean("loggedMode",false);
    }
}
