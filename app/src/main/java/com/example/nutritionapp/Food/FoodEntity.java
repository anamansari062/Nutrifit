package com.example.nutritionapp.Food;


import android.provider.ContactsContract;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;


import com.example.nutritionapp.DataConverter;

import java.util.Date;

@Entity(tableName = "food_table")
public class FoodEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @TypeConverters(DataConverter.class)
    private final Date date;
    private final String foodName;
    private final String foodType;
    private final Float serving_size_g;
    private final Float calories;
    private final Float protein_g;
    private final Float carbohydrates_total_g;
    private final Float sugar_g;
    private final Float fiber_g;
    private final Float sodium_mg;
    private final Float potassium_mg;
    private final Float fat_saturated_g;
    private final Float fat_total_g;
    private final Float cholesterol_mg;

    public FoodEntity(Date date, String foodName, String foodType, Float serving_size_g, Float calories, Float protein_g, Float carbohydrates_total_g, Float sugar_g, Float fiber_g, Float sodium_mg, Float potassium_mg, Float fat_saturated_g, Float fat_total_g, Float cholesterol_mg) {
        this.date = date;
        this.foodName = foodName;
        this.foodType= foodType;
        this.serving_size_g = serving_size_g;
        this.calories = calories;
        this.protein_g = protein_g;
        this.carbohydrates_total_g = carbohydrates_total_g;
        this.sugar_g = sugar_g;
        this.fiber_g = fiber_g;
        this.sodium_mg = sodium_mg;
        this.potassium_mg = potassium_mg;
        this.fat_saturated_g = fat_saturated_g;
        this.fat_total_g = fat_total_g;
        this.cholesterol_mg = cholesterol_mg;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public String getFoodName() {
        return foodName;
    }

    public String getFoodType() {
        return foodType;
    }

    public Float getServing_size_g() {
        return serving_size_g;
    }

    public Float getCalories() {
        return calories;
    }

    public Float getProtein_g() {
        return protein_g;
    }

    public Float getCarbohydrates_total_g() {
        return carbohydrates_total_g;
    }

    public Float getSugar_g() {
        return sugar_g;
    }

    public Float getFiber_g() {
        return fiber_g;
    }

    public Float getSodium_mg() {
        return sodium_mg;
    }

    public Float getPotassium_mg() {
        return potassium_mg;
    }

    public Float getFat_saturated_g() {
        return fat_saturated_g;
    }

    public Float getFat_total_g() {
        return fat_total_g;
    }

    public Float getCholesterol_mg() {
        return cholesterol_mg;
    }
}

