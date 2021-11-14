package com.example.nutritionapp.interfaces;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.nutritionapp.Food.FoodEntity;

import java.util.Date;
import java.util.List;

@Dao
public interface FoodDao {
    @Insert
    void insert(FoodEntity foods);

    @Delete
    void delete(FoodEntity foods);

    @Query("DELETE FROM food_table")
    void deleteAllFoodData();

    @Query("SELECT * FROM food_table where date = :date1")
    LiveData<List<FoodEntity>> getAllFoodData(String date1);

    @Query("SELECT * FROM food_table where foodType='Breakfast' and date= :date1 ")
    LiveData<List<FoodEntity>> getBreakfastData(String date1);

    @Query("SELECT * FROM food_table where foodType='Lunch' and date= :date1")
    LiveData<List<FoodEntity>> getLunchData(String date1);

    @Query("SELECT * FROM food_table where foodType='Snacks' and date= :date1")
    LiveData<List<FoodEntity>> getSnacksData(String date1);

    @Query("SELECT * FROM food_table where foodType='Dinner' and date= :date1")
    LiveData<List<FoodEntity>> getDinnerData(String date1);

    @Query("SELECT sum(calories) FROM food_table where date= :date1")
    LiveData<Float> getTotalTodayCalories(String date1);

    @Query("SELECT sum(calories) FROM food_table where foodType='Breakfast' and date= :date1")
    LiveData<Float> getBreakfastCalories(String date1);

    @Query("SELECT sum(calories) FROM food_table where foodType='Lunch' and date= :date1")
    LiveData<Float> getLunchCalories(String date1);

    @Query("SELECT sum(calories) FROM food_table where foodType='Snacks' and date= :date1")
    LiveData<Float> getSnacksCalories(String date1);

    @Query("SELECT sum(calories) FROM food_table where foodType='Dinner' and date= :date1")
    LiveData<Float> getDinnerCalories(String date1);

    @Query("SELECT sum(protein_g) FROM food_table where date= :date1")
    LiveData<Float> getTotalProtein(String date1);

    @Query("SELECT sum(carbohydrates_total_g) FROM food_table where date= :date1")
    LiveData<Float> getTotalCarb(String date1);

    @Query("SELECT sum(fat_total_g) FROM food_table where date= :date1")
    LiveData<Float> getTotalFat(String date1);

    @Query("SELECT sum(fat_saturated_g) FROM food_table where date= :date1")
    LiveData<Float> getTotalFatSat(String date1);

    @Query("SELECT sum(sugar_g) FROM food_table where date= :date1")
    LiveData<Float> getTotalSugar(String date1);

    @Query("SELECT sum(sodium_mg) FROM food_table where date= :date1")
    LiveData<Float> getTotalSodium(String date1);

    @Query("SELECT sum(fiber_g) FROM food_table where date= :date1")
    LiveData<Float> getTotalFiber(String date1);

    @Query("SELECT sum(potassium_mg) FROM food_table where date= :date1")
    LiveData<Float> getTotalPotassium(String date1);

    @Query("SELECT sum(cholesterol_mg) FROM food_table where date= :date1")
    LiveData<Float> getTotalCholesterol(String date1);




}
