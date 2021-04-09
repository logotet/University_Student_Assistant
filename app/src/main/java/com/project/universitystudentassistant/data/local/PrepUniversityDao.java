package com.project.universitystudentassistant.data.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.project.universitystudentassistant.data.entities.UniversityEntity;
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

    @Query("SELECT * FROM prepopulated_universities_table WHERE name LIKE :uniName LIMIT 1")
    LiveData<UniversityEntityPrep> getUniversityByName(String uniName);

    @Query("SELECT * FROM prepopulated_universities_table WHERE city LIKE :uniCity")
    LiveData<List<UniversityEntityPrep>> getUniversitiesByCity(String uniCity);

    @Query("SELECT * FROM prepopulated_universities_table WHERE state LIKE :uniState")
    LiveData<List<UniversityEntityPrep>> getUniversitiesByState(String uniState);

    @Query("SELECT * FROM prepopulated_universities_table WHERE cost BETWEEN :startPrice AND :endPrice")
    LiveData<List<UniversityEntityPrep>> getUniversitiesOverPrice(int startPrice, int endPrice);

    @Query("SELECT * FROM prepopulated_universities_table WHERE cost BETWEEN :uniPrice AND 0")
    LiveData<List<UniversityEntityPrep>> getUniversitiesUnderPrice(int uniPrice);

    @Query("SELECT * FROM prepopulated_universities_table WHERE cost BETWEEN :uniPrice AND 1000000000")
    LiveData<List<UniversityEntityPrep>> getUniversitiesOverPrice(int uniPrice);

    @Query("DELETE  FROM prepopulated_universities_table WHERE name LIKE :uniName")
    void deleteUniversityByName(String uniName);

    @Query("DELETE FROM prepopulated_universities_table")
    void deleteAllUniversities();
}
