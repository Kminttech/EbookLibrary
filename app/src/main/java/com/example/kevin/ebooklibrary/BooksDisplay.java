package com.example.kevin.ebooklibrary;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class BooksDisplay extends AppCompatActivity {

    private EBookDatabase db;
    private ArrayList data;
    int curSelect;
    ImageView coverImageDisplay;
    TextView bookInfoDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent info = getIntent();
        String queryType = info.getStringExtra("queryType");
        db = Room.databaseBuilder(getApplicationContext(),EBookDatabase.class, "EbookLib").build();
        curSelect = 0;
        if(queryType.equals("Author")){
            data = (ArrayList) db.bookDao().loadByAuthorID(info.getIntExtra("authorID", 0));
        }else if(queryType.equals("Series")){
            data = (ArrayList) db.bookDao().loadBySeriesID(info.getIntExtra("seriesID", 0));
        }else if(queryType.equals("Tag")){
            data = (ArrayList) db.bookDao().loadByTagID(info.getIntExtra("tagID", 0));
        }else {
            data = (ArrayList) db.bookDao().getAll();
        }
        coverImageDisplay = findViewById(R.id.coverDisplay);
        bookInfoDisplay = findViewById(R.id.infoDisplay);
        setContentView(R.layout.activity_books);
        updateDisplay();
    }

    private void updateDisplay(){

    }

    public void nextBookData(View view) {
        if(++curSelect >= data.size()){
            curSelect = 0;
        }
        updateDisplay();
    }

    public void prevBookData(View view) {
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
