package com.example.nutritionapp.ui.calculate;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;

import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;


import com.example.nutritionapp.MyProfile;

import com.example.nutritionapp.R;
import com.example.nutritionapp.databinding.FragmentCalculateBinding;

import java.util.Objects;

public class CalculateFragment extends Fragment {

    private CalculateViewModel calculateViewModel;
    private FragmentCalculateBinding binding;
    private Button button;

    Button mcalculate;
    TextView mcurrentheight;
    TextView mcurrentage,mcurrentweight;
    ImageView mincrementage,mincrementweight,mdecrementage,mdecrementweight;
    SeekBar mheightbar;
    RelativeLayout mmale,mfemale;

    int currentProgress;
    String mintProgress="160";
    int age=18;
    String age2="18";
    int weight=70;
    String weight2="70";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        calculateViewModel =
                new ViewModelProvider(this).get(CalculateViewModel.class);

//        View v = inflater.inflate(R.layout.fragment_calculate, container, false); //don't forget the third argument here
        binding = FragmentCalculateBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        mcalculate = root.findViewById(R.id.button);
        mcurrentage=root.findViewById(R.id.currentAge);
        mcurrentheight=root.findViewById(R.id.currentHeight);
        mcurrentweight= root.findViewById(R.id.currentWeight);
        mincrementweight=root.findViewById(R.id.incrementWeight);
        mincrementage=root.findViewById(R.id.incrementAge);
        mdecrementage=root.findViewById(R.id.decrementAge);
        mdecrementweight=root.findViewById(R.id.decrementWeight);
        mheightbar=root.findViewById(R.id.heightBar);
        mmale=root.findViewById(R.id.maleLogo);
        mfemale=root.findViewById(R.id.femaleLogo);

        mmale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mmale.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.malefemalefocus));
                mfemale.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.malefemalenotfocus));
            }
        });

        mfemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mfemale.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.malefemalefocus));
                mmale.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.malefemalenotfocus));
            }
        });

        mheightbar.setMax(300);
        mheightbar.setProgress(160);
        mheightbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                currentProgress=i;
                mintProgress=String.valueOf(currentProgress);
                mcurrentheight.setText(mintProgress);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mincrementage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                age+=1;
                age2=String.valueOf(age);
                mcurrentage.setText(age2);
            }
        });
        mincrementweight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                weight+=1;
                weight2=String.valueOf(weight);
                mcurrentweight.setText(weight2);
            }
        });
        mdecrementage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                age-=1;
                weight2=String.valueOf(age);
                mcurrentage.setText(age2);
            }
        });
        mdecrementweight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                weight-=1;
                age2=String.valueOf(weight);
                mcurrentweight.setText(weight2);
            }
        });


//        final TextView textView = binding.textCalculate;
        calculateViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
//                textView.setText(s);

            }
        });
        button = root.findViewById(R.id.button4);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MyProfile.class);
                startActivity(intent);
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
