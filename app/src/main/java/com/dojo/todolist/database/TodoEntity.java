package com.dojo.todolist.database;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "todo")
public class TodoEntity {

    @PrimaryKey(autoGenerate = true)
    private  int id;
    private String title;
    private Date todoCreated;
    private Date todoCompleted;
    private Boolean completed;

    @Ignore
    public TodoEntity() {
    }

    public TodoEntity(int id, String title, Date todoCreated, Date todoCompleted, Boolean completed) {
        this.id = id;
        this.title = title;
        this.todoCreated = todoCreated;
        this.todoCompleted = todoCompleted;
        this.completed = completed;
    }

    @Ignore
    public TodoEntity(String title, Date todoCompleted, Boolean completed) {
        this.title = title;
        this.todoCompleted = todoCompleted;
        this.completed = completed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getTodoCreated() {
        return todoCreated;
    }

    public void setTodoCreated(Date todoCreated) {
        this.todoCreated = todoCreated;
    }

    public Date getTodoCompleted() {
        return todoCompleted;
    }

    public void setTodoCompleted(Date todoCompleted) {
        this.todoCompleted = todoCompleted;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "TodoEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", todoCreated=" + todoCreated +
                ", todoCompleted=" + todoCompleted +
                ", completed=" + completed +
                '}';
    }
}
