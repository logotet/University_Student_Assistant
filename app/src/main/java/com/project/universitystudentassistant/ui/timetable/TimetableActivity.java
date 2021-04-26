package com.project.universitystudentassistant.ui.timetable;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.project.universitystudentassistant.R;
import com.project.universitystudentassistant.databinding.ActivityTimetableBinding;
import com.project.universitystudentassistant.utils.ViewHelper;


public class TimetableActivity extends AppCompatActivity {

    private ActivityTimetableBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_timetable);
        ViewHelper.setUpToolbar(this, binding.toolbarTimetable);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_timetable);
        NavController navController = navHostFragment.getNavController();

        binding.fabDayView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.fabWeekView.setEnabled(true);
                navController.navigate(R.id.action_week_to_day);
                binding.fabDayView.setEnabled(false);
            }
        });

        binding.fabWeekView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.fabDayView.setEnabled(true);
                navController.navigate(R.id.action_day_to_week);
                binding.fabWeekView.setEnabled(false);
            }
        });

        binding.fabAddScheduleTask.setOnClickListener(view -> {
            opedAddSubject();
        });


    }

    public void opedAddSubject() {
        DialogFragment addScheduleTaskFragment = AddScheduleTaskFragment.newInstance();
        addScheduleTaskFragment.show(getSupportFragmentManager(), "time picker");
    }


}