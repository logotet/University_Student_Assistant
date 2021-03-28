package com.logotet.universitystudentassistant.data.local;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.logotet.universitystudentassistant.data.entities.UniversityEntity;
import com.logotet.universitystudentassistant.data.entities.UniversityEntityPrep;
import com.logotet.universitystudentassistant.data.entities.User;

@Database(entities = {UniversityEntity.class, User.class, UniversityEntityPrep.class}, version = 4, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase appDatabase;

    public abstract UserDao userDao();
    public abstract UniversityDao universityDao();
    public abstract PrepUniversityDao prepUniversityDao();

    public static AppDatabase getInstance(Context context){
        if(appDatabase==null){
            appDatabase = Room.databaseBuilder(context,
                    AppDatabase.class,
                    "university-assistant-database.db")
                    .addMigrations(new Migration(3, 4) {
                        @Override
                        public void migrate(@NonNull SupportSQLiteDatabase database) {
//                        database.execSQL(
//                                "CREATE TABLE prepopulated_universities_table (" +
//                                        "uid INTEGER NOT NULL, " +
//                                        "web_page TEXT," +
//                                        "image TEXT," +
//                                        "address TEXT," +
//                                        "cost INTEGER NOT NULL," +
//                                        "city TEXT," +
//                                        "name TEXT," +
//                                        "state TEXT," +
//                                        " PRIMARY KEY(uid))"
//                        );
                        }
                    })
                    .allowMainThreadQueries()
                    .build();
        }
        return appDatabase;
    }
}
