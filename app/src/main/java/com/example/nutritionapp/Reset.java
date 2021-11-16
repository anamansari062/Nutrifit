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

        sent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email=email.getText().toString().trim();
                if (!Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
                    email.setError("Please provide valid email");
                    email.requestFocus();
                    return;
                }
                FirebaseAuth.getInstance().sendPasswordResetEmail(Email)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(Reset.this,"reset password link sent",Toast.LENGTH_SHORT).show();
                            }
                        });


            }
        });
    }
}