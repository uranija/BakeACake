package com.example.android.bakingapp.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.adapter.IngredientAdapter;
import com.example.android.bakingapp.adapter.StepAdapter;
import com.example.android.bakingapp.api.Api;
import com.example.android.bakingapp.model.Ingredient;
import com.example.android.bakingapp.model.Recipe;
import com.example.android.bakingapp.model.Step;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RecipeDetailActivity extends AppCompatActivity {
    private boolean mTwoPane;
    private RecyclerView recyclerView;
    private RecyclerView recyclerView2;
    public Recipe recipe;
    public List<Ingredient> recipeIngredient;
    public List<Step> recipeStep;
    public String recipeName;
    public ArrayList<Object> recipeObjects;

    StepAdapter stepAdapter;
    List<Step> stepList;
    IngredientAdapter ingredientAdapter;
    List<Ingredient>ingredientList;
    private Context mContext;
    private boolean isTablet;

    ArrayList<Step> mStepArrayList;
    String mJsonResult;
    List<Ingredient> mIngredientList;
    public boolean isTwoPane;
    private RecipeDetailActivity mParentAvtivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Check if device is a tablet
        if(findViewById(R.id.cooking_tablet) != null){
            isTablet = true;
        }
        else{
            isTablet = false;
        }



        recipeObjects = new ArrayList<Object>();

        Intent intentThatStartedThisActivity = getIntent();
        if (intentThatStartedThisActivity.hasExtra("Recipe")) {

            recipe = getIntent().getParcelableExtra("Recipe");
            recipeIngredient = recipe.getIngredients();
            recipeStep = recipe.getSteps();
            recipeName = recipe.getName();
            recipeObjects.addAll(recipeIngredient);
            recipeObjects.addAll(recipeStep);

            //Toast.makeText(this, recipeStep + " ", Toast.LENGTH_SHORT).show();
            setTitle(recipeName);
            //UpdateIngService.startBakingService(mContext, recipeObjects);

        } else {
            Toast.makeText(this, "Data not available", Toast.LENGTH_SHORT).show();
        }








        initSteps();
        initIngredient();


    }






    private void initSteps(){
        stepList = new ArrayList<>();
        stepAdapter = new StepAdapter(this, stepList, mParentAvtivity, isTwoPane);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view2);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(stepAdapter);
        stepAdapter.notifyDataSetChanged();

        getSteps();


    }


    private void getSteps() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();

        Api api = retrofit.create(Api.class);

        Call<List<Step>> call = api.getStep();

        call.enqueue(new Callback<List<Step>>() {


            @Override
            public void onResponse(Call<List<Step>> call, Response<List<Step>> response) {

                List<Step> recipes = response.body();






                //displaying the string array into listview

                recyclerView.setAdapter(new StepAdapter(getApplicationContext(), recipeStep, mParentAvtivity, isTwoPane));

            }

            @Override
            public void onFailure(Call<List<Step>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initIngredient(){
        ingredientList = new ArrayList<>();
        ingredientAdapter = new IngredientAdapter(this, ingredientList);

        recyclerView2 = (RecyclerView) findViewById(R.id.recycler_view3);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView2.setLayoutManager(mLayoutManager);
        recyclerView2.setAdapter(ingredientAdapter);
        ingredientAdapter.notifyDataSetChanged();

        getIngredient();

    }


    private void getIngredient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();

        Api api = retrofit.create(Api.class);

        Call<List<Ingredient>> call = api.getIngredient();

        call.enqueue(new Callback<List<Ingredient>>() {


            @Override
            public void onResponse(Call<List<Ingredient>> call, Response<List<Ingredient>> response) {

                List<Ingredient> recipes = response.body();






                //displaying the string array into listview

                recyclerView2.setAdapter(new IngredientAdapter(getApplicationContext(), recipeIngredient));

            }

            @Override
            public void onFailure(Call<List<Ingredient>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }






}