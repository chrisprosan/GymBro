package com.example.gymbro;

import java.util.Calendar;
import java.util.List;

public class WorkoutSchedule {
    private List<Exercise> exerciseList;
    private List<WorkoutDay> schedule;
    private String workoutName;

    public WorkoutSchedule(){}

    public WorkoutSchedule(String workoutName, List<Exercise> exerciseList, List<WorkoutDay> schedule) {
        this.workoutName = workoutName;
        this.exerciseList = exerciseList;
        this.schedule = schedule;
    }

    public List<Exercise> getExerciseList() {
        return exerciseList;
    }

    public List<WorkoutDay> getSchedule() {
        return schedule;
    }

    public String getWorkoutName() {
        return workoutName;
    }

    public void setExerciseList(List<Exercise> exerciseList) {
        this.exerciseList = exerciseList;
    }

    public void setSchedule(List<WorkoutDay> schedule) {
        this.schedule = schedule;
    }

    public void setWorkoutName(String workoutName) {
        this.workoutName = workoutName;
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
