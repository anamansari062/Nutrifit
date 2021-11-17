package com.example.nutritionapp.Register;

import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.nutritionapp.R;
import com.example.nutritionapp.databinding.FragmentActiveBinding;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


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

        lactive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lactive.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.registermalefemalefocus));
                mactive.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.registermalefemalenotfocus));
                hactive.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.registermalefemalenotfocus));
                active= "Light";

            }
        });
        mactive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mactive.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.registermalefemalefocus));
                lactive.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.registermalefemalenotfocus));
                hactive.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.registermalefemalenotfocus));
                active= "Moderate";
            }
        });
        hactive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hactive.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.registermalefemalefocus));
                lactive.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.registermalefemalenotfocus));
                mactive.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.registermalefemalenotfocus));
                active= "Highly";
            }
        });

        registerMain = (RegisterMain) getActivity();

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean valid = true;
                if(active.equals(""))
                {
                    Toast.makeText(getContext(), "This field cannot be empty", Toast.LENGTH_SHORT).show();
                    valid=false;
                }
                if(valid) {
                    FragmentTransaction fr = getParentFragmentManager().beginTransaction();
                    fr.replace(R.id.register_container, new FragmentCalorie());
//                fr.addToBackStack(null);
                    fr.commit();
                }
                registerMain.myEdit.putString("active", active);
                registerMain.myEdit.commit();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentManager = getFragmentManager().beginTransaction();
                fragmentManager.replace(R.id.register_container, new HeightFragment()).addToBackStack(null);
                fragmentManager.commit();

            }
        });

        return rootView;
    }

}