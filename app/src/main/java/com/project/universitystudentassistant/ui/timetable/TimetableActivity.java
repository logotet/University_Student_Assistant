package com.project.universitystudentassistant.ui.timetable;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.islandparadise14.mintable.model.ScheduleEntity;
import com.project.universitystudentassistant.R;
import com.project.universitystudentassistant.adapters.WeekAdapter;
import com.project.universitystudentassistant.databinding.ActivityTimetableBinding;
import com.project.universitystudentassistant.utils.ViewHelper;

import java.util.ArrayList;

public class TimetableActivity extends AppCompatActivity {

    private ActivityTimetableBinding binding;
    private String[] days = new String[]{"Mon", "Tue", "Wen", "Thu", "Fri", "Sat", "Sun"};
    private ArrayList<ScheduleEntity> scheduleEntities = new ArrayList<>();
    private TimetableActivityViewModel viewModel;
    private WeekAdapter weekAdapter;

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
//
//        List<String> collect = Arrays.stream(days).collect(Collectors.toList());
//        weekAdapter = new WeekAdapter(collect);
//        binding.recViewWeeks.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
//        binding.recViewWeeks.setAdapter(weekAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_timetable);

        viewModel = new ViewModelProvider(this).get(TimetableActivityViewModel.class);

        ViewHelper.setUpToolbar(this, binding.toolbarTimetable);


        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_timetable);
        NavController navController = navHostFragment.getNavController();

        binding.fabDayView.setOnClickListener(view -> navController.navigate(R.id.action_week_to_day));

        binding.fabWeekView.setOnClickListener(view -> navController.navigate(R.id.action_day_to_week));


        binding.fabAddScheduleTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment addScheduleTaskFragment = AddScheduleTaskFragment.newInstance();
//                addScheduleTaskFragment.setTargetFragment(targetFragment, 300);
                addScheduleTaskFragment.show(getSupportFragmentManager(), "time picker");

            }
        });

//        ScheduleEntity scheduleEntity = new ScheduleEntity(
//                32, //originId
//                "Database", //scheduleName
//                "IT Building 301", //roomInfo
//                ScheduleDay.TUESDAY, //ScheduleDay object (MONDAY ~ SUNDAY)
//                "8:20", //startTime format: "HH:mm"
//                "10:30", //endTime  format: "HH:mm"
//                "#73fcae68", //backgroundColor (optional)
//                "#000000" //textcolor (optional)
//        );
//
//        scheduleEntities.add(scheduleEntity);

    }


}