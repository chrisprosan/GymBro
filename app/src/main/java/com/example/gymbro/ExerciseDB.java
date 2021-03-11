package com.example.gymbro;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.LineNumberInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


@Entity(tableName = "exercise")
public class ExerciseDB {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String workoutName;
    public int sets;
    public int reps;
    public long duration;
    public String imgId;
    public String vidId;
    public ArrayList<String> exercisesInstr;

    @Ignore
    public ExerciseDB(String workoutName, int sets, int reps, long duration, String imgId, String vidId, ArrayList<String> exercisesInstr) {
        this.workoutName = workoutName;
        this.sets = sets;
        this.reps = reps;
        this.duration = duration;
        this.imgId = imgId;
        this.vidId = vidId;
        this.exercisesInstr = exercisesInstr;
    }

    public ExerciseDB(String workoutName, int sets, int reps, long duration, String imgId, ArrayList<String> exercisesInstr) {
        this.workoutName = workoutName;
        this.sets = sets;
        this.reps = reps;
        this.duration = duration;
        this.imgId = imgId;
        this.exercisesInstr = exercisesInstr;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String workoutName() {
        return workoutName;
    }

    public void setName(String workoutName) {
        this.workoutName = workoutName;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getImgId() {
        return imgId;
    }

    public void setImgId(String imgId) {
        this.imgId = imgId;
    }

    public ArrayList<String> exercisesInstr() {
        return exercisesInstr;
    }


    public String toString() {
        return String.format(Locale.getDefault(), "Workout name: %s\n" +
                            "Recommended sets: %d\n" +
                            "Recommended reps: %d\n" +
                            "Duration: %d\n" +
                            "Image ID: %s\n" +
                            "Instructions: %s ", workoutName, sets, reps, duration, imgId, exercisesInstr
        );
    }
}