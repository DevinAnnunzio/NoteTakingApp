package com.devinannunzio.android.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.devinannunzio.android.notes.adapters.NotesRecyclerAdapter;
import com.devinannunzio.android.notes.models.Note;
import com.devinannunzio.android.notes.util.VerticalSpacingItemDecorator;

import java.util.ArrayList;

public class NotesListActivity extends AppCompatActivity implements NotesRecyclerAdapter.OnNoteListener {

    //UI components
    private RecyclerView mRecyclerView;

    //Vars
    private ArrayList<Note> mNotes = new ArrayList<>();
    private NotesRecyclerAdapter mNotesRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_list);
        //implicit view reference so don't need to associate it with view
        mRecyclerView = findViewById(R.id.recyclerView);
        initRecyclerView();
        insertFakeNotes();

        setSupportActionBar((Toolbar)findViewById(R.id.notesToolBar));
        setTitle("Notes");

    }

    private void insertFakeNotes(){
        for (int i = 0; i < 100; i++){
            mNotes.add(new Note("k"+i,"l","today"));
        }
        mNotesRecyclerAdapter.notifyDataSetChanged();
    }

    private void initRecyclerView(){
        //All recycler views need manager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        VerticalSpacingItemDecorator itemDecorator = new VerticalSpacingItemDecorator(10);
        mRecyclerView.addItemDecoration(itemDecorator);
        mNotesRecyclerAdapter = new NotesRecyclerAdapter(mNotes,this);
        mRecyclerView.setAdapter(mNotesRecyclerAdapter);

    }

    @Override
    public void onNoteClick(int position) {
        Intent intent = new Intent(this,NoteActivity.class);
        //need to implement parcelable on class to package it
        intent.putExtra("selected_note",mNotes.get(position));
        startActivity(intent);

    }
}