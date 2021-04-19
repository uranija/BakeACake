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
import com.example.android.bakingapp.model.Recipe;
import com.example.android.bakingapp.ui.RecipeDetailActivity;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    Context mCtx;
    List<Recipe> recipeList;


    public RecipeAdapter(Context mCtx, List<Recipe> recipeList) {
        this.mCtx = mCtx;
        this.recipeList = recipeList;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.recipe_card, parent, false);
        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        Recipe recipe = recipeList.get(position);

        String imageUrl = recipe.getImage();
        holder.id = Integer.parseInt(String.valueOf(recipe.getId()));
        holder.textView.setText(recipe.getName());


        //The Json that we query has 4 recipes,we will use a different photo for each recipe
        if (imageUrl != null && imageUrl.isEmpty()) {
            switch (holder.id) {
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
        } else {
            Glide.with(mCtx).load(imageUrl).into(holder.imageView);
        }


    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    class RecipeViewHolder extends RecyclerView.ViewHolder {


        ImageView imageView;
        TextView textView;
        public int id;


        public RecipeViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.recipe_item);
            textView = itemView.findViewById(R.id.textView);

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
                        Toast.makeText(v.getContext(), "You clicked" + recipe.getName(), Toast.LENGTH_SHORT).show();

                    }
                }


            });


        }


    }


}
