package com.devinannunzio.android.notes.async;

import android.os.AsyncTask;

import com.devinannunzio.android.notes.models.Note;
import com.devinannunzio.android.notes.persistence.NoteDao;

public class UpdateAsyncTask extends AsyncTask<Note,Void,Void> {

    private NoteDao mNoteDao;

    //used to execute DB operation using NoteDao
    public UpdateAsyncTask(NoteDao noteDao) {
        mNoteDao = noteDao;
    }

    //only function is to insert notes into DB
    @Override
    protected Void doInBackground(Note... notes) {
        mNoteDao.update(notes);
        return null;
    }
}


