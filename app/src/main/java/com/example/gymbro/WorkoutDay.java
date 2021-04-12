package com.example.gymbro;

public class WorkoutDay {
    private int hour;
    private int minute;
    private int day;

    public WorkoutDay(){}

    public WorkoutDay(int hour, int minute, int day) {
        this.hour = hour;
        this.minute = minute;
        this.day = day;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
}
