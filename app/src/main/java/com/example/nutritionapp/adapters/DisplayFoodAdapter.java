package com.example.nutritionapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nutritionapp.Food.FoodEntity;
import com.example.nutritionapp.Food.FoodRepository;
import com.example.nutritionapp.R;

import java.util.ArrayList;
import java.util.List;


public class DisplayFoodAdapter extends RecyclerView.Adapter<DisplayFoodAdapter.DisplayFoodViewHolder> {
    List<FoodEntity> foodEntity= new ArrayList<>();


    @NonNull
    @Override
    public DisplayFoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.food_prototype, parent, false);
        return new DisplayFoodViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DisplayFoodViewHolder holder, int position) {
        FoodEntity currentFoodEntity= foodEntity.get(position);
        holder.textDisplayFood.setText(currentFoodEntity.getFoodName());
        holder.textDisplayCalories.setText(currentFoodEntity.getCalories().toString());

    }

    public FoodEntity getFoodAt(int position){
        return foodEntity.get(position);
    }

    public void setFoods(List<FoodEntity> foodEntity) {
        this.foodEntity = foodEntity;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return foodEntity.size();
    }

    public class DisplayFoodViewHolder extends RecyclerView.ViewHolder {
        TextView textDisplayFood, textDisplayCalories;

        public DisplayFoodViewHolder(@NonNull View itemView) {
            super(itemView);
            textDisplayFood= itemView.findViewById(R.id.text_display_name);
            textDisplayCalories= itemView.findViewById(R.id.text_display_calories);
        }
    }
}
