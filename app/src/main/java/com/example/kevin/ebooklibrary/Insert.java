package com.example.kevin.ebooklibrary;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class Insert extends AppCompatActivity {

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = SQLiteDatabase.openDatabase("EBookLib.db", null, 0);
        setContentView(R.layout.activity_insert);
    }
}
