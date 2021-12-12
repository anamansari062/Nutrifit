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
        if (!pref.isSplashIn()) {
            //Splash Screen Load
            pref.setSplashIn(true);
        } else {
            Thread td = new Thread() {
                public void run() {
                    try {
                        sleep(2000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        Intent intent = new Intent(SplashActivity.this, Login.class);
                        startActivity(intent);
                        finish();
                    }
                }
            };
            td.start();
        }
    }
}