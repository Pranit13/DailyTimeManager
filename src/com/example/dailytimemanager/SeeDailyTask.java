package com.example.dailytimemanager;

import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.support.v4.app.NavUtils;

public class SeeDailyTask extends Activity {
	String task, time, place;
	int count;

    @SuppressWarnings("deprecation")
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_daily_task);        
       //    getActionBar().setDisplayHomeAsUpEnabled(true);
        DBHandle db = new DBHandle(this);        
        List<SeeDailyTask> tasks =  db.getAllDailyTasks();
        ScrollView scroll = new ScrollView(this);
        LinearLayout l = new LinearLayout(this);
        l.setOrientation(LinearLayout.VERTICAL);
        final TextView titleText = new TextView(this);
        titleText.setText("Daily tasks: \n");
        titleText.setBackgroundColor(Color.BLACK);
        titleText.setTextColor(Color.WHITE);
        l.addView(titleText);
        TextView records[] = new TextView[10];
        for (SeeDailyTask t : tasks) {        
        	final View rulerRed = new View(this); 
    	    rulerRed.setBackgroundColor(Color.RED);
    	    l.addView(rulerRed,new ViewGroup.LayoutParams( ViewGroup.LayoutParams.FILL_PARENT, 2));
    	    final View rulerWhite = new View(this); 
    	    rulerWhite.setBackgroundColor(Color.WHITE);
    	    l.addView(rulerWhite,new ViewGroup.LayoutParams( ViewGroup.LayoutParams.FILL_PARENT, 2));
       	    final TextView rowTextView = new TextView(this);
      	    rowTextView.setText(" Task: " + t.task +"\n Time: "+ t.time +"\n Place:"+ t.place +"\n");
       	    rowTextView.setBackgroundColor(Color.DKGRAY);
       	    rowTextView.setTextColor(Color.WHITE); 
       	    rowTextView.setOnClickListener(new OnClickListener(){

				public void onClick(View v) {
					// TODO Auto-generated method stub
					
				}
       	    	
       	    });
       	    l.addView(rowTextView);
      	} 
        scroll.addView(l);
        setContentView(scroll);
    } 

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_see_daily_task, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    public void setTask(String dbtask, String dbtime, String dbplace, int dbcount){
    	this.task = dbtask;
    	this.time = dbtime;
    	this.place = dbplace;
    	this.count = dbcount;
    }
}