package com.example.kevin.ebooklibrary;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL = 1;
    final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale( this,  Manifest.permission.READ_EXTERNAL_STORAGE)) {

            } else {
                ActivityCompat.requestPermissions(this , new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_READ_EXTERNAL);
            }
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale( this,  Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

            } else {
                ActivityCompat.requestPermissions(this , new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL);
            }
        }
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
