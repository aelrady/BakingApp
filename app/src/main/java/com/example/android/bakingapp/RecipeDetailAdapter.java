package com.example.android.bakingapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class RecipeDetailAdapter extends RecyclerView.Adapter<RecipeDetailAdapter.RecipeDetailAdapterViewHolder> {

    private ArrayList<String> mRecipeSteps;
    private Context mContext;

    public RecipeDetailAdapter(Context context, ArrayList<String> recipeSteps) {
        this.mContext = context;
        this.mRecipeSteps = recipeSteps;
    }

    public class RecipeDetailAdapterViewHolder extends RecyclerView.ViewHolder {
        public final TextView recipeStepTextView;
        LinearLayout linearLayout;

        public RecipeDetailAdapterViewHolder(View itemView) {
            super(itemView);
            recipeStepTextView = itemView.findViewById(R.id.recipe_step);
            linearLayout = itemView.findViewById(R.id.recipe_detail_layout);
        }
    }

    @NonNull
    @Override
    public RecipeDetailAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForRecipeItem = R.layout.recipe_detail_item;
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(layoutIdForRecipeItem, viewGroup, false);
        return new RecipeDetailAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeDetailAdapterViewHolder holder, int position) {

        holder.recipeStepTextView.setText(mRecipeSteps.get(position));

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, StepDetailActivity.class);
                //intent.putExtra("recipe", mRecipes[position]);
                mContext.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return mRecipeSteps.size();
    }
}

