package com.example.kevin.ebooklibrary;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class ViewQuote extends AppCompatActivity {

    private EBookDatabase db;
    private ArrayList<Quote> data;
    private int curSelect;
    private int bookID;
    private TextView quoteView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_quotes);
        quoteView = findViewById(R.id.quoteDisplay);
        Intent info = getIntent();
        bookID = info.getIntExtra("bID", -1);
        db = Room.databaseBuilder(getApplicationContext(),EBookDatabase.class, "EbookLib").allowMainThreadQueries().build();
        curSelect = 0;
        data = (ArrayList) db.quoteDao().getQuotesOfBook(bookID);
        if(data.size() > 0){
            updateDisplay();
        }
    }

    public void updateDisplay() {
        Quote curQuote = data.get(curSelect);
        quoteView.setText("Page: " + curQuote.getPageNum() + " Line: " + curQuote.getLineNum() + "\n" + curQuote.getQuote());
    }

    public void nextQuoteData(View view) {
        if(++curSelect >= data.size()){
            curSelect = 0;
        }
        updateDisplay();
    }

    public void prevQuoteData(View view) {
        if(--curSelect < 0){
            curSelect = data.size()-1;
        }
        updateDisplay();
    }

    public void closeActivity(View view) {
        db.close();
        finish();
    }
}
