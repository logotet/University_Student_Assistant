package com.project.universitystudentassistant.ui.timetable;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.islandparadise14.mintable.model.ScheduleDay;
import com.islandparadise14.mintable.model.ScheduleEntity;
import com.islandparadise14.mintable.tableinterface.OnScheduleClickListener;
import com.project.universitystudentassistant.ui.university.Fragments.AddScheduleTaskFragment;
import com.project.universitystudentassistant.R;
import com.project.universitystudentassistant.databinding.ActivityTimetableBinding;
import com.project.universitystudentassistant.utils.ViewHelper;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class TimetableActivity extends AppCompatActivity {
//
    private  ActivityTimetableBinding binding;




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
                navController.navigate(R.id.action_week_to_day);
            }
        });

        binding.fabWeekView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_day_to_week);
            }
        });






        binding.fabAddScheduleTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment addScheduleTaskFragment = AddScheduleTaskFragment.newInstance();
//                addScheduleTaskFragment.setTargetFragment(targetFragment, 300);
                addScheduleTaskFragment.show(getSupportFragmentManager(), "time picker");
            }
        });

    }

//    private void openFragment(FragmentManager fm, Fragment targetFragment, Fragment fragment) {
//        fragment.setTargetFragment(targetFragment, 300);
//        FragmentTransaction transaction = fm.beginTransaction();
//        transaction.replace(R.id.fragment_container, fragment);
//        transaction.addToBackStack(null);
//        transaction.commit();
//    }

    public void openTimeDialogFragment(FragmentManager fm, Fragment targetFragment, int viewId) {
        DialogFragment addScheduleTaskFragment = AddScheduleTaskFragment.newInstance();
        addScheduleTaskFragment.setTargetFragment(targetFragment, 300);
        addScheduleTaskFragment.show(fm, "time picker");
    }
}