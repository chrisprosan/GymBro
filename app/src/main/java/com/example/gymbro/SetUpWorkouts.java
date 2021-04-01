package com.example.gymbro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class SetUpWorkouts extends AppCompatActivity {
    Button mAddExercise;
    Button mNext;
    List<Exercise> exerciseNames = new ArrayList<>();
    GymBroApplication appContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_up_workouts);
        appContext = (GymBroApplication) getApplicationContext();
        appContext.setup_workouts = this;

        ExerciseAdapter adapter = new ExerciseAdapter(SetUpWorkouts.this, exerciseNames);
        ListView listview = (ListView) findViewById(R.id.list_view);
        listview.setAdapter(adapter);

        mAddExercise = (Button) findViewById(R.id.btnAddExercise);
        mNext = (Button) findViewById(R.id.btnNext);

        mAddExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SetUpWorkouts.this, AddExerciseActivity.class);
                startActivity(i);
            }
        });

        mNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SetUpWorkouts.this, SetUpWorkoutsSchedule.class);
                startActivity(i);
            }
        });


    }

}