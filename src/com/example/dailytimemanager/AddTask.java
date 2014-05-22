package com.example.dailytimemanager;

import java.util.Calendar;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;


public class AddTask extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
      //  getActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_add_task, menu);
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

    public void goBack(View view) {
    	Intent intent = new Intent(this, DailyTimeManager.class);
    	startActivity(intent);
    }
    
    public void save(final View view) {    	 	
    	TextView taskDetails = (TextView) findViewById(R.id.editText2);
    	String taskDetail = taskDetails.getText().toString();
    	TextView time = (TextView) findViewById(R.id.textView5);
    	String taskTime = time.getText().toString();
    	TextView date = (TextView) findViewById(R.id.textView6);
    	String taskDate = date.getText().toString();
    	TextView place = (TextView) findViewById(R.id.editText1);
    	String taskPlace = place.getText().toString();
    	Spinner repeat = (Spinner) findViewById(R.id.spinner1);
    	String taskRepeat = repeat.getSelectedItem().toString();    	
        DBHandle addTask = new DBHandle(this);
        addTask.addTask(taskDetail, taskTime, taskDate, taskPlace, taskRepeat);
        
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Task Saved");
        builder.setMessage("Task saved successfully");
        builder.setPositiveButton("Ok", new OnClickListener(){
			public void onClick(DialogInterface arg0, int arg1) {			
				Button closeBt = (Button) findViewById(R.id.button1);
				closeBt.performClick();
			}
        });
        builder.show();        
    }
    
    public void selectTime(View view){
    	final int hour;
		final int minute;
    	final TextView time = (TextView) findViewById(R.id.textView5);
		final Calendar c = Calendar.getInstance();
		hour = c.get(Calendar.HOUR_OF_DAY);
		minute = c.get(Calendar.MINUTE);		
    	TimePickerDialog.OnTimeSetListener timePickerListener = 
                new TimePickerDialog.OnTimeSetListener() {    		
    		public void onTimeSet(TimePicker view, int selectedHour,
    				int selectedMinute) {
    			 int newhour = selectedHour;
    			 int newminute = selectedMinute;
    			 time.setText(new StringBuilder().append(pad(hour))
    						.append(":").append(pad(minute)));

    			time.setText(newhour+ ":" +newminute);
    		}
    	};    	
    	TimePickerDialog timeSet = new TimePickerDialog(this, timePickerListener, hour, minute,false);
    	timeSet.show();
    }
    
    private static String pad(int c) {
		if (c >= 10)
		   return String.valueOf(c);
		else if((c%10) == 0)
		   return String.valueOf(c) + "0";
		else
		   return "0" + String.valueOf(c);
	}


	public void selectDate(View view){
    	final int date, month, year;
    	final TextView dateText = (TextView) findViewById(R.id.textView6);
		final Calendar c = Calendar.getInstance();
		date = c.get(Calendar.DAY_OF_MONTH);
		month = c.get(Calendar.MONTH);
		year = c.get(Calendar.YEAR);
		DatePickerDialog.OnDateSetListener datePickerListener 
        = new DatePickerDialog.OnDateSetListener() {

			public void onDateSet(DatePicker view, int selectedYear,
		int selectedMonth, int selectedDay) {
				int yearSet = selectedYear;
				int monthSet = selectedMonth;
				int dateSet = selectedDay;
				dateText.setText(new StringBuilder().append(dateSet)
						.append("-").append(monthSet + 1).append("-").append(yearSet)
						.append(" "));
				}
		};
    	DatePickerDialog dateSet = new DatePickerDialog(this, datePickerListener, year, month, date);
    	dateSet.show();
    }
}