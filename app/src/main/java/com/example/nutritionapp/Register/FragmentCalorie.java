package com.example.nutritionapp.Register;

import static android.view.View.GONE;
import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nutritionapp.Activity.MainActivity;
import com.example.nutritionapp.R;
import com.example.nutritionapp.Register1;
import com.example.nutritionapp.databinding.FragmentBlogBinding;
import com.example.nutritionapp.databinding.FragmentCalorieBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;


public class FragmentCalorie extends Fragment {
    private SharedViewModel sharedViewModel;
    TextView calories;
    FragmentCalorieBinding binding;
    Button register;
    String height, weight, age, gender, active, name, pass, email, mobile;
    String Email,Password;
    private FirebaseAuth mAuth;

    public void setName(String name) {
        this.name = name;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public String getPass() {
        return pass;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }

    public String getHeight() {
        return height;
    }

    public String getWeight() {
        return weight;
    }

    public String getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getActive() {
        return active;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentCalorieBinding.inflate(inflater, container, false);
        View rootView = binding.getRoot();
        calories= rootView.findViewById(R.id.display_calories);
        register= rootView.findViewById(R.id.button_register);
        mAuth=FirebaseAuth.getInstance();

        sharedViewModel = new ViewModelProvider(getActivity()).get(SharedViewModel.class);
        sharedViewModel.getEmail().observe(getViewLifecycleOwner(), this::setEmail);
        sharedViewModel.getMobile().observe(getViewLifecycleOwner(), this::setMobile);
        sharedViewModel.getPass().observe(getViewLifecycleOwner(), this::setPass);
        sharedViewModel.getName().observe(getViewLifecycleOwner(), this::setName);
        sharedViewModel.getGender().observe(getViewLifecycleOwner(), this::setGender);
        sharedViewModel.getAge().observe(getViewLifecycleOwner(), this::setAge);
        sharedViewModel.getWeight().observe(getViewLifecycleOwner(), this::setWeight);
        sharedViewModel.getHeight().observe(getViewLifecycleOwner(), this::setHeight);
        sharedViewModel.getActive().observe(getViewLifecycleOwner(), this::setActive);
        sharedViewModel.getCalories().observe(getViewLifecycleOwner(), Double-> displayCalories(Double));


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Here register function will come use get functions for taking data eg: for gender do getGender()
                registerUser();
            }
        });

        return rootView;
    }

    private void registerUser() {

        Email = getEmail();
        Password = getPass();



    }

    public void displayCalories(Double calorie) {
        calories.setText(calorie.toString());
    }


}