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
import com.example.nutritionapp.databinding.FragmentWeightBinding;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

public class WeightFragment extends Fragment {
    private FragmentWeightBinding binding;
    private ExtendedFloatingActionButton next,back;
    TextView weight;
    ImageView incweight,decweight;
    RegisterMain registerMain;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentWeightBinding.inflate(inflater, container, false);
        View rootView = binding.getRoot();

        weight=rootView.findViewById(R.id.fragment_weight_currentWeight);
        incweight=rootView.findViewById(R.id.fragment_weight_incrementWeight);
        decweight=rootView.findViewById(R.id.fragment_weight_decrementWeight);
        next= rootView.findViewById(R.id.weight_next);
        back=rootView.findViewById(R.id.weight_back);


        int bundleWeight = 50, bundleHeight = 180, age=18;
        String active = "light";
        //  to get the data
        Bundle bundle = this.getArguments();
        if(bundle!=null) {
            bundleWeight = bundle.getInt("weight", 50);
            bundleHeight = bundle.getInt("height", 180);
            active = bundle.getString("active", "light");

            weight.setText(String.valueOf(bundleWeight));
        }
        if(bundle==null){
            weight.setText("50");
        }
        final int[] weight1 = {50};
        final String[] weight2 = {"50"};
        incweight.setOnClickListener(view -> {
            weight1[0] +=1;
            weight2[0] =String.valueOf(weight1[0]);
            weight.setText(weight2[0]);
        });

        decweight.setOnClickListener(view -> {
            if(weight1[0] >0) {
                weight1[0] -= 1;
                weight2[0] = String.valueOf(weight1[0]);
                weight.setText(weight2[0]);
            }
        });

        registerMain = (RegisterMain) getActivity();

        int finalBundleHeight = bundleHeight;
        String finalActive = active;
        next.setOnClickListener(view -> {
            boolean valid = true;
            if(weight2[0].equals("0"))
            {
                Toast.makeText(getContext(), "Weight cannot be 0", Toast.LENGTH_SHORT).show();
                valid=false;
            }
            if(valid) {
                HeightFragment heightFragment = new HeightFragment();
                sendInfo(heightFragment, finalBundleHeight, finalActive);
                getFragmentManager().beginTransaction()
                        .replace(R.id.register_container, heightFragment)
                        .commit();
                registerMain.myEdit.putString("weight", weight.getText().toString());
                registerMain.myEdit.commit();
            }

        });
        back.setOnClickListener(view -> {
            AgeFragment ageFragment = new AgeFragment();
            sendInfo(ageFragment, finalBundleHeight, finalActive);
            getFragmentManager().beginTransaction()
                    .replace(R.id.register_container, ageFragment)
                    .commit();
        });

        return rootView;
    }

        private void sendInfo(Fragment fragment, int height, String active){

        boolean selectedMale = false;
        boolean selectedFemale = false;
        int sendWeight  = Integer.parseInt(weight.getText().toString());
            /** data  only gets stored in shared preferences if next is clicked
             * other wise take data from editText
             * */

        // get data from SharedPreferences if next/back is pressed
        SharedPreferences sh = requireActivity().getSharedPreferences("MySharedPref", MODE_PRIVATE);
        String name= sh.getString("name", "");
        String email= sh.getString("email", "");
        String mobile= sh.getString("mobile", "");
        String passwd= sh.getString("pass", "");
        String age = sh.getString("age", "");
        String gender = sh.getString("gender", "");

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
        bundle.putInt("weight", sendWeight);
        bundle.putInt("height", height);
        bundle.putString("active", active);
        fragment.setArguments(bundle);
    }
}