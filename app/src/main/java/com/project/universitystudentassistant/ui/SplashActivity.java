package com.project.universitystudentassistant.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;

import com.project.universitystudentassistant.R;
import com.project.universitystudentassistant.data.Repository;
import com.project.universitystudentassistant.models.UniversityEntity;
import com.project.universitystudentassistant.ui.user.LoginActivity;
import com.project.universitystudentassistant.utils.AppStringFormatter;

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
                        UniversityEntity entityPrep = new UniversityEntity(AppStringFormatter.replaceSymbols(tokens[0]));
                        entityPrep.setAddress(AppStringFormatter.replaceSymbols(tokens[1]));
                        entityPrep.setCity(AppStringFormatter.replaceSymbols(tokens[2]));
                        entityPrep.setState(AppStringFormatter.replaceSymbols(tokens[3]).replace("'", ""));
                        entityPrep.setCostOfAttendance(Integer.parseInt(AppStringFormatter.replaceSymbols(tokens[4])));
                        entityPrep.setWebPage(AppStringFormatter.replaceSymbols(tokens[5]));
                        entityPrep.setImage(AppStringFormatter.replaceSymbols(tokens[6]));
                        entityPrep.setGraduationRate(Integer.parseInt(AppStringFormatter.replaceSymbols(tokens[7])));
                        entityPrep.setAcceptanceRate(Integer.parseInt(AppStringFormatter.replaceSymbols(tokens[8])));
                        entityPrep.setDescription(AppStringFormatter.replaceSymbols(tokens[9]));
                        Log.i("Entity", entityPrep.getName() + " "
                                + "address " + entityPrep.getAddress() + ", "
                                + "city " + entityPrep.getCity() + " "
                                + "state " + entityPrep.getState() + " "
                                + "web page " + entityPrep.getWebPage() + " "
                                + "grad rate " + entityPrep.getGraduationRate()
                                + "acc rate " + entityPrep.getAcceptanceRate() + " "
                                + "description " + entityPrep.getDescription()
                        );
                        repository.insertUniveristy(entityPrep);
                    }
                } catch (IOException e) {
                    Log.wtf("Splash Activity", "Error reading file!!! " + line, e);
                    e.printStackTrace();
                }
            }
        }).start();

    }
}