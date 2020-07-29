package com.example.android.bakingapp.api;

import com.example.android.bakingapp.model.Ingredient;
import com.example.android.bakingapp.model.Recipe;
import com.example.android.bakingapp.model.Step;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    String BASE_URL = "https://d17h27t6h515a5.cloudfront.net/";

    @GET("topher/2017/May/59121517_baking/baking.json")
    Call<List<Recipe>> getRecipes();

    @GET("topher/2017/May/59121517_baking/baking.json")
    Call<List<Step>> getStep();

    @GET("topher/2017/May/59121517_baking/baking.json")
    Call<List<Ingredient>> getIngredient();

}
