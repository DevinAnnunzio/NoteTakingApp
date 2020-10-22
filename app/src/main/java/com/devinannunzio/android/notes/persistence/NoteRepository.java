package com.devinannunzio.android.notes.persistence;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.devinannunzio.android.notes.async.DeleteAsyncTask;
import com.devinannunzio.android.notes.async.InsertAsyncTask;
import com.devinannunzio.android.notes.async.UpdateAsyncTask;
import com.devinannunzio.android.notes.models.Note;

import java.util.List;

//Repository class is most effective when handling different data sources.
public class NoteRepository {

    private NoteDB mNoteDatabse;

    public NoteRepository(Context context) {
        //Returns a singleton
        mNoteDatabse = NoteDB.getInstance(context);
    }

    public void insertNoteItem(Note note){
        new InsertAsyncTask(mNoteDatabse.getNoteDao()).execute(note);

    }

    public void updateNote(Note note){
        new UpdateAsyncTask(mNoteDatabse.getNoteDao()).execute(note);

    }
    public void deleteNote(Note note){
        new DeleteAsyncTask(mNoteDatabse.getNoteDao()).execute(note);
    }

    //Use LD to retrieve
    public LiveData<List<Note>> getNotesItem(){
        return mNoteDatabse.getNoteDao().getNotes();
    }

}
