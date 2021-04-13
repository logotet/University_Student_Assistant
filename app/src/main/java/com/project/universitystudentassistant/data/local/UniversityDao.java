package com.project.universitystudentassistant.data.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.project.universitystudentassistant.data.entities.UniversityEntity;
import com.project.universitystudentassistant.data.entities.UniversityEntityPrep;

import java.util.List;

@Dao
public interface UniversityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUniversity(UniversityEntity entityPrep);

    @Query("SELECT * FROM my_universities_table")
    LiveData<List<UniversityEntity>> getAll();

    @Update
//            (entity = UniversityEntity.class, onConflict = OnConflictStrategy.REPLACE)
    void update(UniversityEntity entity);

    @Query("SELECT EXISTS(SELECT 1 FROM my_universities_table WHERE name = :name)")
    LiveData<Boolean> isUniSelected(String name);

    @Query("SELECT * FROM my_universities_table LIMIT 5")
    LiveData<List<UniversityEntity>> getFive();

    @Query("SELECT * FROM my_universities_table WHERE name LIKE :uniName LIMIT 1")
    LiveData<UniversityEntity> getUniversityByName(String uniName);

    @Query("SELECT * FROM my_universities_table WHERE image LIKE :saved")
    LiveData<List<UniversityEntity>> getSavedUniversities(String saved);

    @Query("SELECT * FROM my_universities_table WHERE city LIKE :uniCity")
    LiveData<List<UniversityEntity>> getUniversitiesByCity(String uniCity);

    @Query("SELECT * FROM my_universities_table WHERE state LIKE :uniState")
    LiveData<List<UniversityEntity>> getUniversitiesByState(String uniState);

    @Query("SELECT * FROM my_universities_table WHERE cost BETWEEN :startPrice AND :endPrice")
    LiveData<List<UniversityEntity>> getUniversitiesInPriceRange(int startPrice, int endPrice);

    @Query("SELECT * FROM my_universities_table WHERE cost BETWEEN :startRate AND :endRate")
    LiveData<List<UniversityEntity>> getUniversitiesInAccRange(int startRate, int endRate);

    @Query("SELECT * FROM my_universities_table WHERE cost BETWEEN :startRate AND :endRate")
    LiveData<List<UniversityEntity>> getUniversitiesInGradRange(int startRate, int endRate);

    @Query("SELECT * FROM my_universities_table WHERE state IN (:states)")
    LiveData<List<UniversityEntity>> getUniversitiesByStates(String...states);

    @Query("SELECT MIN(cost) FROM my_universities_table LIMIT 1")
    LiveData<Integer> getMinCost();

    @Query("SELECT MAX(cost) FROM my_universities_table LIMIT 1")
    LiveData<Integer> getMaxCost();

    @Query("SELECT MIN(acceptance_rate) FROM my_universities_table LIMIT 1")
    LiveData<Integer> getMinAccRate();

    @Query("SELECT MAX(acceptance_rate) FROM my_universities_table LIMIT 1")
    LiveData<Integer> getMaxAccRate();

    @Query("SELECT MIN(graduation_rate) FROM my_universities_table LIMIT 1")
    LiveData<Integer> getMinGradRate();

    @Query("SELECT MAX(graduation_rate) FROM my_universities_table LIMIT 1")
    LiveData<Integer> getMaxGradRate();

    @Query("SELECT * FROM my_universities_table WHERE cost BETWEEN :uniPrice AND 0")
    LiveData<List<UniversityEntity>> getUniversitiesUnderPrice(int uniPrice);

    @Query("SELECT * FROM my_universities_table WHERE cost BETWEEN :uniPrice AND 1000000000")
    LiveData<List<UniversityEntity>> getUniversitiesOverPrice(int uniPrice);

    @Query("DELETE  FROM my_universities_table WHERE name LIKE :uniName")
    void deleteUniversityByName(String uniName);

    @Query("DELETE FROM my_universities_table")
    void deleteAllUniversities();


}
