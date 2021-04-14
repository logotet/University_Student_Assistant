package com.project.universitystudentassistant.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.project.universitystudentassistant.R;
import com.project.universitystudentassistant.databinding.ActivityMainBinding;
import com.project.universitystudentassistant.ui.timetable.TimetableActivity;
import com.project.universitystudentassistant.ui.university.UniversityActivity;
import com.project.universitystudentassistant.ui.user.ProfileActivity;
import com.project.universitystudentassistant.utils.ViewHelper;

public class MainActivity extends BaseActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        ViewHelper.setUpToolbar(this, binding.toolbarHomeActivity);

        binding.btnUniversities.setOnClickListener(view -> {
            goToUniversityActivityActivity();
        });
        binding.imgUniversity.setOnClickListener(view ->
                goToUniversityActivityActivity());
        binding.btnTimetable.setOnClickListener(view -> {
            goToTimetableActivity();
        });
        binding.imgTimetable.setOnClickListener(view ->
                goToTimetableActivity());

    }

    private void goToUniversityActivityActivity() {
        startActivity(new Intent(MainActivity.this, UniversityActivity.class));
    }

    private void goToTimetableActivity() {
        startActivity(new Intent(MainActivity.this, TimetableActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.menu_profile){
            startActivity(new Intent(MainActivity.this, ProfileActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}