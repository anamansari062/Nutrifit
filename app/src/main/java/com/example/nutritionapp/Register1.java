package com.example.nutritionapp;

import static android.view.View.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Register1 extends AppCompatActivity implements OnClickListener {
    private TextView register1;
    private EditText name, email, mobile, passwd, cmpasswd;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register1);
        mAuth = FirebaseAuth.getInstance();
        register1 = (Button) findViewById(R.id.register1);
        register1.setOnClickListener(this);
        name = (EditText) findViewById(R.id.Name);
        email = (EditText) findViewById(R.id.Email);
        mobile = (EditText) findViewById(R.id.mobile);
        passwd = (EditText) findViewById(R.id.Password);
        progressBar = (ProgressBar) findViewById(R.id.progressBar2);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.register1:
                registeruser();
                break;
        }
    }

    private void registeruser() {
        String Email = email.getText().toString().trim();
        String Name = name.getText().toString().trim();
        String Mobile = mobile.getText().toString().trim();
        String Password = passwd.getText().toString().trim();

        if (Name.isEmpty()) {
            name.setError("Name is required");
            name.requestFocus();
            return;
        }
        if (Email.isEmpty()) {
            email.setError("Email is required");
            email.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            email.setError("Please provide valid email");
            email.requestFocus();
        }
        if (Mobile.isEmpty()) {
            mobile.setError("Mobile number is required");
            mobile.requestFocus();
            return;
        }
        if (Password.length() < 6) {
            passwd.setError("Mobile number is required");
            passwd.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(Email, Password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            User user=new User(Name, Email, Mobile);

                            FirebaseDatabase.getInstance().getReference("USERS")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(Register1.this, "user has been registerd", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(VISIBLE);
                                    } else {
                                        Toast.makeText(Register1.this, "failed to register", Toast.LENGTH_SHORT).show();
                                        progressBar.setVisibility(GONE);
                                    }
                                }
                            });

                        } else {
                            Toast.makeText(Register1.this, "failed to register", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(GONE);
                        }
                    }
                });
    }

}