package com.project.universitystudentassistant.data.remote.firebase;


import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.project.universitystudentassistant.data.models.User;
import com.project.universitystudentassistant.ui.user.LoginActivity;

public class FirebaseAuthService {
    private FirebaseAuth auth;
    private FirestoreService firestoreService;

    public FirebaseAuthService() {
        auth = FirebaseAuth.getInstance();
        firestoreService = new FirestoreService();
    }

    public void createAccount(String email, String password, Activity activity, User user) {
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener( activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            //TODO: there is a bug not registering every user and not uploading user details to firestore
                            FirebaseUser firebaseUser = task.getResult().getUser();
                            user.setId(firebaseUser.getUid());
                            firestoreService.uploadUserDetails(user);
                            Toast.makeText(activity, "Account created successfully",
                                    Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(activity, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(activity, "Authentication failed: " + e.getMessage(),
                        Toast.LENGTH_SHORT).show();
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
