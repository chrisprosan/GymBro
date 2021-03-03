package com.example.gymbro;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class HomeActivity extends AppCompatActivity {
    Button mCreateWorkoutSchedule;
    FloatingActionButton fab;
    public GymBroApplication app_context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        app_context = (GymBroApplication) getApplicationContext();
        app_context.home_context = this;

        mCreateWorkoutSchedule = (Button) findViewById(R.id.btn_create_workout_schedule);
        fab = findViewById(R.id.fab);

        mCreateWorkoutSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, SetUpWorkouts.class);
                startActivity(i);
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this, WorkoutActivity.class);
                startActivity(i);
            }
        });

    }
}
