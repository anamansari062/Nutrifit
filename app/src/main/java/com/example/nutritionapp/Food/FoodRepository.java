package com.example.nutritionapp.Food;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.nutritionapp.interfaces.FoodDao;

import java.util.List;

public class FoodRepository {
    private FoodDao foodDao;
    private LiveData<List<FoodEntity>> allFoods, allBreakfast, allLunch, allSnacks, allDinner;
    private LiveData<Float> totalTodayCalories, totalBreakfastCalories;

    public FoodRepository(Application application) {
        FoodDatabase database = FoodDatabase.getInstance(application);
        foodDao = database.foodDao();
        allFoods = foodDao.getAllFoodData();
        allBreakfast= foodDao.getBreakfastData();
        allLunch= foodDao.getLunchData();
        allSnacks= foodDao.getSnacksData();
        allDinner= foodDao.getDinnerData();
        totalTodayCalories= foodDao.getTotalTodayCalories();
//        totalBreakfastCalories= foodDao.getBreakfastCalories();
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
//
//    public Float getBreakfastCalories(){
//        return totalBreakfastCalories;
//    }

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
