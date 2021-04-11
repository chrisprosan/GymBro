package com.example.gymbro;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Workouts {
    private List<String> Exercises;

    public Workouts() {}

    public Workouts(List<String> Exercises) {

        this.Exercises = Exercises;
    }


    public void setExercises(List<String> Exercises) {
        this.Exercises = Exercises;
    }



    public List<String> getExercises() {
        return Exercises;
    }

}

