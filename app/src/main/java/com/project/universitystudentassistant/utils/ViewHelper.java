package com.project.universitystudentassistant.utils;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.project.universitystudentassistant.R;

public class ViewHelper {

    public static  void setUpToolbar(AppCompatActivity activity, Toolbar toolbar) {
        activity.setSupportActionBar(toolbar);
        ActionBar supportActionBar = activity.getSupportActionBar();
        supportActionBar.setTitle("");
        supportActionBar.setDisplayHomeAsUpEnabled(true);
//        supportActionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back);

    }
}
