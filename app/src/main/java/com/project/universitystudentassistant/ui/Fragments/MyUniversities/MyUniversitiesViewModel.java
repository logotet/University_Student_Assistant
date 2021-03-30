package com.project.universitystudentassistant.ui.Fragments.MyUniversities;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.project.universitystudentassistant.data.Repository;
import com.project.universitystudentassistant.data.entities.UniversityEntity;

import java.util.List;

public class MyUniversitiesViewModel extends AndroidViewModel {

    private MutableLiveData<String> mText;
    private Repository repository;

    public MyUniversitiesViewModel(@NonNull Application application) {
        super(application);
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
        repository = new Repository(application.getApplicationContext());
    }


    public LiveData<List<UniversityEntity>> getUniversities(){
        return repository.getAllUniversities();
    }

    public void deleteUniversity(UniversityEntity entity){
        repository.deleteUniversity(entity);
    }
}