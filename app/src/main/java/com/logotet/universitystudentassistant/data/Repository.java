package com.logotet.universitystudentassistant.data;

import android.content.Context;

import com.logotet.universitystudentassistant.data.remote.firabase.FirebaseAuthService;

public class Repository {
    FirebaseAuthService firebaseAuthService;

    public Repository() {
        firebaseAuthService = new FirebaseAuthService();
    }

    public void createAccount(String email, String password, Context context){
        firebaseAuthService.createAccount(email, password, context);
    }

    public void signIn(String email, String password, Context context){
        firebaseAuthService.signIntoAccount(email, password, context);
    }
}
