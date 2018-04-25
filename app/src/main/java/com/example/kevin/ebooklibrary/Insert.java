package com.example.kevin.ebooklibrary;

import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.net.Uri;

import java.util.ArrayList;

public class Insert extends AppCompatActivity {
    final int ACTIVITY_CHOOSE_FILE = 1;
    final int ACTIVITY_CHOOSE_COVER = 2;

    private EBookDatabase db;
    private Uri filePath;
    private Uri coverPath;
    private ArrayList<Integer> tagIDs;
    private EditText titleEntry;
    private EditText firstNameEntry;
    private EditText lastNameEntry;
    private EditText seriesEntry;
    private EditText tagEntry;
    private TextView fileDisplay;
    private TextView coverDisplay;
    private TextView tagDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = Room.databaseBuilder(getApplicationContext(),EBookDatabase.class, "EbookLib").build();
        tagIDs = new ArrayList<>();
        titleEntry = findViewById(R.id.enterTitle);
        firstNameEntry = findViewById(R.id.enterFirstName);
        lastNameEntry = findViewById(R.id.enterLastName);
        seriesEntry = findViewById(R.id.enterSeries);
        tagEntry = findViewById(R.id.newTagEntry);
        fileDisplay = findViewById(R.id.filePathDisplay);
        coverDisplay = findViewById(R.id.displayCoverPath);
        tagDisplay = findViewById(R.id.tagListDisplay);
        setContentView(R.layout.activity_insert);
    }

    public void selectFile(View view) {
        Intent chooseFile;
        Intent intent;
        chooseFile = new Intent(Intent.ACTION_GET_CONTENT);
        chooseFile.setType("file/*");
        intent = Intent.createChooser(chooseFile, "Choose a file");
        startActivityForResult(intent, ACTIVITY_CHOOSE_FILE);
    }

    public void selectCover(View view) {
        Intent chooseFile;
        Intent intent;
        chooseFile = new Intent(Intent.ACTION_GET_CONTENT);
        chooseFile.setType("file/*");
        intent = Intent.createChooser(chooseFile, "Choose a cover");
        startActivityForResult(intent, ACTIVITY_CHOOSE_COVER);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(requestCode) {
            case ACTIVITY_CHOOSE_FILE: {
                if (resultCode == RESULT_OK){
                    filePath = data.getData();
                    fileDisplay.setText(filePath.toString());
                }
            }
            case ACTIVITY_CHOOSE_COVER: {
                if (requestCode == RESULT_OK){
                    coverPath = data.getData();
                    coverDisplay.setText(coverPath.toString());
                }
            }
        }
    }

    public void addTag(View view) {
        String curDesc = tagEntry.getText().toString();
        Tag curTag = db.tagDao().getByDescriptor(curDesc);
        if(curTag == null){
            //db.execSQL("INSERT INTO Tag (Descriptor) VALUES (?)", req);
            curTag = new Tag(curDesc);
            db.tagDao().insertAll(curTag);

        }
        tagIDs.add(tagCheck.getInt(tagCheck.getColumnIndex("Descriptor")));
        tagDisplay.setText(tagDisplay.getText() + " " + curDesc);
    }

    public void confirmEntry(View view) {
        String[] authorValues = new String[2];
        authorValues[0] = firstNameEntry.getText().toString();
        authorValues[1] = lastNameEntry.getText().toString();
        Cursor authorInfo = db.rawQuery("SELECT AuthorID FROM Author WHERE FirstName = ? AND LastName = ?", authorValues);
        if(!authorInfo.moveToFirst()){
            db.execSQL("INSERT INTO Author (FirstName, LastName) VALUES (?, ?)", authorValues);
            authorInfo = db.rawQuery("SELECT AuthorID FROM Author WHERE FirstName = ? AND LastName = ?", authorValues);
            authorInfo.moveToFirst();
        }
        String[] seriesValues = new String[1];
        seriesValues[0] = seriesEntry.getText().toString();
        Cursor seriesInfo = db.rawQuery("SELECT SeriesID FROM Series WHERE SeriesName = ?", seriesValues);
        if(!seriesInfo.moveToFirst()){
            db.execSQL("INSERT INTO Series (SeriesName) VALUES (?)", seriesValues);
            seriesInfo = db.rawQuery("SELECT AuthorID FROM Author WHERE FirstName = ? AND LastName = ?", authorValues);
            seriesInfo.moveToFirst();
        }
        String[] bookValues = new String[3];
        bookValues[0] = titleEntry.getText().toString();
        bookValues[1] = filePath.toString();
        bookValues[2] = coverPath.toString();
        db.execSQL("INSERT INTO Book (Title, File, Cover) VALUES (?, ?, ?)", bookValues);
        Cursor bookInfo = db.rawQuery("SELECT BookID FROM Book WHERE Title = ? AND File = ? AND Cover = ?", bookValues);
        bookInfo.moveToFirst();
        int bookID = bookInfo.getInt(bookInfo.getColumnIndex("BookID"));
        int authorID = authorInfo.getInt(authorInfo.getColumnIndex("AuthorID"));
        int seriesID = seriesInfo.getInt(seriesInfo.getColumnIndex("SeriesID"));
        db.execSQL("INSERT INTO Wrote (BookID, AuthorID) VALUES (" + bookID + "," + authorID + ")");
        db.execSQL("INSERT INTO BookSeries (BookID, SeriesID) VALUES (" + bookID + "," + seriesID + ")");
        for(int curTagID : tagIDs){
            db.execSQL("INSERT INTO BookTag (BookID, TagID) VALUES (" + bookID + "," + curTagID + ")");
        }
        resetInterface();
    }

    public void resetInterface(){
        filePath = null;
        coverPath = null;
        tagIDs = new ArrayList<>();
        titleEntry.setText("");
        seriesEntry.setText("");
        firstNameEntry.setText("");
        lastNameEntry.setText("");
        fileDisplay.setText("File");
        coverDisplay.setText("Cover");
        tagDisplay.setText("Tags: ");
    }

    public void launchMainMenu(View view) {
        db.close();
        Intent nextIntent = new Intent( this, MainActivity.class);
        startActivity(nextIntent);
    }
}
