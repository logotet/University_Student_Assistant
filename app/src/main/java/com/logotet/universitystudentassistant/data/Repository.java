package com.logotet.universitystudentassistant.data;

import android.content.Context;

import com.logotet.universitystudentassistant.data.models.UniversityEntity;
import com.logotet.universitystudentassistant.data.remote.dummyremote.DummyRemoteDataProvider;
import com.logotet.universitystudentassistant.data.remote.firebase.FirebaseAuthService;

import java.util.List;

public class Repository {
    FirebaseAuthService firebaseAuthService;
    DummyRemoteDataProvider dummyRemoteDataProvider;

    public Repository() {
        firebaseAuthService = new FirebaseAuthService();
        dummyRemoteDataProvider = new DummyRemoteDataProvider();
    }

    public void createAccount(String email, String password, Context context){
        firebaseAuthService.createAccount(email, password, context);
    }

    public void signIn(String email, String password, Context context){
        firebaseAuthService.signIntoAccount(email, password, context);
    }

    public List<UniversityEntity> getRemoteUniversityData(){
        return dummyRemoteDataProvider.getListOFUniversities();
    }
}
