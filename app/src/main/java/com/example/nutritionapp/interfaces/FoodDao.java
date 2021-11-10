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

    @Query("SELECT * FROM food_table where foodType='Breakfast' ")
    LiveData<List<FoodEntity>> getBreakfastData();

    @Query("SELECT * FROM food_table where foodType='Lunch' ")
    LiveData<List<FoodEntity>> getLunchData();

    @Query("SELECT * FROM food_table where foodType='Snacks' ")
    LiveData<List<FoodEntity>> getSnacksData();

    @Query("SELECT * FROM food_table where foodType='Dinner' ")
    LiveData<List<FoodEntity>> getDinnerData();

    @Query("SELECT sum(calories) FROM food_table")
    LiveData<Float> getTotalTodayCalories();

    @Query("SELECT sum(calories) FROM food_table where foodType='Breakfast' ")
    LiveData<Float> getBreakfastCalories();

    @Query("SELECT sum(calories) FROM food_table where foodType='Lunch' ")
    LiveData<Float> getLunchCalories();

    @Query("SELECT sum(calories) FROM food_table where foodType='Snacks' ")
    LiveData<Float> getSnacksCalories();

    @Query("SELECT sum(calories) FROM food_table where foodType='Dinner' ")
    LiveData<Float> getDinnerCalories();

    @Query("SELECT sum(protein_g) FROM food_table")
    LiveData<Float> getTotalProtein();

    @Query("SELECT sum(carbohydrates_total_g) FROM food_table")
    LiveData<Float> getTotalCarb();

    @Query("SELECT sum(fat_total_g) FROM food_table")
    LiveData<Float> getTotalFat();

    @Query("SELECT sum(fat_saturated_g) FROM food_table")
    LiveData<Float> getTotalFatSat();

    @Query("SELECT sum(sugar_g) FROM food_table")
    LiveData<Float> getTotalSugar();

    @Query("SELECT sum(sodium_mg) FROM food_table")
    LiveData<Float> getTotalSodium();

    @Query("SELECT sum(fiber_g) FROM food_table")
    LiveData<Float> getTotalFiber();

    @Query("SELECT sum(potassium_mg) FROM food_table")
    LiveData<Float> getTotalPotassium();

    @Query("SELECT sum(cholesterol_mg) FROM food_table")
    LiveData<Float> getTotalCholesterol();




}
