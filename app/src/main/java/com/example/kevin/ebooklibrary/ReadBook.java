package com.example.kevin.ebooklibrary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mertakdut.BookSection;
import com.github.mertakdut.Reader;

public class ReadBook extends AppCompatActivity {

    private TextView bookContent;
    private Reader reader;
    private BookSection bookSection;
    private int curSection;
    private String curContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_book);
        curSection = 1;
        bookContent = findViewById(R.id.bookTextDisplay);
        reader = new Reader();
        reader.setMaxContentPerSection(1000);
        reader.setIsIncludingTextContent(true);
        Intent info = getIntent();
        try {
            reader.setFullContent(info.getStringExtra("file"));
            updateDisplay();
        }catch (Exception e){
            Toast.makeText(this, "Error Reading File", Toast.LENGTH_LONG);
        }
    }

    public void updateDisplay(){
        try {
            bookSection = reader.readSection(curSection);
            curContent = bookSection.getSectionTextContent();
            bookContent.setText(curContent);
        }catch (Exception e){
            Toast.makeText(this, "Error Reading File", Toast.LENGTH_LONG);
        }
    }

    public void nextSection(View view){
        curSection++;
        updateDisplay();
    }

    public void prevSection(View view){
        if(curSection > 0){
            curSection--;
            updateDisplay();
        }
    }

    public void closeBook(View view){
        finish();
    }
}
