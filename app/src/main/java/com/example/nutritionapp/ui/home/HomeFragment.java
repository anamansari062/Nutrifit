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
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.nutritionapp.DisplayFood;
import com.example.nutritionapp.Food.FoodViewModel;
import com.example.nutritionapp.R;
import com.example.nutritionapp.adapters.DisplayFoodAdapter;
import com.example.nutritionapp.databinding.FragmentHomeBinding;
import com.example.nutritionapp.ui.Dashboard.DashboardActivity;
import com.example.nutritionapp.ui.search.SearchActivity;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    CardView cardToday;
    FoodViewModel foodViewModel;
    ImageButton breakfastAdd, lunchAdd, snacksAdd, dinnerAdd;
    CardView cardBreakfast,cardLunch,cardSnacks,cardDinner;
    RelativeLayout fixedBreakfast,fixedLunch,fixedSnacks,fixedDinner;
    LinearLayout hiddenBreakfast,hiddenLunch,hiddenSnacks,hiddenDinner;
    RecyclerView recyclerBreakfast,recyclerLunch,recyclerSnacks,recyclerDinner;
    TextView homeBreakfast,homeLunch,homeSnacks,homeDinner,breakfastCalories,lunchCalories,snacksCalories,dinnerCalories,totalCalories,todayInsights;
    ProgressBar progressBar;

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

        progressBar = (ProgressBar) root.findViewById(R.id.progress_bar);
        foodViewModel.getTotalTodayCalories().observe(getViewLifecycleOwner(), Float -> updateProgressBar(Float));

        cardToday= root.findViewById(R.id.card_today_home);
        cardToday.setOnClickListener(view -> {
            Intent intent= new Intent(root.getContext(), DisplayFood.class);
            intent.putExtra("type", "Today");
            startActivity(intent);
        });

        //To display breakfast list using cardView
        cardBreakfast= root.findViewById(R.id.base_breakfast);
        fixedBreakfast= root.findViewById(R.id.fixed_breakfast);
        hiddenBreakfast= root.findViewById(R.id.hidden_breakfast);
        homeBreakfast= root.findViewById(R.id.home_breakfast);

        recyclerBreakfast = root.findViewById(R.id.recycler_breakfast);
        recyclerBreakfast.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerBreakfast.setHasFixedSize(true);

        final DisplayFoodAdapter adapterBreakfast = new DisplayFoodAdapter();
        recyclerBreakfast.setAdapter(adapterBreakfast);
        foodViewModel.getAllBreakfast().observe(getViewLifecycleOwner(), adapterBreakfast::setFoods);
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

        //To display Lunch list using cardView

        cardLunch= root.findViewById(R.id.base_lunch);
        fixedLunch= root.findViewById(R.id.fixed_lunch);
        hiddenLunch= root.findViewById(R.id.hidden_lunch);
        homeLunch= root.findViewById(R.id.home_lunch);

        recyclerLunch = root.findViewById(R.id.recycler_lunch);
        recyclerLunch.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerLunch.setHasFixedSize(true);


        final DisplayFoodAdapter adapterLunch = new DisplayFoodAdapter();
        recyclerLunch.setAdapter(adapterLunch);
        foodViewModel.getAllLunch().observe(getViewLifecycleOwner(), adapterLunch::setFoods);
        fixedLunch.setOnClickListener(view -> {
            if (hiddenLunch.getVisibility() == View.VISIBLE) {
                TransitionManager.beginDelayedTransition(cardLunch,
                        new AutoTransition());
                hiddenLunch.setVisibility(View.GONE);
            }
            else {
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

        recyclerSnacks = root.findViewById(R.id.recycler_snacks);
        recyclerSnacks.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerSnacks.setHasFixedSize(true);


        final DisplayFoodAdapter adapterSnacks = new DisplayFoodAdapter();
        recyclerSnacks.setAdapter(adapterSnacks);
        foodViewModel.getAllSnacks().observe(getViewLifecycleOwner(), adapterSnacks::setFoods);
        fixedSnacks.setOnClickListener(view -> {
            if (hiddenSnacks.getVisibility() == View.VISIBLE) {
                TransitionManager.beginDelayedTransition(cardSnacks,
                        new AutoTransition());
                hiddenSnacks.setVisibility(View.GONE);
            }
            else {
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

        recyclerDinner = root.findViewById(R.id.recycler_dinner);
        recyclerDinner.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerDinner.setHasFixedSize(true);


        final DisplayFoodAdapter adapterDinner = new DisplayFoodAdapter();
        recyclerDinner.setAdapter(adapterDinner);
        foodViewModel.getAllDinner().observe(getViewLifecycleOwner(), adapterDinner::setFoods);
        fixedDinner.setOnClickListener(view -> {
            if (hiddenDinner.getVisibility() == View.VISIBLE) {
                TransitionManager.beginDelayedTransition(cardDinner,
                        new AutoTransition());
                hiddenDinner.setVisibility(View.GONE);
            }
            else {
                TransitionManager.beginDelayedTransition(cardDinner,
                        new AutoTransition());
                hiddenDinner.setVisibility(View.VISIBLE);
            }
        });

        todayInsights = root.findViewById(R.id.today_insights);

        todayInsights.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), DashboardActivity.class);
            startActivity(intent);
        });

        return root;
    }

    private void setDinnerCalories(Float calories) {
        if(calories!= null)
            dinnerCalories.setText(calories.toString());
//            dinnerCalories.setText("0");
//        else

    }

    private void setSnacksCalories(Float calories) {
        if(calories!= null)
            snacksCalories.setText(calories.toString());
//            snacksCalories.setText("0");
//        else

    }

    private void setLunchCalories(Float calories) {
        if(calories!= null)
            lunchCalories.setText(calories.toString());
//            lunchCalories.setText("0");
//        else

    }

    private void setBreakfastCalories(Float calories) {
        if(calories!= null)
            breakfastCalories.setText(calories.toString());
//            breakfastCalories.setText("0");
//        else

    }

    private void setTotalCalories(Object calories) {
        if(calories!= null)
            totalCalories.setText(String.format("%sCal", calories.toString()));
        }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void updateProgressBar(Object calories) {
        if (calories != null) {
            float totalCaloriesEaten = (Float) calories;
            int calorieGoal = 800;

            if (calorieGoal > totalCaloriesEaten)
                progressBar.setProgress((int) ((totalCaloriesEaten / calorieGoal) * 100));
            else
                progressBar.setProgress(100);
        }
        else{
            progressBar.setProgress(0);
            totalCalories.setText("0Cal");
        }
    }
}