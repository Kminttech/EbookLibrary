package com.example.kevin.ebooklibrary;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface SeriesDao {
    @Query("SELECT * FROM Series")
    List<Series> getAll();

    @Query("SELECT * FROM Series WHERE series_name = :seriesName LIMIT 1")
    Series findByName(String seriesName);

    @Query("SELECT * FROM Series NATURAL JOIN BookSeries WHERE bookID = :bID LIMIT 1")
    Series findSeriesOfBook(int bID);

    @Insert
    void insertAll(Series... series);

    @Delete
    void delete(Series series);
}
