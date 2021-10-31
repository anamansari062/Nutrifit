package com.example.nutritionapp.Food;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class FoodViewModel extends AndroidViewModel {
    private FoodRepository repository;
    private LiveData<List<FoodEntity>> allFoods;

    public FoodViewModel(@NonNull Application application) {
        super(application);
        repository = new FoodRepository(application);
        allFoods = repository.getAllFoodData();
    }

    public void insert(FoodEntity food) {
        repository.insert(food);
    }

    public void delete(FoodEntity food) {
        repository.delete(food);
    }

    public void deleteAllFoods() {
        repository.deleteAllFoods();
    }

    public LiveData<List<FoodEntity>> getAllFoodData() {
        return allFoods;
    }
}
