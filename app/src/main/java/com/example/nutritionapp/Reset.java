package com.example.nutritionapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Reset extends AppCompatActivity {
    EditText email;
    Button sent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);
        email=findViewById(R.id.em);
        sent=findViewById(R.id.sent);
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}

        sent.setOnClickListener(v -> {
            String Email=email.getText().toString().trim();
            if (!Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
                email.setError("Please provide valid email");
                email.requestFocus();
                return;
            }
            FirebaseAuth.getInstance().sendPasswordResetEmail(Email)
                    .addOnCompleteListener(task -> Toast.makeText(Reset.this,"Reset Password Link Sent",Toast.LENGTH_SHORT).show());


        });
    }
}