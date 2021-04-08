package com.example.gymbro;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class SetUpWorkoutsFragment extends Fragment {
    Button mAddExercise;
    List<Exercise> exerciseList;
    WorkoutWizardActivity workoutWizard_context;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        workoutWizard_context = (WorkoutWizardActivity) getActivity();
        if (workoutWizard_context != null) {
            exerciseList = workoutWizard_context.getExerciseList();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(
                R.layout.fragment_set_up_workouts, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mAddExercise = (Button) view.findViewById(R.id.btnAddExercise);

        ExerciseAdapter adapter = new ExerciseAdapter(getActivity(), exerciseList);
        ListView listview = (ListView) view.findViewById(R.id.list_view);
        listview.setAdapter(adapter);

        mAddExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), AddExerciseActivity.class);
                startActivity(i);
            }
        });

    }

    public void finish_setup() {

    }
}