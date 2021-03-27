package com.project.universitystudentassistant.data.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.project.universitystudentassistant.models.UniversityEntityPrep;

import java.util.List;

@Dao
public interface UniversityPrepDao {

    @Insert
    void insertUniversity(UniversityEntityPrep entity);

    @Query("SELECT * FROM all_university_database_table")
    LiveData<List<UniversityEntityPrep>> getAll();

    @Query("SELECT * FROM all_university_database_table WHERE name LIKE :uniName LIMIT 1")
    LiveData<UniversityEntityPrep> getUniversityByName(String uniName);

    @Query("SELECT * FROM all_university_database_table WHERE city LIKE :uniCity")
    LiveData<List<UniversityEntityPrep>> getUniversitiesByCity(String uniCity);

    @Query("SELECT * FROM all_university_database_table WHERE state LIKE :uniState")
    LiveData<List<UniversityEntityPrep>> getUniversitiesByState(String uniState);

    @Query("SELECT * FROM all_university_database_table WHERE cost BETWEEN :uniPrice AND 100000000")
    LiveData<List<UniversityEntityPrep>> getUniversitiesOverPrice(int uniPrice);

    @Query("SELECT * FROM all_university_database_table WHERE cost BETWEEN :uniPrice AND 0")
    LiveData<List<UniversityEntityPrep>> getUniversitiesUnderPrice(int uniPrice);

    @Query("DELETE  FROM all_university_database_table WHERE name LIKE :uniName")
    void deleteUniversityByName(String uniName);

    @Query("DELETE FROM all_university_database_table")
    void deleteAllUniversities();

}
