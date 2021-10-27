package com.example.nutritionapp.Json;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JsonObject {
    @SerializedName("items")
    private List<Items> items;

    public JsonObject(List<Items> items) {
        this.items = items;
    }

    public List<Items> getItems() {
        return items;
    }
}
