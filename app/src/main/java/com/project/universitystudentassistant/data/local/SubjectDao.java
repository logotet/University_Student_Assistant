package com.project.universitystudentassistant.data.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.project.universitystudentassistant.models.Subject;
import com.project.universitystudentassistant.models.UniversityEntity;

import java.util.List;

@Dao
public interface SubjectDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertSubject(Subject subject);

    @Query("SELECT * FROM subjects_table")
    LiveData<List<Subject>> getAll();


}
