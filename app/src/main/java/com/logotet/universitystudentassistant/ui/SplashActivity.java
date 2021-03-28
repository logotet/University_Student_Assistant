package com.logotet.universitystudentassistant.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.logotet.universitystudentassistant.R;
import com.logotet.universitystudentassistant.data.Repository;
import com.logotet.universitystudentassistant.data.entities.UniversityEntityPrep;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class SplashActivity extends AppCompatActivity {

    private Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        repository = new Repository(this);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        readData();

        new Handler().postDelayed(() ->
                startActivity(new Intent(SplashActivity.this, LoginActivity.class)),
                2000);

    }

    private void readData() {
        new Thread(() -> {
    InputStream inputStream = getResources().openRawResource(R.raw.all_university_database_table);
    BufferedReader bufferedReader = new BufferedReader(
            new InputStreamReader(inputStream, StandardCharsets.UTF_8)
    );
    String line = null;
    try {
        bufferedReader.readLine();
        while ((line = bufferedReader.readLine()) != null) {
            String[] tokens = line.split(",");
            UniversityEntityPrep entityPrep = new UniversityEntityPrep(tokens[0]);
            entityPrep.setAddress(tokens[1]);
            entityPrep.setCity(tokens[2]);
            entityPrep.setState(tokens[3]);
            entityPrep.setWebPage(tokens[tokens.length - 1]);
            Log.i("Entity", entityPrep.getName() + " "
                    + entityPrep.getAddress() + ", "
                    + entityPrep.getCity() + " "
                    + entityPrep.getState() + " "
                    + "web page " + entityPrep.getWebPage());
            repository.insertPrepUniversity(entityPrep);
        }
    } catch (IOException e) {
        Log.wtf("Splash Activity", "Error reading file!!! " + line, e);
        e.printStackTrace();
    }
        });
    }
}