package com.project.universitystudentassistant.ui.timetable;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.project.universitystudentassistant.models.SubjectTime;

import java.util.List;

public class AddScheduleFragmentViewModel extends ViewModel {

    private MutableLiveData<List<SubjectTime>> week;

    public AddScheduleFragmentViewModel() {
        week = new MutableLiveData<>();
        week.setValue(SubjectTime.getDummySubjectData());
    }

    public LiveData<List<SubjectTime>> getWeek() {
        return week;
    }

    public void setWeek(List<SubjectTime> week) {
        this.week.setValue(week);
    }
}
