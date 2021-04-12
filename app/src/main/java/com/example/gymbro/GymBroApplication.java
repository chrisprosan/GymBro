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
    public WorkoutWizardActivity workout_wizard_context = null;

    @Override
    public void onCreate() {
        super.onCreate();

        // Simple code to test Room database. Should rely on a handler instead.
//        new InsertToDatabase().execute();
    }

    public void showToast(String message, int length) {
        Toast.makeText(this, message, length).show();
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

    private void scheduleAlarm(Calendar calendar) {

        // Set this to whatever you were planning to do at the given time
        Log.i("Chris", "Setting alarm");


        Intent notifyIntent = new Intent(this,MyReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast
                (this, 2, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        if (pendingIntent != null && alarmManager != null) {
            alarmManager.cancel(pendingIntent);
        }

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY * 7, pendingIntent);
        Log.i("Chris", "Alarm set");
        Log.i("Chris", "calendar: " + calendar.toString());
    }

    public void setUpAlarms(List<Calendar> schedule) {
        for (Calendar calendar : schedule) {
            scheduleAlarm(calendar);
        }
    }
}