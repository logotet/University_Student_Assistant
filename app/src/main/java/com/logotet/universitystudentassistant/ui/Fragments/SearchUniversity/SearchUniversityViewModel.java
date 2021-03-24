package com.logotet.universitystudentassistant.ui.Fragments.SearchUniversity;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.logotet.universitystudentassistant.data.Repository;
import com.logotet.universitystudentassistant.data.entities.UniversityEntity;

import java.util.List;

public class SearchUniversityViewModel extends AndroidViewModel {

    private MutableLiveData<List<UniversityEntity>> universities;
    private Repository repository;

    public SearchUniversityViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application.getApplicationContext());
        universities = new MutableLiveData<>();
        setUniversities();
    }


    public void setUniversities() {
        universities.setValue(repository.getRemoteUniversityData());
    }

    public LiveData<List<UniversityEntity>> getUniversitiesList() {
        return universities;
    }

    public void insertUniversity(UniversityEntity entity){
        repository.insertUniversityToRoomDb(entity);
    }


}