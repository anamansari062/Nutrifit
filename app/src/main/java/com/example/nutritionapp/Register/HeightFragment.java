package com.example.nutritionapp.Register;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nutritionapp.R;
import com.example.nutritionapp.databinding.FragmentHeightBinding;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;


public class HeightFragment extends Fragment {
    private FragmentHeightBinding binding;
    private SharedViewModel sharedViewModel;
    TextView currentHeight;
    SeekBar heightBar;
    private ExtendedFloatingActionButton next,back;
    RegisterMain registerMain;
    int currentProgress;
    String mintProgress="160";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding= FragmentHeightBinding.inflate(inflater, container, false);
        View rootView = binding.getRoot();
        currentHeight=rootView.findViewById(R.id.fragment_height_currentHeight);
        heightBar=(SeekBar) rootView.findViewById(R.id.fragment_height_heightBar);
        next= rootView.findViewById(R.id.height_next);
        back=rootView.findViewById(R.id.height_back);

        heightBar.setMax(300);
        heightBar.setProgress(160);
        heightBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                currentProgress=i;
                mintProgress=String.valueOf(currentProgress);
                currentHeight.setText(mintProgress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        int bundleHeight = 180, age=18;
        String active = "Light";
        //  to get the data
        Bundle bundle = this.getArguments();
        if(bundle!=null) {
            bundleHeight = bundle.getInt("height", 180);
            active = bundle.getString("active", "Light");

            heightBar.setProgress(bundleHeight);
        }
        if(bundle==null){
            heightBar.setProgress(180);
        }

        registerMain = (RegisterMain) getActivity();

        String finalActive = active;
        next.setOnClickListener(view -> {
            Boolean valid = true;
            if(mintProgress.equals("0"))
            {
                Toast.makeText(getContext(), "Height cannot be 0", Toast.LENGTH_SHORT).show();
                valid=false;
            }
            if(valid) {
                ActiveFragment activeFragment = new ActiveFragment();
                sendInfo(activeFragment, finalActive);
                getFragmentManager().beginTransaction()
                        .replace(R.id.register_container, activeFragment)
                        .commit();
            }
            registerMain.myEdit.putString("height", currentHeight.getText().toString());
            registerMain.myEdit.commit();
        });
        back.setOnClickListener(view -> {
            WeightFragment weightFragment = new WeightFragment();
            sendInfo(weightFragment, finalActive);
            getFragmentManager().beginTransaction()
                    .replace(R.id.register_container, weightFragment)
                    .commit();

        });

        return rootView;
    }

    private void sendInfo(Fragment fragment, String active){

        boolean selectedMale = false;
        boolean selectedFemale = false;
        int sendHeight  = heightBar.getProgress();

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

        int intWeight = Integer.parseInt(weight);

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
        bundle.putInt("height", sendHeight);
        bundle.putString("active", active);
        fragment.setArguments(bundle);
    }
}