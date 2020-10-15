package com.devinannunzio.android.notes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.devinannunzio.android.notes.adapters.NotesRecyclerAdapter;
import com.devinannunzio.android.notes.models.Note;
import com.devinannunzio.android.notes.util.VerticalSpacingItemDecorator;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class NotesListActivity extends AppCompatActivity implements
        NotesRecyclerAdapter.OnNoteListener,
        View.OnClickListener {

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
        findViewById(R.id.fab).setOnClickListener(this);

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
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(mRecyclerView);
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

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this,NoteActivity.class);
        startActivity(intent);
    }
    private void deleteNote(Note note){
        mNotes.remove(note);
        mNotesRecyclerAdapter.notifyDataSetChanged();
    }

    private ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT|ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            deleteNote(mNotes.get(viewHolder.getAdapterPosition()));
        }
    };
}