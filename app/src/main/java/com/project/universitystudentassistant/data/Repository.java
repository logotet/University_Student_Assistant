package com.project.universitystudentassistant.data;

import android.app.Activity;
import android.content.Context;

import androidx.lifecycle.LiveData;

import com.project.universitystudentassistant.models.Subject;
import com.project.universitystudentassistant.models.SubjectSchedule;
import com.project.universitystudentassistant.models.UniversityEntity;
import com.project.universitystudentassistant.models.User;
import com.project.universitystudentassistant.data.local.AppDatabase;
import com.project.universitystudentassistant.data.remote.dummyremote.DummyRemoteDataProvider;
import com.project.universitystudentassistant.data.remote.firebase.FirebaseAuthService;
import com.project.universitystudentassistant.data.remote.firebase.FirestoreService;
import com.project.universitystudentassistant.utils.AppConstants;

import java.time.DayOfWeek;
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

    //My Universities Data
    public void insertUniveristy(UniversityEntity universityEntity) {
        executor.execute(() -> roomDb.universityDao().insertUniversity(universityEntity));
    }

    public void updateUniversity(UniversityEntity entity) {
        executor.execute(() -> roomDb.universityDao().update(entity));
    }

    public LiveData<List<UniversityEntity>> getAllUniversities() {
        return roomDb.universityDao().getAll();
    }

    public LiveData<List<UniversityEntity>> getSavedUniversities() {
        return roomDb.universityDao().getSavedUniversities(AppConstants.SAVED);
    }



    public void deleteUniversity(UniversityEntity entity) {
        String name = entity.getName();
        executor.execute(() -> roomDb.universityDao().deleteUniversityByName(name));
    }

    public LiveData<List<UniversityEntity>> getAllPrepUniversities() {
        return roomDb.universityDao().getAll();
    }

    public LiveData<List<UniversityEntity>> getPrepUniversitiesByStates(String... states) {
        return roomDb.universityDao().getUniversitiesByStates(states);
    }

    public LiveData<Integer> getMinCost() {
        return roomDb.universityDao().getMinCost();
    }

    public LiveData<Integer> getMaxCost() {
        return roomDb.universityDao().getMaxCost();
    }

    public LiveData<Integer> getMinAccRate() {
        return roomDb.universityDao().getMinAccRate();
    }

    public LiveData<Integer> getMaxAccRate() {
        return roomDb.universityDao().getMaxAccRate();
    }

    public LiveData<Integer> getMinGradRate() {
        return roomDb.universityDao().getMinGradRate();
    }

    public LiveData<Integer> getMaxGradRate() {
        return roomDb.universityDao().getMaxGradRate();
    }


    //Subjects
    public void insertSubject(Subject subject) {
        executor.execute(() -> roomDb.subjectDao().insertSubject(subject));
    }

    public LiveData<List<Subject>> getAllSubjects(){
        return roomDb.subjectDao().getAll();
    }


    public void insertSubjectSchedule(SubjectSchedule subject) {
        executor.execute(() -> roomDb.subjectScheduleDao().insertSubject(subject));
    }

    public LiveData<List<SubjectSchedule>> getAllSubjectsSchedule(){
        return roomDb.subjectScheduleDao().getAll();
    }

    public LiveData<List<SubjectSchedule>> getAllSubjectsOnThisDay(DayOfWeek dayOfWeek){
        return roomDb.subjectScheduleDao().getAllOnThisDay(dayOfWeek);
    }

    public void deleteSubject(String name){
        executor.execute(() -> roomDb.subjectScheduleDao().deleteSubject(name));
    }


}
