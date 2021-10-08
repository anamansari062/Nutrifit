package com.example.nutritionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class Register1 extends AppCompatActivity implements View.OnClickListener {
    private TextView register1;
    private EditText name,email,mobile,passwd,cmpasswd;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register1);
        mAuth=FirebaseAuth.getInstance();
        register1=(Button) findViewById(R.id.register1);
        register1.setOnClickListener(this);
        name=(EditText) findViewById(R.id.Name);
        email=(EditText) findViewById(R.id.Email);
        mobile=(EditText) findViewById(R.id.mobile);
        passwd=(EditText) findViewById(R.id.Password);
        progressBar=(ProgressBar) findViewById(R.id.progressBar2);




    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.register1:
                registeruser();
                break;
        }
    }

    private void registeruser() {
        String Email=email.getText().toString().trim();
        String Name=name.getText().toString().trim();
        String Mobile=mobile.getText().toString().trim();
        String Password=passwd.getText().toString().trim();

    if(Name.isEmpty()){
        name.setError("Name is required");
        name.requestFocus();
        return;
    }
        if(Email.isEmpty()){
            email.setError("Email is required");
            email.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(Email).matches()){
            email.setError("Please provide valid email");
            email.requestFocus();
        }
        if(Mobile.isEmpty()){
            mobile.setError("Mobile number is required");
            mobile.requestFocus();
            return;
        }
    }
}