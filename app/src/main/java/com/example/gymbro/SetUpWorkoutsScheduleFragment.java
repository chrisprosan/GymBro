package com.example.gymbro;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Calendar;

public class SetUpWorkoutsScheduleFragment extends Fragment {
    WorkoutWizardActivity workoutWizard_context;
    boolean[] days_selected;
    int[] arr_hour_minutes;
    TimePicker time_picker;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        workoutWizard_context = (WorkoutWizardActivity) getActivity();
        if (workoutWizard_context != null) {
            days_selected = workoutWizard_context.getDays_selected();
            arr_hour_minutes = workoutWizard_context.getArr_hour_minutes();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(
                R.layout.fragment_set_up_workouts_schedule, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        time_picker = (TimePicker) view.findViewById(R.id.time_picker);

        Button mMonday = (Button) view.findViewById(R.id.btnMon);
        Button mTuesday = (Button) view.findViewById(R.id.btnTues);
        Button mWednesday = (Button) view.findViewById(R.id.btnWed);
        Button mThursday = (Button) view.findViewById(R.id.btnThurs);
        Button mFriday = (Button) view.findViewById(R.id.btnFri);
        Button mSaturday = (Button) view.findViewById(R.id.btnSat);
        Button mSunday = (Button) view.findViewById(R.id.btnSun);

        int hourOfDay = time_picker.getHour();
        int minute = time_picker.getMinute();

        arr_hour_minutes[0] = hourOfDay;
        arr_hour_minutes[1] = minute;

        time_picker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                arr_hour_minutes[0] = hourOfDay;
                arr_hour_minutes[1] = minute;
            }
        });

        Button[] btnArrl = {mMonday, mTuesday, mWednesday, mThursday, mFriday, mSaturday, mSunday};

        for (int i = 0; i < btnArrl.length; i++) {
            int day_index = i;
            btnArrl[i].setOnClickListener(new View.OnClickListener() {
                final int defaultColor = Color.parseColor("#808080");
                final int activeColor = Color.parseColor("#ff0099cc");
                boolean active = false;
                @Override
                public void onClick(View v) {
                    if (active) {
                        v.setBackgroundColor(defaultColor);
                    } else {
                        v.setBackgroundColor(activeColor);
                    }
                    active = !active;
                    days_selected[day_index] = active;
                }
            });
        }
    }
}
