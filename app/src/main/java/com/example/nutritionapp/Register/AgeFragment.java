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
import com.example.nutritionapp.databinding.FragmentAgeBinding;
import com.example.nutritionapp.databinding.FragmentEmailBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AgeFragment extends Fragment {
    TextView age;
    ImageView incage,decage;
    private FloatingActionButton next;
    private FragmentAgeBinding binding;
    ViewPagerMain viewPagerMain;

    int age1=18;
    String age2="18";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAgeBinding.inflate(inflater, container, false);
        View rootView = binding.getRoot();
        age = rootView.findViewById(R.id.fragment_age_currentAge);
        incage=rootView.findViewById(R.id.fragment_age_incrementAge);
        decage=rootView.findViewById(R.id.fragment_age_decrementAge);
        next= rootView.findViewById(R.id.age_next);

        incage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                age1+=1;
                age2=String.valueOf(age1);
                age.setText(age2);
            }
        });
        decage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(age1>0) {
                    age1 -= 1;
                    age2 = String.valueOf(age1);
                    age.setText(age2);
                }
            }
        });

        viewPagerMain= (ViewPagerMain) getActivity();

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPagerMain.myEdit.putString("age", age.getText().toString());
                viewPagerMain.myEdit.commit();
            }
        });


        return rootView;
    }
}