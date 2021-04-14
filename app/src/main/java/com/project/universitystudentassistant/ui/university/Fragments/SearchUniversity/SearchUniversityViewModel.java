package com.project.universitystudentassistant.ui.university.Fragments.SearchUniversity;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.project.universitystudentassistant.data.Repository;
import com.project.universitystudentassistant.data.entities.UniversityEntity;

import java.util.List;

public class SearchUniversityViewModel extends AndroidViewModel {

    private Repository repository;

    public SearchUniversityViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application.getApplicationContext());
    }

    public LiveData<List<UniversityEntity>> getUniversitiesList() {
        return repository.getAllPrepUniversities();
    }

    public LiveData<List<UniversityEntity>> getUniversitiesListA(String...states) {
        if(states.length > 0) {
            return repository.getPrepUniversitiesByStates(states);
        }else {
            return repository.getAllPrepUniversities();
        }
    }

    public void insertUniversity(UniversityEntity entity){
        repository.insertUniveristy(entity);
    }

    public void updateUniversity(UniversityEntity entity){
        repository.updateUniversity(entity);
    }


    public LiveData<Boolean> isUniSaved(String name){
        return repository.checkIfUniExists(name);
    }


}