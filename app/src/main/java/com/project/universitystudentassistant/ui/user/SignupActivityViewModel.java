package com.project.universitystudentassistant.ui.user;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.project.universitystudentassistant.data.Repository;

public class SignupActivityViewModel extends AndroidViewModel {

    private Repository repository;

    public SignupActivityViewModel(@NonNull Application application) {
        super(application);
    }

}
