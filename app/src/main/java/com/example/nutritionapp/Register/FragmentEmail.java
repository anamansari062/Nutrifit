package com.example.nutritionapp.Register;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nutritionapp.Activity.Login;
import com.example.nutritionapp.R;
import com.example.nutritionapp.databinding.FragmentEmailBinding;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

public class FragmentEmail extends Fragment {
    private FragmentEmailBinding binding;
    RegisterMain registerMain;
    EditText textEmail, textMobile,textName;
    ExtendedFloatingActionButton add,back;
    TextInputLayout nameL,emailL,mobileL;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEmailBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        textEmail= root.findViewById(R.id.text_email);
        textMobile= root.findViewById(R.id.text_mobile);
        textName=root.findViewById(R.id.text_name);
        add= root.findViewById(R.id.email_next);
        back=root.findViewById(R.id.email_back);
        nameL=root.findViewById(R.id.nameLayout);
        emailL=root.findViewById(R.id.emailLayout);
        mobileL=root.findViewById(R.id.mobileLayout);


        registerMain = (RegisterMain) getActivity();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(textEmail.getText().toString()!=null & textMobile.getText().toString()!= null) {
                    registerMain.myEdit.putString("email", textEmail.getText().toString());
                    registerMain.myEdit.putString("mobile", textMobile.getText().toString());
                    registerMain.myEdit.commit();
                }

                Boolean valid = true;
                if ( !validateEmail()) {
                    valid = false;
                }
//                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
//                    Toast.makeText(getContext(), "Email not valid", Toast.LENGTH_LONG).show();
//                }

                if (textMobile.length() == 0||textMobile.length()<10||textMobile.length()>10) {
                    mobileL.setError("Please Enter a Valid Mobile Number");
                    textMobile.setError(null);
                    valid = false;

                }
                if(textName.length()==0)
                {
//            Toast.makeText(getContext(), "F", Toast.LENGTH_SHORT).show();
                    nameL.setError("Name is required");
                    textName.setError(null);
                    valid=false;
                }
                if(valid) {
                    FragmentTransaction fr = getParentFragmentManager().beginTransaction();
                    fr.replace(R.id.register_container, new FragmentPassword());
//                fr.addToBackStack(null);
                    fr.commit();
                    registerMain.myEdit.putString("name", textName.getText().toString());
                    registerMain.myEdit.commit();
                }

            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), Login.class);
                startActivity(intent);
            }
        });
        textName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                nameL.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        textMobile.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mobileL.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        textEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                emailL.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        return root;

    }
    private boolean validateEmail() {
        String email = textEmail.getText().toString().trim();

        if (email.isEmpty()) {
            emailL.setError("Email is required");
            textEmail.setError(null);
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailL.setError("Please Enter a Valid Email Address");
            textEmail.setError(null);
            return false;
        } else {
            emailL.setError(null);
            return true;
        }
    }
}