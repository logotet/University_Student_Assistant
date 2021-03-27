package com.project.universitystudentassistant.ui.activities;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;

import com.islandparadise14.mintable.model.ScheduleEntity;
import com.project.universitystudentassistant.R;
import com.project.universitystudentassistant.databinding.ActivityTimetableBinding;
import com.project.universitystudentassistant.ui.fragments.AddScheduleTaskFragment;

import java.util.ArrayList;

public class TimetableActivity extends AppCompatActivity {
//
    private  ActivityTimetableBinding binding;
    private String[] days = new String[]{"Mon", "Tue", "Wen", "Thu", "Fri"};
    private ArrayList<ScheduleEntity> scheduleEntities = new ArrayList<>();

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        binding.table.baseSetting(30, 20, 70); //default (20, 30, 50)
        binding.table.initTable(days);
        binding.table.updateSchedules(scheduleEntities);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_timetable);


        binding.table.setOnScheduleClickListener(scheduleEntity -> Toast.makeText(TimetableActivity.this, scheduleEntity.getScheduleName(), Toast.LENGTH_SHORT).show());


        binding.fabAddScheduleTask.setOnClickListener(view -> {
            DialogFragment addScheduleTaskFragment = AddScheduleTaskFragment.newInstance();
            addScheduleTaskFragment.show(getSupportFragmentManager(), "time picker");
        });

    }


}