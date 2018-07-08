package com.example.android.bakingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.android.bakingapp.Model.Step;

public class StepDetailActivity extends AppCompatActivity {

    private Step step;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_detail);

        Intent intent = getIntent();
        step = intent.getParcelableExtra("step");
        Log.v("Step: ", String.valueOf(step));


    }
}

