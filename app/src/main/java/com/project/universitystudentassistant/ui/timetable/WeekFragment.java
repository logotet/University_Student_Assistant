package com.project.universitystudentassistant.ui.timetable;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.islandparadise14.mintable.model.ScheduleDay;
import com.islandparadise14.mintable.model.ScheduleEntity;
import com.islandparadise14.mintable.tableinterface.OnScheduleClickListener;
import com.project.universitystudentassistant.R;
import com.project.universitystudentassistant.databinding.FragmentWeekBinding;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public class WeekFragment extends Fragment {

    private FragmentWeekBinding binding;
    private String[] days = new String[]{"Mon", "Tue", "Wen", "Thu", "Fri"};
    private ArrayList<ScheduleEntity> scheduleEntities = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_week, container, false);
        binding = DataBindingUtil.bind(view);
        binding.table.baseSetting(30, 20, 70); //default (20, 30, 50)
        binding.table.initTable(days);
        binding.table.updateSchedules(scheduleEntities);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

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
                Toast.makeText(getActivity(), scheduleEntity.getScheduleName(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}