package com.example.nutritionapp.Register;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nutritionapp.R;
import com.example.nutritionapp.databinding.FragmentWeightBinding;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class WeightFragment extends Fragment {
    private FragmentWeightBinding binding;
    private ExtendedFloatingActionButton next,back;
    EditText weight;
    ImageView incweight,decweight;
    RegisterMain registerMain;
    int weight1;
    String weight2;


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

        incweight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(weight.getText().toString().equals(""))
                    weight1= 1;
                else
                    weight1= Integer.parseInt(weight.getText().toString()) + 1;
                weight2=String.valueOf(weight1);
                weight.setText(weight2);
            }
        });

        decweight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(weight1>0) {
                    weight1= Integer.parseInt(weight.getText().toString()) - 1;
                    weight2=String.valueOf(weight1);
                    weight.setText(weight2);
                }
            }
        });

        registerMain = (RegisterMain) getActivity();

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean valid = true;
                weight2= weight.getText().toString();
                if(weight2.equals("0") || weight2.equals(""))
                {
                    Toast.makeText(getContext(), "Weight cannot be 0", Toast.LENGTH_SHORT).show();
                    valid=false;
                }
                if(valid) {
                    FragmentTransaction fr = getParentFragmentManager().beginTransaction();
                    fr.replace(R.id.register_container, new HeightFragment());
//                fr.addToBackStack(null);
                    fr.commit();
                    registerMain.myEdit.putString("weight", weight.getText().toString());
                    registerMain.myEdit.commit();
                }

            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentManager = getFragmentManager().beginTransaction();
                fragmentManager.replace(R.id.register_container, new AgeFragment()).addToBackStack(null);
                fragmentManager.commit();

            }
        });

        return rootView;
    }
}