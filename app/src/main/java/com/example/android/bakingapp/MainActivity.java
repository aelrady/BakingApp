package com.example.android.bakingapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.android.bakingapp.Model.Recipe;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private RecipeAdapter recipeAdapter;
    private TextView noConnection;
    private TextView networkException;
    ArrayList<String> recipeNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        noConnection = findViewById(R.id.no_connection);
        noConnection.setVisibility(View.GONE);
        networkException = findViewById(R.id.network_exception);
        networkException.setVisibility(View.GONE);

        populateRecipeNames();
    }

    private void populateRecipeNames() {
        recyclerView = findViewById(R.id.rv_recipes);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        OkHttpClient client = new OkHttpClient();
        String url = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";
        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                if(response.isSuccessful()) {
                    recipeNames = new ArrayList<>();
                    Gson gson = new Gson();
                    String stringResponse = response.body().string();
                    Recipe[] recipes = gson.fromJson(stringResponse, Recipe[].class);
                    for(int i=0; i<recipes.length; i++) {
                        recipeNames.add(recipes[i].getName());
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            recipeAdapter = new RecipeAdapter(MainActivity.this, recipeNames);
                            recyclerView.setAdapter(recipeAdapter);
                        }
                    });
                    Log.v("Recipes: ", String.valueOf(recipeNames));

                }

            }
        });
    }
}
