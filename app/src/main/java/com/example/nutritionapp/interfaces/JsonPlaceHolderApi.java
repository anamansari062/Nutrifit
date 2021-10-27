package com.example.nutritionapp.interfaces;


import com.example.nutritionapp.Json.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface JsonPlaceHolderApi {

    @GET("v1/nutrition?")
    Call<JsonObject> getJsonObject(@Query("query") String query,
                                   @Header("x-rapidapi-host") String host, @Header("x-rapidapi-key")  String key);
    }

