package com.example.kevin.ebooklibrary;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void launchAllBooks(View view) {
        Intent nextIntent = new Intent(this, BooksDisplay.class);
        nextIntent.putExtra("query", "SELECT * FROM Book ORDER BY Title ASC");
        startActivity(nextIntent);
    }

    public void launchAuthorSort(View view) {
        Intent nextIntent = new Intent(this, FiltersDisplay.class);
        nextIntent.putExtra("filterType", "Author");
        nextIntent.putExtra("query", "SELECT * FROM Author ORDER BY LastName ASC");
        startActivity(nextIntent);
    }

    public void launchSeriesSort(View view) {
        Intent nextIntent = new Intent(this, FiltersDisplay.class);
        nextIntent.putExtra("filterType", "Series");
        nextIntent.putExtra("query", "SELECT * FROM Series ORDER BY Title ASC");
        startActivity(nextIntent);
    }

    public void launchTagSort(View view) {
        Intent nextIntent = new Intent(this, FiltersDisplay.class);
        nextIntent.putExtra("filterType", "Tag");
        nextIntent.putExtra("query", "SELECT * FROM Tag ORDER BY Descriptor ASC");
        startActivity(nextIntent);
    }

    public void launchInsertNew(View view) {
        Intent nextIntent = new Intent( this, Insert.class);
        startActivity(nextIntent);
    }

}
