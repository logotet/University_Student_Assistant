package com.project.universitystudentassistant.ui.university.Fragments.Filter;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.project.universitystudentassistant.data.Repository;

public class FilterFragmentViewModel extends AndroidViewModel {

    private Repository repository;

    public FilterFragmentViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application.getApplicationContext());
    }

    public LiveData<Integer> getMinCost(){
        return repository.getMinCost();
    }

    public LiveData<Integer> getMaxCost(){
        return repository.getMaxCost();
    }

    public LiveData<Integer> getMinAccRate(){
        return repository.getMinAccRate();
    }

    public LiveData<Integer> getMaxAccRate(){
        return repository.getMaxAccRate();
    }

    public LiveData<Integer> getMinGradRate(){
        return repository.getMinGradRate();
    }

    public LiveData<Integer> getMaxGradRate(){
        return repository.getMaxGradRate();
    }


}
