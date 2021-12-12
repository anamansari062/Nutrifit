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
import com.example.nutritionapp.databinding.FragmentActiveBinding;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;


public class ActiveFragment extends Fragment {
    RelativeLayout lactive,mactive,hactive;
    private FragmentActiveBinding binding;
    private ExtendedFloatingActionButton next,back;
    RegisterMain registerMain;
    String active="";

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


        //  to get the data
        Bundle bundle = this.getArguments();
        if(bundle!=null) {
            active = bundle.getString("active", "Light");

            if(active.equals("Light"))
            {
                lactive.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.registermalefemalefocus));
                mactive.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.registermalefemalenotfocus));
                hactive.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.registermalefemalenotfocus));
            }
            if(active.equals("Moderate"))
            {
                lactive.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.registermalefemalenotfocus));
                mactive.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.registermalefemalefocus));
                hactive.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.registermalefemalenotfocus));
            }
            if(active.equals("Highly"))
            {
                lactive.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.registermalefemalenotfocus));
                mactive.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.registermalefemalenotfocus));
                hactive.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.registermalefemalefocus));
            }
        }
        if(bundle==null){
            lactive.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.registermalefemalenotfocus));
            mactive.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.registermalefemalenotfocus));
            hactive.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.registermalefemalenotfocus));
        }

        lactive.setOnClickListener(view -> {
            lactive.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.registermalefemalefocus));
            mactive.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.registermalefemalenotfocus));
            hactive.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.registermalefemalenotfocus));
            active= "Light";

        });
        mactive.setOnClickListener(view -> {
            mactive.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.registermalefemalefocus));
            lactive.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.registermalefemalenotfocus));
            hactive.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.registermalefemalenotfocus));
            active= "Moderate";
        });
        hactive.setOnClickListener(view -> {
            hactive.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.registermalefemalefocus));
            lactive.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.registermalefemalenotfocus));
            mactive.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.registermalefemalenotfocus));
            active= "Highly";
        });

        registerMain = (RegisterMain) getActivity();

        next.setOnClickListener(view -> {
            Boolean valid = true;
            if(active.equals(""))
            {
                Toast.makeText(getContext(), "This field cannot be empty", Toast.LENGTH_SHORT).show();
                valid=false;
            }
            if(valid) {
                FragmentCalorie fragmentCalorie = new FragmentCalorie();
                sendInfo(fragmentCalorie, active);
                getFragmentManager().beginTransaction()
                        .replace(R.id.register_container, fragmentCalorie)
                        .commit();
            }
            registerMain.myEdit.putString("active", active);
            registerMain.myEdit.commit();
        });
        back.setOnClickListener(view -> {
            HeightFragment heightFragment = new HeightFragment();
            sendInfo(heightFragment, active);
            getFragmentManager().beginTransaction()
                    .replace(R.id.register_container, heightFragment)
                    .commit();

        });

        return rootView;
    }

    private void sendInfo(Fragment fragment, String active){


        boolean selectedMale = false, selectedFemale = false;

        /** data  only gets stored in shared preferences if next is clicked
         * other wise take data from editText
         */
        // get data from SharedPreferences if next/back is pressed
        SharedPreferences sh = requireActivity().getSharedPreferences("MySharedPref", MODE_PRIVATE);
        String name= sh.getString("name", "");
        String email= sh.getString("email", "");
        String mobile= sh.getString("mobile", "");
        String passwd= sh.getString("pass", "");
        String age = sh.getString("age", "");
        String gender = sh.getString("gender", "");
        String weight = sh.getString("weight", "");
        String height = sh.getString("height", "");

        int intWeight = Integer.parseInt(weight);
        int intHeight = Integer.parseInt(height);

        if(gender.equals("Male"))
            selectedMale = true;
        else
            selectedFemale= true;
        int intAge = Integer.parseInt(age);

        // add data to bundle
        Bundle bundle = new Bundle();
        bundle.putString("name", name);
        bundle.putString("email", email);
        bundle.putString("mobile", mobile);
        bundle.putString("pass", passwd);
        bundle.putBoolean("selectedMale", selectedMale);
        bundle.putBoolean("selectedFemale", selectedFemale);
        bundle.putInt("age", intAge);
        bundle.putInt("weight", intWeight);
        bundle.putInt("height", intHeight);
        bundle.putString("active", active);
        fragment.setArguments(bundle);
    }

}