package com.example.nutritionapp.Json;

import com.google.gson.annotations.SerializedName;

public class Items {
    private Float sugar_g;
    private Float fiber_g;
    private Float serving_size_g;
    private Float sodium_mg;
    @SerializedName("name")
    private String foodName;
    private Float potassium_mg;
    private Float fat_saturated_g;
    private Float fat_total_g;
    private Float calories;
    private Float cholesterol_mg;
    private Float protein_g;
    private Float carbohydrates_total_g;

    public Items(Float sugar_g, Float fiber_g, Float serving_size_g, Float sodium_mg, String name, Float potassium_mg, Float fat_saturated_g, Float fat_total_g, Float calories, Float cholesterol_mg, Float protein_g, Float carbohydrates_total_g) {
        this.sugar_g = sugar_g;
        this.fiber_g = fiber_g;
        this.serving_size_g = serving_size_g;
        this.sodium_mg = sodium_mg;
        this.foodName = foodName;
        this.potassium_mg = potassium_mg;
        this.fat_saturated_g = fat_saturated_g;
        this.fat_total_g = fat_total_g;
        this.calories = calories;
        this.cholesterol_mg = cholesterol_mg;
        this.protein_g = protein_g;
        this.carbohydrates_total_g = carbohydrates_total_g;
    }

    public Float getSugar_g() {
        return sugar_g;
    }

    public Float getFiber_g() {
        return fiber_g;
    }

    public Float getServing_size_g() {
        return serving_size_g;
    }

    public Float getSodium_mg() {
        return sodium_mg;
    }

    public String getfoodName() {
        return foodName;
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

    public Float getCalories() {
        return calories;
    }

    public Float getCholesterol_mg() {
        return cholesterol_mg;
    }

    public Float getProtein_g() {
        return protein_g;
    }

    public Float getCarbohydrates_total_g() {
        return carbohydrates_total_g;
    }
}
