package com.example.nutritionapp.Register;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.nutritionapp.R;
import com.example.nutritionapp.databinding.FragmentPasswordBinding;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FragmentPassword extends Fragment {
    FragmentPasswordBinding binding;
    EditText textPass, textPassC;
    RegisterMain registerMain;
    TextInputLayout passwordLayout, confirmPasswordLayout;
    private ExtendedFloatingActionButton next,back;
    private SharedViewModel sharedViewModel;
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[0-9])" +         //at least 1 digit
                    "(?=.*[a-z])" +         //at least 1 lower case letter
                    "(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{8,}" +               //at least 8 characters
                    "$");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding= FragmentPasswordBinding.inflate(inflater, container, false);
        View root= binding.getRoot();

        textPass= root.findViewById(R.id.text_pass);
        textPassC= root.findViewById(R.id.text_passc);
        next= root.findViewById(R.id.pass_next);
        back=root.findViewById(R.id.pass_back);
        passwordLayout=root.findViewById(R.id.passLayout);
        confirmPasswordLayout=root.findViewById(R.id.confirmPassLayout);


        registerMain = (RegisterMain) getActivity();



        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean valid = true;
                if(!validatePassword()){
//                    textPass.setError("Password cannot be empty");
                    valid=false;
                }
                if(textPassC.length()==0){
                    confirmPasswordLayout.setError("Password cannot be empty");
                    textPassC.setError(null);
                    valid=false;
                }
                if(!(textPass.getText().toString().equals( textPassC.getText().toString())))
                {
                    confirmPasswordLayout.setError("Passwords do not match");
                    textPassC.setError(null);
                    valid = false;
                }
//                if(textPass.getText().toString().length()<8 && !isValidPassword(textPass.getText().toString()))
//                {
//                    textPass.setError("Please enter a valid password");
//                    textPass.requestFocus();
//                    valid=false;
//                }
                if(valid) {
                    FragmentTransaction fr = getParentFragmentManager().beginTransaction();
                    fr.replace(R.id.register_container, new GenderFragment());
//                fr.addToBackStack(null);
                    fr.commit();
                }
                sharedViewModel= new ViewModelProvider(getActivity()).get(SharedViewModel.class);
                if(textPass.getText().toString()!= null){
                    registerMain.myEdit.putString("pass", textPass.getText().toString());
                    registerMain.myEdit.commit();
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentManager = getFragmentManager().beginTransaction();
                fragmentManager.replace(R.id.register_container, new FragmentEmail()).addToBackStack(null);
                fragmentManager.commit();


            }
        });
        textPass.addTextChangedListener(new TextWatcher() {
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
        textPassC.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                confirmPasswordLayout.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });









        return root;
    }
    private boolean validatePassword() {
        String password = textPass.getText().toString();

        if (password.isEmpty()) {
            passwordLayout.setError("Password cannot be empty");
            textPass.setError(null);
            return false;
        } else if (!PASSWORD_PATTERN.matcher(password).matches()) {
            passwordLayout.setError("Password too weak");
            textPass.setError(null);
            return false;
        } else {
            passwordLayout.setError(null);
            return true;
        }
    }
//    public static boolean isValidPassword(final String password) {
//
//        Pattern pattern;
//        Matcher matcher;
//        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
//        pattern = Pattern.compile(PASSWORD_PATTERN);
//        matcher = pattern.matcher(password);
//
//        return matcher.matches();
//
//    }

}