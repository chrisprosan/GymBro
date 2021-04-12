package com.example.gymbro;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class WorkoutWizardActivity extends AppCompatActivity {
    /**
     * The number of pages (wizard steps) to show in this demo.
     */
    private static final int NUM_PAGES = 2;

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager mPager;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter pagerAdapter;

    public GymBroApplication app_context;
    public HomeActivity home_context;

    private Button mAdvance;
    private List<Exercise> exerciseList = new ArrayList<>();
    public HashMap<Integer, Integer> hashMap_days = new HashMap<>();
    private final boolean[] days_selected = new boolean[7];  // 7 days in a week, starting from Monday
    private final int[] arr_hour_minutes = new int[2];  // index 0 is hour, 1 is minutes
    private List<WorkoutDay> schedule = new ArrayList<>();
    private String workoutName = "";
    AlertDialog.Builder alertDialogBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_wizard);
        app_context = (GymBroApplication) getApplication();
        app_context.workout_wizard_context = this;
        home_context = app_context.home_context;

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.pager);
        pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(pagerAdapter);

        mAdvance = (Button) findViewById(R.id.advance);

        mAdvance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPager.getCurrentItem() == (pagerAdapter.getCount() - 1)) {
                    showSetWorkoutNameDialog();
                } else {
                    advance();
                }

            }
        });

        hashMap_days.put(0, Calendar.MONDAY);
        hashMap_days.put(1, Calendar.TUESDAY);
        hashMap_days.put(2, Calendar.WEDNESDAY);
        hashMap_days.put(3, Calendar.THURSDAY);
        hashMap_days.put(4, Calendar.FRIDAY);
        hashMap_days.put(5, Calendar.SATURDAY);
        hashMap_days.put(6, Calendar.SUNDAY);

    }

    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
            String finish = "NEXT";
            mAdvance.setText(finish);
        }
    }

    private void advance() {
        mPager.setCurrentItem(mPager.getCurrentItem() + 1);
        if (mPager.getCurrentItem() == (pagerAdapter.getCount() - 1)) {
            String finish = "FINISH";
            mAdvance.setText(finish);
        }
    }

    private void finishSetup() {
        for (int i = 0; i < days_selected.length; i++) {
            if (days_selected[i]) {
                Calendar calendar = Calendar.getInstance();
                int hour = arr_hour_minutes[0];
                int minutes = arr_hour_minutes[1];
                Integer day_in_week = hashMap_days.get(i);
                if (day_in_week != null) {
                    WorkoutDay workoutDay = new WorkoutDay(day_in_week, hour, minutes);

                    // Check we aren't setting it in the past which would trigger it to fire instantly
                    if (calendar.getTimeInMillis() < System.currentTimeMillis()) {
                        calendar.add(Calendar.DAY_OF_YEAR, 7);
                    }
                    schedule.add(workoutDay);
                }
            }
        }

        WorkoutSchedule workout_schedule = new WorkoutSchedule(workoutName, exerciseList, schedule);
        exerciseList = new ArrayList<>();
        schedule = new ArrayList<>();
        home_context.addWorkout(workout_schedule);
        home_context.setup_alarms(workout_schedule);
        Log.e("Chris", "" + workout_schedule);
        app_context.showToast("Workout schedule created!", Toast.LENGTH_SHORT);
        finish();
    }

    /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new SetUpWorkoutsFragment();
                case 1:
                    return new SetUpWorkoutsScheduleFragment();
            }
            return new SetUpWorkoutsFragment();
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }

    }

    public List<Exercise> getExerciseList() {
        return exerciseList;
    }

    public boolean[] getDays_selected() {
        return days_selected;
    }

    public int[] getArr_hour_minutes() {
        return arr_hour_minutes;
    }

    protected void showSetWorkoutNameDialog() {
        alertDialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View inputView = inflater.inflate(R.layout.dialog_workout_name, null);

        final EditText mWorkoutName = (EditText) inputView.findViewById(R.id.dialog_workout_name_edittext);	//20190329
        // set title
        alertDialogBuilder.setTitle("Enter workout name");
        alertDialogBuilder.setView(inputView);
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        String workoutName = mWorkoutName.getText().toString();
                        if (workoutName.isEmpty() || workoutName.trim().equals("")) {
                            app_context.showToast("Please enter a workout name", Toast.LENGTH_SHORT);
                            return;
                        }
                        WorkoutWizardActivity.this.workoutName = workoutName;
                        finishSetup();
                        dialog.dismiss();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        app_context.workout_wizard_context = null;
    }
}

