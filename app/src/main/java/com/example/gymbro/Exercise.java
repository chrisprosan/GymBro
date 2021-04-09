package com.example.gymbro;

import java.util.List;
import java.util.Locale;

public class Exercise {

    private String Workout;
    private int sets;
    private int reps;
    private int Duration; // in seconds
    private String Video_id;
    private List<String> Instructions;

    private static final int DEFAULT_SETS = 3;
    private static final int DEFAULT_REPS = 8;

    public Exercise() {}

    public Exercise(String Workout, int Duration, String Video_id, List<String> Instructions) {
        this.Workout = Workout;
        this.sets = DEFAULT_SETS;
        this.Duration = Duration;
        if (this.Duration == 0) {  // Rep-based exercises
            this.reps = DEFAULT_REPS;
        } else {  // Time-based exercises
            this.reps = 0;
        }
        this.Video_id = Video_id;  // Can be null
        this.Instructions = Instructions;
    }


    public String getCues() {
        StringBuilder formattedCues = new StringBuilder();
        int index = 0;
        for (String cue : this.Instructions) {
            index++;
            formattedCues.append(String.format(Locale.getDefault(), "%d: %s\n\n", index, cue));
        }

        return formattedCues.toString();
    }

    public void setWorkout(String Workout) {
        this.Workout = Workout;
    }

    public void setInstructions(List<String> Instructions) {
        this.Instructions = Instructions;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public void setDuration(int Duration) {
        this.Duration = Duration;
    }

    public void setVideo_id(String Video_id) {
        this.Video_id = Video_id;
    }

    public String getWorkout() {
        return Workout;
    }

    public int getSets() {
        return sets;
    }

    public int getReps() {
        return reps;
    }

    public long getDuration() {
        return Duration;
    }

    public String getVideo_id() { return Video_id; }

    public List<String> getInstructions() {
        return Instructions;
    }

}
