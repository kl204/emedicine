package com.example.emedicine;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {UserProfile1.class}, version = 1)
public abstract class UserProfileDatabase extends RoomDatabase {
    public abstract UserProfileDao getUserProfileDao();
}
