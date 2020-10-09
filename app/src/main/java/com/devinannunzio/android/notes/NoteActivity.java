package com.devinannunzio.android.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.devinannunzio.android.notes.models.Note;

public class NoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        if (getIntent().hasExtra("selected_note")){
            Note note = getIntent().getParcelableExtra("selected_note");
        }

    }
}