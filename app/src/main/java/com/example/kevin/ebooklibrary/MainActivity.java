package com.example.kevin.ebooklibrary;

import android.content.Intent;
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
        nextIntent.putExtra("queryType", "All");
        startActivity(nextIntent);
    }

    public void launchAuthorSort(View view) {
        Intent nextIntent = new Intent(this, FiltersDisplay.class);
        nextIntent.putExtra("filterType", "Author");
        startActivity(nextIntent);
    }

    public void launchSeriesSort(View view) {
        Intent nextIntent = new Intent(this, FiltersDisplay.class);
        nextIntent.putExtra("filterType", "Series");
        startActivity(nextIntent);
    }

    public void launchTagSort(View view) {
        Intent nextIntent = new Intent(this, FiltersDisplay.class);
        nextIntent.putExtra("filterType", "Tag");
        startActivity(nextIntent);
    }

    public void launchInsertNew(View view) {
        Intent nextIntent = new Intent( this, Insert.class);
        startActivity(nextIntent);
    }

}
