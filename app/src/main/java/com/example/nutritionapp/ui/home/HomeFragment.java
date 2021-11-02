package com.example.nutritionapp.ui.home;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nutritionapp.DisplayFood;
import com.example.nutritionapp.Food.FoodEntity;
import com.example.nutritionapp.Food.FoodRepository;
import com.example.nutritionapp.Food.FoodViewModel;
import com.example.nutritionapp.R;
import com.example.nutritionapp.adapters.HomeAdapter;
import com.example.nutritionapp.databinding.FragmentHomeBinding;
import com.example.nutritionapp.ui.blog.BlogViewModel;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {
    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    RelativeLayout relativeToday;
    FoodRepository foodRepository;
    FoodViewModel foodViewModel;
    LiveData<Float> totalTodayCalories;

    RecyclerView homeRecycler;
    HomeAdapter homeAdapter;
    TextView todayCalories;
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

        //homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//        todayCalories= root.findViewById(R.id.textCalories);
//        foodViewModel = new ViewModelProvider(this).get(FoodViewModel.class);
//        totalTodayCalories= foodViewModel.getTotalTodayCalories().getValue();
//        todayCalories.setText(foodViewModel.getTotalTodayCalories().getValue());


        homeRecycler = root.findViewById(R.id.home_recycler_view);
        homeAdapter = new HomeAdapter(mealImages,mealName);
        homeRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        homeRecycler.setAdapter(homeAdapter);

        relativeToday= root.findViewById(R.id.relative_today);
        relativeToday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(root.getContext(), DisplayFood.class);
                intent.putExtra("type", "Today");
                startActivity(intent);
            }
        });

        homeAdapter.setOnClickListener(new HomeAdapter.itemOnClickListener() {
            @Override
            public void onItemClick(String type) {
                        Intent intent= new Intent(getContext(), DisplayFood.class);
                        intent.putExtra("type", type);
                        startActivity(intent);

            }
        });

//        TextView textView = binding.textCalories;
//        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}