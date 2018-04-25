package com.example.kevin.ebooklibrary;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;

@Dao
public interface WroteDao {

    @Insert
    void insertAll(Wrote... wrotes);

    @Delete
    void delete(Wrote wrote);
}
