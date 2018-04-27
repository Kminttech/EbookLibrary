package com.example.kevin.ebooklibrary;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

@Dao
public interface BookTagDao {

    @Query("SELECT count(*) FROM BookTag WHERE tagID = :tID LIMIT 1")
    int getBookCountByTag(int tID);

    @Insert
    void insertAll(BookTag... bookTags);

    @Delete
    void delete(BookTag bookTag);
}
