package com.logotet.universitystudentassistant.data.remote.firebase;


import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.logotet.universitystudentassistant.data.models.User;
import com.logotet.universitystudentassistant.ui.LoginActivity;
import com.logotet.universitystudentassistant.utils.AppConstants;

public class FirebaseAuthService {
    private FirebaseAuth auth;
    private FirestoreService firestoreService;

    public FirebaseAuthService() {
        auth = FirebaseAuth.getInstance();
        firestoreService = new FirestoreService();
    }

    public void createAccount(String email, String password, Context context) {
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener((Activity) context, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //TODO: add user model with the entity values
                        FirebaseUser firebaseUser = auth.getCurrentUser();
//                        firestoreService.uploadUserDetails(firebaseUser);
                    }
                });
    }


    public void signIntoAccount(String email, String password, Context context) {
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener((Activity) context, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser firebaseUser = auth.getCurrentUser();
                        Toast.makeText(context, firebaseUser.getEmail(), Toast.LENGTH_LONG).show();
                        if (context instanceof LoginActivity) {
                            ((LoginActivity) context).goToMain();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context, "Failed to login", Toast.LENGTH_LONG).show();
                    }
                });
    }


}
