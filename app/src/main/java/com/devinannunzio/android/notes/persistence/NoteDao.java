package com.devinannunzio.android.notes.persistence;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.devinannunzio.android.notes.models.Note;

import java.util.List;

@Dao
public interface NoteDao {

    //By default need to have a return type
    //Returns a long array that will hold all rows inserted into DB
    //... elipsy is another way to write an array of Notes Note[]
    @Insert
    long[] insertNotes(Note...notes);


    //Referring to livedata object that holds list of notes.
    //LD is a data observation class and part of lifecycle library on android
    @Query("SELECT * FROM notes")
    LiveData<List<Note>> getNotes();

    @Query("SELECT * FROM notes WHERE title LIKE :title")
    List<Note> getWithCustomQuery(String title);

    //returns total rows deleted
    @Delete
    int delete(Note...notes);


    @Update
    int update(Note...notes);
}
