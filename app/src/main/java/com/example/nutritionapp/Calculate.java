package com.example.nutritionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.nutritionapp.R;

public class Calculate extends AppCompatActivity {
    String bmi2;
    int bmi;
    String height2;
    String weight2;
    float height,weight;
    ProgressBar progressBar;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bmi_main);
//        Intent intent=getIntent();
        progressBar=findViewById(R.id.progressBar);
        textView=findViewById(R.id.textView_Progress);

//        height2=intent.getStringExtra("Height");
//        weight2=intent.getStringExtra("Weight");

        height=Float.parseFloat(height2);
        weight=Float.parseFloat(weight2);

        height=height/100;
        bmi = (int) (weight/(height*height));
        bmi2=Double.toString(bmi);
        progressBar.setProgress(bmi);
        textView.setText(bmi2);



    }
}