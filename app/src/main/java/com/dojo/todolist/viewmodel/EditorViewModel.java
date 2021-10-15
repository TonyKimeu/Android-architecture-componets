package com.dojo.todolist.viewmodel;

import android.app.Application;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.dojo.todolist.database.TodoEntity;
import com.dojo.todolist.database.TodoRepository;

import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class EditorViewModel extends AndroidViewModel {

    public MutableLiveData<TodoEntity> mLiveTodo =
            new MutableLiveData<>();
    private TodoRepository mRepository;
    private Executor executor = Executors.newSingleThreadExecutor();

    public EditorViewModel(@NonNull Application application) {
        super(application);
        mRepository = TodoRepository.getInstance(getApplication());
    }

    public void loadData(final int todoId) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                TodoEntity note = mRepository.getTodoById(todoId);
                mLiveTodo.postValue(note);
            }
        });
    }

    public void saveNote(String todoText) {
        TodoEntity todo = mLiveTodo.getValue();

        if (todo == null) {
            if (TextUtils.isEmpty(todoText.trim())) {
                return;
            }
            todo = new TodoEntity(todoText.trim(),new Date());
        } else {
            todo.setTitle(todoText.trim());
        }
        mRepository.insertTodo(todo);
    }

    public void completeTodo() {
        TodoEntity todo = mLiveTodo.getValue();
        if(todo.getCompleted()){
            todo.setCompleted(false);
        } else {
            todo.setCompleted(true);
        }

        mRepository.insertTodo(todo);
    }

    public void deleteNote() {
        mRepository.deleteTodo(mLiveTodo.getValue());
    }
}
