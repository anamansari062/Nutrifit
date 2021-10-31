package com.example.nutritionapp.Json;

import com.example.nutritionapp.Food.FoodEntity;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JsonObject {
    @SerializedName("items")
    private List<FoodEntity> foods;

    public JsonObject(List<FoodEntity> foods) {
        this.foods = foods;
    }

    public List<FoodEntity> getFood() {
        return foods;
    }
}
