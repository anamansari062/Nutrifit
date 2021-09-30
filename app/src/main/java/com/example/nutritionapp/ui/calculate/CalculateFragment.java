package com.example.nutritionapp.ui.calculate;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.nutritionapp.databinding.FragmentCalculateBinding;

public class CalculateFragment extends Fragment {

    private CalculateViewModel calculateViewModel;
    private FragmentCalculateBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        calculateViewModel =
                new ViewModelProvider(this).get(CalculateViewModel.class);

        binding = FragmentCalculateBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textCalculate;
        calculateViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
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