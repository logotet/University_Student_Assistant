package com.project.universitystudentassistant.ui.timetable;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.project.universitystudentassistant.data.Repository;
import com.project.universitystudentassistant.models.Subject;
import com.project.universitystudentassistant.utils.SortManager;

import java.time.DayOfWeek;
import java.util.List;

public class DayFragmentViewModel extends AndroidViewModel {

    private Repository repository;
    private MutableLiveData<List<Subject>> subjectsByWeekDay;
    private SortManager sortManager;
    public DayFragmentViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application.getApplicationContext());
        sortManager = new SortManager();
        subjectsByWeekDay = new MutableLiveData<>();
    }

    public LiveData<List<Subject>> getAllSubjects(){
        return repository.getAllSubjects();
    }

    public LiveData<List<Subject>> getSubjectsByWeekDay() {
        return subjectsByWeekDay;
    }

    public void setSubjectsByWeekDay(DayOfWeek dayOfWeek) {
        this.subjectsByWeekDay.setValue(sortManager.getSubjectsByWeekDay(repository.getAllSubjects().getValue(), dayOfWeek));
    }
}
