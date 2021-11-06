package com.example.nutritionapp.ui.search;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.example.nutritionapp.Food.FoodEntity;
import com.example.nutritionapp.Food.FoodViewModel;
import com.example.nutritionapp.Json.JsonObject;
import com.example.nutritionapp.R;
import com.example.nutritionapp.interfaces.JsonPlaceHolderApi;
import com.example.nutritionapp.ui.home.HomeFragment;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchActivity extends AppCompatActivity {
    String host = "calorieninjas.p.rapidapi.com";
    String key = "9a845c1749msh980401f10c34888p1dbde1jsn50abf0bfda0b";
    String title;
    Float totalServing, totalNutrients, savedCalories;
    EditText textSearch, textServe;
    TextView textResult;
    Button buttonSearch;
    FoodEntity food;
    private FoodViewModel foodViewModel;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_activity);

        textSearch= (EditText) findViewById(R.id.textSearch);
        textServe= (EditText) findViewById(R.id.textServe);
        textResult= (TextView) findViewById(R.id.textResult);
        buttonSearch = (Button) findViewById(R.id.buttonSearch);
        Intent intent= getIntent();
        title= intent.getStringExtra("title");
        setTitle("Add " + title);



        foodViewModel = new ViewModelProvider(this).get(FoodViewModel.class);
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(textSearch.getText().toString().length()==0){
                    Toast.makeText(SearchActivity.this, "Please enter the food name", Toast.LENGTH_SHORT).show();
                }
                else if(textServe.getText().toString().length()==0){
                    Toast.makeText(SearchActivity.this, "Please enter the serving size", Toast.LENGTH_SHORT).show();
                }
                else
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
                    Toast.makeText(getApplicationContext(), response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                ArrayList<FoodEntity> itemsArrayList = new ArrayList<FoodEntity>(response.body().getFood());
                for (FoodEntity item : itemsArrayList) {
                    totalServing= item.getServing_size_g()/(serving);
                    food= new FoodEntity(content, title, serving, calculateGrams(item.getCalories()), calculateGrams(item.getProtein_g()), calculateGrams(item.getCarbohydrates_total_g()), calculateGrams(item.getSugar_g()), calculateGrams(item.getFiber_g()), calculateGrams(item.getSodium_mg()), calculateGrams(item.getPotassium_mg()), calculateGrams(item.getFat_saturated_g()), calculateGrams(item.getFat_total_g()), calculateGrams(item.getCholesterol_mg()));
                    displayResults(food);
                }
                return;

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                return;
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
        if (item.getItemId() == R.id.menuAddItem) {
            if(saveItem(food)) {
                Fragment fragment = new HomeFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.nav_host_fragment_content_main, fragment).commit();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public void displayResults(FoodEntity food){
        textResult.setText(food.getFoodName());
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

}


