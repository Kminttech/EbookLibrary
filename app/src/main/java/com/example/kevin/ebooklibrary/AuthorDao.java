package com.example.kevin.ebooklibrary;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface AuthorDao {
    @Query("SELECT * FROM Author")
    List<Author> getAll();

    @Query("SELECT * FROM Author WHERE first_name LIKE :first AND last_name LIKE :last LIMIT 1")
    Author findByName(String first, String last);

    @Insert
    void insertAll(Author... authors);

    @Delete
    void delete(Author author);
}
