package com.example.kevin.ebooklibrary;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class FiltersDisplay extends AppCompatActivity {

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent info = getIntent();
        String query = info.getStringExtra("query");
        db = SQLiteDatabase.openDatabase("EBookLib.db", null, 0);
        Cursor data = db.rawQuery( query, null);
        RecyclerView display = (RecyclerView) findViewById(R.id.filterList);
        display.setLayoutManager(new LinearLayoutManager(this));
        while (data.moveToNext()){

        }
        setContentView(R.layout.activity_filters);
    }
}
