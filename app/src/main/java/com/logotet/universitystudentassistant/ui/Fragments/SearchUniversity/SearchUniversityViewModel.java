package com.logotet.universitystudentassistant.ui.Fragments.SearchUniversity;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.logotet.universitystudentassistant.data.Repository;
import com.logotet.universitystudentassistant.data.models.UniversityEntity;

import java.util.List;

public class SearchUniversityViewModel extends ViewModel {

    private MutableLiveData<List<UniversityEntity>> universities;
    private Repository repository;

    public SearchUniversityViewModel() {
        repository = new Repository();
        universities = new MutableLiveData<>();
        setUniversities();
    }

    public void setUniversities() {
        universities.setValue(repository.getRemoteUniversityData());
    }

    public LiveData<List<UniversityEntity>> getUniversitiesList() {
        return universities;
    }


}