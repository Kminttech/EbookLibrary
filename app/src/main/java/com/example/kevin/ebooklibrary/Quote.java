package com.example.kevin.ebooklibrary;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

@Entity(foreignKeys = {
            @ForeignKey(entity = Book.class,
                    parentColumns = "bookID",
                    childColumns = "bookID")
        })
public class Quote {
    @PrimaryKey(autoGenerate = true)
    private int quoteID;
    private int bookID;
    private String quote;
    private int pageNum;
    private int lineNum;

    public Quote(int bookID, String quote, int pageNum, int lineNum){
        this.bookID = bookID;
        this.quote = quote;
        this.pageNum = pageNum;
        this.lineNum = lineNum;
    }

    public String getQuote() {
        return quote;
    }

    public int getPageNum() {
        return pageNum;
    }

    public int getLineNum() {
        return lineNum;
    }

    public int getQuoteID() {
        return quoteID;
    }

    public int getBookID() {
        return bookID;
    }

    public void setQuoteID(int quoteID) {
        this.quoteID = quoteID;
    }
}
