package com.logotet.universitystudentassistant.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.logotet.universitystudentassistant.R;
import com.logotet.universitystudentassistant.databinding.ActivitySignupBinding;

public class SignupActivity extends BaseActivity {

    private ActivitySignupBinding binding;
    private String email;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_signup);

        binding.btnRegister.setOnClickListener(view ->
        {
//            if (validateRegisterDetails()) {
//                startActivity(new Intent(SignupActivity.this, MainActivity.class));
//            }
            
        });
    }

    private boolean validateRegisterDetails() {
        if (TextUtils.isEmpty(binding.edtFirstName.getText().toString().trim())) {
            showErrorSnackBar(getResources().getString(R.string.err_msg_enter_first_name), true);
            return false;
        }

        if (TextUtils.isEmpty(binding.edtLastName.getText().toString().trim())) {
            showErrorSnackBar(getResources().getString(R.string.err_msg_enter_last_name), true);
            return false;
        }
        if (TextUtils.isEmpty(binding.edtEmail.getText().toString().trim())) {
            showErrorSnackBar(getResources().getString(R.string.err_msg_enter_email), true);
            return false;
        }

        if (TextUtils.isEmpty(binding.edtPassword.getText().toString().trim())) {
            showErrorSnackBar(getResources().getString(R.string.err_msg_enter_password), true);
            return false;
        }

        if (TextUtils.isEmpty(binding.edtConfirmPassword.getText().toString().trim())) {
            showErrorSnackBar(getResources().getString(R.string.err_msg_enter_confirm_password), true);
            return false;
        }


        if (!binding.edtPassword.getText().toString().trim().equals(binding.edtConfirmPassword.getText().toString()
                .trim())) {
            showErrorSnackBar(getResources().getString(R.string.err_msg_password_and_confirm_password_mismatch), true);
            return false;
        }

        showErrorSnackBar("Your details are valid.", false);
        email = binding.edtEmail.getText().toString().trim();
        password = binding.edtPassword.getText().toString().trim();
        return true;

    }
}