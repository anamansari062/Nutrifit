package com.example.nutritionapp.Register;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nutritionapp.R;
import com.example.nutritionapp.databinding.FragmentAgeBinding;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

public class AgeFragment extends Fragment {
    TextView age;
    ImageView incage,decage;
    private ExtendedFloatingActionButton next,back;
    private FragmentAgeBinding binding;
    RegisterMain registerMain;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAgeBinding.inflate(inflater, container, false);
        View rootView = binding.getRoot();
        age = rootView.findViewById(R.id.fragment_age_currentAge);
        incage=rootView.findViewById(R.id.fragment_age_incrementAge);
        decage=rootView.findViewById(R.id.fragment_age_decrementAge);
        next= rootView.findViewById(R.id.age_next);
        back=rootView.findViewById(R.id.age_back);

        int weight =50, bundleAge,bundleHeight = 180;
        String active = "light";
        //  to get the data
        Bundle bundle = this.getArguments();
        if(bundle!=null) {
            weight = bundle.getInt("weight", 50);
            bundleAge = bundle.getInt("age", 18);
            bundleHeight = bundle.getInt("height", 180);
            active = bundle.getString("active", "light");

            age.setText(String.valueOf(bundleAge));
        }
        if(bundle==null){
            age.setText("18");
        }
        final int[][] age1 = {{Integer.parseInt(String.valueOf(age.getText()))}};
        final String[] age2 = {"18"};
        incage.setOnClickListener(view -> {
            age1[0][0] +=1;
            age2[0] =String.valueOf(age1[0][0]);
            age.setText(age2[0]);
        });
        decage.setOnClickListener(view -> {
            if(age1[0][0] >0) {
                age1[0][0] -= 1;
                age2[0] = String.valueOf(age1[0][0]);
                age.setText(age2[0]);
            }
        });

        registerMain = (RegisterMain) getActivity();
        int finalWeight = weight;
        String finalActive = active;
        int finalBundleHeight = bundleHeight;
        next.setOnClickListener(view -> {
            boolean valid = true;
            if(age2[0].equals("0"))
            {
                Toast.makeText(getContext(), "Age cannot be 0", Toast.LENGTH_SHORT).show();
                valid=false;
            }
            if(valid) {
                WeightFragment weightFragment = new WeightFragment();
                sendInfo(weightFragment, finalWeight, finalBundleHeight, finalActive);
                getFragmentManager().beginTransaction()
                        .replace(R.id.register_container, weightFragment)
                        .commit();
            }
            registerMain.myEdit.putString("age", age.getText().toString());
            registerMain.myEdit.commit();
        });
        back.setOnClickListener(view -> {
            GenderFragment genderFragment = new GenderFragment();
            sendInfo(genderFragment, finalWeight, finalBundleHeight, finalActive);
            getFragmentManager().beginTransaction()
                    .replace(R.id.register_container, genderFragment)
                    .commit();

        });


        return rootView;
    }

    private void sendInfo(Fragment fragment, int weight, int height, String active){

        boolean selectedMale = false;
        boolean selectedFemale = false;
        // get data from SharedPreferences if next/back is pressed
        /** data  only gets stored in shared preferences if next is clicked
         * other wise take data from editText
         * */
        int sendAge  = Integer.parseInt(age.getText().toString());
        SharedPreferences sh = requireActivity().getSharedPreferences("MySharedPref", MODE_PRIVATE);
        String name= sh.getString("name", "");
        String email= sh.getString("email", "");
        String mobile= sh.getString("mobile", "");
        String passwd= sh.getString("pass", "");
        String age = sh.getString("age", "");
        String gender = sh.getString("gender", "");

        int intAge=0;

        try {
             intAge = Integer.parseInt(age);
        }catch (NumberFormatException e){
            System.out.println("not a number");
        }

        if(gender.equals("Male"))
            selectedMale = true;
        else
            selectedFemale= true;

        // add data to bundle
        Bundle bundle = new Bundle();
        bundle.putString("name", name);
        bundle.putString("email", email);
        bundle.putString("mobile", mobile);
        bundle.putString("pass", passwd);
        bundle.putBoolean("selectedMale", selectedMale);
        bundle.putBoolean("selectedFemale", selectedFemale);
        bundle.putInt("age", intAge);
        bundle.putInt("weight", weight);
        bundle.putInt("height", height);
        bundle.putString("active", active);
        fragment.setArguments(bundle);
    }
}