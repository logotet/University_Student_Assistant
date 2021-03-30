package com.project.universitystudentassistant.data.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.project.universitystudentassistant.data.entities.UniversityEntityPrep;

import java.util.List;

@Dao
public interface PrepUniversityDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertUniversity(UniversityEntityPrep entityPrep);

    @Query("SELECT * FROM prepopulated_universities_table")
    LiveData<List<UniversityEntityPrep>> getAll();

    @Query("SELECT * FROM prepopulated_universities_table LIMIT 5")
    LiveData<List<UniversityEntityPrep>> getFive();
}
