package com.example.kevin.ebooklibrary;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Series {
    @PrimaryKey
    private int seriesID;

    @ColumnInfo(name = "series_name")
    private String seriesName;
}
