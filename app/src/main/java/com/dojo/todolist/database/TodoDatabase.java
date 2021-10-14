package com.dojo.todolist.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {TodoEntity.class}, version = 1)
@TypeConverters(DateConverter.class)
public abstract class TodoDatabase extends RoomDatabase {
    public static final String DATABASE_NAME = "TodoDatabase.db";
    private static volatile TodoDatabase instance;
    private static final Object lock = new Object();

    public abstract TodoDao todoDao();

    public static TodoDatabase getInstance(Context context) {
        if (instance == null){
            synchronized (lock){
                if (instance == null){
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            TodoDatabase.class, DATABASE_NAME).build();
                }
            }
        }
        return instance;
    }

}
