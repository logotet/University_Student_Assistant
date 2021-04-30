package com.project.universitystudentassistant.ui.user;

import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.project.universitystudentassistant.R;
import com.project.universitystudentassistant.data.Repository;
import com.project.universitystudentassistant.databinding.ActivityLoginBinding;
import com.project.universitystudentassistant.ui.BaseActivity;
import com.project.universitystudentassistant.ui.ForgotPasswordActivity;
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


        binding.txtRegister.setOnClickListener(view ->
                startActivity(new Intent(LoginActivity.this, SignupActivity.class)));

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validateEmail() && validateEmail()) {
                    email = binding.edtEmail.getText().toString().trim();
                    password = binding.edtPassword.getText().toString().trim();
                    repository.signIn(email, password, LoginActivity.this);
                }else {
                    Toast.makeText(LoginActivity.this, "The fields should not be empty", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.txtForgotPassword.setOnClickListener(view -> startActivity(new Intent(LoginActivity.this, ForgotPasswordActivity.class)));
        binding.edtEmail.getText().clear();
        binding.edtPassword.getText().clear();
    }

    public void goToMain() {
        LoginActivity.this.startActivity(new Intent(LoginActivity.this, MainActivity.class));
    }


    private boolean validateEmail() {
        return !TextUtils.isEmpty(binding.edtEmail.getText().toString().trim());
    }

    private boolean validatePassword() {
        return !TextUtils.isEmpty(binding.edtPassword.getText().toString().trim());
    }
}