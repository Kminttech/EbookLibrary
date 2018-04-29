package com.example.kevin.ebooklibrary;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

@Dao
public interface BookSeriesDao {

    @Query("SELECT numInSeries FROM BookSeries WHERE bookID = :bID LIMIT 1")
    int getNumInSeries(int bID);

    @Query("SELECT count(*) FROM BookSeries WHERE seriesID = :sID LIMIT 1")
    int getBookCountBySeries(int sID);

    @Insert
    void insertAll(BookSeries... bookSeries);

    @Delete
    void delete(BookSeries bookSeries);
}
