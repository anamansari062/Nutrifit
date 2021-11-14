package com.example.nutritionapp.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nutritionapp.R;
import com.example.nutritionapp.ui.search.SearchActivity;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> {
    ArrayList<Integer> mealImages = new ArrayList<Integer>();
    ArrayList<String> mealName= new ArrayList<String>();
    itemOnClickListener listener;

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
        holder.addMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(view.getContext(), SearchActivity.class);
                intent.putExtra("title", holder.mealType.getText().toString());
                view.getContext().startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public  class HomeViewHolder extends RecyclerView.ViewHolder {
        private TextView mealType,mealCalories;
        private ImageButton addMeal,showListMeal;
        private ImageView mealImage;



        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);
            mealType =  itemView.findViewById(R.id.home_recycler_meal_name);
            mealCalories =  itemView.findViewById(R.id.home_recycler_meal_calories);
            mealImage =  itemView.findViewById(R.id.home_recycler_meal_image);
            addMeal =  itemView.findViewById(R.id.home_recycler_meal_add);
            showListMeal = itemView.findViewById(R.id.home_recycler_meal_show_list);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position= getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {

                        listener.onItemClick(mealName.get(position));

                    }
                }
            });




        }
    }
    public interface itemOnClickListener{
        void onItemClick(String type);
    }

    public void setOnClickListener(itemOnClickListener listener){
        this.listener= listener;
    }

}
