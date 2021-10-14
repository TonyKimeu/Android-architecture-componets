package com.dojo.todolist.database;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.dojo.todolist.Utils.SampleData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class TodoRepository {
    private static TodoRepository ourInstance;

    public LiveData<List<TodoEntity>> mTodos;
    private TodoDatabase mDb;
    private Executor executor = Executors.newSingleThreadExecutor();

    public static TodoRepository getInstance(Context context) {
        if (ourInstance == null) {
            ourInstance = new TodoRepository(context);
        }
        return ourInstance;
    }

    private TodoRepository(Context context) {
        mDb = TodoDatabase.getInstance(context);
        mTodos = getAllTodos();
    }

    public void addSampleData() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDb.todoDao().insertAll(SampleData.getTodos());
            }
        });
    }

    private LiveData<List<TodoEntity>> getAllTodos() {
        return mDb.todoDao().getAll();
    }

    public void deleteAllTodos() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDb.todoDao().deleteAll();
            }
        });
    }

    public TodoEntity getTodoById(int todoId) {
        return mDb.todoDao().getTodoById(todoId);
    }

    public void insertTodo(final TodoEntity todo) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDb.todoDao().insert(todo);
            }
        });
    }

    public void deleteTodo(final TodoEntity todo) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDb.todoDao().deleteTodo(todo);
            }
        });
    }
}
