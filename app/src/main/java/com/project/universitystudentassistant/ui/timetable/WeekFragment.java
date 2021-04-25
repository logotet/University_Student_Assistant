package com.project.universitystudentassistant.ui.timetable;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.alamkanak.weekview.WeekViewEvent;
import com.jakewharton.threetenabp.AndroidThreeTen;
import com.project.universitystudentassistant.R;
import com.project.universitystudentassistant.adapters.MyAdapter;
import com.project.universitystudentassistant.databinding.FragmentWeekBinding;
import com.project.universitystudentassistant.models.SubjectSchedule;
import com.project.universitystudentassistant.utils.SortManager;

import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalTime;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import de.tobiasschuerg.weekview.data.Event;
import de.tobiasschuerg.weekview.data.WeekData;


public class WeekFragment extends Fragment {

    private FragmentWeekBinding binding;
    private List<WeekViewEvent> events = new ArrayList<>();
    private WeekFragmentViewModel viewModel;
    private boolean focus;


    @Override
    public void onResume() {
        super.onResume();
        focus = getView().requestFocus();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_week, container, false);
        binding = DataBindingUtil.bind(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(WeekFragmentViewModel.class);
        MyAdapter adapter = new MyAdapter();
        binding.weekView.setAdapter(adapter);
        viewModel.getEventData().observe(getViewLifecycleOwner(), new Observer<List<SubjectSchedule>>() {
            @Override
            public void onChanged(List<SubjectSchedule> subjectSchedules) {
//                adapter.submitList(subjectSchedules);
                SortManager sortManager = new SortManager();
                List<SubjectSchedule> subjectsWithDates = new ArrayList<>();
                for (SubjectSchedule subject :
                        subjectSchedules) {
                    subjectsWithDates.addAll(sortManager.getSubjectsWithDates(subject));
                }
                adapter.submitList(subjectsWithDates);
            }
        });


    }


}