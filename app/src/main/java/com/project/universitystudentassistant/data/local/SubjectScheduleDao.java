package com.project.universitystudentassistant.data.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.project.universitystudentassistant.models.Subject;
import com.project.universitystudentassistant.models.SubjectSchedule;
import com.project.universitystudentassistant.models.UniversityEntity;

import java.time.DayOfWeek;
import java.util.List;

@Dao
public interface SubjectScheduleDao {
    @Insert
    void insertSubject(SubjectSchedule subject);

    @Query("SELECT * FROM subjects_schedule_table")
    LiveData<List<SubjectSchedule>> getAll();

    @Query("SELECT * FROM subjects_schedule_table WHERE week_day = :dayOfWeek")
    LiveData<List<SubjectSchedule>> getAllOnThisDay(DayOfWeek dayOfWeek);

    @Query("SELECT * FROM subjects_schedule_table WHERE name = :name")
    LiveData<List<SubjectSchedule>> getSubject(String name);


    @Update(entity = SubjectSchedule.class, onConflict = OnConflictStrategy.REPLACE)
    void update(SubjectSchedule subjectSchedule);

    @Query("DELETE  FROM subjects_schedule_table WHERE name LIKE :name")
    void deleteSubject(String name);
}
