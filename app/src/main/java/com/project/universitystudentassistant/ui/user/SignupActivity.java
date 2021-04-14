package com.project.universitystudentassistant.ui.user;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import androidx.databinding.DataBindingUtil;

import com.google.firebase.auth.FirebaseUser;
import com.project.universitystudentassistant.R;
import com.project.universitystudentassistant.data.Repository;
import com.project.universitystudentassistant.data.models.User;
import com.project.universitystudentassistant.databinding.ActivitySignupBinding;
import com.project.universitystudentassistant.ui.BaseActivity;
import com.project.universitystudentassistant.ui.MainActivity;

public class SignupActivity extends BaseActivity {

    private ActivitySignupBinding binding;
    private String email;
    private String password;
    private Repository repository;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_signup);
        repository = new Repository(this);


        binding.btnRegister.setOnClickListener(view ->
        {
//            if (validateRegisterDetails()) {
                email = binding.edtEmail.getText().toString();
                password = binding.edtPassword.getText().toString();
                User user = new User("dummy_id",
                        binding.edtFirstName.getText().toString().trim(),
                        binding.edtLastName.getText().toString().trim(),
                        email,
                        password
                );
                repository.createAccount(email, password, this, user);
                repository.insertUserToRoomDb(user);
                startActivity(new Intent(SignupActivity.this, MainActivity.class));
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