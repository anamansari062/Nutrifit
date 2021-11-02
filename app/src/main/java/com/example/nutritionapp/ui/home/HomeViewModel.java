package com.example.nutritionapp.ui.home;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.nutritionapp.Food.FoodRepository;
import com.example.nutritionapp.Food.FoodViewModel;

public class HomeViewModel extends ViewModel {
    FoodViewModel foodViewModel;
    Float totalTodayCalories;

    private MutableLiveData<String> mText;

    public HomeViewModel(Application application) {
//        foodViewModel= new FoodViewModel(application);
//        totalTodayCalories= foodViewModel.getTotalTodayCalories();
//        mText = new MutableLiveData<>();
//        mText.setValue(totalTodayCalories.toString());
    }

    public LiveData<String> getText() {
        return mText;
    }
}
