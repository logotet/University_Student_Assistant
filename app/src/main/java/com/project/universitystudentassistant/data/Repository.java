package com.project.universitystudentassistant.data;

import android.app.Activity;
import android.content.Context;

import androidx.lifecycle.LiveData;

import com.project.universitystudentassistant.data.entities.UniversityEntity;
import com.project.universitystudentassistant.data.entities.UniversityEntityPrep;
import com.project.universitystudentassistant.data.entities.User;
import com.project.universitystudentassistant.data.local.AppDatabase;
import com.project.universitystudentassistant.data.remote.dummyremote.DummyRemoteDataProvider;
import com.project.universitystudentassistant.data.remote.firebase.FirebaseAuthService;
import com.project.universitystudentassistant.data.remote.firebase.FirestoreService;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Repository {
    private FirebaseAuthService firebaseAuthService;
    private DummyRemoteDataProvider dummyRemoteDataProvider;
    private FirestoreService firestoreService;
    private AppDatabase roomDb;
    private Executor executor = Executors.newSingleThreadExecutor();

    private static Repository repository;

    //TODO: change the constructor to private
    public Repository(Context context) {
        firebaseAuthService = new FirebaseAuthService();
        dummyRemoteDataProvider = new DummyRemoteDataProvider();
        firestoreService = new FirestoreService();
        roomDb = AppDatabase.getInstance(context);
    }

    public static Repository getInstance(Context context) {
        if (repository == null) {
            repository = new Repository(context);
        }
        return repository;
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
    public void insertUserToRoomDb(User user) {
        roomDb.userDao().insertUser(user);
    }

    //University
    public void insertUniversityToRoomDb(UniversityEntity universityEntity) {
        executor.execute(() -> roomDb.universityDao().insertUniversity(universityEntity));
    }

    public LiveData<List<UniversityEntity>> getAllUniversities() {
        return roomDb.universityDao().getAll();
    }

    public void deleteUniversity(UniversityEntity entity) {
        String name = entity.getName();
        executor.execute(() -> roomDb.universityDao().deleteUniversityByName(name));

    }

    //Pre populated University Entity
    public void insertPrepUniversity(UniversityEntityPrep universityEntityPrep) {
        executor.execute(() -> roomDb.prepUniversityDao().insertUniversity(universityEntityPrep));

    }

    public LiveData<List<UniversityEntityPrep>> getAllPrepUniversities() {
        return roomDb.prepUniversityDao().getAll();
    }

    public LiveData<List<UniversityEntityPrep>> getSomePrepUniversities() {
        return roomDb.prepUniversityDao().getFive();
    }


}
