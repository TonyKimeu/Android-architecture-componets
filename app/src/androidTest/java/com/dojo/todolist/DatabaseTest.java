package com.dojo.todolist;

import static org.junit.Assert.assertEquals;

import android.content.Context;
import android.util.Log;

import androidx.room.Room;
import androidx.test.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.dojo.todolist.Utils.SampleData;
import com.dojo.todolist.database.TodoDao;
import com.dojo.todolist.database.TodoDatabase;
import com.dojo.todolist.database.TodoEntity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class DatabaseTest {
    public static final String TAG = "junit";
    private TodoDatabase mDb;
    private TodoDao mDao;

    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getTargetContext();
        mDb = Room.inMemoryDatabaseBuilder(context,
                TodoDatabase.class).build();
        mDao = mDb.todoDao();
        Log.i(TAG, "createDb");
    }

    @After
    public void closeDb() {
        mDb.close();
        Log.i(TAG, "closeDb");
    }

    @Test
    public void createAndRetrieveNotes() {
        mDao.insertAll(SampleData.getTodos());
        int count = mDao.getCount();
        Log.i(TAG, "createAndRetrieveNotes: count=" + count);
        assertEquals(SampleData.getTodos().size(), count);
    }

    @Test
    public void compareStrings() {
        mDao.insertAll(SampleData.getTodos());
        TodoEntity original = SampleData.getTodos().get(0);
        TodoEntity fromDb = mDao.getTodoById(1);
        assertEquals(original.getTitle(), fromDb.getTitle());
        assertEquals(1, fromDb.getId());
    }

}
