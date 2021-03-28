package com.logotet.universitystudentassistant.ui.Fragments.SearchUniversity;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.logotet.universitystudentassistant.data.Repository;
import com.logotet.universitystudentassistant.data.entities.UniversityEntity;
import com.logotet.universitystudentassistant.data.entities.UniversityEntityPrep;

import java.util.List;

public class SearchUniversityViewModel extends AndroidViewModel {

    private Repository repository;

    public SearchUniversityViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application.getApplicationContext());
    }

    public LiveData<List<UniversityEntityPrep>> getUniversitiesList() {
        return repository.getAllPrepUniversities();
    }

    public void insertUniversity(UniversityEntity entity){
        repository.insertUniversityToRoomDb(entity);
    }


}