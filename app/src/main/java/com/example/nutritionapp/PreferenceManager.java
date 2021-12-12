package com.example.nutritionapp;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceManager {
    private static final String KEY_IS_SPLASH_IN = "isSplashScreenIn";
    private static final String PREF_NAME = "!2";
    SharedPreferences pref;
    // Editor for Shared preferences
    SharedPreferences.Editor editor;
    // Context
    Context _context;
    // Shared pref mode
    int PRIVATE_MODE = 0;

    public PreferenceManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public boolean isSplashIn() {
        return pref.getBoolean(KEY_IS_SPLASH_IN, false);
    }

    public void setSplashIn(boolean setLogin) {
        editor.putBoolean(KEY_IS_SPLASH_IN, setLogin);
        editor.commit();
    }
}
