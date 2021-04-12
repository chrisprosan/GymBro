package com.example.gymbro;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    Button mCreateWorkoutSchedule;
    public GymBroApplication app_context;
    private final List<WorkoutSchedule> workouts = new ArrayList<>();
    ListView list;
    DatabaseReference databaseReference;
    FirebaseUser user;
    FirebaseAuth fAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        app_context = (GymBroApplication) getApplicationContext();
        app_context.home_context = this;
        list = findViewById((R.id.list_view));
        mCreateWorkoutSchedule = (Button) findViewById(R.id.btn_create_workout_schedule);
        user = FirebaseAuth.getInstance().getCurrentUser();
        fAuth = FirebaseAuth.getInstance();
        if (user != null) {
            String uid = user.getUid();
            databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("Workouts");
//            databaseReference.child("username").setValue("Chris");

            WorkoutAdapter adapter = new WorkoutAdapter(HomeActivity.this, workouts);
            list.setAdapter(adapter);

            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(HomeActivity.this, WorkoutActivity.class);
                    intent.putExtra("workoutIndex", (int) l);
                    startActivity(intent);
                }
            });


            mCreateWorkoutSchedule.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                Intent i = new Intent(HomeActivity.this, SetUpWorkouts.class);
//                startActivity(i);
                    Intent i = new Intent(HomeActivity.this, WorkoutWizardActivity.class);
                    startActivity(i);
                }
            });

        }
    }

    public void onLogout(View v) {
        fAuth.signOut();
        finishAffinity();
        startActivity(new Intent(this, MainActivity.class));

    }
    public List<WorkoutSchedule> getWorkouts() {
        return workouts;
    }

    public void test() {
        Log.i("Chris", ""+ workouts.get(0));
    }

    public void setup_alarms(WorkoutSchedule workoutSchedule) {
        List<WorkoutDay> schedule = workoutSchedule.getSchedule();
        app_context.setUpAlarms(schedule);
    }

    public void addWorkout(WorkoutSchedule workoutSchedule) {
//        workouts.add(workoutSchedule);

        String id = databaseReference.push().getKey();
        Task setValueTask = databaseReference.child(id).setValue(workoutSchedule);

        setValueTask.addOnSuccessListener(new OnSuccessListener() {
            @Override
            public void onSuccess(Object o) {
                app_context.showToast("Work added to Firebase.",Toast.LENGTH_LONG);

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                workouts.clear();
                for (DataSnapshot casesSnapshot : dataSnapshot.getChildren()) {
                    Log.e("Oves", casesSnapshot.getValue().toString());
                    WorkoutSchedule ws = casesSnapshot.getValue(WorkoutSchedule.class);
                    workouts.add(ws);

                }

                WorkoutAdapter adapter = new WorkoutAdapter(HomeActivity.this, workouts);
                list.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
