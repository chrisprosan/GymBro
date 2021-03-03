package com.example.gymbro;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * Load in an Exercise object and extract variables from it
 * Expected variables: Name, Instructions, boolTimeBased, Reps, Sets, duration
 */
public class WorkoutActivity extends AppCompatActivity {
    GymBroApplication app_context;
    Button mStartTimer;
    Button mSetIncrement;
    Button mRepIncrement;
    Button mShowInstructions;
    Button mAdvance;

    TextView exerciseNameTextView;
    TextView timerCountTextView;
    TextView setTextView;
    TextView repTextView;

    int currentExerciseIndex = 0;
    int currSet = 0;
    int currRep = 0;
    ArrayList<Exercise> exerciseList;

    static final String END_WORKOUT = "End Workout";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);
        app_context = (GymBroApplication) getApplicationContext();
        app_context.workout_context = this;

        mStartTimer = (Button) findViewById(R.id.btn_timer_start);
        mSetIncrement = (Button) findViewById(R.id.btn_set_increment);
        mRepIncrement = (Button) findViewById(R.id.btn_rep_increment);
        mAdvance = (Button) findViewById(R.id.btn_advance_exercise);

        exerciseNameTextView = (TextView) findViewById(R.id.exercise_name);
        timerCountTextView = (TextView) findViewById(R.id.timer_count);
        setTextView = (TextView) findViewById(R.id.set_count);
        repTextView = (TextView) findViewById(R.id.rep_count);

        ArrayList<String> pushUpCues = new ArrayList<>();
        pushUpCues.add("Body in a straight line from head to toe: don't let the hips sag!");
        pushUpCues.add("Lock out arms and protract the shoulder blades at the top");
        pushUpCues.add("Go down until chest nearly touches the ground");
        pushUpCues.add("Keep the elbows in, don't let them flare out");
        pushUpCues.add("Don't shrug up your shoulders to your ears, focus on depressing the shoulder blades");

        ArrayList<String> pullUpCues = new ArrayList<>();
        pullUpCues.add("Body slightly hollow with straight legs throughout the whole exercise. " +
                "Don't cross your legs.");
        pullUpCues.add("If you cannot get straight legs, it's preferable to keep the feet " +
                "in front of the body rather than behind.");
        pullUpCues.add("Arms straight at the bottom. Don't think about anything else, " +
                "just straight arms. The rest will happen automatically.");
        pullUpCues.add("Strive for chest to bar at the top. For this the forearms have to deviate from vertical, " +
                "which may be a bit hard on the elbows, so build up to it slowly.");
        pullUpCues.add("Keep the neck in a neutral position: avoid craning it to " +
                "get your chin over the bar");
        pullUpCues.add("It's natural for your legs to come forward: this keeps your centre of " +
                "mass under the bar. Just make sure you're not violently swinging them upwards.");

        exerciseList = new ArrayList<>();
        exerciseList.add(new Exercise("Push-Up", 3, 8, 90, new Exercise.Instruction(pushUpCues)));  // Test exercise
        exerciseList.add(new Exercise("Pull-Up", 3, 5, 90, new Exercise.Instruction(pullUpCues)));  // Test exercise
        updateExercise();

        mAdvance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentExerciseIndex++;
                if (currentExerciseIndex < exerciseList.size()) {
                    updateExercise();
                    if (currentExerciseIndex == exerciseList.size() - 1) {
                        mAdvance.setText(END_WORKOUT);
                    }
                }
                else {
                    finish();
                }
            }
        });
    }

    private void updateExercise() {
        Exercise currentExercise = exerciseList.get(currentExerciseIndex);

        String exerciseName = currentExercise.getExerciseName();
        int sets = currentExercise.getSets();
        int reps = currentExercise.getReps();
        long duration = currentExercise.getDuration();

        exerciseNameTextView.setText(exerciseName);
        setTextView.setText(String.format(Locale.getDefault(), "Sets: %01d/%01d", currSet, sets));
        repTextView.setText(String.format(Locale.getDefault(), "Reps: %01d/%01d", currRep, reps));

        String timer = String.format(Locale.getDefault(), "%02d:%02d", (duration % 3600) / 60, (duration % 60));
        timerCountTextView.setText(timer);
        long milliseconds = TimeUnit.SECONDS.toMillis(duration);

        mStartTimer.setOnClickListener(v -> new CountDownTimer(milliseconds, 1000) {
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
        }.start());

        mSetIncrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currSet++;
                setTextView.setText(String.format(Locale.getDefault(), "Sets: %01d/%01d", currSet, sets));
            }
        });

        mRepIncrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currRep++;
                repTextView.setText(String.format(Locale.getDefault(), "Reps: %01d/%01d", currRep, reps));
            }
        });
    }


}
