package com.example.android.bakingapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.android.bakingapp.R;
import com.example.android.bakingapp.ui.RecipeDetailActivity;
import com.example.android.bakingapp.model.Ingredient;
import com.example.android.bakingapp.model.Recipe;
import com.example.android.bakingapp.model.Step;
import com.example.android.bakingapp.widget.UpdateIngService;

import java.util.ArrayList;
import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>{

    Context mCtx;
    List<Recipe> recipeList;
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String INGREDIENTS = "ingredients";
    public static final String INGREDIENTS_BUNDLE = "ingredientsBundle";
    public static final String STEPS = "steps";
    public static final String STEPS_BUNDLE = "stepsBundle";
    public static final String SERVINGS = "servings";
    private String mJsonResult;
    String recipeJson;
    private ArrayList<String> ingStrArray, measureStrArray;

    {
        recipeList = new ArrayList<>();
        ingStrArray = new ArrayList<>();
        measureStrArray = new ArrayList<>();
    }

    public RecipeAdapter(Context mCtx, List<Recipe> recipeList) {
        this.mCtx = mCtx;
        this.recipeList = recipeList;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.recyclerview_layout, parent, false);
        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        Recipe recipe = recipeList.get(position);

        String imageUrl = recipe.getImage();
        holder.id= Integer.parseInt(String.valueOf(recipe.getId()));
        holder.textView.setText(recipe.getName());
        holder.textView2.setText(recipe.getServings());
        holder.mSteps=(recipe.getSteps());
        holder.mIngredients=(recipe.getIngredients());

        //The Json that we query has 4 recipes,we will use a different photo for each recipe
        if (imageUrl != null && imageUrl.isEmpty())
        {
            switch (holder.id)
            {
                //Nutella Pie
                case 1:
                    Glide.with(mCtx).load(R.drawable.nutella).into(holder.imageView);
                    break;
                //Brownies
                case 2:
                    Glide.with(mCtx).load(R.drawable.brownnies).into(holder.imageView);
                    break;
                //Yellow Cake
                case 3:
                    Glide.with(mCtx).load(R.drawable.yellowcake).into(holder.imageView);
                    break;
                //Cheesecake
                case 4:
                    Glide.with(mCtx).load(R.drawable.cheesecake).into(holder.imageView);
                    break;
            }
        } else
        {
            Glide.with(mCtx).load(imageUrl).into(holder.imageView);
        }


        ingStrArray.add(String.valueOf(recipeList.get(position).getIngredients()));


        UpdateIngService.startBakingService(mCtx, ingStrArray);


    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    class RecipeViewHolder extends RecyclerView.ViewHolder {



        ImageView imageView;
        TextView textView;
        TextView textView2;
        TextView textView3;
        public int id;
        public String image;
        //recipe name
        private String name;
        private int servings;
        public List<Step> mSteps;
        public List<Recipe> recipe;
        private List<Ingredient> mIngredients;



        public RecipeViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.recipe_item);

            textView = itemView.findViewById(R.id.textView);
            textView2 = itemView.findViewById(R.id.textView2);
            textView3 = itemView.findViewById(R.id.textView3);

            imageView.setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        Recipe recipe = recipeList.get(pos);

                        Intent intent = new Intent(mCtx, RecipeDetailActivity.class);
                        intent.putExtra("Recipe", recipe);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mCtx.startActivity(intent);




                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mCtx.startActivity(intent);
                        Toast.makeText(v.getContext(), "You clicked" + recipe.getName(), Toast.LENGTH_SHORT).show();

                    }
                }


            });


        }






    }




}
