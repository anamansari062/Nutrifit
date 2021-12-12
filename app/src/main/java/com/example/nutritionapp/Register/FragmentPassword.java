package com.example.nutritionapp.Register;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.nutritionapp.R;
import com.example.nutritionapp.databinding.FragmentPasswordBinding;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;
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

        boolean selectedMale = false;
        boolean selectedFemale = false;
        String active = "light";
        int weight =50, age = 18, height = 180;
        // tp get the data
        Bundle bundle = this.getArguments();
        if(bundle!=null) {
            String passwd = bundle.getString("pass", "");
            selectedMale = bundle.getBoolean("selectedMale", false);
            selectedFemale = bundle.getBoolean("selectedFemale", false);
            weight = bundle.getInt("weight", 50);
            age = bundle.getInt("age", 18);
            height = bundle.getInt("height", 180);
            active = bundle.getString("active", "light");

            // set the gotten data
            textPass.setText(passwd);
            textPassC.setText(passwd);
        }
        int tempAge = age;
        boolean finalSelectedMale = selectedMale;
        boolean finalSelectedFemale = selectedFemale;
        int finalAge = age;
        int finalWeight = weight;
        int finalHeight = height;
        String finalActive = active;
        next.setOnClickListener(view -> {
            Boolean valid = true;

            if(!validatePassword()){
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

            if(valid) {
                GenderFragment fragmentGender = new GenderFragment();
                sendInfo(fragmentGender, finalSelectedMale, finalSelectedFemale, finalAge, finalWeight, finalHeight, finalActive);
                getFragmentManager().beginTransaction()
                        .replace(R.id.register_container, fragmentGender)
                        .commit();
            }
            sharedViewModel= new ViewModelProvider(getActivity()).get(SharedViewModel.class);
            if(textPass.getText().toString()!= null){
                registerMain.myEdit.putString("pass", textPass.getText().toString());
                registerMain.myEdit.commit();
            }
        });
        back.setOnClickListener(view -> {
            FragmentEmail fragmentEmail = new FragmentEmail();
            sendInfo(fragmentEmail, finalSelectedMale, finalSelectedFemale, finalAge, finalWeight, finalHeight, finalActive);
            getFragmentManager().beginTransaction()
            .replace(R.id.register_container, fragmentEmail)
            .commit();


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
        }
        else if (!PASSWORD_PATTERN.matcher(password).matches()) {
            passwordLayout.setError("Password too weak");
            textPass.setError(null);
            return false;
        } else {
            passwordLayout.setError(null);
            return true;
        }
    }

    private void sendInfo(Fragment fragment,  boolean selectedMale, boolean selectedFemale, int age, int weight, int height, String active){

        // get data from SharedPreferences if next/back is pressed
        /** data  only gets stored in shared preferences if next is clicked
         * other wise take data from editText
         * */
        SharedPreferences sh = requireActivity().getSharedPreferences("MySharedPref", MODE_PRIVATE);
        String name= sh.getString("name", "");
        String email= sh.getString("email", "");
        String mobile= sh.getString("mobile", "");


        String passwd = textPass.getText().toString();
        String confirmPasswd = textPassC.getText().toString();

        // add data to bundle
        Bundle bundle = new Bundle();
        bundle.putString("name", name);
        bundle.putString("email", email);
        bundle.putString("mobile", mobile);
        bundle.putString("pass", passwd);
        bundle.putString("confirmPass", confirmPasswd);
        bundle.putBoolean("selectedMale", selectedMale);
        bundle.putBoolean("selectedFemale", selectedFemale);
        bundle.putInt("age", age);
        bundle.putInt("weight", weight);
        bundle.putInt("height", height);
        bundle.putString("active", active);
        fragment.setArguments(bundle);

    }

}