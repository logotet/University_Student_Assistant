package com.project.universitystudentassistant.data.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.project.universitystudentassistant.models.UniversityEntity;

import java.util.List;

@Dao
public interface UniversityDao {

    @Insert
    void insertUniversity(UniversityEntity entity);

    @Query("SELECT * FROM my_universities_table")
    LiveData<List<UniversityEntity>> getAll();

    @Query("SELECT * FROM my_universities_table WHERE name LIKE :uniName LIMIT 1")
    LiveData<UniversityEntity> getUniversityByName(String uniName);

    @Query("SELECT * FROM my_universities_table WHERE city LIKE :uniCity")
    LiveData<List<UniversityEntity>> getUniversitiesByCity(String uniCity);

    @Query("SELECT * FROM my_universities_table WHERE state LIKE :uniState")
    LiveData<List<UniversityEntity>> getUniversitiesByState(String uniState);

    @Query("SELECT * FROM my_universities_table WHERE cost BETWEEN :uniPrice AND 100000000")
    LiveData<List<UniversityEntity>> getUniversitiesOverPrice(int uniPrice);

    @Query("SELECT * FROM my_universities_table WHERE cost BETWEEN :uniPrice AND 0")
    LiveData<List<UniversityEntity>> getUniversitiesUnderPrice(int uniPrice);

    @Query("DELETE  FROM my_universities_table WHERE name LIKE :uniName")
    void deleteUniversityByName(String uniName);

    @Query("DELETE FROM my_universities_table")
    void deleteAllUniversities();


}
