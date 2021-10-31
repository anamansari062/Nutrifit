package com.example.nutritionapp.interfaces;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.nutritionapp.Food.FoodEntity;

import java.util.List;

@Dao
public interface FoodDao {
    @Insert
    void insert(FoodEntity foods);


    @Delete
    void delete(FoodEntity foods);

    @Query("DELETE FROM food_table")
    void deleteAllFoodData();

    @Query("SELECT * FROM food_table")
    LiveData<List<FoodEntity>> getAllFoodData();
}
