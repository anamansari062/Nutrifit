package com.example.nutritionapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;

import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.nutritionapp.R;
import com.example.nutritionapp.Register.RegisterMain;
import com.example.nutritionapp.Reset;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;

import java.util.Objects;


public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "SignInActivity";
    TextView register,reset;
    Button login;
    EditText Passwd, emailID;
    FirebaseAuth mAuth;
    SharedPreferences sharedPreferences;
    int autoSave;
    TextInputLayout passwordLayout, emailLayout ;
    ConstraintLayout parentLayout ;
    final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        register = findViewById(R.id.register);
        login = findViewById(R.id.login);
        Passwd = findViewById(R.id.Password1);
        emailID = findViewById(R.id.emailid);
        reset=findViewById(R.id.reset);
        passwordLayout = findViewById(R.id.etPasswordLayout);
        emailLayout = findViewById(R.id.etEmailLayout);

        // hide toolbar
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}

        mAuth = FirebaseAuth.getInstance();
        sharedPreferences = getSharedPreferences("autoLogin", Context.MODE_PRIVATE);
        int j = sharedPreferences.getInt("key", 0);
        if (j > 0) {
            Intent activity = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(activity);
        }
        register.setOnClickListener(v -> {
            Intent intent;
            intent = new Intent(LoginActivity.this, RegisterMain.class);
            startActivity(intent);
            finish();
        });
        reset.setOnClickListener(v -> {
            Intent intent;
            intent = new Intent(LoginActivity.this, Reset.class);
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
        emailID.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                emailLayout.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        login.setOnClickListener(v -> {
            if (!validateEmail()) {
                return;
            } else if (Passwd.getText().toString().contentEquals("")) {
                passwordLayout.setError("Please Enter Password");
                Passwd.setError(null);
                Passwd.requestFocus();
                return;
            }

            String Email = emailID.getText().toString();
            final String Password = Passwd.getText().toString();

            mAuth.signInWithEmailAndPassword(Email, Password)
                    .addOnCompleteListener(LoginActivity.this, task -> {
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            try
                            {
                                throw Objects.requireNonNull(task.getException());
                            }
                            // if user enters wrong email or if email doesn't exist or disabled.
                            catch (FirebaseAuthInvalidUserException invalidEmail)
                            {
                                Log.d(TAG, "onComplete: invalid_email");
                                emailLayout.setError("Invalid Email");
                                emailID.requestFocus();
                            }
                            // if user enters wrong password.
                            catch (FirebaseAuthInvalidCredentialsException wrongPassword)
                            {
                                Log.d(TAG, "onComplete: wrong_password");
                                passwordLayout.setError("Entered password is wrong");
                                Passwd.requestFocus();
                            }catch (Exception e) {
                                Log.d(TAG, "onComplete: " + e.getMessage());
                            }
                        } else {
                            autoSave = 1;
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putInt("key", autoSave);
                            editor.apply();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    });
    });
    }

    private boolean validateEmail() {
        String email = emailID.getText().toString().trim();
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        if (email.isEmpty()) {
            emailLayout.setError("Please Enter an Email-ID");
            emailID.requestFocus();
            return false;
        }
        if (email.matches(EMAIL_PATTERN)) {
            //check email already exist or not.
            final boolean[] isNewUser = new boolean[1];
            firebaseAuth.fetchSignInMethodsForEmail(email)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()){
                            isNewUser[0] =task.getResult().getSignInMethods().isEmpty();
                            if (isNewUser[0]) {
                                isNewUser[0] = false;
                                Log.e("TAG", "Is New User!");
                                emailLayout.setError("Email does not exists");
                                emailID.requestFocus();
                            }
                            else {
                                Log.e("TAG", "Is Old User!");
                            }
                        }
                    });
            return !isNewUser[0];
        }
        else {
            emailLayout.setError("Please Enter a valid Email-ID");
            emailID.requestFocus();
            return false;
        }
    }
}

