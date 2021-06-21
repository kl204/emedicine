package com.example.emedicine;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserProfileDao {
    @Insert
    void insert(UserProfile1 userProfile1);

    @Update
    void update(UserProfile1 userProfile1);

    @Delete
    void delete(UserProfile1 userProfile1);

    @Query("SELECT * FROM UserProfile1")
    List<UserProfile1> getAll();
}
