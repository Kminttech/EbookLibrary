package com.example.kevin.ebooklibrary;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface BookDao {
    @Query("SELECT * FROM Book")
    List<Book> getAll();

    @Query("SELECT * FROM Book WHERE bookID IN (:bookIds)")
    List<Book> loadAllByIds(int[] bookIds);

    @Query("SELECT * FROM Book WHERE title = :t AND file = :f AND cover = :c LIMIT 1")
    Book loadByData(String t, String f, String c);

    @Query("SELECT * FROM Book NATURAL JOIN Wrote WHERE authorID = :aID ORDER BY Title DESC")
    List<Book> loadByAuthorID(int aID);

    @Query("SELECT * FROM Book NATURAL JOIN BookSeries WHERE seriesID = :sID ORDER BY TITLE DESC")
    List<Book> loadBySeriesID(int sID);

    @Query("SELECT * FROM Book NATURAL JOIN BookTag WHERE tagID = :tID ORDER BY Title DESC")
    List<Book> loadByTagID(int tID);

    @Insert
    void insertAll(Book... books);

    @Delete
    void delete(Book book);
}
