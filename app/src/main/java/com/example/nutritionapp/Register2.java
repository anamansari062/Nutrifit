package com.example.nutritionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

public class Register2 extends AppCompatActivity {
    Button proceed;
    EditText height,weight;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);
        proceed=findViewById(R.id.proceed);
        height=findViewById(R.id.Height);
        weight=findViewById(R.id.Weight);
        progressBar=findViewById(R.id.progressBar2);

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Height=height.getText().toString().trim();
                String Weight=weight.getText().toString().trim();
                if(Height.isEmpty()){
                    height.setError("Height is required");
                    height.requestFocus();
                    return;
                }
                if(Weight.isEmpty()){
                    weight.setError("Weight is required");
                    weight.requestFocus();
                    return;
                }
                proceed.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);
                Intent intent3=new Intent(Register2.this,Register1.class);
                intent3.putExtra(Height,"Height");
                intent3.putExtra(Weight,"Weight");
                startActivity(intent3);
            }
        });
    }

}