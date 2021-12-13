package com.example.nutritionapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import com.example.nutritionapp.PreferenceManager;
import com.example.nutritionapp.R;

import java.util.Objects;

public class SplashActivity extends AppCompatActivity {

    PreferenceManager pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Objects.requireNonNull(getSupportActionBar()).hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        pref = new PreferenceManager(this);
        // the below lines of code was causing the app to not go beyond the splash screen when the user
        // installs and opens the app for the first time
        // issue #51- Splash Bug

//        if (!pref.isSplashIn()) {
//            //Splash Screen Load
//            pref.setSplashIn(true);
//        } else {
            Thread td = new Thread() {
                public void run() {
                    try {
                        sleep(2000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            };
            td.start();
        }
    }
//}
