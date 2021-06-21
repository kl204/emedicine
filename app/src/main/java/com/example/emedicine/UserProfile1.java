package com.example.emedicine;

import android.graphics.Bitmap;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class UserProfile1 {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String code;
    public String med_name;
    public String ent_name;
    public String img;
}