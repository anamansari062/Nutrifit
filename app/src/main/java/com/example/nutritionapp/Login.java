package com.example.nutritionapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nutritionapp.ui.profile.ProfileFragment;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class Login extends AppCompatActivity {
    private static final String TAG = "SignInActivity";
    com.google.android.gms.common.SignInButton SignIn;
    GoogleSignInClient googleSignInClient;
    GoogleSignInAccount googleSignInAccount;
    TextView register2;
    Button login;
    TextView forgot;
    EditText Passwd,emailid;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        SignIn = findViewById(R.id.google_sign_in);
        register2=findViewById(R.id.register2);
        login=findViewById(R.id.login);
        forgot=findViewById(R.id.textView2);
        Passwd=findViewById(R.id.Password1);
        emailid=findViewById(R.id.emailid);
        mAuth=FirebaseAuth.getInstance();
        register2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(Login.this,Register1.class);
                startActivity(intent);
                finish();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(emailid.getText().toString().contentEquals("")){
                    emailid.setError("email can't be empty");
                    emailid.requestFocus();
                }
                else if(Passwd.getText().toString().contentEquals("")){
                    Passwd.setError("password can't be empty");
                    Passwd.requestFocus();
                }

                    String Email = emailid.getText().toString();
                    final String Password = Passwd.getText().toString();

                if (TextUtils.isEmpty(Email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(Password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.signInWithEmailAndPassword(Email, Password)
                        .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.

                                if (!task.isSuccessful()) {
                                    // there was an error
                                    if (Password.length() < 6) {
                                        Toast.makeText(Login.this,"error",Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(Login.this, "f", Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    Intent intent = new Intent(Login.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        });
                }
            });




        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);
        googleSignInAccount = GoogleSignIn.getLastSignedInAccount(this);
        SignIn.setSize(SignInButton.SIZE_STANDARD);
        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });
    }
    private void signIn() {
        Intent intent = googleSignInClient.getSignInIntent();
        startActivityForResult(intent, 1);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1){
            Task<GoogleSignInAccount> task=GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
        else{
            Toast.makeText(this,"Something Went Wrong",Toast.LENGTH_LONG).show();
        }
    }
    private void handleSignInResult(Task<GoogleSignInAccount> task) {
        try{
            GoogleSignInAccount googleSignInAccount1=task.getResult(ApiException.class);
            DataModal dm=new DataModal();

            dm.setName(googleSignInAccount1.getDisplayName());
            dm.setEmail(googleSignInAccount1.getEmail());
            dm.setImageUrl(googleSignInAccount1.getPhotoUrl());
            dm.setName2(googleSignInAccount1.getGivenName());
            Intent intent=new Intent(Login.this, MainActivity.class);
            intent.putExtra("obj",dm);
            startActivity(intent);
            finish();
        }
        catch (ApiException exp){

        }



    }


}

