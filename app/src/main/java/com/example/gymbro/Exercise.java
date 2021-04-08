package com.example.gymbro;

import java.util.ArrayList;
import java.util.Locale;

public class Exercise {

    public int id;

    public String exerciseName;

    public int sets;

    public int reps;

    public int duration; // in seconds

    public String vidId;

    public String[] instruction;




    public Exercise() {
    }

    public Exercise(String Workout, int Duration, String Video_id, String[] Instructions) {
        this.exerciseName = Workout;
        this.duration = Duration;
        this.vidId = Video_id;
        this.instruction = Instructions;
    }

    public String getCues() {
        StringBuilder formattedCues = new StringBuilder();
        int index = 0;
        for (String cue : this.instruction) {
            index++;
            formattedCues.append(String.format(Locale.getDefault(), "%d: %s\n\n", index, cue));
        }

        return formattedCues.toString();
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setVidId(String vidId) {
        this.vidId = vidId;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public int getSets() {
        return sets;
    }

    public int getReps() {
        return reps;
    }

    public long getDuration() {
        return duration;
    }

    public String getVidId() { return vidId; }

    public String[] getInstruction() {
        return instruction;
    }

}
