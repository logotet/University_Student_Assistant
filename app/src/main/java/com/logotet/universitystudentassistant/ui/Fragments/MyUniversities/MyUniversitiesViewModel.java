package com.logotet.universitystudentassistant.ui.Fragments.MyUniversities;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyUniversitiesViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public MyUniversitiesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}