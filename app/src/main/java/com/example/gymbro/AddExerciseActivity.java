package com.example.gymbro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AddExerciseActivity extends AppCompatActivity {
    DatabaseReference databaseReference;
    EditText exercise_edit;
    String exercise;
    ListView list;
    List<Exercise> exerciseList;
    ProgressBar progressBar;
    GymBroApplication appContext;
    SetUpWorkouts setUpWorkoutsContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exercise);
        appContext = (GymBroApplication) getApplicationContext();

        databaseReference = FirebaseDatabase.getInstance().getReference();
        list = findViewById((R.id.list_view));
        exerciseList = new ArrayList<>();

    }


    public void onFind(View view) {

        exercise_edit = findViewById(R.id.exercise);
        exercise = exercise_edit.getText().toString().trim();
        String output = exercise.substring(0, 1).toUpperCase() + exercise.substring(1);

        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                exerciseList.clear();
                for (DataSnapshot casesSnapshot : dataSnapshot.getChildren()) {
                    if (String.valueOf(casesSnapshot).contains(output)) {
                        Exercise exercise = dataSnapshot.getValue(Exercise.class);

                        exerciseList.add(exercise);


                    }

                }
                if (exerciseList.size() == 0) {
                    Toast.makeText(AddExerciseActivity.this, "No exercise was found. ", Toast.LENGTH_LONG).show();
                }

                ExerciseAdapter adapter = new ExerciseAdapter(AddExerciseActivity.this, exerciseList);
                list.setAdapter(adapter);
                progressBar.setVisibility(View.INVISIBLE);


                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Exercise e = exerciseList.get((int) l);


                        TextView txtview = view.findViewById(R.id.ExerciseName);
                        String name = txtview.getText().toString();
                        appContext.setup_workouts.exerciseNames.add(e);
                        finish();
                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


}
