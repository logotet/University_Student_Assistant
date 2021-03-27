package com.project.universitystudentassistant.data.local;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.project.universitystudentassistant.models.UniversityEntity;
import com.project.universitystudentassistant.models.UniversityEntityPrep;
import com.project.universitystudentassistant.models.User;

@Database(entities = {UniversityEntity.class, User.class, UniversityEntityPrep.class}, version = 3, exportSchema = true)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase appDatabase;

    public abstract UserDao userDao();
    public abstract UniversityDao universityDao();
    public abstract UniversityPrepDao universityPrepDao();

    public static AppDatabase getInstance(Context context){
//        if(appDatabase==null){
            appDatabase = Room.databaseBuilder(context,
                    AppDatabase.class,
                    "university-assistant-database.db")
                    .createFromAsset("university-assitant-database.db")
                    .addMigrations(new Migration(2,3) {
                        @Override
                        public void migrate(@NonNull SupportSQLiteDatabase database) {
                            database.execSQL(
                                    "CREATE TABLE IF NOT EXISTS 'all_university_database_table' ('uid INTEGER' NOT NULL, 'name' TEXT,  " +
                                            "'address' TEXT, 'city' TEXT, 'state' TEXT , 'url' TEXT, 'image' TEXT)"
                            );

//                            database.execSQL("ALTER TABLE all_university_database_table ADD COLUMN uid INTEGER");
//                            database.execSQL("ALTER TABLE all_university_database_table ADD COLUMN name TEXT");
//                            database.execSQL("ALTER TABLE all_university_database_table ADD COLUMN address TEXT");
//                            database.execSQL("ALTER TABLE all_university_database_table ADD COLUMN city TEXT");
//                            database.execSQL("ALTER TABLE all_university_database_table ADD COLUMN state TEXT");
//                            database.execSQL("ALTER TABLE all_university_database_table ADD COLUMN url TEXT");
//                            database.execSQL("ALTER TABLE all_university_database_table ADD COLUMN image TEXT");
                        }
                    })
                    .allowMainThreadQueries()
                    .build();
//        }
        return appDatabase;
    }
}
