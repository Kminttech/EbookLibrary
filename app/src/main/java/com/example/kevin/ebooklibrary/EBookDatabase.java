package com.example.kevin.ebooklibrary;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {Book.class, Author.class, Series.class, Tag.class, Wrote.class, BookSeries.class, BookTag.class}, version = 1)
public abstract class EBookDatabase extends RoomDatabase {
    public abstract BookDao bookDao();
    public abstract AuthorDao authorDao();
    public abstract SeriesDao seriesDao();
    public abstract TagDao tagDao();
    public abstract WroteDao wroteDao();
    public abstract BookSeriesDao bookSeriesDao();
    public abstract BookTagDao bookTagDao();
}
