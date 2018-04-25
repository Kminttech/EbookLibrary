package com.example.kevin.ebooklibrary;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Book {
    @PrimaryKey
    private int bookID;

    private String title;

    private String file;

    private String cover;
}
