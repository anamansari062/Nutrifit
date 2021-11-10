package com.example.nutritionapp.ui.home;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.nutritionapp.DisplayFood;
import com.example.nutritionapp.Food.FoodViewModel;
import com.example.nutritionapp.R;
import com.example.nutritionapp.adapters.DisplayFoodAdapter;
import com.example.nutritionapp.databinding.FragmentHomeBinding;
import com.example.nutritionapp.ui.search.SearchActivity;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    RelativeLayout relativeToday;
    FoodViewModel foodViewModel;
    ImageButton breakfastAdd, lunchAdd, snacksAdd, dinnerAdd;
    CardView cardBreakfast,cardLunch,cardSnacks,cardDinner;
    RelativeLayout fixedBreakfast,fixedLunch,fixedSnacks,fixedDinner;
    LinearLayout hiddenBreakfast,hiddenLunch,hiddenSnacks,hiddenDinner;
    RecyclerView recyclerBreakfast,recyclerLunch,recyclerSnacks,recyclerDinner;
    TextView homeBreakfast,homeLunch,homeSnacks,homeDinner,breakfastCalories,lunchCalories,snacksCalories,dinnerCalories,totalCalories;
    ImageButton showListBreakfast,showListLunch,showListDinner,showListSnacks;

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

        foodViewModel = new ViewModelProvider(this).get(FoodViewModel.class);

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


        //To display all food list & calories
        totalCalories = root.findViewById(R.id.total_calories);
        foodViewModel.getTotalTodayCalories().observe(getViewLifecycleOwner(), Float -> setTotalCalories(Float));

        breakfastCalories = root.findViewById(R.id.home_breakfast_calories);
        foodViewModel.getTotalBreakfastCalories().observe(getViewLifecycleOwner(), Float -> setBreakfastCalories(Float));

        lunchCalories = root.findViewById(R.id.home_lunch_calories);
        foodViewModel.getTotalLunchCalories().observe(getViewLifecycleOwner(), Float -> setLunchCalories(Float));

        snacksCalories = root.findViewById(R.id.home_snacks_calories);
        foodViewModel.getTotalSnacksCalories().observe(getViewLifecycleOwner(), Float -> setSnacksCalories(Float));

        dinnerCalories = root.findViewById(R.id.home_dinner_calories);
        foodViewModel.getTotalDinnerCalories().observe(getViewLifecycleOwner(), Float -> setDinnerCalories(Float));


        relativeToday= root.findViewById(R.id.relative_today);
        relativeToday.setOnClickListener(view -> {
            Intent intent= new Intent(root.getContext(), DisplayFood.class);
            intent.putExtra("type", "Today");
            startActivity(intent);
        });

        //To display breakfast list using cardView
        cardBreakfast= root.findViewById(R.id.base_breakfast);
        fixedBreakfast= root.findViewById(R.id.fixed_breakfast);
        hiddenBreakfast= root.findViewById(R.id.hidden_breakfast);
        homeBreakfast= root.findViewById(R.id.home_breakfast);
        showListBreakfast= root.findViewById(R.id.show_breakfast_list);

        recyclerBreakfast = root.findViewById(R.id.recycler_breakfast);
        recyclerBreakfast.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerBreakfast.setHasFixedSize(true);

        final DisplayFoodAdapter adapterBreakfast = new DisplayFoodAdapter();
        recyclerBreakfast.setAdapter(adapterBreakfast);
        foodViewModel.getAllBreakfast().observe(getViewLifecycleOwner(), adapterBreakfast::setFoods);
        showListBreakfast.setOnClickListener(view -> {
            if (hiddenBreakfast.getVisibility() == View.VISIBLE) {
                TransitionManager.beginDelayedTransition(cardBreakfast,
                        new AutoTransition());
                showListBreakfast.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
                hiddenBreakfast.setVisibility(View.GONE);
            }
            else {
                showListBreakfast.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
                TransitionManager.beginDelayedTransition(cardBreakfast,
                        new AutoTransition());
                hiddenBreakfast.setVisibility(View.VISIBLE);
            }
        });

        //To display Lunch list using cardView

        cardLunch= root.findViewById(R.id.base_lunch);
        fixedLunch= root.findViewById(R.id.fixed_lunch);
        hiddenLunch= root.findViewById(R.id.hidden_lunch);
        homeLunch= root.findViewById(R.id.home_lunch);
        showListLunch= root.findViewById(R.id.show_lunch_list);

        recyclerLunch = root.findViewById(R.id.recycler_lunch);
        recyclerLunch.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerLunch.setHasFixedSize(true);


        final DisplayFoodAdapter adapterLunch = new DisplayFoodAdapter();
        recyclerLunch.setAdapter(adapterLunch);
        foodViewModel.getAllLunch().observe(getViewLifecycleOwner(), adapterLunch::setFoods);
        showListLunch.setOnClickListener(view -> {
            if (hiddenLunch.getVisibility() == View.VISIBLE) {
                TransitionManager.beginDelayedTransition(cardLunch,
                        new AutoTransition());
                showListLunch.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
                hiddenLunch.setVisibility(View.GONE);
            }
            else {
                showListLunch.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
                TransitionManager.beginDelayedTransition(cardLunch,
                        new AutoTransition());
                hiddenLunch.setVisibility(View.VISIBLE);
            }
        });

        //To display Snacks list using cardView

        cardSnacks= root.findViewById(R.id.base_snacks);
        fixedSnacks= root.findViewById(R.id.fixed_snacks);
        hiddenSnacks= root.findViewById(R.id.hidden_snacks);
        homeSnacks= root.findViewById(R.id.home_snacks);
        showListSnacks = root.findViewById(R.id.show_snacks_list);

        recyclerSnacks = root.findViewById(R.id.recycler_snacks);
        recyclerSnacks.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerSnacks.setHasFixedSize(true);


        final DisplayFoodAdapter adapterSnacks = new DisplayFoodAdapter();
        recyclerSnacks.setAdapter(adapterSnacks);
        foodViewModel.getAllSnacks().observe(getViewLifecycleOwner(), adapterSnacks::setFoods);
        showListSnacks.setOnClickListener(view -> {
            if (hiddenSnacks.getVisibility() == View.VISIBLE) {
                TransitionManager.beginDelayedTransition(cardSnacks,
                        new AutoTransition());
                showListSnacks.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
                hiddenSnacks.setVisibility(View.GONE);
            }
            else {
                showListSnacks.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
                TransitionManager.beginDelayedTransition(cardSnacks,
                        new AutoTransition());
                hiddenSnacks.setVisibility(View.VISIBLE);
            }
        });

        //To display Dinner list using cardView

        cardDinner= root.findViewById(R.id.base_dinner);
        fixedDinner= root.findViewById(R.id.fixed_dinner);
        hiddenDinner= root.findViewById(R.id.hidden_dinner);
        homeDinner= root.findViewById(R.id.home_dinner);
        showListDinner= root.findViewById(R.id.show_dinner_list);

        recyclerDinner = root.findViewById(R.id.recycler_dinner);
        recyclerDinner.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerDinner.setHasFixedSize(true);


        final DisplayFoodAdapter adapterDinner = new DisplayFoodAdapter();
        recyclerDinner.setAdapter(adapterDinner);
        foodViewModel.getAllDinner().observe(getViewLifecycleOwner(), adapterDinner::setFoods);
        showListDinner.setOnClickListener(view -> {
            if (hiddenDinner.getVisibility() == View.VISIBLE) {
                TransitionManager.beginDelayedTransition(cardDinner,
                        new AutoTransition());
                showListDinner.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
                hiddenDinner.setVisibility(View.GONE);
            }
            else {
                showListDinner.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
                TransitionManager.beginDelayedTransition(cardDinner,
                        new AutoTransition());
                hiddenDinner.setVisibility(View.VISIBLE);
            }
        });
        return root;
    }

    private void setDinnerCalories(Float calories) {
        if(calories== null)
            dinnerCalories.setText("0");
        else
            dinnerCalories.setText(calories.toString());
    }

    private void setSnacksCalories(Float calories) {
        if(calories== null)
            snacksCalories.setText("0");
        else
            snacksCalories.setText(calories.toString());
    }

    private void setLunchCalories(Float calories) {
        if(calories== null)
            lunchCalories.setText("0");
        else
            lunchCalories.setText(calories.toString());
    }

    private void setBreakfastCalories(Float calories) {
        if(calories== null)
            breakfastCalories.setText("0");
        else
            breakfastCalories.setText(calories.toString());
    }

    private void setTotalCalories(Object calories) {
        if(calories== null)
            totalCalories.setText("0");
        else
            totalCalories.setText(calories.toString());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}