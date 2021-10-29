package com.example.nutritionapp.ui.update;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.nutritionapp.R;

public class CalculateActivity extends AppCompatActivity {
    Button backButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_fragment);
//        Intent intent = getIntent();
        backButton = findViewById(R.id.button2);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(this, "Your profile has been updated", Toast.LENGTH_SHORT).show();
                Intent back = new Intent(CalculateActivity.this, UpdateFragment.class);
                startActivity(back);
            }
        });

    }
}