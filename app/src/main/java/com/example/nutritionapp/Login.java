package com.example.nutritionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;


public class Login extends AppCompatActivity {
    com.google.android.gms.common.SignInButton SignIn;
    GoogleSignInClient googleSignInClient;
    GoogleSignInAccount googleSignInAccount;
    Button register2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        SignIn = findViewById(R.id.google_sign_in);
        register2=findViewById(R.id.register2);
        register2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(Login.this,Register1.class);
                startActivity(intent);
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
            Intent intent=new Intent(this, MyProfile.class);
            intent.putExtra("obj",dm);
            startActivity(intent);
        }
        catch (ApiException exp){

        }



    }


}

