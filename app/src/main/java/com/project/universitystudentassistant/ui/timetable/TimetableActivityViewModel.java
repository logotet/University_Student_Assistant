package com.project.universitystudentassistant.ui.timetable;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.project.universitystudentassistant.data.Repository;
import com.project.universitystudentassistant.models.SubjectSchedule;

import java.util.List;

public class TimetableActivityViewModel extends AndroidViewModel {
    private Repository repository;

    public TimetableActivityViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application.getApplicationContext());
    }

    public LiveData<List<SubjectSchedule>> getEventData() {
        return repository.getAllSubjectsSchedule();
    }
}
