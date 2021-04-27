package com.project.universitystudentassistant.ui.university;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.project.universitystudentassistant.R;
import com.project.universitystudentassistant.databinding.ActivityUniversityBinding;
import com.project.universitystudentassistant.ui.BaseActivity;
import com.project.universitystudentassistant.utils.ViewHelper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class UniversityActivity extends BaseActivity {

    private ActivityUniversityBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_university);
        ViewHelper.setUpToolbar(this, binding.toolbarSearchUniversitiesActivity);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                 R.id.navigation_search_universities, R.id.navigation_my_universities)
                .build();


        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        NavController navController = navHostFragment.getNavController();

//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAndRemoveTask();
    }
}