package com.example.gymbro;

import java.util.Locale;

public class Exercise {

    public int id;
    public final String exerciseName;
    public int sets;
    public int reps;
    public long duration; // in seconds
    public String video_id;
    public final String[] instructions;

    private static final int DEFAULT_SETS = 3;
    private static final int DEFAULT_REPS = 8;

    public Exercise(String exerciseName, long duration, String video_id, String[] instructions) {
        this.exerciseName = exerciseName;
        this.sets = DEFAULT_SETS;
        this.duration = duration;
        if (this.duration == 0) {  // Rep-based exercises
            this.reps = DEFAULT_REPS;
        } else {  // Time-based exercises
            this.reps = 0;
        }
        this.video_id = video_id;  // Can be null
        this.instructions = instructions;
    }


    public String getCues() {
        StringBuilder formattedCues = new StringBuilder();
        int index = 0;
        for (String cue : this.instructions) {
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

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public void setVideo_id(String video_id) {
        this.video_id = video_id;
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

    public String getVideo_id() { return video_id; }

    public String[] getInstructions() {
        return instructions;
    }

}
