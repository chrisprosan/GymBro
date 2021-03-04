package com.example.gymbro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;

public class SetUpWorkoutsSchedule extends AppCompatActivity {
    NumberPicker hour;
    NumberPicker minutes;
    Button mNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_up_workouts_schedule);

        hour = (NumberPicker) findViewById(R.id.scrollHours);
        minutes = (NumberPicker) findViewById(R.id.scrollMins);

        mNext = (Button) findViewById(R.id.btnNext);

        hour.setMinValue(1);
        hour.setMaxValue(12);

        minutes.setMinValue(0);
        minutes.setMaxValue(59);

        mNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SetUpWorkoutsSchedule.this, HomeActivity.class);
                startActivity(i);
            }
        });
    }

}