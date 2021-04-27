package com.project.universitystudentassistant.ui.timetable;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.project.universitystudentassistant.R;
import com.project.universitystudentassistant.adapters.WeekEventAdapter;
import com.project.universitystudentassistant.databinding.FragmentWeekBinding;
import com.project.universitystudentassistant.models.SubjectSchedule;
import com.project.universitystudentassistant.utils.SortManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class WeekFragment extends Fragment {

    private FragmentWeekBinding binding;
    private WeekFragmentViewModel viewModel;
    private Calendar today;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy");

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_week, container, false);
        binding = DataBindingUtil.bind(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(WeekFragmentViewModel.class);
        today = Calendar.getInstance();
        today.setTime(new Date());
        WeekEventAdapter adapter = new WeekEventAdapter();
        binding.weekView.setAdapter(adapter);
        viewModel.getEventData().observe(getViewLifecycleOwner(), new Observer<List<SubjectSchedule>>() {
            @Override
            public void onChanged(List<SubjectSchedule> subjectSchedules) {
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

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.timetable_menu, menu);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Week Schedule");
        MenuItem item = menu.findItem(R.id.menu_today);
        item.setTitle(dateFormat.format(new Date()));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_today) {
            binding.weekView.scrollToDate(today);
        }

        return true;
    }


}