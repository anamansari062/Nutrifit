package com.example.nutritionapp.Register;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.nutritionapp.Activity.MainActivity;
import com.example.nutritionapp.R;
import com.example.nutritionapp.databinding.FragmentActiveBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.zip.DeflaterOutputStream;


public class ActiveFragment extends Fragment {
    RelativeLayout lactive,mactive,hactive;
    private FragmentActiveBinding binding;
    private FloatingActionButton next;
    ViewPagerMain viewPagerMain;
    String active;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding= FragmentActiveBinding.inflate(inflater, container, false);
        View rootView = binding.getRoot();
        lactive=rootView.findViewById(R.id.fragment_active_light);
        mactive=rootView.findViewById(R.id.fragment_active_moderate);
        hactive=rootView.findViewById(R.id.fragment_active_high);
        next= rootView.findViewById(R.id.active_next);

        lactive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lactive.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.malefemalefocus));
                mactive.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.malefemalenotfocus));
                hactive.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.malefemalenotfocus));
                active= "Light";

            }
        });
        mactive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mactive.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.malefemalefocus));
                lactive.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.malefemalenotfocus));
                hactive.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.malefemalenotfocus));
                active= "Moderate";
            }
        });
        hactive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hactive.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.malefemalefocus));
                lactive.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.malefemalenotfocus));
                mactive.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.malefemalenotfocus));
                active= "Highly";
            }
        });

        viewPagerMain= (ViewPagerMain) getActivity();

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPagerMain.myEdit.putString("active", active);
                viewPagerMain.myEdit.commit();
            }
        });

        return rootView;
    }

}