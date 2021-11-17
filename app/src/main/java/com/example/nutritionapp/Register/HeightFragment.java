package com.example.nutritionapp.Register;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nutritionapp.R;
import com.example.nutritionapp.databinding.FragmentHeightBinding;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class HeightFragment extends Fragment {
    private FragmentHeightBinding binding;
    private SharedViewModel sharedViewModel;
    TextView currentheight;
    SeekBar heightbar;
    private ExtendedFloatingActionButton next,back;
    RegisterMain registerMain;
    int currentProgress;
    String mintProgress="160";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding= FragmentHeightBinding.inflate(inflater, container, false);
        View rootView = binding.getRoot();
        currentheight=rootView.findViewById(R.id.fragment_height_currentHeight);
        heightbar=(SeekBar) rootView.findViewById(R.id.fragment_height_heightBar);
        next= rootView.findViewById(R.id.height_next);
        back=rootView.findViewById(R.id.height_back);

        heightbar.setMax(300);
        heightbar.setProgress(160);
        heightbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                currentProgress=i;
                mintProgress=String.valueOf(currentProgress);
                currentheight.setText(mintProgress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        registerMain = (RegisterMain) getActivity();

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean valid = true;
                if(mintProgress.equals("0"))
                {
                    Toast.makeText(getContext(), "Height cannot be 0", Toast.LENGTH_SHORT).show();
                    valid=false;
                }
                if(valid) {
                    FragmentTransaction fr = getParentFragmentManager().beginTransaction();
                    fr.replace(R.id.register_container, new ActiveFragment());
//                fr.addToBackStack(null);
                    fr.commit();
                }
                registerMain.myEdit.putString("height", currentheight.getText().toString());
                registerMain.myEdit.commit();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentManager = getFragmentManager().beginTransaction();
                fragmentManager.replace(R.id.register_container, new WeightFragment()).addToBackStack(null);
                fragmentManager.commit();

            }
        });

        return rootView;
    }
}