package com.example.nutritionapp.Food;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.nutritionapp.interfaces.FoodDao;

import java.util.List;

public class FoodRepository {
    private FoodDao foodDao;
    private LiveData<List<FoodEntity>> allFoods, allBreakfast, allLunch, allSnacks, allDinner;
    private LiveData<Float> totalTodayCalories, totalBreakfastCalories, totalLunchCalories, totalSnacksCalories, totalDinnerCalories, totalProtein, totalSugar, totalFiber, totalFat, totalFatSat, totalCarb, totalPotassium, totalCholesterol, totalSodium;

    public FoodRepository(Application application) {
        FoodDatabase database = FoodDatabase.getInstance(application);
        foodDao = database.foodDao();
        allFoods = foodDao.getAllFoodData();
        allBreakfast= foodDao.getBreakfastData();
        allLunch= foodDao.getLunchData();
        allSnacks= foodDao.getSnacksData();
        allDinner= foodDao.getDinnerData();
        totalTodayCalories= foodDao.getTotalTodayCalories();
        totalBreakfastCalories= foodDao.getBreakfastCalories();
        totalLunchCalories= foodDao.getLunchCalories();
        totalSnacksCalories= foodDao.getSnacksCalories();
        totalDinnerCalories= foodDao.getDinnerCalories();
        totalProtein= foodDao.getTotalProtein();
        totalCarb= foodDao.getTotalCarb();
        totalFiber= foodDao.getTotalFiber();
        totalFat= foodDao.getTotalFat();
        totalFatSat= foodDao.getTotalFatSat();
        totalSugar= foodDao.getTotalSugar();
        totalSodium= foodDao.getTotalSodium();
        totalPotassium= foodDao.getTotalPotassium();
        totalCholesterol= foodDao.getTotalCholesterol();
    }

    public void insert(FoodEntity food) {
        new InsertFoodAsyncTask(foodDao).execute(food);
    }


    public void delete(FoodEntity food) {
        new DeleteFoodAsyncTask(foodDao).execute(food);
    }

    public void deleteAllFoods() {
        new DeleteAllFoodsAsyncTask(foodDao).execute();
    }

    public LiveData<List<FoodEntity>> getAllFoodData() {
        return allFoods;
    }

    public  LiveData<List<FoodEntity>> getBreakfastData(){return allBreakfast; }

    public LiveData<List<FoodEntity>> getAllLunch(){return allLunch;}

    public LiveData<List<FoodEntity>> getAllSnacks(){return allSnacks;}

    public LiveData<List<FoodEntity>> getAllDinner(){return  allDinner;}

    public LiveData<Float> getTotalTodayCalories(){
        return totalTodayCalories;
    }

    public LiveData<Float> getTotalBreakfastCalories(){
        return totalBreakfastCalories;
    }

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

    public LiveData<Float> getTotalLunchCalories(){
        return totalLunchCalories;
    }

    public LiveData<Float> getTotalSnacksCalories(){
        return totalSnacksCalories;
    }

    public LiveData<Float> getTotalDinnerCalories(){
        return totalDinnerCalories;
    }
    private static class InsertFoodAsyncTask extends AsyncTask<FoodEntity, Void, Void> {
        private FoodDao foodDao;

        private InsertFoodAsyncTask(FoodDao foodDao) {
            this.foodDao = foodDao;
        }

        @Override
        protected Void doInBackground(FoodEntity... foods) {
            foodDao.insert(foods[0]);
            return null;
        }
    }


    private static class DeleteFoodAsyncTask extends AsyncTask<FoodEntity, Void, Void> {
        private FoodDao foodDao;

        private DeleteFoodAsyncTask(FoodDao foodDao) {
            this.foodDao = foodDao;
        }

        @Override
        protected Void doInBackground(FoodEntity... foods) {
            foodDao.delete(foods[0]);
            return null;
        }
    }


    private static class DeleteAllFoodsAsyncTask extends AsyncTask<Void, Void, Void> {
        private FoodDao foodDao;

        private DeleteAllFoodsAsyncTask(FoodDao foodDao) {
            this.foodDao = foodDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            foodDao.deleteAllFoodData();
            return null;
        }
    }
}
