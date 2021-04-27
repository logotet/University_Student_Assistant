package com.project.universitystudentassistant.ui.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.project.universitystudentassistant.R;
import com.project.universitystudentassistant.databinding.ActivityProfileBinding;
import com.project.universitystudentassistant.ui.BaseActivity;

public class ProfileActivity extends BaseActivity {

    private ActivityProfileBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile);

    }
}