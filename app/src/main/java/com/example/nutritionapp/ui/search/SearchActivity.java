package com.example.nutritionapp.ui.search;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nutritionapp.Json.Items;
import com.example.nutritionapp.Json.JsonObject;
import com.example.nutritionapp.R;
import com.example.nutritionapp.interfaces.JsonPlaceHolderApi;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchActivity extends AppCompatActivity {
    String host = "calorieninjas.p.rapidapi.com";
    String key = "9a845c1749msh980401f10c34888p1dbde1jsn50abf0bfda0b";
    Float calories, totalCalories;
    EditText textSearch, textServe;
    TextView textResult;
    Button buttonSearch, buttonAdd;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_activity);

        textSearch= (EditText) findViewById(R.id.textSearch);
        textServe= (EditText) findViewById(R.id.textServe);
        textResult= (TextView) findViewById(R.id.textResult);
        buttonSearch = (Button) findViewById(R.id.buttonSearch);
        buttonAdd= (Button) findViewById(R.id.buttonAdd);
        buttonAdd.setVisibility(View.GONE);



        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchValue(textSearch.getText().toString(), textServe.getText().toString());
                buttonAdd.setVisibility(View.VISIBLE);

            }
        });

    }

    public void searchValue(String content, String serving) {

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
                ArrayList<Items> itemsArrayList = new ArrayList<Items>(response.body().getItems());
                for (Items item : itemsArrayList) {
                    calories= calculateCalories(item.getCalories(), Float.parseFloat(serving), item.getServing_size_g());
                    textResult.setText(item.getfoodName()+ " has " + calories.toString() + " calories");
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

    public Float calculateCalories(Float calories, Float serving, Float defaultServing){
        totalCalories= ( calories * serving )/defaultServing;
        return totalCalories;
    }

    }


