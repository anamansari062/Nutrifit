package com.example.nutritionapp.ui.home;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Layout;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nutritionapp.DisplayFood;
import com.example.nutritionapp.Food.FoodEntity;
import com.example.nutritionapp.Food.FoodRepository;
import com.example.nutritionapp.Food.FoodViewModel;
import com.example.nutritionapp.R;
import com.example.nutritionapp.adapters.DisplayFoodAdapter;
import com.example.nutritionapp.adapters.HomeAdapter;
import com.example.nutritionapp.databinding.FragmentHomeBinding;
import com.example.nutritionapp.ui.blog.BlogViewModel;
import com.example.nutritionapp.ui.search.SearchActivity;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    RelativeLayout relativeToday;
    FoodViewModel foodViewModel;
    ImageButton breakfastAdd, lunchAdd, snacksAdd, dinnerAdd;
    CardView cardBreakfast;
    RelativeLayout fixedBreakfast;
    LinearLayout hiddenBreakfast;
    RecyclerView recyclerBreakfast;
    TextView homeBreakfast;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        breakfastAdd= root.findViewById(R.id.home_breakfast_add);
        lunchAdd= root.findViewById(R.id.home_lunch_add);
        snacksAdd= root.findViewById(R.id.home_snacks_add);
        dinnerAdd= root.findViewById(R.id.home_dinner_add);

        //To intent to search activity

        breakfastAdd.setOnClickListener(view -> {
            Intent intent= new Intent(root.getContext(), SearchActivity.class);
            intent.putExtra("title", "Breakfast");
            startActivity(intent);
        });

        lunchAdd.setOnClickListener(view -> {
            Intent intent= new Intent(root.getContext(), SearchActivity.class);
            intent.putExtra("title", "Lunch");
            startActivity(intent);
        });

        snacksAdd.setOnClickListener(view -> {
            Intent intent= new Intent(root.getContext(), SearchActivity.class);
            intent.putExtra("title", "Snacks");
            startActivity(intent);
        });

        dinnerAdd.setOnClickListener(view -> {
            Intent intent= new Intent(root.getContext(), SearchActivity.class);
            intent.putExtra("title", "Dinner");
            startActivity(intent);
        });


        //To display all food list
        relativeToday= root.findViewById(R.id.relative_today);
        relativeToday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(root.getContext(), DisplayFood.class);
                intent.putExtra("type", "Today");
                startActivity(intent);
            }
        });

        //To display breakfast list using cardview

        cardBreakfast= root.findViewById(R.id.base_breakfast);
        fixedBreakfast= root.findViewById(R.id.fixed_breakfast);
        hiddenBreakfast= root.findViewById(R.id.hidden_breakfast);
        homeBreakfast= root.findViewById(R.id.home_breakfast);

        recyclerBreakfast = root.findViewById(R.id.recycler_breakfast);
        recyclerBreakfast.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerBreakfast.setHasFixedSize(true);


        final DisplayFoodAdapter adapterBreakfast = new DisplayFoodAdapter();
        recyclerBreakfast.setAdapter(adapterBreakfast);
        foodViewModel = new ViewModelProvider(this).get(FoodViewModel.class);
        foodViewModel.getAllFoodData().observe(getViewLifecycleOwner(), adapterBreakfast::setFoods);
        fixedBreakfast.setOnClickListener(view -> {
            if (hiddenBreakfast.getVisibility() == View.VISIBLE) {
                TransitionManager.beginDelayedTransition(cardBreakfast,
                        new AutoTransition());
                hiddenBreakfast.setVisibility(View.GONE);
            }
            else {

                TransitionManager.beginDelayedTransition(cardBreakfast,
                        new AutoTransition());
                hiddenBreakfast.setVisibility(View.VISIBLE);
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