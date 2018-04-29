package com.example.kevin.ebooklibrary;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class AddQuote extends AppCompatActivity {

    private EBookDatabase db;
    private EditText quoteEntry;
    private EditText pageEntry;
    private EditText lineEntry;
    private int bookID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_quote);
        db = Room.databaseBuilder(getApplicationContext(),EBookDatabase.class, "EbookLib").allowMainThreadQueries().build();
        quoteEntry = findViewById(R.id.enterQuote);
        pageEntry = findViewById(R.id.enterPage);
        lineEntry = findViewById(R.id.enterLine);
        Intent info = getIntent();
        bookID = info.getIntExtra("bID", -1);
    }

    public void resetDisplay() {
        quoteEntry.setText("");
        pageEntry.setText("");
        lineEntry.setText("");
    }

    public void submitQuote(View view) {
        Quote newQuote = new Quote(bookID, quoteEntry.getText().toString(), Integer.parseInt(pageEntry.getText().toString()), Integer.parseInt(lineEntry.getText().toString()));
        db.quoteDao().insertAll(newQuote);
        resetDisplay();
    }

    public void closeActivity(View view) {
        db.close();
        finish();
    }
}
