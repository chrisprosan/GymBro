package com.example.gymbro;

import java.util.ArrayList;

public class Exercise {
    private final String exerciseName;
    private int sets;
    private int reps;
    private long duration; // in seconds
    private final Instruction instruction;

    public Exercise(String exerciseName, int sets, int reps, long duration, Instruction instruction) {
        this.exerciseName = exerciseName;
        this.sets = sets;
        this.reps = reps;
        this.duration = duration;
        this.instruction = instruction;
    }

    public static class Instruction{
        private final ArrayList<String> steps;

        public Instruction(ArrayList<String> steps) {
            this.steps = steps;
        }

        public ArrayList<String> getSteps() {
            return steps;
        }
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

    public Instruction getInstruction() {
        return instruction;
    }

}
