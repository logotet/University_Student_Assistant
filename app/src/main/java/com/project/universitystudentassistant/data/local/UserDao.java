package com.project.universitystudentassistant.data.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.project.universitystudentassistant.models.User;

@Dao
public interface UserDao {

    @Insert
    void insertUser(User user);

    @Query("SELECT * FROM USER_TABLE LIMIT 1")
    LiveData<User> getUser();

}
