package com.logotet.universitystudentassistant.data.remote.firebase;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.logotet.universitystudentassistant.data.entities.User;
import com.logotet.universitystudentassistant.utils.AppConstants;

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
