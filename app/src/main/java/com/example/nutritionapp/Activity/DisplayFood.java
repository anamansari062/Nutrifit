package com.example.nutritionapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.nutritionapp.Food.FoodViewModel;
import com.example.nutritionapp.R;
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

        foodViewModel.getAllFoodData(dateOnly).observe(this, foodEntity -> adapter.setFoods(foodEntity));

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                foodViewModel.delete(adapter.getFoodAt(viewHolder.getAdapterPosition()));
                Toast.makeText(DisplayFood.this, "Food Deleted", Toast.LENGTH_SHORT).show();

            }
        }).attachToRecyclerView(recyclerView);
    }
}