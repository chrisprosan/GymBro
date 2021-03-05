package com.example.gymbro;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Fts4;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.Locale;

@Fts4
@Entity(tableName = "exercise")
public class Exercise {

    @PrimaryKey
    public int id;

    @ColumnInfo(name = "exercise_name")
    public final String exerciseName;

    @ColumnInfo(name = "sets")
    public int sets;

    @ColumnInfo(name = "reps")
    public int reps;

    @ColumnInfo(name = "duration")
    public long duration; // in seconds

    @ColumnInfo(name = "vidId")
    public String vidId;

    @ColumnInfo(name = "instruction")
    public final Instruction instruction;

    public Exercise(String exerciseName, int sets, int reps, long duration, String vidId, Instruction instruction) {
        this.exerciseName = exerciseName;
        this.sets = sets;
        this.reps = reps;
        this.duration = duration;
        this.vidId = vidId;
        this.instruction = instruction;
    }

    public static class Instruction{
        private final ArrayList<String> cues;

        public Instruction(ArrayList<String> cues) {
            this.cues = cues;
        }

        public ArrayList<String> getCues() {
            return cues;
        }
    }

    public String getCues() {
        StringBuilder formattedCues = new StringBuilder();
        int index = 0;
        for (String cue : this.instruction.getCues()) {
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

    public Instruction getInstruction() {
        return instruction;
    }

}
