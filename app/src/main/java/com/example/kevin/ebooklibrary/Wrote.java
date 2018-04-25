package com.example.kevin.ebooklibrary;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;

@Entity(primaryKeys = { "bookID", "tagID" },
        foreignKeys = {
                @ForeignKey(entity = Book.class,
                        parentColumns = "id",
                        childColumns = "bookID"),
                @ForeignKey(entity = Author.class,
                        parentColumns = "id",
                        childColumns = "authorID")
        })
public class Wrote {
    public final int bookID;
    public final int authorID;

    public Wrote(int bookID, int authorID) {
        this.bookID = bookID;
        this.authorID = authorID;
    }
}
