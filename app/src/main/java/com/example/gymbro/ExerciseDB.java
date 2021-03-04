package com.example.gymbro;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "exercise")
public class ExerciseDB {
    @PrimaryKey(autoGenerate = true)
    int id;
    String workoutName;
    int sets;
    int reps;
    long duration;
    String imgId;

    @Ignore
    public ExerciseDB(String workoutName, int sets, int reps, long duration, String imgId) {
        this.workoutName = workoutName;
        this.sets = sets;
        this.reps = reps;
        this.duration = duration;
        this.imgId = imgId;
    }

    public ExerciseDB(int id, String workoutName, int sets, int reps, long duration, String imgId) {
        this.id = id;
        this.workoutName = workoutName;
        this.sets = sets;
        this.reps = reps;
        this.duration = duration;
        this.imgId = imgId;
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
}