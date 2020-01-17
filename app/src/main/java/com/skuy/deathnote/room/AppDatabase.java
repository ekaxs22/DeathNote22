package com.skuy.deathnote.room;

import android.content.Context;

import com.skuy.deathnote.model.Note;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Note.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public static AppDatabase db(Context context) {
        return Room.databaseBuilder(
                context.getApplicationContext(),
                AppDatabase.class, "amikom")
                .allowMainThreadQueries()
                .build();
    }

    public abstract NoteRoom noteRoom();
}
