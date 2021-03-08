package com.example.gymbro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.Application;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class GymBroApplication extends Application {

    public HomeActivity home_context = null;
    public WorkoutActivity workout_context = null;
    public InstructionActivity instruction_context = null;
    @Override
    public void onCreate() {
        super.onCreate();

        // Simple code to test Room database. Should rely on a handler instead.
        new Thread(() -> {
            Log.i("Chris", "Creating database");
            AppDatabase db = AppDatabase.getInstance(this);
            Log.i("Chris", "Created database db");
            ExerciseDao exerciseDao = db.exerciseDao();
            Log.i("Chris", "Inserting sample data");
            exerciseDao.insertAllExercises(generateTestExercises());
            Log.i("Chris", "Inserted sample data");
            Log.i("Chris", "Retrieving sample data");
            List<ExerciseDB> exerciseDBList = exerciseDao.loadAllExercises();
            Log.i("Chris", "Retrieved sample data");
            for (ExerciseDB exerciseDB : exerciseDBList) {
                Log.i("Chris", ""+ exerciseDB);
            }
        }).start();
    }

    public List<ExerciseDB> generateTestExercises() {
        List<ExerciseDB> exerciseList = new ArrayList<>();
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

//        exerciseList.add(new Exercise("Push-Up", 3, 8, 90, "IODxDxX7oi4", new Exercise.Instruction(pushUpCues)));  // Test exercise
//        exerciseList.add(new Exercise("Pull-Up", 3, 5, 90, "eGo4IYlbE5g", new Exercise.Instruction(pullUpCues)));  // Test exercise

        exerciseList.add(new ExerciseDB("Push-Up", 3, 8, 90, "IODxDxX7oi4"));  // Test exercise
        exerciseList.add(new ExerciseDB("Pull-Up", 3, 5, 90, "eGo4IYlbE5g"));  // Test exercise
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
}