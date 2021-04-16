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
import androidx.recyclerview.widget.LinearLayoutManager;

import com.project.universitystudentassistant.R;
import com.project.universitystudentassistant.adapters.SubjectAdapter;
import com.project.universitystudentassistant.databinding.FragmentDayBinding;
import com.project.universitystudentassistant.models.Subject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;


public class DayFragment extends Fragment implements SubjectAdapter.SubjectHolder.OnSubjectClickedListener {

    private FragmentDayBinding binding;
    private List<Subject> subjects = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
    }

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

        subjects = Subject.getDummySubjectData();
        SubjectAdapter subjectAdapter = new SubjectAdapter(subjects, this);
        binding.recViewSubjects.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recViewSubjects.setAdapter(subjectAdapter);

        /* starts before 1 month from now */
        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.MONTH, -1);

        /* ends after 1 month from now */
        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.MONTH, 1);
        HorizontalCalendar horizontalCalendar =  new HorizontalCalendar.Builder(view.getRootView(), R.id.calendarView)
                .range(startDate, endDate)
                .datesNumberOnScreen(5)
                .build();

        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
            @Override
            public void onDateSelected(Calendar date, int position) {
                int i = date.get(Calendar.DATE);
//                Toast.makeText(getContext(), date.toString() + position, Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onSubjectClicked(Subject subject) {
        Toast.makeText(getContext(), subject.getName(), Toast.LENGTH_SHORT).show();
    }
}