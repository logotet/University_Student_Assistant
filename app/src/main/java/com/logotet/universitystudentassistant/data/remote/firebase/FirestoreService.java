package com.logotet.universitystudentassistant.data.remote.firebase;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.logotet.universitystudentassistant.data.models.User;
import com.logotet.universitystudentassistant.utils.AppConstants;

public class FirestoreService {

    private FirebaseFirestore firestoreDb = FirebaseFirestore.getInstance();

    public void uploadUserDetails(FirebaseUser firebaseUser){
        if(firebaseUser != null) {
            firestoreDb.collection(AppConstants.USERS)
                    .document()
                    .set(firebaseUser, SetOptions.merge())
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                        }
                    });
        }
    }
}
