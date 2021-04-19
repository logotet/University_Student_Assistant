package com.project.universitystudentassistant.ui.timetable;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.project.universitystudentassistant.R;
import com.project.universitystudentassistant.adapters.WeekPickerAdapter;
import com.project.universitystudentassistant.databinding.AddScheduleTaskFragmentBinding;
import com.project.universitystudentassistant.models.Subject;
import com.project.universitystudentassistant.models.SubjectTime;
import com.project.universitystudentassistant.ui.university.Fragments.Filter.FilterFragmentViewModel;
import com.project.universitystudentassistant.ui.university.Fragments.SearchUniversity.SearchUniversityFragment;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddScheduleTaskFragment extends DialogFragment implements WeekPickerAdapter.OnWeekPickerClickListener,
        TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {

    private AddScheduleTaskFragmentBinding binding;
    private Subject subject;
    private List<SubjectTime> days = new ArrayList<>();
    private boolean startHour;
    private DayOfWeek day;
    private WeekPickerAdapter weekPickerAdapter;
    private AddScheduleFragmentViewModel addSUbjectViewModel;
    private Map<DayOfWeek, SubjectTime> activeDays= new HashMap<>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.MY_DIALOG);
    }

    @Override
    public void onResume() {
        super.onResume();
        ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = ViewGroup.LayoutParams.MATCH_PARENT;
        getDialog().getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);
    }

    public static AddScheduleTaskFragment newInstance() {
        return new AddScheduleTaskFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_schedule_task_fragment, container, false);
        binding = DataBindingUtil.bind(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.toolbarAddSubject.inflateMenu(R.menu.filter_menu);

        addSUbjectViewModel = new ViewModelProvider(this).get(AddScheduleFragmentViewModel.class);

        weekPickerAdapter = new WeekPickerAdapter(this, days);
        binding.recViewWeekPicker.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recViewWeekPicker.setAdapter(weekPickerAdapter);

        addSUbjectViewModel.getWeek().observe(getViewLifecycleOwner(), new Observer<List<SubjectTime>>() {
            @Override
            public void onChanged(List<SubjectTime> subjectTimes) {
                days = subjectTimes;
                weekPickerAdapter.updateData(days);
            }
        });
//        TODO same hours for every day option

        subject = new Subject();

        binding.switchRepeating.setOnCheckedChangeListener((compoundButton, b) -> setRepeatingVisibilty(b));
//        days = SubjectTime.getDummySubjectData();


        binding.txtDate.setOnClickListener(view1 -> {
            FragmentManager fm = getActivity().getSupportFragmentManager();
            DialogFragment datePicker = new DateDialogFragment();
            datePicker.setTargetFragment(this, 300);
            datePicker.show(fm, "date picker");
        });


        binding.toolbarAddSubject.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.menu_close) {
                    dismiss();
                }
                if (item.getItemId() == R.id.menu_apply) {
                    dismiss();
                                saveSubject();
                }
                return true;
            }
        });
    }

    private void setRepeatingVisibilty(boolean repeating) {
        if (repeating) {
            binding.txtDaysOfWeek.setVisibility(View.VISIBLE);
            binding.txtDate.setVisibility(View.GONE);
            binding.recViewWeekPicker.setVisibility(View.VISIBLE);
        } else {
            binding.txtDate.setVisibility(View.VISIBLE);
            binding.txtDaysOfWeek.setVisibility(View.GONE);
            binding.recViewWeekPicker.setVisibility(View.GONE);
        }
    }

    private void saveSubject() {
        String subjectName = "";
        String subjectTeacher = "";
        String subjectLocation = "";
//        if (binding.edtSubjectName.getText() == null) {
//            binding.edtSubjectName.setError(getString(R.string.string_subject_name_is_empty));
//        } else {
//            subjectName = binding.edtSubjectName.getText().toString().trim();
//        }
//        if (binding.edtTeacherName.getText() == null) {
//            subjectTeacher = "";
//        } else {
//            subjectTeacher = binding.edtTeacherName.getText().toString().trim();
//        }
//        if (binding.edtRoomInfo.getText() == null) {
//            subjectLocation = "";
//        } else {
//            subjectLocation = binding.edtRoomInfo.getText().toString().trim();
//        }
        subject.setName(subjectName);
        subject.setTeacher(subjectTeacher);
        subject.setLocation(subjectLocation);
        activeDays = Subject.createWeekMap(days);
        subject.setWeekMap(activeDays);
    }

    @Override
    public void onWeekDayChecked(SubjectTime subjectTime, boolean checked) {
        day = subjectTime.getDayOfWeek();
//        SubjectTime subjectTime = days.stream().filter(time -> time.getDayOfWeek() == day).
//                findFirst().orElse(null);
//        subjectTime.setActive(checked);
//        addSUbjectViewModel.setWeek(days);
    }

    @Override
    public void onHourClicked(SubjectTime subjectTime, boolean startHour) {
        this.startHour = startHour;
        day = subjectTime.getDayOfWeek();
        FragmentManager fm = getActivity().getSupportFragmentManager();
        DialogFragment timePicker = TimeDialogFragment.newInstance();
        timePicker.setTargetFragment(this, 300);
        timePicker.show(fm, "time picker");
    }


    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {
        SubjectTime subjectTime = days.stream().filter(time -> time.getDayOfWeek() == day).
                findFirst().orElse(null);
//        subjectTime.setActive(true);
        if(startHour) {
            subjectTime.setStartHour(LocalTime.of(i, i1));
        }else {
            subjectTime.setEndHour(LocalTime.of(i, i1));
        }
        addSUbjectViewModel.setWeek(days);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

    }

}