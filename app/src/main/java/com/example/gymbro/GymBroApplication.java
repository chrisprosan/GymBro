package com.example.gymbro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class GymBroApplication extends Application {

    public HomeActivity home_context = null;
    public WorkoutActivity workout_context = null;
    public InstructionActivity instruction_context = null;
    public SetUpWorkoutsSchedule setup_workout_schedule_context = null;
    public SetUpWorkouts setup_workouts = null;

    @Override
    public void onCreate() {
        super.onCreate();

        // Simple code to test Room database. Should rely on a handler instead.
        new InsertToDatabase().execute();
    }

    /**
     * Note: The database is actually saved locally on the device.
     * Meaning, we only need to insert the sample data ONCE.
     * Even after clearing all exercises from the database, their IDs are saved.
     */
    class InsertToDatabase extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Log.i("Chris", "Creating database");
            AppDatabase db = AppDatabase.getInstance(GymBroApplication.this);
            Log.i("Chris", "Created database db");
            ExerciseDao exerciseDao = db.exerciseDao();
            Log.i("Chris", "Inserting sample data");
            List<ExerciseDB> exerciseDBList = exerciseDao.loadAllExercises();

            if (exerciseDBList.size() != 0) { // Check if
                exerciseDao.deleteAll(exerciseDBList);
            }

            exerciseDao.insertAllExercises(generateTestExercises());

            Log.i("Chris", "Inserted sample data");

            exerciseDBList = exerciseDao.loadAllExercises();
            Log.i("Chris", "Retrieved sample data");
            Log.i("Chris", "exerciseDBList size: " + exerciseDBList.size());
            for (ExerciseDB exerciseDB : exerciseDBList) {
                Log.i("Chris", ""+ exerciseDB);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

        }
    }

    public List<ExerciseDB> generateTestExercises() {
        List<ExerciseDB> exerciseList = new ArrayList<>();
        ArrayList<String> pushUpInstructions = new ArrayList<>();
        pushUpInstructions.add("Body in a straight line from head to toe: don't let the hips sag!");
        pushUpInstructions.add("Lock out arms and protract the shoulder blades at the top");
        pushUpInstructions.add("Go down until chest nearly touches the ground");
        pushUpInstructions.add("Keep the elbows in, don't let them flare out");
        pushUpInstructions.add("Don't shrug up your shoulders to your ears, focus on depressing the shoulder blades");

        ArrayList<String> pullUpInstructions = new ArrayList<>();
        pullUpInstructions.add("Body slightly hollow with straight legs throughout the whole exercise. " +
                "Don't cross your legs.");
        pullUpInstructions.add("If you cannot get straight legs, it's preferable to keep the feet " +
                "in front of the body rather than behind.");
        pullUpInstructions.add("Arms straight at the bottom. Don't think about anything else, " +
                "just straight arms. The rest will happen automatically.");
        pullUpInstructions.add("Strive for chest to bar at the top. For this the forearms have to deviate from vertical, " +
                "which may be a bit hard on the elbows, so build up to it slowly.");
        pullUpInstructions.add("Keep the neck in a neutral position: avoid craning it to " +
                "get your chin over the bar");
        pullUpInstructions.add("It's natural for your legs to come forward: this keeps your centre of " +
                "mass under the bar. Just make sure you're not violently swinging them upwards.");

//        exerciseList.add(new Exercise("Push-Up", 3, 8, 90, "IODxDxX7oi4", new Exercise.Instruction(pushUpInstructions)));  // Test exercise
//        exerciseList.add(new Exercise("Pull-Up", 3, 5, 90, "eGo4IYlbE5g", new Exercise.Instruction(pullUpInstructions)));  // Test exercise



        exerciseList.add(new ExerciseDB(0,"Push-Up", 3, 8, 90, "IODxDxX7oi4", pushUpInstructions));  // Test exercise
        exerciseList.add(new ExerciseDB(1,"Pull-Up", 3, 5, 90, "eGo4IYlbE5g", pullUpInstructions));  // Test exercise
        return exerciseList;
    }

    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void watchYoutubeVideo(Context context, String id){
        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + id));
        Intent webIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://www.youtube.com/watch?v=" + id));
        try {
            context.startActivity(appIntent);
        } catch (ActivityNotFoundException ex) {
            context.startActivity(webIntent);
        }
    }

    private void scheduleAlarm(int dayOfWeek) {

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, dayOfWeek);

        // Check we aren't setting it in the past which would trigger it to fire instantly
        if(calendar.getTimeInMillis() < System.currentTimeMillis()) {
            calendar.add(Calendar.DAY_OF_YEAR, 7);
        }

        // Set this to whatever you were planning to do at the given time


        Intent notifyIntent = new Intent(this,MyReceiver.class);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        PendingIntent pendingIntent = PendingIntent.getService(this, 0, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        if (pendingIntent != null && alarmManager != null) {
            alarmManager.cancel(pendingIntent);
        }

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY * 7, pendingIntent);
    }

    private void setUpAlarms() {

        scheduleAlarm(Calendar.MONDAY);
        scheduleAlarm(Calendar.FRIDAY);
    }
}