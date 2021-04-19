package com.example.android.bakingapp.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.adapter.RecipeAdapter;
import com.example.android.bakingapp.api.Api;
import com.example.android.bakingapp.model.Recipe;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    RecipeAdapter adapter;
    List<Recipe> recipeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //calling the method to display the heroes
        getRecipes();
        RecipeViewModel model = ViewModelProviders.of(this).get(RecipeViewModel.class);

        model.getRecipes().observe(this, new Observer<List<Recipe>>() {
            @Override
            public void onChanged(@Nullable List<Recipe> heroList) {
                adapter = new RecipeAdapter(MainActivity.this, heroList);
                recyclerView.setAdapter(adapter);
            }
        });


    }

    private void getRecipes() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();

        Api api = retrofit.create(Api.class);

        Call<List<Recipe>> call = api.getRecipes();

        call.enqueue(new Callback<List<Recipe>>() {


            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {

                List<Recipe> recipes = response.body();


                //displaying the string array into listview
                recyclerView.setAdapter(adapter);


            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}
