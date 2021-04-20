package com.project.universitystudentassistant.ui.timetable;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.project.universitystudentassistant.data.Repository;
import com.project.universitystudentassistant.models.Subject;
import com.project.universitystudentassistant.models.SubjectTime;

import java.util.List;

public class AddScheduleFragmentViewModel extends AndroidViewModel {

    private MutableLiveData<List<SubjectTime>> week;
    private Repository repository;

    public AddScheduleFragmentViewModel(@NonNull Application application) {
        super(application);
        week = new MutableLiveData<>();
        week.setValue(SubjectTime.getDummySubjectData());
        repository = new Repository(application.getApplicationContext());
    }

    public void insertSubject(Subject subject) {
        repository.insertSubject(subject);
    }

    public LiveData<List<SubjectTime>> getWeek() {
        return week;
    }

    public void setWeek(List<SubjectTime> week) {
        this.week.setValue(week);
    }
}
