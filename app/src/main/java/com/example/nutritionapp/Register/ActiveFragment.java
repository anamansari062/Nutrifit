package com.example.nutritionapp.Register;

import android.content.Intent;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.nutritionapp.Activity.MainActivity;
import com.example.nutritionapp.R;
import com.example.nutritionapp.databinding.FragmentActiveBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.zip.DeflaterOutputStream;


public class ActiveFragment extends Fragment {
    RelativeLayout lactive,mactive,hactive;
    private FragmentActiveBinding binding;
    private SharedViewModel sharedViewModel;
    private FloatingActionButton next;
    String active;
    String height, weight, age, gender;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding= FragmentActiveBinding.inflate(inflater, container, false);
        View rootView = binding.getRoot();
        lactive=rootView.findViewById(R.id.fragment_active_light);
        mactive=rootView.findViewById(R.id.fragment_active_moderate);
        hactive=rootView.findViewById(R.id.fragment_active_high);
        next= rootView.findViewById(R.id.active_next);

        lactive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lactive.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.malefemalefocus));
                mactive.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.malefemalenotfocus));
                hactive.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.malefemalenotfocus));
                active= "Light";

            }
        });
        mactive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mactive.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.malefemalefocus));
                lactive.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.malefemalenotfocus));
                hactive.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.malefemalenotfocus));
                active= "Moderate";
            }
        });
        hactive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hactive.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.malefemalefocus));
                lactive.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.malefemalenotfocus));
                mactive.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.malefemalenotfocus));
                active= "Highly";
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedViewModel= new ViewModelProvider(getActivity()).get(SharedViewModel.class);
                sharedViewModel.setActive(active);
                calorieSet();
            }
        });

        return rootView;
    }

    //This function sets the calorie i.e calculates it.
    public void calorieSet(){
        sharedViewModel.getGender().observe(getViewLifecycleOwner(), String -> setGender(String));
        sharedViewModel.getAge().observe(getViewLifecycleOwner(), String -> setAge(String));
        sharedViewModel.getWeight().observe(getViewLifecycleOwner(), String -> setWeight(String));
        sharedViewModel.getHeight().observe(getViewLifecycleOwner(), String -> setHeight(String));
        sharedViewModel.setCalories(getHeight(), getWeight(), getAge(), getGender(), active);
    }
}