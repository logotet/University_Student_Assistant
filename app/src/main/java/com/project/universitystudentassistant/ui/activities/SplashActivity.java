package com.project.universitystudentassistant.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.project.universitystudentassistant.R;
import com.project.universitystudentassistant.models.UniversityEntityPrep;
import com.project.universitystudentassistant.data.local.AppDatabase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        AppDatabase appDatabase = AppDatabase.getInstance(this);


//        readData();
    }

    private void readData(){
        InputStream inputStream = getResources().openRawResource(R.raw.university_sample_database);
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(inputStream, StandardCharsets.UTF_8)
        );

        String line = null;
        try{
            bufferedReader.readLine();
            while ((line =bufferedReader.readLine()) != null){
                String[] tokens = line.split(",");
                UniversityEntityPrep entityPrep = new UniversityEntityPrep(tokens[1]);
                entityPrep.setAddress(tokens[2]);
                entityPrep.setCity(tokens[3]);
                entityPrep.setState(tokens[4]);
                entityPrep.setWebPage(tokens[tokens.length-1]);
                Log.i("Entity", entityPrep.getName() + " "
                + entityPrep.getAddress() + ", "
                + entityPrep.getCity() + " "
                + entityPrep.getState() + " "
                + entityPrep.getWebPage());
            }
        } catch (IOException e) {
            Log.wtf("Splash Activity", "Error reading file!!! " + line, e);
            e.printStackTrace();
        }
    }
}