package com.example.gymbro;

import java.util.Calendar;
import java.util.List;

public class WorkoutSchedule {
    private List<Exercise> exerciseList;
    private List<Calendar> schedule;
    private String workoutName;

    public WorkoutSchedule(String workoutName, List<Exercise> exerciseList, List<Calendar> schedule) {
        this.workoutName = workoutName;
        this.exerciseList = exerciseList;
        this.schedule = schedule;
    }

    public List<Exercise> getExerciseList() {
        return exerciseList;
    }

    public List<Calendar> getSchedule() {
        return schedule;
    }

    public String getWorkoutName() {
        return workoutName;
    }

    @Override
    public String toString() {
        return "WorkoutSchedule{" +
                "exerciseList=" + exerciseList +
                ", schedule=" + schedule +
                ", workoutName='" + workoutName + '\'' +
                '}';
    }
}
