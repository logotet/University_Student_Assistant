package com.logotet.universitystudentassistant.data.remote.firabase;


import android.app.Activity;
import android.content.Context;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.logotet.universitystudentassistant.data.models.User;
import com.logotet.universitystudentassistant.utils.AppConstants;

public class FirebaseAuthService {
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private FirebaseFirestore firestoreDb = FirebaseFirestore.getInstance();


    public void signIn(String email, String password, Context context) {
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener((Activity) context, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //TODO: add user model
                        FirebaseUser firebaseUser = auth.getCurrentUser();
                        User user = new User();
                        registerUser(user);
                    }
                });
    }


    private void registerUser(User user) {
        if (user != null) {
            firestoreDb.collection(AppConstants.USERS)
                    .document(user.getId())
                    .set(user, SetOptions.merge())
                    .addOnSuccessListener(aVoid -> {

                            }
                    )
                    .addOnFailureListener(e -> {

                            }
                    );
        }
    }
}
