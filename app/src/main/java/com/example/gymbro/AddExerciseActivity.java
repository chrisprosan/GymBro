package com.example.gymbro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AddExerciseActivity extends AppCompatActivity {
    Button mAddExercise;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exercise);
        mAddExercise = (Button) findViewById(R.id.btn_advance_exercise);

        mAddExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addExercises();
                finish();
            }
        });
    }

    private void addExercises() {

    }
}