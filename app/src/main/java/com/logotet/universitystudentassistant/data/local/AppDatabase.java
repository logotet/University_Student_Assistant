package com.logotet.universitystudentassistant.data.local;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.logotet.universitystudentassistant.data.entities.UniversityEntity;
import com.logotet.universitystudentassistant.data.entities.User;

@Database(entities = {UniversityEntity.class, User.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase appDatabase;

    public abstract UserDao userDao();
    public abstract UniversityDao universityDao();

    public static AppDatabase getInstance(Context context){
        if(appDatabase==null){
            appDatabase = Room.databaseBuilder(context,
                    AppDatabase.class,
                    "university-assitant-database.db")
                    .allowMainThreadQueries()
                    .build();
        }
        return appDatabase;
    }
}
