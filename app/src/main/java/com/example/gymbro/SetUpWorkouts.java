package com.example.gymbro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SetUpWorkouts extends AppCompatActivity {
    Button mAddExercise;
    Button mNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_up_workouts);

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