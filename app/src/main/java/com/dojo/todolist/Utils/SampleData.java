package com.dojo.todolist.Utils;

import com.dojo.todolist.database.TodoEntity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class SampleData {

    private static final String SAMPLE_TODO_1 = "Todo 1";
    private static final String SAMPLE_TODO_2 = "Todo 2";
    private static final String SAMPLE_TODO_3 = "Todo 3";

    private static Date getDate(int diff) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.add(Calendar.MILLISECOND, diff);
        return cal.getTime();
    }

    public static List<TodoEntity> getTodos() {
        List<TodoEntity> notes = new ArrayList<>();
        notes.add(new TodoEntity(SAMPLE_TODO_1,getDate(0),getDate(0),false));
        notes.add(new TodoEntity(SAMPLE_TODO_2,getDate(0),getDate(0),false));
        notes.add(new TodoEntity(SAMPLE_TODO_3,getDate(0),getDate(0),false));
        return notes;
    }
}
