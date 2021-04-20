package com.project.universitystudentassistant.data.local;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.project.universitystudentassistant.models.Subject;
import com.project.universitystudentassistant.models.UniversityEntity;
import com.project.universitystudentassistant.models.UniversityEntityPrep;
import com.project.universitystudentassistant.models.User;

@Database(entities = {UniversityEntity.class, User.class, UniversityEntityPrep.class, Subject.class}, version = 7, exportSchema = false)
@TypeConverters(SubjectTimeConverter.class)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase appDatabase;

    public abstract UserDao userDao();

    public abstract UniversityDao universityDao();

    public abstract PrepUniversityDao prepUniversityDao();

    public abstract SubjectDao subjectDao();

    public static AppDatabase getInstance(Context context) {
        if (appDatabase == null) {
            appDatabase = Room.databaseBuilder(context,
                    AppDatabase.class,
                    "university-assistant-database.db")
                    .fallbackToDestructiveMigration()
                    .addMigrations(new Migration(6,7) {
                        @Override
                        public void migrate(@NonNull SupportSQLiteDatabase database) {
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

                            database.execSQL("CREATE TABLE IF NOT EXISTS`subjects_table` (`uid` INTEGER, "
                                    + "'teacher' TEXT, "
                                    + "'location' TEXT, "
                                    + "'repeating_time' TEXT, "
                                    + "'color' INTEGER NOT NULL, "
                                    + "'is_repeating_event' INTEGER NOT NULL, "
                                    + "'single_time' TEXT, "
                                    + "`name` TEXT NOT NULL, PRIMARY KEY(`uid`))");
                        }
                    })
                    .build();
        }
        return appDatabase;
    }

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
//                    .allowMainThreadQueries()
}
