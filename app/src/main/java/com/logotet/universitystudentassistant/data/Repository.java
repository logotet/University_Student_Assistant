package com.logotet.universitystudentassistant.data;

import android.content.Context;

import com.google.firebase.auth.FirebaseUser;
import com.logotet.universitystudentassistant.data.models.UniversityEntity;
import com.logotet.universitystudentassistant.data.remote.dummyremote.DummyRemoteDataProvider;
import com.logotet.universitystudentassistant.data.remote.firebase.FirebaseAuthService;
import com.logotet.universitystudentassistant.data.remote.firebase.FirestoreService;

import java.util.List;

public class Repository {
    private FirebaseAuthService firebaseAuthService;
    private DummyRemoteDataProvider dummyRemoteDataProvider;
    private FirestoreService firestoreService;

    public Repository() {
        firebaseAuthService = new FirebaseAuthService();
        dummyRemoteDataProvider = new DummyRemoteDataProvider();
        firestoreService = new FirestoreService();
    }

    //Firebase Authentication
    public void createAccount(String email, String password, Context context) {
        firebaseAuthService.createAccount(email, password, context);
    }

    public void signIn(String email, String password, Context context) {
        firebaseAuthService.signIntoAccount(email, password, context);
    }

    //Remote dummy data
    public List<UniversityEntity> getRemoteUniversityData() {
        return dummyRemoteDataProvider.getListOFUniversities();
    }

    //Firestore Database
    public void registerUser(FirebaseUser firebaseUser) {
        firestoreService.uploadUserDetails(firebaseUser);
    }

}
