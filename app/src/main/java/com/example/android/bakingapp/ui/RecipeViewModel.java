package com.example.android.bakingapp.ui;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.android.bakingapp.api.Api;
import com.example.android.bakingapp.model.Recipe;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RecipeViewModel extends ViewModel {
    //this is the data that we will fetch asynchronously
    private MutableLiveData<List<Recipe>> recipeList;

    //we will call this method to get the data
    public LiveData<List<Recipe>> getRecipes() {
        //if the list is null
        if (recipeList == null) {
            recipeList = new MutableLiveData<List<Recipe>>();
            //we will load it asynchronously from server in this method
            loadRecipes();
        }

        //finally we will return the list
        return recipeList;
    }


    //This method is using Retrofit to get the JSON data from URL
    private void loadRecipes() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
        Call<List<Recipe>> call = api.getRecipes();


        call.enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {

                //finally we are setting the list to our MutableLiveData
                recipeList.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {

            }
        });
    }
}
