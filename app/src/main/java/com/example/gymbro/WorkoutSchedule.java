package com.example.gymbro;

import java.util.Calendar;
import java.util.List;

public class WorkoutSchedule {
    private List<Exercise> exerciseList;
    private List<Calendar> schedule;

    public WorkoutSchedule(List<Exercise> exerciseList, List<Calendar> schedule) {
        this.exerciseList = exerciseList;
        this.schedule = schedule;
    }

    @Override
    public String toString() {
        return "WorkoutSchedule{" +
                "exerciseList=" + exerciseList +
                ", schedule=" + schedule.get(0) +
                '}';
    }
}
