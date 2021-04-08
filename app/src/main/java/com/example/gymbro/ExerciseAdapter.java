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

public class ExerciseAdapter extends ArrayAdapter<Exercise> {

    private Activity context;
    private List<Exercise> exerciseList;

    public ExerciseAdapter(Activity context, List<Exercise> exerciseList) {
        super(context, R.layout.list_layout, exerciseList);
        this.context = context;
        this.exerciseList = exerciseList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_layout, null, true);

        TextView name = listViewItem.findViewById(R.id.ExerciseName);


        Exercise exercises = exerciseList.get(position);
        String exercise_list_item = String.format(Locale.getDefault(),"%d. %s", (position + 1), exercises.getWorkout());
        name.setText(exercise_list_item);


        return listViewItem;
    }

}

