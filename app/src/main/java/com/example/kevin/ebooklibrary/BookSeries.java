package com.example.kevin.ebooklibrary;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;

@Entity(primaryKeys = { "bookID", "seriesID" },
        foreignKeys = {
                @ForeignKey(entity = Book.class,
                        parentColumns = "bookID",
                        childColumns = "bookID"),
                @ForeignKey(entity = Series.class,
                        parentColumns = "seriesID",
                        childColumns = "seriesID")
        })
public class BookSeries {
    public final int bookID;
    public final int seriesID;

    public BookSeries(int bookID, int seriesID) {
        this.bookID = bookID;
        this.seriesID = seriesID;
    }
}
