package com.dojo.todolist.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.dojo.todolist.database.TodoEntity;
import com.dojo.todolist.database.TodoRepository;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    public LiveData<List<TodoEntity>> mTodo;
    private TodoRepository mRepository;

    public MainViewModel(@NonNull Application application) {
        super(application);

        mRepository = TodoRepository.getInstance(application.getApplicationContext());
        mTodo = mRepository.mTodos;
    }

    public void addSampleData() {
        mRepository.addSampleData();
    }

    public void deleteAllNotes() {
        mRepository.deleteAllTodos();
    }
}
