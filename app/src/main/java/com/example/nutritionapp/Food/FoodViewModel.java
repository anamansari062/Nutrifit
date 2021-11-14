package com.example.nutritionapp.Food;

import android.app.Application;
import android.text.format.DateFormat;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class FoodViewModel extends AndroidViewModel {
    private FoodRepository repository;
    private Date currentDate = new Date();
    private SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
    private String dateOnly = dateFormat.format(currentDate);
    private LiveData<List<FoodEntity>> allFoods, allBreakfast, allLunch, allSnacks, allDinner;
    private LiveData<Float> todayCalories, breakfastCalories, lunchCalories, snacksCalories, dinnerCalories, totalProtein, totalSugar, totalFiber, totalFat, totalFatSat, totalCarb, totalPotassium, totalCholesterol, totalSodium;


    public FoodViewModel(@NonNull Application application) {
        super(application);
        repository = new FoodRepository(application);
        allFoods = repository.getAllFoodData(dateOnly);
        allBreakfast= repository.getBreakfastData(dateOnly);
        allLunch= repository.getAllLunch(dateOnly);
        allSnacks= repository.getAllSnacks(dateOnly);
        allDinner= repository.getAllDinner(dateOnly);
        todayCalories= repository.getTotalTodayCalories(dateOnly);
        breakfastCalories= repository.getTotalBreakfastCalories(dateOnly);
        lunchCalories= repository.getTotalLunchCalories(dateOnly);
        snacksCalories= repository.getTotalSnacksCalories(dateOnly);
        dinnerCalories= repository.getTotalDinnerCalories(dateOnly);
        totalCarb= repository.getTotalCarb(dateOnly);
        totalCholesterol= repository.getTotalCholesterol(dateOnly);
        totalFat= repository.getTotalFat(dateOnly);
        totalFatSat= repository.getTotalFatSat(dateOnly);
        totalFiber= repository.getTotalFiber(dateOnly);
        totalPotassium= repository.getTotalPotassium(dateOnly);
        totalProtein= repository.getTotalProtein(dateOnly);
        totalSugar= repository.getTotalSugar(dateOnly);
        totalSodium= repository.getTotalSodium(dateOnly);
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

    public LiveData<List<FoodEntity>> getAllFoodData(String dateOnly) {
        return allFoods;
    }

    public LiveData<List<FoodEntity>> getAllBreakfast(String dateOnly){return allBreakfast;}

    public LiveData<List<FoodEntity>> getAllLunch(String dateOnly){return allLunch;}

    public LiveData<List<FoodEntity>> getAllSnacks(String dateOnly){return allSnacks;}

    public LiveData<List<FoodEntity>> getAllDinner(String dateOnly){return  allDinner;}

    public LiveData<Float> getTotalTodayCalories(String dateOnly){return todayCalories;}

    public LiveData<Float> getTotalBreakfastCalories(String dateOnly){return breakfastCalories;}

    public LiveData<Float> getTotalLunchCalories(String dateOnly){return lunchCalories;}

    public LiveData<Float> getTotalSnacksCalories(String dateOnly){return snacksCalories;}

    public LiveData<Float> getTotalDinnerCalories(String dateOnly){return dinnerCalories;}

    public LiveData<Float> getTotalProtein(String dateOnly){
        return totalProtein;
    }

    public LiveData<Float> getTotalSugar(String dateOnly){
        return totalSugar;
    }

    public LiveData<Float> getTotalFiber(String dateOnly){
        return totalFiber;
    }

    public LiveData<Float> getTotalFat(String dateOnly){
        return totalFat;
    }

    public LiveData<Float> getTotalFatSat(String dateOnly){
        return totalFatSat;
    }

    public LiveData<Float> getTotalCarb(String dateOnly){
        return totalCarb;
    }

    public LiveData<Float> getTotalPotassium(String dateOnly){
        return totalPotassium;
    }

    public LiveData<Float> getTotalCholesterol(String dateOnly){
        return totalCholesterol;
    }

    public LiveData<Float> getTotalSodium(String dateOnly){
        return totalSodium;
    }
}
