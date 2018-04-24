package com.example.kevin.ebooklibrary;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class FiltersDisplay extends AppCompatActivity {

    private SQLiteDatabase db;
    private Cursor data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent info = getIntent();
        String query = info.getStringExtra("query");
        db = SQLiteDatabase.openDatabase("EBookLib.db", null, 0);
        data = db.rawQuery( query, null);
        data.moveToFirst();
        setContentView(R.layout.activity_filters);
    }

    private void updateDisplay(){

    }

    public void nextFilterData(View view) {
        if(!data.moveToNext()){
            data.moveToFirst();
        }
        updateDisplay();
    }

    public void prevFilterData(View view) {
        if(!data.moveToPrevious()){
            data.moveToLast();
        }
        updateDisplay();
    }

    public void launchMainMenu(View view) {
        db.close();
        Intent nextIntent = new Intent( this, MainActivity.class);
        startActivity(nextIntent);
    }
}
