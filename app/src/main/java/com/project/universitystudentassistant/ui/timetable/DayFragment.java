package com.project.universitystudentassistant.ui.timetable;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.project.universitystudentassistant.R;
import com.project.universitystudentassistant.adapters.SubjectAdapter;
import com.project.universitystudentassistant.databinding.FragmentDayBinding;
import com.project.universitystudentassistant.models.Subject;
import com.project.universitystudentassistant.utils.SortManager;

import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;


public class DayFragment extends Fragment implements SubjectAdapter.SubjectHolder.OnSubjectClickedListener {

    private FragmentDayBinding binding;
    private List<Subject> subjects = new ArrayList<>();
    private DayFragmentViewModel viewModel;
    private DayOfWeek dayOfWeek;
    private SubjectAdapter subjectAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_day, container, false);
        binding = DataBindingUtil.bind(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(DayFragmentViewModel.class);

        /* starts before 1 month from now */
        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.MONTH, -1);

        /* ends after 1 month from now */
                Calendar today = Calendar.getInstance();
        today.add(Calendar.DAY_OF_YEAR, 1);
        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.MONTH, 1);
        HorizontalCalendar horizontalCalendar =  new HorizontalCalendar.Builder(view.getRootView(), R.id.calendarView)
                .defaultSelectedDate(today)
                .range(startDate, endDate)
                .datesNumberOnScreen(5)
                .build();
//        Calendar today = Calendar.getInstance();
//        today.add(Calendar.DAY_OF_YEAR, 1);
//        horizontalCalendar.selectDate(today, true);
//        horizontalCalendar.
        int i = horizontalCalendar.getSelectedDate().get(Calendar.DAY_OF_WEEK);
        try {
            dayOfWeek = DayOfWeek.of(i - 1);
        }catch (DateTimeException e){
            dayOfWeek = DayOfWeek.of(7);
        }

        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
            @Override
            public void onDateSelected(Calendar date, int position) {
                int i = date.get(Calendar.DAY_OF_WEEK);
                try {
                    dayOfWeek = DayOfWeek.of(i - 1);
                }catch (DateTimeException e){
                   dayOfWeek = DayOfWeek.of(7);
                }
                Toast.makeText(getContext(), dayOfWeek.toString(), Toast.LENGTH_SHORT).show();
                viewModel.getAllSubjects().observe(getViewLifecycleOwner(), new Observer<List<Subject>>() {
                    @Override
                    public void onChanged(List<Subject> subjects) {
                        SortManager sortManager = new SortManager();
                        List<Subject> subjectsByWeekDay = sortManager.getSubjectsByWeekDay(subjects, dayOfWeek);
                        subjectAdapter.updateData(subjectsByWeekDay);
                    }
                });
            }
        });


        subjectAdapter = new SubjectAdapter(subjects, this);
        binding.recViewSubjects.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recViewSubjects.setAdapter(subjectAdapter);

        viewModel.getAllSubjects().observe(getViewLifecycleOwner(), new Observer<List<Subject>>() {
            @Override
            public void onChanged(List<Subject> subjects) {
                SortManager sortManager = new SortManager();
                List<Subject> subjectsByWeekDay = sortManager.getSubjectsByWeekDay(subjects, dayOfWeek);
                subjectAdapter.updateData(subjectsByWeekDay);
            }
        });



    }

    @Override
    public void onSubjectClicked(Subject subject) {
        Toast.makeText(getContext(), subject.getName(), Toast.LENGTH_SHORT).show();
    }
}