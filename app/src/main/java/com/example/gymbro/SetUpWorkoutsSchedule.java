package com.example.gymbro;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;

import java.util.ArrayList;
import java.util.Calendar;

public class SetUpWorkoutsSchedule extends AppCompatActivity {
    GymBroApplication app_context;
    NumberPicker hour;
    NumberPicker minutes;
    Button mNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app_context = (GymBroApplication) getApplicationContext();
        app_context.setup_workout_schedule_context = this;

        setContentView(R.layout.activity_set_up_workouts_schedule);

        mNext = (Button) findViewById(R.id.btnNext);

        Button mMonday = (Button) findViewById(R.id.btnMon);
        Button mTuesday = (Button) findViewById(R.id.btnTues);
        Button mWednesday = (Button) findViewById(R.id.btnWed);
        Button mThursday = (Button) findViewById(R.id.btnThurs);
        Button mFriday = (Button) findViewById(R.id.btnFri);
        Button mSaturday = (Button) findViewById(R.id.btnSat);
        Button mSunday = (Button) findViewById(R.id.btnSun);

        ArrayList<Button> btnArrl = new ArrayList<>();
        btnArrl.add(mMonday);
        btnArrl.add(mTuesday);
        btnArrl.add(mWednesday);
        btnArrl.add(mThursday);
        btnArrl.add(mFriday);
        btnArrl.add(mSaturday);
        btnArrl.add(mSunday);

        for (Button button : btnArrl) {
            button.setOnClickListener(new View.OnClickListener() {
                final int defaultColor = Color.parseColor("#808080");
                final int activeColor = Color.parseColor("#03DAC5");
                boolean active = false;
                @Override
                public void onClick(View v) {
                    if (active) {
                        v.setBackgroundColor(defaultColor);
                    } else {
                        v.setBackgroundColor(activeColor);
                    }
                    active = !active;
                }
            });
        }

        mNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                app_context.showToast("Workout schedule created!");
                Intent i = new Intent(SetUpWorkoutsSchedule.this, HomeActivity.class);
                startActivity(i);
            }
        });
    }



}