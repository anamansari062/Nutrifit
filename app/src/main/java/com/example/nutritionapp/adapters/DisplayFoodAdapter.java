package com.example.nutritionapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
        holder.imageDisplayFOod.setImageResource(R.drawable.food);
        String upperString = currentFoodEntity.getFoodName().substring(0, 1).toUpperCase() + currentFoodEntity.getFoodName().substring(1).toLowerCase();
        holder.textDisplayFood.setText(upperString);
        holder.textDisplayCalories.setText(String.valueOf(round(currentFoodEntity.getCalories(), 2) + "Cal"));

    }

    public FoodEntity getFoodAt(int position){
        return foodEntity.get(position);
    }

    public void setFoods(List<FoodEntity> foodEntity) {
        this.foodEntity = foodEntity;
        notifyDataSetChanged();
    }

    private  double round (double value, int precision) {
        int scale = (int) Math.pow(10, precision);
        return (double) Math.round(value * scale) / scale;
    }
    @Override
    public int getItemCount() {
        return foodEntity.size();
    }

    public class DisplayFoodViewHolder extends RecyclerView.ViewHolder {
        TextView textDisplayFood, textDisplayCalories;
        ImageView imageDisplayFOod;
        public DisplayFoodViewHolder(@NonNull View itemView) {
            super(itemView);
            textDisplayFood= itemView.findViewById(R.id.text_display_name);
            textDisplayCalories= itemView.findViewById(R.id.text_display_calories);
            imageDisplayFOod = itemView.findViewById(R.id.text_display_image);
        }
    }

}
