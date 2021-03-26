package com.logotet.universitystudentassistant;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.github.tlaabs.timetableview.Schedule;
import com.github.tlaabs.timetableview.Time;
import com.github.tlaabs.timetableview.TimetableView;
import com.islandparadise14.mintable.model.ScheduleDay;
import com.islandparadise14.mintable.model.ScheduleEntity;
import com.islandparadise14.mintable.tableinterface.OnScheduleClickListener;
import com.islandparadise14.mintable.tableinterface.OnTimeCellClickListener;
import com.logotet.universitystudentassistant.databinding.ActivityTimetableBinding;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

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

        ScheduleEntity scheduleEntity = new ScheduleEntity(
                32, //originId
                "Database", //scheduleName
                "IT Building 301", //roomInfo
                ScheduleDay.TUESDAY,
                "8:20", //startTime format: "HH:mm"
                "10:30", //endTime  format: "HH:mm"
                "#73fcae68", //backgroundColor (optional)
                "#000000" //textcolor (optional)//ScheduleDay object
        );

        scheduleEntities.add(scheduleEntity);

        binding.table.setOnScheduleClickListener(new OnScheduleClickListener() {
            @Override
            public void scheduleClicked(@NotNull ScheduleEntity scheduleEntity) {
                Toast.makeText(TimetableActivity.this, scheduleEntity.getScheduleName(), Toast.LENGTH_SHORT).show();
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