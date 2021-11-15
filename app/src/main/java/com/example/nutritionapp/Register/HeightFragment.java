package com.example.nutritionapp.Register;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.nutritionapp.R;
import com.example.nutritionapp.databinding.FragmentEmailBinding;
import com.example.nutritionapp.databinding.FragmentHeightBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class HeightFragment extends Fragment {
    private FragmentHeightBinding binding;
    private SharedViewModel sharedViewModel;
    TextView currentheight;
    SeekBar heightbar;
    private FloatingActionButton next;

    int currentProgress;
    String mintProgress="160";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_height, container, false);
        currentheight=rootView.findViewById(R.id.fragment_height_currentHeight);
        heightbar=(SeekBar) rootView.findViewById(R.id.fragment_height_heightBar);
        next= rootView.findViewById(R.id.height_next);

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

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedViewModel= new ViewModelProvider(getActivity()).get(SharedViewModel.class);
                sharedViewModel.setHeight(currentheight.getText().toString());
            }
        });

        return rootView;
    }
}