package com.logotet.universitystudentassistant.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;

import com.logotet.universitystudentassistant.R;
import com.logotet.universitystudentassistant.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.btnUniversities.setOnClickListener(view ->
                startActivity(new Intent(MainActivity.this, UniversityActivity.class)));
    }
}