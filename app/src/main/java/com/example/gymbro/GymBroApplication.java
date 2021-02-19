package com.example.gymbro;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;

public class GymBroApplication extends Application {

    public HomeActivity home_context = null;
    public WorkoutActivity workout_context = null;
    @Override
    public void onCreate() {
        super.onCreate();
    }
}