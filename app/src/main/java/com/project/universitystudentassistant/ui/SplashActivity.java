package com.project.universitystudentassistant.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;

import com.project.universitystudentassistant.R;
import com.project.universitystudentassistant.data.Repository;
import com.project.universitystudentassistant.data.entities.UniversityEntityPrep;

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

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        readData();

        new Handler().postDelayed(() ->
                        startActivity(new Intent(SplashActivity.this, LoginActivity.class)),
                2000);

    }

    private void readData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
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
                        entityPrep.setCostOfAttendance(Integer.parseInt(tokens[4]));
                        entityPrep.setWebPage(tokens[5]);
                        entityPrep.setImage(tokens[6].replace("|", ","));
                        entityPrep.setGraduationRate(Integer.parseInt(tokens[7]));
                        entityPrep.setAcceptanceRate(Integer.parseInt(tokens[8]));
                        String replace = tokens[9]
                                .replace("|", ",")
                                .replace("�", "'")
                                .replace("\"", "");
                        entityPrep.setDescription(replace);
                        Log.i("Entity", entityPrep.getName() + " "
                                + "address " + entityPrep.getAddress() + ", "
                                + "city " + entityPrep.getCity() + " "
                                + "state " + entityPrep.getState() + " "
                                + "web page " + entityPrep.getWebPage() + " "
                                + "grad rate " + entityPrep.getGraduationRate()
                                + "acc rate " + entityPrep.getAcceptanceRate() + " "
                                + "description " + entityPrep.getDescription()
                        );
                        repository.insertPrepUniversity(entityPrep);
                    }
                } catch (IOException e) {
                    Log.wtf("Splash Activity", "Error reading file!!! " + line, e);
                    e.printStackTrace();
                }
            }
        }).start();

    }
}