package com.example.nutritionapp.Food;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class FoodViewModel extends AndroidViewModel {
    private FoodRepository repository;
    private LiveData<List<FoodEntity>> allFoods, allBreakfast, allLunch, allSnacks, allDinner;
    private LiveData<Float> todayCalories, breakfastCalories, lunchCalories, snacksCalories, dinnerCalories, totalProtein, totalSugar, totalFiber, totalFat, totalFatSat, totalCarb, totalPotassium, totalCholesterol, totalSodium;


    public FoodViewModel(@NonNull Application application) {
        super(application);
        repository = new FoodRepository(application);
        allFoods = repository.getAllFoodData();
        allBreakfast= repository.getBreakfastData();
        allLunch= repository.getAllLunch();
        allSnacks= repository.getAllSnacks();
        allDinner= repository.getAllDinner();
        todayCalories= repository.getTotalTodayCalories();
        breakfastCalories= repository.getTotalBreakfastCalories();
        lunchCalories= repository.getTotalLunchCalories();
        snacksCalories= repository.getTotalSnacksCalories();
        dinnerCalories= repository.getTotalDinnerCalories();
        totalCarb= repository.getTotalCarb();
        totalCholesterol= repository.getTotalCholesterol();
        totalFat= repository.getTotalFat();
        totalFatSat= repository.getTotalFatSat();
        totalFiber= repository.getTotalFiber();
        totalPotassium= repository.getTotalPotassium();
        totalProtein= repository.getTotalProtein();
        totalSugar= repository.getTotalSugar();
        totalSodium= repository.getTotalSodium();
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

    public LiveData<Float> getTotalBreakfastCalories(){return breakfastCalories;}

    public LiveData<Float> getTotalLunchCalories(){return lunchCalories;}

    public LiveData<Float> getTotalSnacksCalories(){return snacksCalories;}

    public LiveData<Float> getTotalDinnerCalories(){return dinnerCalories;}

    public LiveData<Float> getTotalProtein(){
        return totalProtein;
    }

    public LiveData<Float> getTotalSugar(){
        return totalSugar;
    }

    public LiveData<Float> getTotalFiber(){
        return totalFiber;
    }

    public LiveData<Float> getTotalFat(){
        return totalFat;
    }

    public LiveData<Float> getTotalFatSat(){
        return totalFatSat;
    }

    public LiveData<Float> getTotalCarb(){
        return totalCarb;
    }

    public LiveData<Float> getTotalPotassium(){
        return totalPotassium;
    }

    public LiveData<Float> getTotalCholesterol(){
        return totalCholesterol;
    }

    public LiveData<Float> getTotalSodium(){
        return totalSodium;
    }
}
