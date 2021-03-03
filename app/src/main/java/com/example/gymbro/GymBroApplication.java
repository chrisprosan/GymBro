package com.example.gymbro;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class GymBroApplication extends Application {

    public HomeActivity home_context = null;
    public WorkoutActivity workout_context = null;
    @Override
    public void onCreate() {
        super.onCreate();
    }

    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}