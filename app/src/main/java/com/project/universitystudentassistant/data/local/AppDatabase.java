package com.project.universitystudentassistant.data.local;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.project.universitystudentassistant.data.entities.UniversityEntity;
import com.project.universitystudentassistant.data.entities.UniversityEntityPrep;
import com.project.universitystudentassistant.data.entities.User;

@Database(entities = {UniversityEntity.class, User.class, UniversityEntityPrep.class}, version = 5, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase appDatabase;

    public abstract UserDao userDao();

    public abstract UniversityDao universityDao();

    public abstract PrepUniversityDao prepUniversityDao();

    public static AppDatabase getInstance(Context context) {
        if (appDatabase == null) {
            appDatabase = Room.databaseBuilder(context,
                    AppDatabase.class,
                    "university-assistant-database.db")
                    .fallbackToDestructiveMigration()
//                    .addMigrations(new Migration(4, 5) {
//                        @Override
//                        public void migrate(@NonNull SupportSQLiteDatabase database) {
//                            database.execSQL("ALTER TABLE prepopulated_universities_table "
//                                    + " ADD COLUMN graduation_rate INTEGER DEFAULT 1 NOT NULL");
//                            database.execSQL("ALTER TABLE prepopulated_universities_table "
//                                    + " ADD COLUMN acceptance_rate INTEGER DEFAULT 1 NOT NULL");
//                            database.execSQL("ALTER TABLE prepopulated_universities_table "
//                                    + " ADD COLUMN description TEXT");
//                            database.execSQL("ALTER TABLE my_universities_table "
//                                    + " ADD COLUMN graduation_rate INTEGER DEFAULT 1 NOT NULL");
//                            database.execSQL("ALTER TABLE my_universities_table "
//                                    + " ADD COLUMN acceptance_rate INTEGER DEFAULT 1 NOT NULL");
//                            database.execSQL("ALTER TABLE my_universities_table "
//                                    + " ADD COLUMN description TEXT");
//                        }
//                    })
                    .allowMainThreadQueries()
                    .build();
        }
        return appDatabase;
    }


}
