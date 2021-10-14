package com.dojo.todolist.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;
@Dao
public interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(TodoEntity todoEntity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<TodoEntity> todoEntities);

    @Delete
    void deleteTodo(TodoEntity todoEntity);

    @Query("SELECT * FROM todo WHERE id = :id")
    TodoEntity getTodoById(int id);

    @Query("SELECT * FROM todo ORDER BY todoCreated DESC")
    LiveData<List<TodoEntity>> getAll();

    @Query("DELETE FROM todo")
    int deleteAll();

    @Query("SELECT COUNT(*) FROM todo")
    int getCount();

}
