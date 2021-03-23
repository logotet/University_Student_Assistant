package com.logotet.universitystudentassistant.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.logotet.universitystudentassistant.R;
import com.logotet.universitystudentassistant.databinding.ActivityLoginBinding;

public class LoginActivity extends BaseActivity {

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        binding.txtRegister.setOnClickListener(view ->
                startActivity(new Intent(LoginActivity.this, SignupActivity.class)));

        binding.btnLogin.setOnClickListener(view ->
                startActivity(new Intent(LoginActivity.this, MainActivity.class)));
    }
}