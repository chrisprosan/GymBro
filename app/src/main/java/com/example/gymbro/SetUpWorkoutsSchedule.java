package com.example.gymbro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SetUpWorkoutsSchedule extends AppCompatActivity {
    Button mNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_up_workouts_schedule);

        mNext = (Button) findViewById(R.id.btnNext);

        mNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SetUpWorkoutsSchedule.this, HomeActivity.class);
                startActivity(i);
            }
        });
    }

}