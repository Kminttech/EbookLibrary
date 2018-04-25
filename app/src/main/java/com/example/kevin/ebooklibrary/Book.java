package com.example.kevin.ebooklibrary;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Book {
    @PrimaryKey(autoGenerate = true)
    private int bookID;
    private String title;
    private String file;
    private String cover;

    public Book(String title,String file,String cover){
        this.title = title;
        this.file = file;
        this.cover = cover;
    }

    public int getBookID() {
        return bookID;
    }

    public String getTitle() {
        return title;
    }

    public String getFile() {
        return file;
    }

    public String getCover() {
        return cover;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }
}
