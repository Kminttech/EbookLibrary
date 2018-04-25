package com.example.kevin.ebooklibrary;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Series {
    @PrimaryKey(autoGenerate = true)
    private int seriesID;

    @ColumnInfo(name = "series_name")
    private String seriesName;

    public Series(String seriesName){
        this.seriesName = seriesName;
    }

    public int getSeriesID() {
        return seriesID;
    }

    public String getSeriesName() {
        return seriesName;
    }
}
