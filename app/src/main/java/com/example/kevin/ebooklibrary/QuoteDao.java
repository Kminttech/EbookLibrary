package com.example.kevin.ebooklibrary;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface QuoteDao {

    @Query("SELECT * FROM Quote WHERE bookID = :bID")
    List<Quote> getQuotesOfBook(int bID);

    @Insert
    void insertAll(Quote... quotes);

    @Delete
    void delete(Quote quote);
}
