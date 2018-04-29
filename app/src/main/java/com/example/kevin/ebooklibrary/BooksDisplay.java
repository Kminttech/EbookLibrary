package com.example.kevin.ebooklibrary;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class BooksDisplay extends AppCompatActivity {

    private EBookDatabase db;
    private ArrayList<Book> data;
    int curSelect;
    ImageView coverImageDisplay;
    TextView bookInfoDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);
        Intent info = getIntent();
        String queryType = info.getStringExtra("queryType");
        db = Room.databaseBuilder(getApplicationContext(),EBookDatabase.class, "EbookLib").allowMainThreadQueries().build();
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
        if(data.size() > 0){
            updateDisplay();
        }
    }

    private void updateDisplay(){
        File imgFile = new File(data.get(curSelect).getCover());
        if(imgFile.exists()){
            Bitmap myImg = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            coverImageDisplay.setImageBitmap(myImg);
        }else {
            Toast.makeText(this, "Cover File Not Found", Toast.LENGTH_LONG);
        }
        Author curAuthor = db.authorDao().findAuthorOfBook(data.get(curSelect).getBookID());
        Series curSeries = db.seriesDao().findSeriesOfBook(data.get(curSelect).getBookID());
        int numInSeries = db.bookSeriesDao().getNumInSeries(data.get(curSelect).getBookID());
        int outOf = db.bookSeriesDao().getNumInSeries(data.get(curSelect).getBookID());
        bookInfoDisplay.setText("Author: " + curAuthor.getFirstName() + " " + curAuthor.getLastName() + "   Series: " + curSeries.getSeriesName() + "(" + numInSeries +" of " + outOf + ")");
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

    public void launchAddQuote(View view) {
        int curBookID = data.get(curSelect).getBookID();
        Intent nextIntent = new Intent(this, AddQuote.class);
        nextIntent.putExtra("bID", curBookID);
        startActivity(nextIntent);
    }

    public void launchViewQuote(View view) {
        int curBookID = data.get(curSelect).getBookID();
        Intent nextIntent = new Intent(this, ViewQuote.class);
        nextIntent.putExtra("bID", curBookID);
        startActivity(nextIntent);
    }

    public void closeActivity(View view) {
        db.close();
        finish();
    }
}
