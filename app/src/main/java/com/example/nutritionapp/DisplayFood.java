package com.example.nutritionapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;

import com.example.nutritionapp.Food.FoodViewModel;
import com.example.nutritionapp.adapters.DisplayFoodAdapter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DisplayFood extends AppCompatActivity {
    FoodViewModel foodViewModel;
    String type;
    Date currentDate;
    SimpleDateFormat dateFormat;
    String dateOnly;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_food);

        Intent intent= getIntent();
        type= intent.getStringExtra("type");



        RecyclerView recyclerView = findViewById(R.id.recycler_view_food);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);


        final DisplayFoodAdapter adapter = new DisplayFoodAdapter();
        recyclerView.setAdapter(adapter);

        currentDate = new Date();
        dateFormat= new SimpleDateFormat("yyyy-MM-dd");
        dateOnly = dateFormat.format(currentDate);

        foodViewModel = new ViewModelProvider(this).get(FoodViewModel.class);
        switch (type){
            case "Today":
                foodViewModel.getAllFoodData(dateOnly).observe(this, foodEntity -> adapter.setFoods(foodEntity));
                break;

            case "Breakfast":
                foodViewModel.getAllBreakfast(dateOnly).observe(this, foodEntity -> adapter.setFoods(foodEntity));
                break;

            case "Lunch":
                foodViewModel.getAllLunch(dateOnly).observe(this, foodEntity -> adapter.setFoods(foodEntity));
                break;

            case "Snacks":
                foodViewModel.getAllSnacks(dateOnly).observe(this, foodEntity -> adapter.setFoods(foodEntity));
                break;

            case "Dinner":
                foodViewModel.getAllDinner(dateOnly).observe(this, foodEntity -> adapter.setFoods(foodEntity));
                break;

        }



    }
}