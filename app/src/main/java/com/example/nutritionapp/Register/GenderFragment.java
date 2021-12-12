package com.example.nutritionapp.Register;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.nutritionapp.R;
import com.example.nutritionapp.databinding.FragmentGenderBinding;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;


public class GenderFragment extends Fragment {
    private FragmentGenderBinding binding;
    String typeOfUser="";
    private ExtendedFloatingActionButton next,back;
    RegisterMain registerMain;
    RelativeLayout male,female;
    private boolean selectedMale, selectedFemale;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentGenderBinding.inflate(inflater, container, false);
        View rootView = binding.getRoot();
        male = rootView.findViewById(R.id.fragment_gender_maleLogo);
        female = rootView.findViewById(R.id.fragment_gender_femaleLogo);
        next= rootView.findViewById(R.id.gender_next);
        back=rootView.findViewById(R.id.gender_back);

        String active = "light";
        int weight =50, age = 18, height = 180;
        //  to get the data
        Bundle bundle = this.getArguments();
        if(bundle!=null) {
            selectedFemale = bundle.getBoolean("selectedFemale", false);
            selectedMale = bundle.getBoolean("selectedMale", false);
            age = bundle.getInt("age", 18);
            weight = bundle.getInt("weight", 50);
            height = bundle.getInt("height", 18);
            active = bundle.getString("active", "light");
            if(selectedFemale) {
                female.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.registermalefemalefocus));
                typeOfUser= "Female";
            }
            if(selectedMale) {
                male.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.registermalefemalefocus));
                typeOfUser = "Male";
            }
        }
        male.setOnClickListener(view -> {
            male.setBackground(ContextCompat.getDrawable(requireContext(),R.drawable.registermalefemalefocus));
            // to un-focus the female logo if it was selected
            female.setBackground(ContextCompat.getDrawable(requireContext(),R.drawable.registermalefemalenotfocus));
            selectedMale=true;
            selectedFemale=false;
            typeOfUser = "Male";
        });
        female.setOnClickListener(view -> {
            female.setBackground(ContextCompat.getDrawable(requireContext(),R.drawable.registermalefemalefocus));
            // to un-focus the male logo if it was selected
            male.setBackground(ContextCompat.getDrawable(requireContext(),R.drawable.registermalefemalenotfocus));
            selectedFemale=true;
            selectedMale=false;
            typeOfUser= "Female";

        });

        registerMain = (RegisterMain) getActivity();

        int finalAge = age;
        int finalHeight = height;
        int finalWeight = weight;
        String finalActive = active;
        next.setOnClickListener(view -> {
            boolean valid=true;
            if(typeOfUser.equals(""))
            {
                Toast.makeText(getContext(), "This field cannot be empty", Toast.LENGTH_SHORT).show();
                valid=false;
            }
            if(valid) {
                AgeFragment ageFragment = new AgeFragment();
                sendInfo(ageFragment, finalAge, finalWeight, finalHeight, finalActive);
                getFragmentManager().beginTransaction()
                        .replace(R.id.register_container, ageFragment)
                        .commit();
            }
            registerMain.myEdit.putString("gender", typeOfUser);
            registerMain.myEdit.commit();
        });
        back.setOnClickListener(view -> {
            FragmentPassword fragmentPassword = new FragmentPassword();
            sendInfo(fragmentPassword, finalAge, finalWeight, finalHeight, finalActive);
            getFragmentManager().beginTransaction()
                    .replace(R.id.register_container, fragmentPassword)
                    .commit();

        });

        return rootView;
    }

    private void sendInfo(Fragment fragment, int age, int weight, int height, String active){

        // get data from SharedPreferences if next/back is pressed
        /** data  only gets stored in shared preferences if next is clicked
         * other wise take data from editText
         * */
        SharedPreferences sh = requireActivity().getSharedPreferences("MySharedPref", MODE_PRIVATE);
        String name= sh.getString("name", "");
        String email= sh.getString("email", "");
        String mobile= sh.getString("mobile", "");
        String passwd= sh.getString("pass", "");

        // add data to bundle
        Bundle bundle = new Bundle();
        bundle.putString("name", name);
        bundle.putString("email", email);
        bundle.putString("mobile", mobile);
        bundle.putString("pass", passwd);
        bundle.putBoolean("selectedMale", selectedMale);
        bundle.putBoolean("selectedFemale", selectedFemale);
        bundle.putInt("age", age);
        bundle.putInt("weight", weight);
        bundle.putInt("height", height);
        bundle.putString("active", active);
        fragment.setArguments(bundle);
    }
}