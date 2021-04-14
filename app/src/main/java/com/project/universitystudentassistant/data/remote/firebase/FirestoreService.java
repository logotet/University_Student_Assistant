package com.project.universitystudentassistant.data.remote.firebase;

import android.util.Log;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.project.universitystudentassistant.data.models.User;
import com.project.universitystudentassistant.utils.AppConstants;

public class FirestoreService {

    private FirebaseFirestore firestoreDb = FirebaseFirestore.getInstance();

    public void uploadUserDetails(User user){
        if(user != null) {
            firestoreDb.collection(AppConstants.USERS)
                    .document(user.getId())
                    .set(user, SetOptions.merge())
                    .addOnSuccessListener(aVoid -> {
                             Log.d("Success", "Success");
                            }
                    )
                    .addOnFailureListener(e -> {
                                Log.d("Failure", "failure");
                            }
                    );
        }
    }
}
