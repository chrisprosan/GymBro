package com.example.gymbro;

import java.util.List;
import java.util.Locale;

public class Exercise {

    private String Workout;
    private int sets;
    private int reps;
    private int Duration; // in seconds
    private String Video_Id;
    private List<String> Instructions;

    private static final int DEFAULT_SETS = 3;
    private static final int DEFAULT_REPS = 8;

    public Exercise() {
        this.sets = DEFAULT_SETS;
        if (this.Duration == 0) {  // Rep-based exercises
            this.reps = DEFAULT_REPS;
        } else {  // Time-based exercises
            this.reps = 0;
        }
    }

    public Exercise(String Workout, int Duration, String Video_Id, List<String> Instructions) {
        this.Workout = Workout;
        this.Duration = Duration;
        this.Video_Id = Video_Id;  // Can be null
        this.Instructions = Instructions;
    }


    public String returnFormattedCues() {
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

    public void setVideo_Id(String Video_id) {
        this.Video_Id = Video_id;
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

    public String getVideo_Id() { return Video_Id; }

    public List<String> getInstructions() {
        return Instructions;
    }

    @Override
    public String toString() {
        return "Exercise{" +
                "Workout='" + Workout + '\'' +
                ", sets=" + sets +
                ", reps=" + reps +
                ", Duration=" + Duration +
                ", Video_id='" + Video_Id + '\'' +
                ", Instructions=" + Instructions +
                '}';
    }
}
