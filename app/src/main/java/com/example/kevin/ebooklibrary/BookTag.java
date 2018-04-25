package com.example.kevin.ebooklibrary;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;

@Entity(primaryKeys = { "bookID", "tagID" },
        foreignKeys = {
                @ForeignKey(entity = Book.class,
                        parentColumns = "bookID",
                        childColumns = "bookID"),
                @ForeignKey(entity = Tag.class,
                        parentColumns = "tagID",
                        childColumns = "tagID")
        })
public class BookTag {
    public final int bookID;
    public final int tagID;

    public BookTag(int bookID, int tagID) {
        this.bookID = bookID;
        this.tagID = tagID;
    }
}
