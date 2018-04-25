package com.example.kevin.ebooklibrary;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Tag {
    @PrimaryKey
    private int tagID;

    private String descriptor;
}
