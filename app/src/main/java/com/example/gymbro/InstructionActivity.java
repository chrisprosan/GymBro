package com.example.gymbro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class InstructionActivity extends AppCompatActivity {
    GymBroApplication app_context;
    WorkoutActivity workout_context;

    TextView exerciseNameTextView;
    TextView exerciseCuesTextView;
    ImageView exerciseImageView;

    Button exerciseVideoButton;
    Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app_context =  (GymBroApplication) getApplicationContext();
        workout_context = app_context.workout_context;
        app_context.instruction_context = this;

        setContentView(R.layout.activity_instruction);

        exerciseNameTextView = (TextView) findViewById(R.id.exercise_name);
        exerciseCuesTextView = (TextView) findViewById(R.id.exercise_cues);
        exerciseImageView = (ImageView) findViewById(R.id.exercise_img);

        exerciseVideoButton = (Button) findViewById(R.id.btn_exercise_vid);
        backButton = (Button) findViewById(R.id.btn_back);

        Exercise currExercise = workout_context.getCurrExercise();

        exerciseNameTextView.setText(currExercise.getExerciseName());
        exerciseCuesTextView.setText(currExercise.getCues());
        String vidId = currExercise.getVidId();

        exerciseVideoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                app_context.watchYoutubeVideo(InstructionActivity.this, vidId);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}