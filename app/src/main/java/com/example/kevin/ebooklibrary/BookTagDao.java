package com.example.kevin.ebooklibrary;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;

@Dao
public interface BookTagDao {

    @Insert
    void insertAll(BookTag... bookTags);

    @Delete
    void delete(BookTag bookTag);
}
