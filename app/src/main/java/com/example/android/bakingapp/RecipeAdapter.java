package com.example.android.bakingapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class RecipeAdapter  extends RecyclerView.Adapter<RecipeAdapter.RecipeAdapterViewHolder> {

    private ArrayList<String> mRecipeNames;
    private Context mContext;

    public RecipeAdapter(Context context, ArrayList<String> recipeNames) {
        this.mContext = context;
        this.mRecipeNames = recipeNames;
    }

    public class RecipeAdapterViewHolder extends RecyclerView.ViewHolder {
        public final TextView recipeNameTextView;
        LinearLayout linearLayout;

        public RecipeAdapterViewHolder(View itemView) {
            super(itemView);
            recipeNameTextView = itemView.findViewById(R.id.recipe_name);
            linearLayout = itemView.findViewById(R.id.recipe_layout);
        }
    }

    @NonNull
    @Override
    public RecipeAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForRecipeItem = R.layout.recipe_item;
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(layoutIdForRecipeItem, viewGroup, false);
        return new RecipeAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeAdapterViewHolder holder, int position) {

        holder.recipeNameTextView.setText(mRecipeNames.get(position));

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "Clicked on recipe", Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return mRecipeNames.size();
    }
}
