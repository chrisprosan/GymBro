package com.example.gymbro;

import java.util.ArrayList;
import java.util.List;

public class User {
    private List<WorkoutSchedule> workouts;

    public User(){}

    public User(List<WorkoutSchedule> workouts) {
        this.workouts = workouts;
    }

    public List<WorkoutSchedule> getWorkouts() {
        return workouts;
    }

    public void setWorkouts(List<WorkoutSchedule> workouts) {
        this.workouts = workouts;
    }
}
