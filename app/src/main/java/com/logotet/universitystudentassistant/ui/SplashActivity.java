package com.logotet.universitystudentassistant.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.logotet.universitystudentassistant.R;
import com.logotet.universitystudentassistant.data.Repository;
import com.logotet.universitystudentassistant.data.entities.UniversityEntityPrep;
import com.logotet.universitystudentassistant.data.local.AppDatabase;
import com.opencsv.CSVReader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class SplashActivity extends AppCompatActivity {

    private Repository repository;
    private boolean isPrepDbEmpty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        repository = new Repository(this);
        readData();


    }

    private void readData() {
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
                entityPrep.setWebPage(tokens[tokens.length - 2]);
                Log.i("Entity", entityPrep.getName() + " "
                        + entityPrep.getAddress() + ", "
                        + entityPrep.getCity() + " "
                        + entityPrep.getState() + " "
                        + entityPrep.getWebPage());
                repository.insertPrepUniversity(entityPrep);
            }

        } catch (IOException e) {
            Log.wtf("Splash Activity", "Error reading file!!! " + line, e);
            e.printStackTrace();
        }
    }
}