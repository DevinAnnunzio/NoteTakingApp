package com.devinannunzio.android.notes.persistence;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.devinannunzio.android.notes.models.Note;

//When dealing with a db there will always be different versions.
//If you ever change the db structure at all(add tables, new entities, etc) need to change the version
@Database(entities = {Note.class}, version = 1)

public abstract class NoteDB extends RoomDatabase {


    public static final String DATABASE_NAME = "notes_db";

    //Singleton pattern refers to creating an instance of an obj
    //Checking to see if it's null then creating a new one with Room DB builder, otherwise I'm returning the instance
    //Helps to keep 1 version of the obj, and helps optimize memory use
    private static NoteDB instance;

     static NoteDB getInstance(final Context context){
         if (instance == null){
             instance = Room.databaseBuilder(context.getApplicationContext(), NoteDB.class,DATABASE_NAME).build();
         }
         return instance;
     }

     //returns a ref to Dao
     public abstract NoteDao getNoteDao();


}
