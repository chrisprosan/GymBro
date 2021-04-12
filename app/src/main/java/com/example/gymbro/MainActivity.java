package com.example.gymbro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth fAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fAuth = FirebaseAuth.getInstance();
        if (fAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
            finish();
        }

        progressBar = findViewById(R.id.main_progressBar);
    }


    public void onSignUp(View v) {
        EditText email_edit = findViewById(R.id.editEmail);
        EditText pass_edit = findViewById(R.id.editPassword);
        String email = email_edit.getText().toString().trim();
        String pass = pass_edit.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            email_edit.setError("Email is required");
            return;
        }

        if (TextUtils.isEmpty(pass)) {
            email_edit.setError("Password is required");
            return;
        }

        if (pass.length() < 6) {
            email_edit.setError("Password must be >= 6 characters.");
            return;
        }

        progressBar.setVisibility(View.VISIBLE);


        //register user
        fAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "User Created", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                } else {
                    Toast.makeText(MainActivity.this, "ERROR: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });


    }

    public void onLogin(View v) {
        EditText email_edit = findViewById(R.id.editEmail);
        EditText pass_edit = findViewById(R.id.editPassword);
        String email = email_edit.getText().toString().trim();
        String pass = pass_edit.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            email_edit.setError("Email is required");
            return;
        }

        if (TextUtils.isEmpty(pass)) {
            pass_edit.setError("Password is required");
            return;
        }

        if (pass.length() < 6) {
            pass_edit.setError("Password must be >= 6 characters.");
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        //authenticate user
        fAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(MainActivity.this, "Login successful!", Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
            } else {
                Toast.makeText(MainActivity.this, "ERROR: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.GONE);
            }
        });

    }
}

