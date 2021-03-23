package com.logotet.universitystudentassistant.ui.Fragments.SearchUniversity;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SearchUniversityViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SearchUniversityViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}