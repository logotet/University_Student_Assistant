package com.project.universitystudentassistant.data.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.project.universitystudentassistant.models.Subject;
import com.project.universitystudentassistant.models.SubjectSchedule;

import java.util.List;

@Dao
public interface SubjectScheduleDao {
    @Insert
    void insertSubject(SubjectSchedule subject);

    @Query("SELECT * FROM subjects_table")
    LiveData<List<SubjectSchedule>> getAll();
}
