package com.example.dailytimemanager;

import java.util.Calendar;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.graphics.Color;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.support.v4.app.NavUtils;

public class TaskOnDate extends Activity {	
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_on_date);
       //    getActionBar().setDisplayHomeAsUpEnabled(true);
        ScrollView scroll = new ScrollView(this);
        LinearLayout h = new LinearLayout(this);
        h.setOrientation(LinearLayout.HORIZONTAL);
        h.setBackgroundColor(Color.BLACK);
        TextView titleText = new TextView(this);
        titleText.setText("Select Date: ");
   	    titleText.setTextColor(Color.WHITE);
   	    TextView DateText = new TextView(this);
   	    DateText.setId(1);
        DateText.setText(" Set Date");
   	    DateText.setTextColor(Color.WHITE);
   	    DateText.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0) {
				selectDate(arg0);
			}   	    	
   	    });
   	    Button SearchText = new Button(this, null, android.R.attr.buttonStyleSmall);
        SearchText.setText(" Search Tasks");
        SearchText.setBackgroundColor(Color.BLACK);
        SearchText.setTextColor(Color.RED); 
        SearchText.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0) {
				searchTask(arg0);
			}   	    	
   	    });
   	    h.addView(titleText);
        h.addView(DateText);
   	    h.addView(SearchText);   	    
        LinearLayout l = new LinearLayout(this);
        l.setId(2);
        l.setOrientation(LinearLayout.VERTICAL);
        l.addView(h);
        scroll.addView(l);
        setContentView(scroll);   	    	
    }


    @SuppressWarnings("deprecation")
	protected void searchTask(View arg0) {
    	final TextView dateText = (TextView) findViewById(1);
    	ScrollView scroll = new ScrollView(this);
    	final LinearLayout lNew = new LinearLayout(this) ;
    	lNew.setOrientation(LinearLayout.VERTICAL);
    	final DBHandle db = new DBHandle(this);
    	final TextView titleText = new TextView(this);
    	titleText.setText("Tasks on date "+dateText.getText()+" :");
    	titleText.setTextColor(Color.WHITE);
    	titleText.setBackgroundColor(Color.BLACK);
    	lNew.addView(titleText);
    	List<TodaysTask> tasks =  db.getTasksOnDate(dateText.getText());
    	for (TodaysTask t : tasks) {        
        	final View rulerRed = new View(TaskOnDate.this); 
    	    rulerRed.setBackgroundColor(Color.RED);
    	    lNew.addView(rulerRed,new ViewGroup.LayoutParams( ViewGroup.LayoutParams.FILL_PARENT, 2));
    	    final View rulerWhite = new View(TaskOnDate.this); 
    	    rulerWhite.setBackgroundColor(Color.WHITE);
    	    lNew.addView(rulerWhite,new ViewGroup.LayoutParams( ViewGroup.LayoutParams.FILL_PARENT, 2));
       	    final TextView rowTextView = new TextView(TaskOnDate.this);
      	    rowTextView.setText(" Task: " + t.task +"\n Time: "+ t.time +"\n Place:"+ t.place +"\n");
       	    rowTextView.setBackgroundColor(Color.DKGRAY);
       	    rowTextView.setTextColor(Color.WHITE); 
       	    lNew.addView(rowTextView);
    	}
    	scroll.addView(lNew);
    	setContentView(scroll);
	}


	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_task_on_date, menu);
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
    

public void selectDate(View view){
	final TextView dateText = (TextView) findViewById(1);
	DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
		public void onDateSet(DatePicker dtview, int selectedYear, int selectedMonth, int selectedDay) {
			dateText.setText(new StringBuilder().append(selectedDay).append("-").append(selectedMonth + 1).append("-").append(selectedYear).append(" "));			
		}
	};
	new DatePickerDialog(this, datePickerListener, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance()
			             .get(Calendar.DAY_OF_MONTH)).show();
    }
}