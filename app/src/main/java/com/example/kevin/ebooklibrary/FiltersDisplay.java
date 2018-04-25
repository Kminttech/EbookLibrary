package com.example.kevin.ebooklibrary;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class FiltersDisplay extends AppCompatActivity {

    private SQLiteDatabase db;
    private Cursor data;
    private String type;
    private TextView primary;
    private TextView secondary;
    private TextView tertiary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent info = getIntent();
        String query = info.getStringExtra("query");
        type = info.getStringExtra("filterType");
        db = SQLiteDatabase.openDatabase("EBookLib.db", null, 0);
        data = db.rawQuery( query, null);
        data.moveToFirst();
        primary = findViewById(R.id.primaryDataDisplay);
        secondary = findViewById(R.id.secondaryDataDisplay);
        tertiary = findViewById(R.id.thirdDataDisplay);
        setContentView(R.layout.activity_filters);
        updateDisplay();
    }

    private void updateDisplay(){
        if(type.equals("Author")){
            String firstName = data.getString(data.getColumnIndex("FirstName"));
            String lastName = data.getString(data.getColumnIndex("LastName"));
            int authorID = data.getInt(data.getColumnIndex("AuthorID"));
            primary.setText(firstName);
            secondary.setText(lastName);
            Cursor count = db.rawQuery("SELECT count(*) FROM Wrote WHERE AuthorID = " + authorID, null);
            tertiary.setText("Number of Books: " + count.getInt(0));
        }else if(type.equals("Series")){
            String seriesName = data.getString(data.getColumnIndex("SeriesName"));
            int seriesID = data.getInt(data.getColumnIndex("SeriesID"));
            Cursor count = db.rawQuery("SELECT count(*) FROM Wrote WHERE AuthorID = " + seriesID, null);
            primary.setText(seriesName);
            secondary.setText("Number of Books: " + count.getInt(0));
        }else{
            String desc = data.getString(data.getColumnIndex("Descriptor"));
            int tagID = data.getInt(data.getColumnIndex("TagID"));
            Cursor count = db.rawQuery("SELECT count(*) FROM Wrote WHERE AuthorID = " + tagID, null);
            primary.setText(desc);
            secondary.setText("Number of Books: " + count.getInt(0));
        }
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
