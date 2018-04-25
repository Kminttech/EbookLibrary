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

    @Insert
    void insertAll(Book... books);

    @Delete
    void delete(Book book);
}
