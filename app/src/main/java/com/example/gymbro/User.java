package com.example.gymbro;

import java.util.ArrayList;
import java.util.List;

public class User {

    private String userId;
    private List<WorkoutSchedule> workouts;

    public User(){}

    public User(String userId, List<WorkoutSchedule> workouts) {
        this.userId = userId;
        this.workouts = workouts;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<WorkoutSchedule> getWorkouts() {
        return workouts;
    }

    public void setWorkouts(List<WorkoutSchedule> workouts) {
        this.workouts = workouts;
    }
}
