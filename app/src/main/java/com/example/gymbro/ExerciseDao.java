package com.example.gymbro;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.gymbro.ExerciseDB;

import java.util.List;

@Dao
public interface ExerciseDao {

    @Query("SELECT * FROM EXERCISE ORDER BY ID")
    List<ExerciseDB> loadAllExercises();

    @Insert
    void insertExercise(ExerciseDB exerciseDB);

    @Insert
    void insertAllExercises(List<ExerciseDB> exerciseDBList);

    @Update
    void updateExercise(ExerciseDB exerciseDB);

    @Delete
    void delete(ExerciseDB exerciseDB);

    @Delete
    void deleteAll(List<ExerciseDB> exerciseDBList);

    //random comment
    @Query("SELECT * FROM EXERCISE WHERE id = :id")
    ExerciseDB loadExerciseById(int id);
}
