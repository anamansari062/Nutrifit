package com.example.nutritionapp.Register;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nutritionapp.R;
import com.example.nutritionapp.databinding.FragmentEmailBinding;
import com.example.nutritionapp.databinding.FragmentWeightBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class WeightFragment extends Fragment {
    private FragmentWeightBinding binding;
    private FloatingActionButton next;
    TextView weight;
    ImageView incweight,decweight;
    ViewPagerMain viewPagerMain;
    int weight1=70;
    String weight2="70";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentWeightBinding.inflate(inflater, container, false);
        View rootView = binding.getRoot();

        weight=rootView.findViewById(R.id.fragment_weight_currentWeight);
        incweight=rootView.findViewById(R.id.fragment_weight_incrementWeight);
        decweight=rootView.findViewById(R.id.fragment_weight_decrementWeight);
        next= rootView.findViewById(R.id.weight_next);

        incweight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                weight1+=1;
                weight2=String.valueOf(weight1);
                weight.setText(weight2);
            }
        });

        decweight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(weight1>0) {
                    weight1 -= 1;
                    weight2 = String.valueOf(weight1);
                    weight.setText(weight2);
                }
            }
        });

        viewPagerMain= (ViewPagerMain) getActivity();

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPagerMain.myEdit.putString("weight", weight.getText().toString());
                viewPagerMain.myEdit.commit();
            }
        });

        return rootView;
    }
}