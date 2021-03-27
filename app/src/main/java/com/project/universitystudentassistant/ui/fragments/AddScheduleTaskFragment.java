package com.project.universitystudentassistant.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;

import com.project.universitystudentassistant.R;
import com.project.universitystudentassistant.models.ScheduleTask;
import com.project.universitystudentassistant.databinding.AddScheduleTaskFragmentBinding;

public class AddScheduleTaskFragment extends DialogFragment {

    private AddScheduleTaskFragmentBinding binding;
    private ScheduleTask scheduleTask;

    @Override
    public void onResume() {
        super.onResume();
        ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
        params.width = ViewGroup.LayoutParams.WRAP_CONTENT;
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        getDialog().getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);
    }

    public static AddScheduleTaskFragment newInstance() {
        return new AddScheduleTaskFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.add_schedule_task_fragment, container, false);
        binding = DataBindingUtil.bind(view);
        return  view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}