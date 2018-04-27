package com.example.kevin.ebooklibrary;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class FiltersDisplay extends AppCompatActivity {

    private EBookDatabase db;
    private ArrayList data;
    private int curSelect;
    private String type;
    private TextView primary;
    private TextView secondary;
    private TextView tertiary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filters);
        Intent info = getIntent();
        String query = info.getStringExtra("query");
        type = info.getStringExtra("filterType");
        db = Room.databaseBuilder(getApplicationContext(),EBookDatabase.class, "EbookLib").allowMainThreadQueries().build();
        curSelect = 0;
        if(type.equals("Author")){
            data = (ArrayList) db.authorDao().getAll();
        }else if(type.equals("Series")){
            data = (ArrayList) db.seriesDao().getAll();
        }else {
            data = (ArrayList) db.tagDao().getAll();
        }
        primary = findViewById(R.id.primaryDataDisplay);
        secondary = findViewById(R.id.secondaryDataDisplay);
        tertiary = findViewById(R.id.thirdDataDisplay);
        updateDisplay();
    }

    private void updateDisplay(){
        if(type.equals("Author")){
            String firstName = ((Author) data.get(curSelect)).getFirstName();
            String lastName = ((Author) data.get(curSelect)).getLastName();
            int authorID = ((Author) data.get(curSelect)).getAuthorID();
            int count = db.wroteDao().getBookCountByAuthor(authorID);
            primary.setText(firstName);
            secondary.setText(lastName);
            tertiary.setText("Number of Books: " + count);
        }else if(type.equals("Series")){
            String seriesName = ((Series) data.get(curSelect)).getSeriesName();
            int seriesID = ((Series) data.get(curSelect)).getSeriesID();
            int count = db.bookSeriesDao().getBookCountBySeries(seriesID);
            primary.setText(seriesName);
            secondary.setText("Number of Books: " + count);
        }else{
            String desc = ((Tag) data.get(curSelect)).getDescriptor();
            int tagID = ((Tag) data.get(curSelect)).getTagID();
            int count = db.bookTagDao().getBookCountByTag(tagID);
            primary.setText(desc);
            secondary.setText("Number of Books: " + count);
        }
    }

    public void confirmSelect(View view){
        Intent nextIntent = new Intent(this, BooksDisplay.class);
        nextIntent.putExtra("queryType", type);
        if(type.equals("Author")){
            nextIntent.putExtra("authorID", ((Author) data.get(curSelect)).getAuthorID());
        }else if(type.equals("Series")){
            nextIntent.putExtra("seriesID", ((Series) data.get(curSelect)).getSeriesID());
        }else{
            nextIntent.putExtra("tagID", ((Tag) data.get(curSelect)).getTagID());
        }
        startActivity(nextIntent);
    }

    public void nextFilterData(View view) {
        if(++curSelect >= data.size()){
            curSelect = 0;
        }
        updateDisplay();
    }

    public void prevFilterData(View view) {
        if(--curSelect < 0){
            curSelect = data.size()-1;
        }
        updateDisplay();
    }

    public void launchMainMenu(View view) {
        db.close();
        Intent nextIntent = new Intent( this, MainActivity.class);
        startActivity(nextIntent);
    }
}
