package com.example.kevin.ebooklibrary;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Tag {
    @PrimaryKey(autoGenerate = true)
    private int tagID;

    private String descriptor;

    public Tag(String descriptor){
        this.descriptor = descriptor;
    }

    public int getTagID() {
        return tagID;
    }

    public String getDescriptor() {
        return descriptor;
    }

    public void setTagID(int tagID) {
        this.tagID = tagID;
    }
}
