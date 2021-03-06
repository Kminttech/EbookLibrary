package com.example.kevin.ebooklibrary;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface TagDao {
    @Query("SELECT * FROM Tag")
    List<Tag> getAll();

    @Query("SELECT * FROM Tag WHERE descriptor = :desc LIMIT 1")
    Tag getByDescriptor(String desc);

    @Insert
    void insertAll(Tag... tags);

    @Delete
    void delete(Tag tag);
}
