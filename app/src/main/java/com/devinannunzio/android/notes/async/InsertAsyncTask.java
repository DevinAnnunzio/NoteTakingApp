package com.devinannunzio.android.notes.async;

import android.os.AsyncTask;

import com.devinannunzio.android.notes.models.Note;
import com.devinannunzio.android.notes.persistence.NoteDao;

//Good for executing a single job on background thread, until finished.  Then it is destroyed
//Never used for anything that lasts a long time
public class InsertAsyncTask extends AsyncTask<Note,Void,Void> {

    private NoteDao mNoteDao;

    //used to execute DB operation using NoteDao
    public InsertAsyncTask(NoteDao noteDao) {
        mNoteDao = noteDao;
    }

    //only function is to insert notes into DB
    @Override
    protected Void doInBackground(Note... notes) {
        mNoteDao.insertNotes(notes);
        return null;
    }
}
