package com.example.kevin.ebooklibrary;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class Insert extends AppCompatActivity {
    final int ACTIVITY_CHOOSE_FILE = 1;
    final int ACTIVITY_CHOOSE_COVER = 2;

    private SQLiteDatabase db;
    private String filePath;
    private String coverPath;
    private ArrayList<String> tagNames;
    private ArrayList<Integer> tagIDs;
    private EditText titleEntry;
    private EditText firstNameEntry;
    private EditText lastNameEntry;
    private EditText tagEntry;
    private TextView fileDisplay;
    private TextView coverDisplay;
    private TextView tagDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = SQLiteDatabase.openDatabase("EBookLib.db", null, 0);
        tagIDs = new ArrayList<>();
        titleEntry = findViewById(R.id.enterTitle);
        firstNameEntry = findViewById(R.id.enterFirstName);
        lastNameEntry = findViewById(R.id.enterLastName);
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
                    filePath = data.getData().getPath();
                    fileDisplay.setText(filePath);
                }
            }
            case ACTIVITY_CHOOSE_COVER: {
                if (requestCode == RESULT_OK){
                    coverPath = data.getData().getPath();
                    coverDisplay.setText(coverPath);
                }
            }
        }
    }

    public void addTag(View view) {
        String[] req = new String[1];
        req[0] = tagEntry.getText().toString();
        Cursor tagCheck = db.rawQuery("SELECT TagID FROM Tag WHERE Descriptor = ?", req);
        if(!tagCheck.moveToNext()){
            db.execSQL("INSERT INTO Tag (Descriptor) VALUES (?)", req);
            tagCheck = db.rawQuery("SELECT TagID FROM Tag WHERE Descriptor = ?", req);
            tagCheck.moveToNext();
        }
        tagIDs.add(tagCheck.getInt(tagCheck.getColumnIndex("Descriptor")));
        tagDisplay.setText(tagDisplay.getText() + " " + req[0]);
    }

    public void confirmEntry(View view) {


    }

    public void launchMainMenu(View view) {
        Intent nextIntent = new Intent( this, MainActivity.class);
        startActivity(nextIntent);
    }
}
