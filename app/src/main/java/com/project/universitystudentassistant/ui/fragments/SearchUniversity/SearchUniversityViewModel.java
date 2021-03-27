package com.project.universitystudentassistant.ui.fragments.SearchUniversity;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.project.universitystudentassistant.data.Repository;
import com.project.universitystudentassistant.models.UniversityEntity;
import com.project.universitystudentassistant.models.UniversityEntityPrep;

import java.util.List;

public class SearchUniversityViewModel extends AndroidViewModel {

//    private MutableLiveData<List<UniversityEntity>> universities;
        private MutableLiveData<List<UniversityEntityPrep>> universitiesPrep;
    private Repository repository;

    public SearchUniversityViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application.getApplicationContext());
//        universities = new MutableLiveData<>();
//        setUniversities();
                universitiesPrep = new MutableLiveData<>();
    }


//    public void setUniversities() {
//        universities.setValue(repository.getRemoteUniversityData());
//    }

//    public LiveData<List<UniversityEntity>> getUniversitiesList() {
//        return universities;
//    }



    public LiveData<List<UniversityEntityPrep>> getUniversitiesList() {
        return universitiesPrep;
    }

    public void insertUniversity(UniversityEntity entity){
        repository.insertUniversityToRoomDb(entity);
    }


}