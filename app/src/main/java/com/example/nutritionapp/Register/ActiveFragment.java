package com.example.nutritionapp.Register;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.nutritionapp.Activity.MainActivity;
import com.example.nutritionapp.R;
import com.example.nutritionapp.databinding.FragmentActiveBinding;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.zip.DeflaterOutputStream;


public class ActiveFragment extends Fragment {
    RelativeLayout lactive,mactive,hactive;
    private FragmentActiveBinding binding;
    private SharedViewModel sharedViewModel;
    private ExtendedFloatingActionButton next,back;
    String active;
    String email, mobile, pass, name, gender, age, height, weight;

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }

    public String getPass() {
        return pass;
    }

    public String getName() {
        return name;
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
        back=rootView.findViewById(R.id.active_back);

        lactive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lactive.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.registermalefemalefocus));
                mactive.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.registermalefemalenotfocus));
                hactive.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.registermalefemalenotfocus));
                active= "Light";

            }
        });
        mactive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mactive.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.registermalefemalefocus));
                lactive.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.registermalefemalenotfocus));
                hactive.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.registermalefemalenotfocus));
                active= "Moderate";
            }
        });
        hactive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hactive.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.registermalefemalefocus));
                lactive.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.registermalefemalenotfocus));
                mactive.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.registermalefemalenotfocus));
                active= "Highly";
            }
        });

        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("MySharedPref",MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean valid = true;
                if(active.equals(""))
                {
                    Toast.makeText(getContext(), "This field cannot be empty", Toast.LENGTH_SHORT).show();
                    valid=false;
                }
                if(valid) {
                    FragmentTransaction fr = getParentFragmentManager().beginTransaction();
                    fr.replace(R.id.register_container, new FragmentCalorie());
//                fr.addToBackStack(null);
                    fr.commit();
                }

//                sharedViewModel= new ViewModelProvider(getActivity()).get(SharedViewModel.class);
//                sharedViewModel.setActive(active);
                myEdit.putString("active", active);
                myEdit.commit();
//                calorieSet();
//                dataSet();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentManager = getFragmentManager().beginTransaction();
                fragmentManager.replace(R.id.register_container, new HeightFragment()).addToBackStack(null);
                fragmentManager.commit();

            }
        });

        return rootView;
    }

//    private void dataSet() {
//        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("MySharedPref",MODE_PRIVATE);
//        SharedPreferences.Editor myEdit = sharedPreferences.edit();
//
//        myEdit.putString("email", "aksjdsk");
//        myEdit.putString("mobile", getMobile());
//        myEdit.putString("pass", getPass());
//        myEdit.putString("name", getName());
//        myEdit.putString("mobile", getMobile());
//        myEdit.putString("mobile", getMobile());
//        myEdit.putString("mobile", getMobile());
//        myEdit.putString("mobile", getMobile());
//        myEdit.putString("mobile", getMobile());
//
// Once the changes have been made,
// we need to commit to apply those changes made,
// otherwise, it will throw an error
//        myEdit.commit();
//    }

    //This function sets the calorie i.e calculates it.
//    public void calorieSet(){
//        sharedViewModel.getGender().observe(getViewLifecycleOwner(), String -> setGender(String));
//        sharedViewModel.getAge().observe(getViewLifecycleOwner(), String -> setAge(String));
//        sharedViewModel.getWeight().observe(getViewLifecycleOwner(), String -> setWeight(String));
//        sharedViewModel.getHeight().observe(getViewLifecycleOwner(), String -> setHeight(String));
//        sharedViewModel.setCalories(getHeight(), getWeight(), getAge(), getGender(), active);
//
//
//    }
}