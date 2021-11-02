package com.example.nutritionapp.Food;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class FoodViewModel extends AndroidViewModel {
    private FoodRepository repository;
    private LiveData<List<FoodEntity>> allFoods, allBreakfast, allLunch, allSnacks, allDinner;
    private LiveData<Float> todayCalories, breakfastCalories;


    public FoodViewModel(@NonNull Application application) {
        super(application);
        repository = new FoodRepository(application);
        allFoods = repository.getAllFoodData();
        allBreakfast= repository.getBreakfastData();
        allLunch= repository.getAllLunch();
        allSnacks= repository.getAllSnacks();
        allDinner= repository.getAllDinner();
        todayCalories= repository.getTotalTodayCalories();
//        breakfastCalories= repository.getBreakfastCalories();
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

    public LiveData<List<FoodEntity>> getAllBreakfast(){return allBreakfast;}

    public LiveData<List<FoodEntity>> getAllLunch(){return allLunch;}

    public LiveData<List<FoodEntity>> getAllSnacks(){return allSnacks;}

    public LiveData<List<FoodEntity>> getAllDinner(){return  allDinner;}

    public LiveData<Float> getTotalTodayCalories(){return todayCalories;}
//
//    public Float getBreakfastCalories(){return breakfastCalories;}
}
