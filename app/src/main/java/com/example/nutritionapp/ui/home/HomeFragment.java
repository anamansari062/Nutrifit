package com.example.nutritionapp.ui.home;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.nutritionapp.R;
import com.example.nutritionapp.adapters.HomeAdapter;
import com.example.nutritionapp.databinding.FragmentHomeBinding;

import java.util.ArrayList;


public class HomeFragment extends Fragment {
    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    RecyclerView homeRecycler;
    HomeAdapter homeAdapter;
    ArrayList<Integer> mealImages = new ArrayList<Integer>();
    ArrayList<String> mealName= new ArrayList<String>();


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mealName.add("Breakfast");
        mealName.add("Lunch");
        mealName.add("Snacks");
        mealName.add("Dinner");

        mealImages.add(R.drawable.breakfast);
        mealImages.add(R.drawable.lunch);
        mealImages.add(R.drawable.snacks);
        mealImages.add(R.drawable.dinner);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        homeRecycler = root.findViewById(R.id.home_recycler_view);
        homeAdapter = new HomeAdapter(mealImages,mealName);
        homeRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        homeRecycler.setAdapter(homeAdapter);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}