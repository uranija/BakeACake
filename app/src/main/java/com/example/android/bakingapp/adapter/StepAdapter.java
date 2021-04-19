package com.example.android.bakingapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.model.Step;
import com.example.android.bakingapp.ui.RecipeDetailActivity;
import com.example.android.bakingapp.ui.RecipeStepActivity;
import com.example.android.bakingapp.ui.RecipeStepFragment;

import java.util.ArrayList;
import java.util.List;

public class StepAdapter extends RecyclerView.Adapter<StepAdapter.StepViewHolder> {

    Context mCtx;
    List<Step> stepList;
    public ArrayList<Object> recipeObjects;
    public boolean isTwoPane;
    private final RecipeDetailActivity mParentActivity;

    public StepAdapter(Context mCtx, List<Step> stepList, RecipeDetailActivity mParentActivity, boolean isTwoPane) {
        this.mCtx = mCtx;
        this.stepList = stepList;
        this.mParentActivity = mParentActivity;
        this.isTwoPane = isTwoPane;

    }


    @NonNull
    @Override
    public StepAdapter.StepViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.steps_card, parent, false);
        return new StepAdapter.StepViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StepAdapter.StepViewHolder holder, int position) {
        Step step = stepList.get(position);


        holder.shortDesc.setText(step.getShortDescription());
        holder.desc.setText(step.getDescription());

    }

    @Override
    public int getItemCount() {
        return stepList.size();
    }

    class StepViewHolder extends RecyclerView.ViewHolder {
        public TextView shortDesc, desc, videoUrl;
        public boolean isTwoPane;
        public int id;


        public StepViewHolder(View itemView) {
            super(itemView);


            shortDesc = (TextView) itemView.findViewById(R.id.short_description);
            desc = (TextView) itemView.findViewById(R.id.description);
            videoUrl = (TextView) itemView.findViewById(R.id.video_url);

            itemView.setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        if (isTwoPane) {
                            Step clickedDataItem = (Step) stepList.get(pos);
                            Bundle arguments = new Bundle();
                            arguments.putParcelable("Steps", clickedDataItem);
                            RecipeStepFragment fragment = new RecipeStepFragment();
                            fragment.setArguments(arguments);
                            mParentActivity.getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.item_detail_container, fragment)
                                    .commit();

                        } else {


                            Step clickedDataItem = (Step) stepList.get(pos);
                            Intent intent = new Intent(mCtx, RecipeStepActivity.class);
                            intent.putExtra("Steps", clickedDataItem);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                            mCtx.startActivity(intent);


                        }
                    }
                }


            });


        }


    }
}