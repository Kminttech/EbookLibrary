package com.example.kevin.ebooklibrary;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;

@Dao
public interface BookSeriesDao {

    @Insert
    void insertAll(BookSeries... bookSeries);

    @Delete
    void delete(BookSeries bookSeries);
}
