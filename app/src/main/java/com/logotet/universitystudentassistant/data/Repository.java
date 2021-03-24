package com.logotet.universitystudentassistant.data;

import android.app.Activity;
import android.content.Context;

import com.google.firebase.auth.FirebaseUser;
import com.logotet.universitystudentassistant.data.entities.UniversityEntity;
import com.logotet.universitystudentassistant.data.entities.User;
import com.logotet.universitystudentassistant.data.local.AppDatabase;
import com.logotet.universitystudentassistant.data.remote.dummyremote.DummyRemoteDataProvider;
import com.logotet.universitystudentassistant.data.remote.firebase.FirebaseAuthService;
import com.logotet.universitystudentassistant.data.remote.firebase.FirestoreService;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Repository {
    private FirebaseAuthService firebaseAuthService;
    private DummyRemoteDataProvider dummyRemoteDataProvider;
    private FirestoreService firestoreService;
    private AppDatabase roomDb;
    private Executor executor = Executors.newSingleThreadExecutor();

    public Repository(Context context) {
        firebaseAuthService = new FirebaseAuthService();
        dummyRemoteDataProvider = new DummyRemoteDataProvider();
        firestoreService = new FirestoreService();
        roomDb = AppDatabase.getInstance(context);
    }

    //Firebase Authentication
    public void createAccount(String email, String password, Activity activity, User user) {
        firebaseAuthService.createAccount(email, password, activity, user);
    }

    public void signIn(String email, String password, Context context) {
        firebaseAuthService.signIntoAccount(email, password, context);
    }

    //Remote dummy data
    public List<UniversityEntity> getRemoteUniversityData() {
        return dummyRemoteDataProvider.getListOFUniversities();
    }

    //Firestore Database
    public void registerUser(User user) {
        firestoreService.uploadUserDetails(user);
    }

    //Room local database
    //User
    public void insertUserToRoomDb(User user){
        roomDb.userDao().insertUser(user);
    }

    //University
    public void insertUniversityToRoomDb(UniversityEntity universityEntity){
        roomDb.universityDao().insertUniversity(universityEntity);
    }

}
