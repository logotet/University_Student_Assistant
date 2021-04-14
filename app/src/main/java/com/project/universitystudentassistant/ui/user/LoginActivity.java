package com.project.universitystudentassistant.ui.user;

import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.project.universitystudentassistant.R;
import com.project.universitystudentassistant.data.Repository;
import com.project.universitystudentassistant.databinding.ActivityLoginBinding;
import com.project.universitystudentassistant.ui.BaseActivity;
import com.project.universitystudentassistant.ui.MainActivity;
import com.project.universitystudentassistant.utils.AppConstants;

public class LoginActivity extends BaseActivity {

    private ActivityLoginBinding binding;
    private Repository repository;
    private String email;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        repository = new Repository(this);
        email = AppConstants.DUMMY_EMAIL;
        password = AppConstants.DUMMY_PASSWORD;

        binding.txtRegister.setOnClickListener(view ->
                startActivity(new Intent(LoginActivity.this, SignupActivity.class)));

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                repository.signIn(email, password, LoginActivity.this);
            }
        });
    }

    public void goToMain(){
        LoginActivity.this.startActivity(new Intent(LoginActivity.this, MainActivity.class));
    }
}