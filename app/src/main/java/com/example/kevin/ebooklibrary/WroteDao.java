package com.example.kevin.ebooklibrary;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

@Dao
public interface WroteDao {

    @Query("SELECT count(*) FROM Wrote WHERE authorID = :aID LIMIT 1")
    int getBookCountByAuthor(int aID);

    @Insert
    void insertAll(Wrote... wrotes);

    @Delete
    void delete(Wrote wrote);
}
