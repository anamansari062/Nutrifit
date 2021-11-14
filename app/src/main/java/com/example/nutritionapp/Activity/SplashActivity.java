package com.example.nutritionapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import com.example.nutritionapp.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Thread td= new Thread(){
            public void run(){
                try {
                    // TODO change the time later as it takes alot of time to go to home fragment

                    sleep(2000);
                }catch (Exception e){
                    e.printStackTrace();
                }
                finally {
                    Intent intent=new Intent(SplashActivity.this, Login.class);
                    startActivity(intent);
                    finish();
                }
            }
        };td.start();
    }
}