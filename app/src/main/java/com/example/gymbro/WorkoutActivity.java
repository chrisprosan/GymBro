package com.example.gymbro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.number.NumberFormatter;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class WorkoutActivity extends AppCompatActivity {

    GymBroApplication app_context;
    HomeActivity home_context;

    Button mStartTimer;
    Button mSetIncrement;
    Button mShowInstructions;

    TextView exerciseNameTextView;
    TextView timerCountTextView;
    TextView setTextView;
    TextView rest;

    NumberPicker repCount;

    int currentExerciseIndex = 0;
    int currSet = 0;
    int currRep = 0;
    int maxSet = 0;
    List<Exercise> exerciseList;

    CountDownTimer countDownTimer;

    FloatingActionButton nextSet;

    private static final int REST_DURATION = 90; //seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);
        app_context = (GymBroApplication) getApplicationContext();
        app_context.workout_context = this;
        home_context = app_context.home_context;
        int index = getIntent().getExtras().getInt("workoutIndex");

        mStartTimer = (Button) findViewById(R.id.btn_timer_start);
        mSetIncrement = (Button) findViewById(R.id.btn_set_increment);
        mShowInstructions = (Button) findViewById(R.id.btn_show_instructions);

        exerciseNameTextView = (TextView) findViewById(R.id.exercise_name);
        timerCountTextView = (TextView) findViewById(R.id.timer_count);
        setTextView = (TextView) findViewById(R.id.set_count);
        rest = (TextView) findViewById(R.id.rest);

        nextSet = (FloatingActionButton) findViewById(R.id.fab);
        repCount = (NumberPicker) findViewById(R.id.rep_count);

        exerciseList = home_context.getWorkouts().get(index).getExerciseList();

        if (savedInstanceState != null) {
            currentExerciseIndex = savedInstanceState.getInt("currentExerciseIndex");
            currSet = savedInstanceState.getInt("currSet");
            currRep = savedInstanceState.getInt("currRep");
        }

        updateExercise();


        nextSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currSet == maxSet) {
                    nextSet.setImageResource(R.drawable.round_play_arrow_black_18dp);
                    currSet = 0;
                    currRep = 0;
                    currentExerciseIndex++;
                    if (currentExerciseIndex < exerciseList.size()) {
                        updateExercise();
                    }
                    else {
                        app_context.showToast("Workout completed!", Toast.LENGTH_SHORT);
                        finish();
                    }
                } else {
                    currSet++;
                    setTextView.setText(String.format(Locale.getDefault(), "Sets: %01d/%01d", currSet, maxSet));
                    if (currSet == maxSet) {
                        //Change icon to tick
                        nextSet.setImageResource(R.drawable.round_check_black_18dp);
                    }
                }
            }
        });

        repCount.setMinValue(0);
        repCount.setMaxValue(100);
        repCount.setFormatter(new NumberPicker.Formatter() {
            @Override
            public String format(int value) {
                return value + "x";
            }
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt("currentExerciseIndex", currentExerciseIndex);
        outState.putInt("currSet", currSet);
        outState.putInt("currRep", currRep);
        super.onSaveInstanceState(outState);
    }

    private void updateExercise() {
        Exercise currentExercise = exerciseList.get(currentExerciseIndex);

        String exerciseName = currentExercise.getWorkout();
        maxSet = currentExercise.getSets();
        int reps = currentExercise.getReps();
        long duration = currentExercise.getDuration();

        exerciseNameTextView.setText(exerciseName);
        setTextView.setText(String.format(Locale.getDefault(), "Sets: %01d/%01d", currSet, maxSet));
//        repTextView.setText(String.format(Locale.getDefault(), "Reps: %01d/%01d", currRep, reps));

        if (duration == 0) {
            duration = REST_DURATION;
            rest.setVisibility(View.VISIBLE);

        } else {  // Time-based exercises (i.e: planks, dead bugs...)
            rest.setVisibility(View.GONE);
            timerCountTextView.setVisibility(View.VISIBLE);
            mStartTimer.setVisibility(View.VISIBLE);

            String timer = String.format(Locale.getDefault(), "%02d:%02d", (duration % 3600) / 60, (duration % 60));
            timerCountTextView.setText(timer);
            long milliseconds = TimeUnit.SECONDS.toMillis(duration);

            if (countDownTimer != null)
                countDownTimer.cancel();

            countDownTimer = new CountDownTimer(milliseconds, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    long seconds1 = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished);
                    String timer1 = String.format(Locale.getDefault(), "%02d:%02d", (seconds1 % 3600) / 60, (seconds1 % 60));
                    timerCountTextView.setText(timer1);
                }

                @Override
                public void onFinish() {
                    String timer1 = "00:00";
                    timerCountTextView.setText(timer1);
                }
            };

            mStartTimer.setOnClickListener(v -> countDownTimer.start());
        }

        mSetIncrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                maxSet++;
                setTextView.setText(String.format(Locale.getDefault(), "Sets: %01d/%01d", currSet, maxSet));
                if (currSet != maxSet) {
                    nextSet.setImageResource(R.drawable.round_play_arrow_black_18dp);
                }
            }
        });

        mShowInstructions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(WorkoutActivity.this, InstructionActivity.class);
                startActivity(i);
            }
        });
    }

    public Exercise getCurrExercise(){
        return exerciseList.get(currentExerciseIndex);
    }


}
