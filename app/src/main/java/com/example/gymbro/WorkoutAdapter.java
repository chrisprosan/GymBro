package com.example.gymbro;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;



import java.util.List;
import java.util.Locale;

public class WorkoutAdapter extends ArrayAdapter<WorkoutSchedule> {

    private Activity context;
    private List<WorkoutSchedule> workoutsList;

    public WorkoutAdapter(Activity context, List<WorkoutSchedule> workoutsList) {
        super(context, R.layout.list_layout, workoutsList);
        this.context = context;
        this.workoutsList = workoutsList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_layout, null, true);

        TextView name = listViewItem.findViewById(R.id.ExerciseName);


        WorkoutSchedule workouts = workoutsList.get(position);
        name.setText(workouts.getWorkoutName());


        return listViewItem;
    }

}

