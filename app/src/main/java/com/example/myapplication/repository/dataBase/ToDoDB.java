package com.example.myapplication.repository.dataBase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.myapplication.model.ToDo;
import com.example.myapplication.model.ToDoDao;

@Database(entities = ToDo.class, version = 1, exportSchema = false)
public abstract class ToDoDB extends RoomDatabase {
    public abstract ToDoDao getToDoDao();

    private static ToDoDB toDoDB;

    public static synchronized ToDoDB getToDo(Context context) {
        if (toDoDB == null) {
            toDoDB = Room.databaseBuilder(context, ToDoDB.class, "toDo_DB")
                    .setJournalMode(RoomDatabase.JournalMode.TRUNCATE)
                    .build();
        }
        return toDoDB;
    }
}
