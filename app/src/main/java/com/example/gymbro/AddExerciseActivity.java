package com.example.gymbro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exercise);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        list = findViewById((R.id.list_view));
        exerciseList = new ArrayList<>();


    }

    public void onFind(View view) {

        exercise_edit = findViewById(R.id.exercise);
        exercise = exercise_edit.getText().toString().trim();
//        String output = exercise.substring(0, 1).toUpperCase() + exercise.substring(1);

        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);



        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                exerciseList.clear();
                for (DataSnapshot casesSnapshot: dataSnapshot.getChildren()) {
                    if (String.valueOf(casesSnapshot).contains("Workout=" + exercise)) {
                        Map<String, String> workout = (Map<String, String>) casesSnapshot.getValue();
                        String name = workout.get("Workout");
                        String step1 = workout.get("Step 1");
                        String step2 = workout.get("Step 2");
                        String step3 = workout.get("Step 3");
                        String step4 = workout.get("Step 4");
                        String step5 = workout.get("Step 5");
                        String step6 = workout.get("Step 6");
                        ArrayList<String> instructions = new ArrayList<>();
                        instructions.add(step1);
                        instructions.add(step2);
                        instructions.add(step3);
                        instructions.add(step4);
                        instructions.add(step5);
                        instructions.add(step6);

//                        int sets = workout.get("Sets");
//                        int reps = workout.get("Reps");
//                        long duration = workout.get("Duration");
                        String vidId = workout.get("Vid Id");


                        Exercise exercise = new Exercise(name, vidId, instructions);

                        exerciseList.add(exercise);

                    }


                }
                if (exerciseList.size() == 0) {
                    Toast.makeText(AddExerciseActivity.this, "No exercise was found. ", Toast.LENGTH_LONG).show();
                }

                ExerciseAdapter adapter = new ExerciseAdapter(AddExerciseActivity.this, exerciseList);
                list.setAdapter(adapter);
                progressBar.setVisibility(View.INVISIBLE);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


}