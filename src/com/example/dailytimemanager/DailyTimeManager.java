package com.example.dailytimemanager;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class DailyTimeManager extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_time_manager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_daily_time_manager, menu);
        return true;
    }
    
    public void addTask(View view) {
    	Intent intent = new Intent(this, AddTask.class);
    	startActivity(intent);
    }
    
    public void seeDailyTask(View view) {
    	Intent intent = new Intent(this, SeeDailyTask.class);
    	startActivity(intent);
    }
    
    public void seeTodaysTask(View view) {
    	Intent intent = new Intent(this, TodaysTask.class);
    	startActivity(intent);
    }
    
    public void seeTaskOnSpecificDate(View view) {
    	Intent intent = new Intent(this, TaskOnDate.class);
    	startActivity(intent);
    }
    
    public void aboutApp(View view) {
    	Intent intent = new Intent(this, AboutApp.class);
    	startActivity(intent);
    }
}