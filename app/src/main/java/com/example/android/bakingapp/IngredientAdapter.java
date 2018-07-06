package com.example.android.bakingapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.IngredientAdapterViewHolder> {

    private ArrayList<String> mRecipeIngredients;
    private Context mContext;

    public IngredientAdapter(Context context, ArrayList<String> recipeIngredients) {
        this.mContext = context;
        this.mRecipeIngredients = recipeIngredients;
    }

    public class IngredientAdapterViewHolder extends RecyclerView.ViewHolder {
        public final TextView recipeStepTextView;
        LinearLayout linearLayout;

        public IngredientAdapterViewHolder(View itemView) {
            super(itemView);
            recipeStepTextView = itemView.findViewById(R.id.recipe_step);
            linearLayout = itemView.findViewById(R.id.recipe_detail_layout);
        }
    }

    @NonNull
    @Override
    public IngredientAdapter.IngredientAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForRecipeItem = R.layout.recipe_detail_item;
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(layoutIdForRecipeItem, viewGroup, false);
        return new IngredientAdapter.IngredientAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientAdapter.IngredientAdapterViewHolder holder, int position) {

        holder.recipeStepTextView.setText(mRecipeIngredients.get(position));

    }

    @Override
    public int getItemCount() {
        return mRecipeIngredients.size();
    }
}
