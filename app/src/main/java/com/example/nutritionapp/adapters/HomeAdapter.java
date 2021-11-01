package com.example.nutritionapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nutritionapp.R;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> {

    ArrayList<Integer> mealImages = new ArrayList<Integer>();
    ArrayList<String> mealName= new ArrayList<String>();

    public HomeAdapter(ArrayList<Integer> mealImages, ArrayList<String> mealName) {
        this.mealImages = mealImages;
        this.mealName = mealName;
    }

    public HomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(parent.getContext());
        View view= layoutInflater.inflate(R.layout.home_prototype, parent, false);
        return new HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        holder.mealType.setText(mealName.get(position));
        holder.mealImage.setImageResource(mealImages.get(position));
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public static class HomeViewHolder extends RecyclerView.ViewHolder {
        private TextView mealType,mealCalories;
        private ImageButton addMeal;
        private ImageView mealImage;
        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);
            mealType =  itemView.findViewById(R.id.home_recycler_meal_name);
            mealCalories =  itemView.findViewById(R.id.home_recycler_meal_calories);
            mealImage =  itemView.findViewById(R.id.home_recycler_meal_image);
            addMeal =  itemView.findViewById(R.id.home_recycler_meal_add);


        }
    }
}
