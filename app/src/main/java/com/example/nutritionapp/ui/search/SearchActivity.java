package com.example.nutritionapp.ui.search;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.ViewModelProvider;

import com.example.nutritionapp.Food.FoodEntity;
import com.example.nutritionapp.Food.FoodViewModel;
import com.example.nutritionapp.Json.JsonObject;
import com.example.nutritionapp.Activity.MainActivity;
import com.example.nutritionapp.R;
import com.example.nutritionapp.interfaces.JsonPlaceHolderApi;
import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchActivity extends AppCompatActivity {
    String host ="calorieninjas.p.rapidapi.com";
    String key = "9a845c1749msh980401f10c34888p1dbde1jsn50abf0bfda0b";
    String title;
    Float totalServing, totalNutrients, savedCalories;
    EditText textSearch, textServe;
    TextView textResult,nutritionInfoServingSize,nutritionInfoCalories,nutritionInfoProteinCalories,nutritionInfoFatsCalories,
            nutritionInfoCarbsCalories,nutritionInfoFibreCalories,nutritionInfoSugarCalories,nutritionInfoSodiumCalories,
            nutritionInfoPotassiumCalories,nutritionInfoCholesterolCalories,nutritionInfoFatSaturatedCalories;

    Button buttonSearch;
    FoodEntity food;
    Date currentDate;
    SimpleDateFormat dateFormat;
    String dateOnly;
    ProgressBar progressBar;
    CardView cardShowNutrition;
    LinearLayout noFoodFoundLayout;
    ScrollView SearchParentlayout;
    boolean noFoodFound;
    private FoodViewModel foodViewModel;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_activity);

        textSearch= findViewById(R.id.textSearch);
        textServe=  findViewById(R.id.textServe);
        textResult= findViewById(R.id.textResult);
        buttonSearch =  findViewById(R.id.buttonSearch);
        progressBar = findViewById(R.id.search_progress_bar);
        cardShowNutrition = findViewById(R.id.card_show_nutrition);
        nutritionInfoServingSize = findViewById(R.id.nutrition_info_serving_size);
        nutritionInfoCalories = findViewById(R.id.nutrition_info_calories);
        SearchParentlayout = findViewById(R.id.search_layout);
        noFoodFoundLayout = findViewById(R.id.no_food_found_layout);
        nutritionInfoProteinCalories = findViewById(R.id.dashboard_protein_intake);
        nutritionInfoFatsCalories = findViewById(R.id.dashboard_fats_intake);
        nutritionInfoCarbsCalories = findViewById(R.id.dashboard_carbs_intake);
        nutritionInfoFibreCalories = findViewById(R.id.dashboard_fibre_intake);
        nutritionInfoSugarCalories = findViewById(R.id.dashboard_sugar_intake);
        nutritionInfoSodiumCalories = findViewById(R.id.dashboard_sodium_intake);
        nutritionInfoPotassiumCalories = findViewById(R.id.dashboard_potassium_intake);
        nutritionInfoCholesterolCalories = findViewById(R.id.dashboard_cholesterol_intake);
        nutritionInfoFatSaturatedCalories = findViewById(R.id.dashboard_fat_saturated_intake);

        Intent intent= getIntent();
        title= intent.getStringExtra("title");
        setTitle("Add " + title);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        foodViewModel = new ViewModelProvider(this).get(FoodViewModel.class);
        buttonSearch.setOnClickListener(view -> {
            if (textSearch.getText().toString().length() == 0) {
                textSearch.setError("Please enter the food name");
                textSearch.requestFocus();
//                    Toast.makeText(SearchActivity.this, "Please enter the food name", Toast.LENGTH_SHORT).show();
            } else if (textServe.getText().toString().length() == 0) {
                textServe.setError("Please enter the food name");
                textServe.requestFocus();
//                    Toast.makeText(SearchActivity.this, "Please enter the serving size", Toast.LENGTH_SHORT).show();
            } else {
                hideKeyboard();
                cardShowNutrition.setVisibility(View.INVISIBLE);
                progressBar.setVisibility(View.VISIBLE);
                searchValue(textSearch.getText().toString(), Float.parseFloat(textServe.getText().toString()));
            }
        });

    }

    public void searchValue(String content, Float serving) {

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://calorieninjas.p.rapidapi.com/").addConverterFactory(GsonConverterFactory.create()).build();
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        Call<JsonObject> call = jsonPlaceHolderApi.getJsonObject(content, host, key);
        call.enqueue(new Callback<JsonObject>() {

            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                ArrayList<FoodEntity> itemsArrayList = null;
                if (response.body() != null) {
                    itemsArrayList = new ArrayList<>(response.body().getFood());
                }
                if (itemsArrayList != null) {
                    for (FoodEntity item : itemsArrayList) {
                        totalServing= (serving)/item.getServing_size_g();
                        currentDate = new Date();
                        dateFormat= new SimpleDateFormat(   "yyyy-MM-dd");
                        dateOnly = dateFormat.format(currentDate);
                        food= new FoodEntity(dateOnly, content, title, serving, calculateGrams(item.getCalories()), calculateGrams(item.getProtein_g()), calculateGrams(item.getCarbohydrates_total_g()), calculateGrams(item.getSugar_g()), calculateGrams(item.getFiber_g()), calculateGrams(item.getSodium_mg()), calculateGrams(item.getPotassium_mg()), calculateGrams(item.getFat_saturated_g()), calculateGrams(item.getFat_total_g()), calculateGrams(item.getCholesterol_mg()));
                        progressBar.setVisibility(View.GONE);
                        cardShowNutrition.setVisibility(View.VISIBLE);
                        noFoodFoundLayout.setVisibility(View.GONE);
                        showNutritionInfo(item);
                        displayResults(food);
                    }
                }
                if (itemsArrayList != null && itemsArrayList.size() == 0) {
                    noFoodFound = true;
                    LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
                    progressBar.setVisibility(View.GONE);
                    noFoodFoundLayout.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public Float calculateGrams(Float nutrient){
        totalNutrients= nutrient * totalServing;
        return totalNutrients;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater= getMenuInflater();
        menuInflater.inflate(R.menu.search_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuAddItem :
            if(noFoodFound)
            {
                Toast.makeText(this, "Sorry No Food Found, Food didn't get save", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SearchActivity.this, MainActivity.class); intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); finish();

            }
            else if(saveItem(food) && !noFoodFound) {
                Intent intent = new Intent(SearchActivity.this, MainActivity.class); intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); finish();
                return true;
            }
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void displayResults(FoodEntity food){
        String upperString = food.getFoodName().substring(0, 1).toUpperCase() + food.getFoodName().substring(1).toLowerCase();
        textResult.setText(upperString);
    }

    private boolean saveItem(FoodEntity food) {
        if(textResult.getText().toString().length()==0){
            Toast.makeText(this, "Please search the item first", Toast.LENGTH_SHORT).show();
            return false;
        }
            foodViewModel.insert(food);
            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
        return true;
    }
     private void hideKeyboard() {
         View view = this.getCurrentFocus();
         if (view != null) {
             InputMethodManager imm =
                     (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
             imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
         }
     }

     private void showNutritionInfo(FoodEntity item){
            nutritionInfoServingSize.setText(String.format("Serving Size: %s", Float.parseFloat(totalServing.toString())));
                nutritionInfoCalories.setText(("Calories " + round(calculateGrams(item.getCalories()), 2) + " Cal"));
            nutritionInfoProteinCalories.setText(String.valueOf(round(calculateGrams(item.getProtein_g()),2)));
            nutritionInfoFatsCalories.setText(String.valueOf(round(calculateGrams(item.getFat_total_g()), 2)));
            nutritionInfoCarbsCalories.setText(String.valueOf(round(calculateGrams(item.getCarbohydrates_total_g()), 2)));
            nutritionInfoFibreCalories.setText(String.valueOf(round(calculateGrams(item.getFiber_g()), 2)));
            nutritionInfoFatSaturatedCalories.setText(String.valueOf(round(calculateGrams(item.getFat_saturated_g()), 2)));
            nutritionInfoSodiumCalories.setText(String.valueOf(round(calculateGrams(item.getSodium_mg()), 2)));
            nutritionInfoPotassiumCalories.setText(String.valueOf(round(calculateGrams(item.getPotassium_mg()), 2)));
            nutritionInfoSugarCalories.setText(String.valueOf(round(calculateGrams(item.getSugar_g()), 2)));
            nutritionInfoCholesterolCalories.setText(String.valueOf(round(calculateGrams(item.getCholesterol_mg()), 2)));
     }

    private static double round (double value, int precision) {
        int scale = (int) Math.pow(10, precision);
        return (double) Math.round(value * scale) / scale;
    }

}


