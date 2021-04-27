package com.project.universitystudentassistant.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;

import com.google.firebase.auth.FirebaseAuth;
import com.project.universitystudentassistant.R;
import com.project.universitystudentassistant.databinding.ActivityMainBinding;
import com.project.universitystudentassistant.ui.timetable.TimetableActivity;
import com.project.universitystudentassistant.ui.university.UniversityActivity;
import com.project.universitystudentassistant.ui.user.LoginActivity;
import com.project.universitystudentassistant.ui.user.ProfileActivity;
import com.project.universitystudentassistant.utils.ViewHelper;

public class MainActivity extends BaseActivity {

    private ActivityMainBinding binding;
    private boolean doebleBackPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        showProgressDialog();

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

        binding.toolbarHomeActivity.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.super.onBackPressed();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        new Handler().postDelayed(() ->
                        hideProgressBar(),
                200);
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
        if (item.getItemId() == R.id.menu_logout) {
//            FirebaseAuth.getInstance().signOut();
//            Toast.makeText(MainActivity
//                            .this, "You signed out!",
//                    Toast.LENGTH_SHORT).show();
//            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            FragmentManager fm = getSupportFragmentManager();
            LogoutDialiogFragment alertDialog = new LogoutDialiogFragment();
            alertDialog.show(fm, "fragment_alert");
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if(doebleBackPressedOnce){
            finishAndRemoveTask();
            return;
        }
        this.doebleBackPressedOnce = true;
        Toast.makeText(this, getResources().getString(R.string.please_click_back_again_to_exit), Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(() -> doebleBackPressedOnce = false, 2000);    }
}