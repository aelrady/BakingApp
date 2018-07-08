package com.example.android.bakingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.android.bakingapp.Model.Ingredient;
import com.example.android.bakingapp.Model.Recipe;
import com.example.android.bakingapp.Model.Step;

import java.util.ArrayList;

public class RecipeDetailActivity extends AppCompatActivity {

    private Recipe recipe;
    RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private RecipeDetailAdapter recipeDetailAdapter;
    private IngredientAdapter ingredientAdapter;
    private TextView ingredientsTextView;
    private TextView stepsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        Intent intent = getIntent();
        recipe = intent.getParcelableExtra("recipe");

        ingredientsTextView = findViewById(R.id.ingredients);
        stepsTextView = findViewById(R.id.steps);

        populateRecipeDetailActivity();
    }

    private void populateRecipeDetailActivity() {
        ArrayList<Step> recipeSteps = (ArrayList<Step>) recipe.getSteps();
        ArrayList<String> stepNames = new ArrayList<>();
        for (Step step : recipeSteps) {
            stepNames.add(step.getShortDescription().substring(0,1).toUpperCase() + step.getShortDescription().substring(1).toLowerCase());
        }

        ArrayList<Ingredient> recipeIngredients = (ArrayList<Ingredient>) recipe.getIngredients();
        ArrayList<String> ingredients = new ArrayList<>();
        for (Ingredient ingredient : recipeIngredients) {
            ingredients.add(ingredient.getIngredient().substring(0,1).toUpperCase() + ingredient.getIngredient().substring(1).toLowerCase());
        }

        recyclerView = findViewById(R.id.rv_steps);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recipeDetailAdapter = new RecipeDetailAdapter(RecipeDetailActivity.this, stepNames, recipeSteps);
        ingredientAdapter = new IngredientAdapter(RecipeDetailActivity.this, ingredients);

        populateIngredients();

        ingredientsTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                populateIngredients();
            }
        });

        stepsTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                populateSteps();
            }
        });
    }

    private void populateIngredients() {
        recyclerView.setAdapter(ingredientAdapter);
        ingredientsTextView.setBackgroundColor(RecipeDetailActivity.this.getResources().getColor(R.color.colorAccent));
        ingredientsTextView.setTextColor(RecipeDetailActivity.this.getResources().getColor(R.color.menu_clicked_text_color));
        stepsTextView.setBackgroundColor(RecipeDetailActivity.this.getResources().getColor(R.color.menu_clicked_text_color));
        stepsTextView.setTextColor(RecipeDetailActivity.this.getResources().getColor(R.color.recipe_name_color));
    }

    private void populateSteps() {
        recyclerView.setAdapter(recipeDetailAdapter);
        stepsTextView.setBackgroundColor(RecipeDetailActivity.this.getResources().getColor(R.color.colorAccent));
        stepsTextView.setTextColor(RecipeDetailActivity.this.getResources().getColor(R.color.menu_clicked_text_color));
        ingredientsTextView.setBackgroundColor(RecipeDetailActivity.this.getResources().getColor(R.color.menu_clicked_text_color));
        ingredientsTextView.setTextColor(RecipeDetailActivity.this.getResources().getColor(R.color.recipe_name_color));
    }
}
