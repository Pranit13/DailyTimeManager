package com.example.dailytimemanager;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandle extends SQLiteOpenHelper { 
  
    // All Static variables 
    // Database Version 
    private static final int DATABASE_VERSION = 1; 
  
    // Database Name 
    private static final String DATABASE_NAME = "taskManager"; 
  
    // Contacts table name 
    private static final String TABLE_TASK = "tasks"; 
  
    // Contacts Table Columns names 
    private static final String KEY_ID = "id"; 
    private static final String TASK = "task"; 
    private static final String TIME = "time";
    private static final String DATE = "date";
    private static final String PLACE = "place";
    private static final String REPEAT = "repeat";
  
    public DBHandle(Context context) { 
        super(context, DATABASE_NAME, null, DATABASE_VERSION); 
    } 
  
    // Creating Tables 
    @Override
    public void onCreate(SQLiteDatabase db) { 
        String CREATE_TASK_TABLE = "CREATE TABLE " + TABLE_TASK + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + TASK + " TEXT,"
                + TIME + " TEXT," + DATE + " TEXT," + PLACE + " TEXT," + REPEAT + " TEXT" + ")"; 
        db.execSQL(CREATE_TASK_TABLE); 
    } 
  
    // Upgrading database 
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { 
        // Drop older table if existed 
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASK); 
        
        onCreate(db); 
    }
    
    public void addTask(String task, String time, String date, String place, String repeat) { 
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TASK, task);
        values.put(TIME, time);
        values.put(DATE, date);
        values.put(PLACE, place);
        values.put(REPEAT, repeat);
     
        db.insert(TABLE_TASK, null, values); 
        db.close();
    }
    
    public List<SeeDailyTask> getAllDailyTasks() { 
        List<SeeDailyTask> dailyTaskList = new ArrayList<SeeDailyTask>(); 
        String selectQuery = "SELECT  * FROM " + TABLE_TASK + " where repeat = 'Daily'"; 
        int i=0;
        SQLiteDatabase db = this.getWritableDatabase(); 
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) { 
            do { 
            	SeeDailyTask dailyTask = new SeeDailyTask();
            	dailyTask.setTask(cursor.getString(1), cursor.getString(2), cursor.getString(4), ++i);
            	dailyTaskList.add(dailyTask); 
            } while (cursor.moveToNext()); 
        } 
        return dailyTaskList; 
    }
    
    public List<TodaysTask> getTasksOnDate(int date, int month, int year) { 
		StringBuilder formattedDate = new StringBuilder().append(date).append("-").append(month + 1).append("-").append(year).append(" ");
        List<TodaysTask> todaysTaskList = new ArrayList<TodaysTask>(); 
        String selectQuery = "SELECT  * FROM " + TABLE_TASK + " where date = '"+formattedDate+"' OR repeat='Daily'";
        SQLiteDatabase db = this.getWritableDatabase(); 
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) { 
            do { 
            	TodaysTask todaysTask = new TodaysTask();
            	todaysTask.setTask(cursor.getString(1), cursor.getString(2), cursor.getString(4));
            	todaysTaskList.add(todaysTask); 
            } while (cursor.moveToNext()); 
        } 
        return todaysTaskList;
    }
    
    public List<TodaysTask> getTasksOnDate(CharSequence formattedDate) { 
        List<TodaysTask> todaysTaskList = new ArrayList<TodaysTask>(); 
        String selectQuery = "SELECT  * FROM " + TABLE_TASK + " where date = '"+formattedDate+"' OR repeat='Daily'";
        SQLiteDatabase db = this.getWritableDatabase(); 
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) { 
            do { 
            	TodaysTask todaysTask = new TodaysTask();
            	todaysTask.setTask(cursor.getString(1), cursor.getString(2), cursor.getString(4));
            	todaysTaskList.add(todaysTask); 
            } while (cursor.moveToNext()); 
        } 
        return todaysTaskList;
    }
}