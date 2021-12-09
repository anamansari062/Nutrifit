package com.example.nutritionapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;

import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.nutritionapp.R;
import com.example.nutritionapp.Register.RegisterMain;
import com.example.nutritionapp.Reset;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;


public class Login extends AppCompatActivity {
    private static final String TAG = "SignInActivity";
    TextView register,reset;
    Button login;
    EditText Passwd, emailid;
    FirebaseAuth mAuth;
    SharedPreferences sharedPreferences;
    int autosave;
    TextInputLayout passwordLayout ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        register = findViewById(R.id.register);
        login = findViewById(R.id.login);
        Passwd = findViewById(R.id.Password1);
        emailid = findViewById(R.id.emailid);
        reset=findViewById(R.id.reset);
        passwordLayout = findViewById(R.id.etPasswordLayout);

        mAuth = FirebaseAuth.getInstance();
        sharedPreferences = getSharedPreferences("autoLogin", Context.MODE_PRIVATE);
        int j = sharedPreferences.getInt("key", 0);
        if (j > 0) {
            Intent activity = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(activity);
        }
        register.setOnClickListener(v -> {
            Intent intent;
            intent = new Intent(Login.this, RegisterMain.class);
            startActivity(intent);
            finish();
        });
        reset.setOnClickListener(v -> {
            Intent intent;
            intent = new Intent(Login.this, Reset.class);
            startActivity(intent);
        });
        Passwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                passwordLayout.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        login.setOnClickListener(v -> {
            if (emailid.getText().toString().contentEquals("")) {
                emailid.setError("email can't be empty");
                emailid.requestFocus();
            } else if (Passwd.getText().toString().contentEquals("")) {
                passwordLayout.setError("Please Enter Password");
                Passwd.setError(null);
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
                    .addOnCompleteListener(Login.this, task -> {
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.

                        if (!task.isSuccessful()) {
                            // there was an error
                            if (Password.length() < 6) {
                                Toast.makeText(Login.this, "error", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(Login.this, "Something went wrong", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            autosave = 1;
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putInt("key", autosave);
                            editor.apply();
                            Intent intent = new Intent(Login.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    });
        });


//        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestEmail()
//                .build();
//        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);
//        googleSignInAccount = GoogleSignIn.getLastSignedInAccount(this);
//        SignIn.setSize(SignInButton.SIZE_STANDARD);
//        SignIn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                signIn();
//            }
//        });
//    }
//    private void signIn() {
//        Intent intent = googleSignInClient.getSignInIntent();
//        startActivityForResult(intent, 1);
//
//    }
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode==1){
//            Task<GoogleSignInAccount> task=GoogleSignIn.getSignedInAccountFromIntent(data);
//            handleSignInResult(task);
//        }
//        else{
//            Toast.makeText(this,"Something Went Wrong",Toast.LENGTH_LONG).show();
//        }
//    }
//    private void handleSignInResult(Task<GoogleSignInAccount> task) {
//        try{
//            GoogleSignInAccount googleSignInAccount1=task.getResult(ApiException.class);
//            dm=new DataModal();
//
//            dm.setName(googleSignInAccount1.getDisplayName());
//            dm.setEmail(googleSignInAccount1.getEmail());
//            dm.setImageUrl(googleSignInAccount1.getPhotoUrl());
//            dm.setName2(googleSignInAccount1.getGivenName());
//
//            Intent intent=new Intent(Login.this, MainActivity.class);
//
//
//            intent.putExtra("obj",dm);
//            startActivity(intent);
//            finish();
//        }
//        catch (ApiException exp) {
//            exp.printStackTrace();
//        }
//
//
//
//    }


    }
}

