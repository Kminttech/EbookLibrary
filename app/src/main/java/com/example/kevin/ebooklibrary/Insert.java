package com.example.kevin.ebooklibrary;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class Insert extends AppCompatActivity {
    final int ACTIVITY_CHOOSE_FILE = 1;
    final int ACTIVITY_CHOOSE_COVER = 2;

    private EBookDatabase db;
    private String filePath;
    private String coverPath;
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
        setContentView(R.layout.activity_insert);
        db = Room.databaseBuilder(getApplicationContext(),EBookDatabase.class, "EbookLib").allowMainThreadQueries().build();
        tagIDs = new ArrayList<>();
        titleEntry = findViewById(R.id.enterTitle);
        firstNameEntry = findViewById(R.id.enterFirstName);
        lastNameEntry = findViewById(R.id.enterLastName);
        seriesEntry = findViewById(R.id.enterSeries);
        tagEntry = findViewById(R.id.newTagEntry);
        fileDisplay = findViewById(R.id.filePathDisplay);
        coverDisplay = findViewById(R.id.displayCoverPath);
        tagDisplay = findViewById(R.id.tagListDisplay);
    }

    public void selectFile(View view) {
        Intent chooseFile = new Intent(Intent.ACTION_GET_CONTENT);
        chooseFile.setType("*/*");
        startActivityForResult(Intent.createChooser(chooseFile, "Choose a file"), ACTIVITY_CHOOSE_FILE);
    }

    public void selectCover(View view) {
        Intent chooseCover = new Intent(Intent.ACTION_GET_CONTENT);
        chooseCover.setType("image/*");
        startActivityForResult(Intent.createChooser(chooseCover, "Choose a cover"), ACTIVITY_CHOOSE_COVER);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(requestCode) {
            case ACTIVITY_CHOOSE_FILE: {
                if (resultCode == RESULT_OK){
                    filePath = data.getData().getPath();
                    fileDisplay.setText(filePath);
                }
                break;
            }
            case ACTIVITY_CHOOSE_COVER: {
                if (resultCode == RESULT_OK){
                    coverPath = data.getData().getPath();
                    coverDisplay.setText(coverPath);
                }
                break;
            }
        }
    }

    public void addTag(View view) {
        String curDesc = tagEntry.getText().toString();
        Tag curTag = db.tagDao().getByDescriptor(curDesc);
        if(curTag == null){
            curTag = new Tag(curDesc);
            db.tagDao().insertAll(curTag);
            curTag = db.tagDao().getByDescriptor(curDesc);
        }
        tagIDs.add(curTag.getTagID());
        tagEntry.setText("");
        tagDisplay.setText(tagDisplay.getText() + " " + curDesc);
    }

    public void confirmEntry(View view) {
        String curFirstName = firstNameEntry.getText().toString();
        String curLastName = lastNameEntry.getText().toString();
        Author curAuthor = db.authorDao().findByName(curFirstName, curLastName);
        if(curAuthor == null){
            curAuthor = new Author(curFirstName, curLastName);
            db.authorDao().insertAll(curAuthor);
            curAuthor = db.authorDao().findByName(curFirstName, curLastName);
        }
        String curSeriesName = seriesEntry.getText().toString();
        Series curSeries = db.seriesDao().findByName(curSeriesName);
        if(curSeries == null) {
            curSeries = new Series(curSeriesName);
            db.seriesDao().insertAll(curSeries);
            curSeries = db.seriesDao().findByName(curSeriesName);
        }
        String curTitle = titleEntry.getText().toString();
        String curFile = filePath;
        String curCover = coverPath;
        Book curBook = new Book(curTitle, curFile, curCover);
        db.bookDao().insertAll(curBook);
        curBook = db.bookDao().loadByData(curTitle, curFile, curCover);
        int bookID = curBook.getBookID();
        int authorID = curAuthor.getAuthorID();
        int seriesID = curSeries.getSeriesID();
        db.wroteDao().insertAll(new Wrote(bookID, authorID));
        db.bookSeriesDao().insertAll(new BookSeries(bookID, seriesID));
        for(int curTagID : tagIDs){
            db.bookTagDao().insertAll(new BookTag(bookID, curTagID));
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
        tagEntry.setText("");
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
