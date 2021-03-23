package com.logotet.universitystudentassistant.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.logotet.universitystudentassistant.R;
import com.logotet.universitystudentassistant.data.Repository;
import com.logotet.universitystudentassistant.databinding.ActivityLoginBinding;
import com.logotet.universitystudentassistant.utils.AppConstants;

public class LoginActivity extends BaseActivity {

    private ActivityLoginBinding binding;
    private Repository repository;
    private String email;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        repository = new Repository();
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