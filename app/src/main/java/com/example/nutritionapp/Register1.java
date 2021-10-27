package com.example.nutritionapp;

import static android.view.View.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class Register1 extends AppCompatActivity implements OnClickListener {
    private Button register1;
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
        cmpasswd=findViewById(R.id.cmpasswd);


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
        String cPassword = cmpasswd.getText().toString().trim();
        String Height=getIntent().getStringExtra("Height");
        String Weight=getIntent().getStringExtra("Weight");

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
            passwd.setError("Enter the Password");
            passwd.requestFocus();
            return;
        }
        int value=cPassword.compareTo(Password);
        if (value==1) {
            cmpasswd.setError("Password not match");
            cmpasswd.requestFocus();
            return;
        }



        mAuth.createUserWithEmailAndPassword(Email, Password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser use=mAuth.getCurrentUser();
                            use.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(Register1.this,"verification link sent",Toast.LENGTH_SHORT).show();

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(Register1.this,"failed",Toast.LENGTH_SHORT).show();
                                }
                            });
                            User user=new User(Name, Email, Mobile,Height,Weight);

                            FirebaseDatabase.getInstance().getReference("USERS")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        register1.setVisibility(VISIBLE);
                                        progressBar.setVisibility(INVISIBLE);
                                        Toast.makeText(Register1.this, "user has been registerd", Toast.LENGTH_LONG).show();



                                    } else {
                                        Toast.makeText(Register1.this, "failed to register", Toast.LENGTH_SHORT).show();
                                        progressBar.setVisibility(GONE);

                                    }
                                }
                            });
                            Intent intent=new Intent(Register1.this,Login.class);

                            intent.putExtra("Mobile",Mobile);
                            startActivity(intent);
                            finish();




                        } else {
                            Toast.makeText(Register1.this, "failed to register", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(GONE);
                        }
                    }
                });
    }

}
