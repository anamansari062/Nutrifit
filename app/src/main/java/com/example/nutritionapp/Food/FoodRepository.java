package com.example.nutritionapp.Food;

import android.app.Application;
import android.os.AsyncTask;
import android.text.format.DateFormat;

import androidx.lifecycle.LiveData;

import com.example.nutritionapp.interfaces.FoodDao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class FoodRepository {
    private FoodDao foodDao;
    private Date currentDate = new Date();
    private SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
    private String dateOnly = dateFormat.format(currentDate);
    private LiveData<List<FoodEntity>> allFoods, allBreakfast, allLunch, allSnacks, allDinner;
    private LiveData<Float> totalTodayCalories, totalBreakfastCalories, totalLunchCalories, totalSnacksCalories, totalDinnerCalories, totalProtein, totalSugar, totalFiber, totalFat, totalFatSat, totalCarb, totalPotassium, totalCholesterol, totalSodium;

    public FoodRepository(Application application) {
        FoodDatabase database = FoodDatabase.getInstance(application);
        foodDao = database.foodDao();
        allFoods = foodDao.getAllFoodData(dateOnly);
        allBreakfast= foodDao.getBreakfastData(dateOnly);
        allLunch= foodDao.getLunchData(dateOnly);
        allSnacks= foodDao.getSnacksData(dateOnly);
        allDinner= foodDao.getDinnerData(dateOnly);
        totalTodayCalories= foodDao.getTotalTodayCalories(dateOnly);
        totalBreakfastCalories= foodDao.getBreakfastCalories(dateOnly);
        totalLunchCalories= foodDao.getLunchCalories(dateOnly);
        totalSnacksCalories= foodDao.getSnacksCalories(dateOnly);
        totalDinnerCalories= foodDao.getDinnerCalories(dateOnly);
        totalProtein= foodDao.getTotalProtein(dateOnly);
        totalCarb= foodDao.getTotalCarb(dateOnly);
        totalFiber= foodDao.getTotalFiber(dateOnly);
        totalFat= foodDao.getTotalFat(dateOnly);
        totalFatSat= foodDao.getTotalFatSat(dateOnly);
        totalSugar= foodDao.getTotalSugar(dateOnly);
        totalSodium= foodDao.getTotalSodium(dateOnly);
        totalPotassium= foodDao.getTotalPotassium(dateOnly);
        totalCholesterol= foodDao.getTotalCholesterol(dateOnly);
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

    public LiveData<List<FoodEntity>> getAllFoodData(String dateOnly) {
        return allFoods;
    }

    public  LiveData<List<FoodEntity>> getBreakfastData(String dateOnly){return allBreakfast; }

    public LiveData<List<FoodEntity>> getAllLunch(String dateOnly){return allLunch;}

    public LiveData<List<FoodEntity>> getAllSnacks(String dateOnly){return allSnacks;}

    public LiveData<List<FoodEntity>> getAllDinner(String dateOnly){return  allDinner;}

    public LiveData<Float> getTotalTodayCalories(String dateOnly){
        return totalTodayCalories;
    }

    public LiveData<Float> getTotalBreakfastCalories(String dateOnly){
        return totalBreakfastCalories;
    }

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

    public LiveData<Float> getTotalLunchCalories(String dateOnly){
        return totalLunchCalories;
    }

    public LiveData<Float> getTotalSnacksCalories(String dateOnly){
        return totalSnacksCalories;
    }

    public LiveData<Float> getTotalDinnerCalories(String dateOnly){
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
