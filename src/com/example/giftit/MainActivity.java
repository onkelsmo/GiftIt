package com.example.giftit;

import java.util.GregorianCalendar;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.provider.CalendarContract.Calendars;
import android.provider.CalendarContract.Events;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CalendarView;
import android.widget.Toast;

import com.database.giftit.data.DatabaseHandler;
import com.interfaces.giftit.StringConstants;

public class MainActivity extends Activity implements StringConstants{
		
		
	DatabaseHandler dbHandler;
			
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        dbHandler = new DatabaseHandler(this);
//        dbHandler.onCreate(db);

    }

	// jsmolka - 20130616 - Calendar_Implementation
    /**
     * 
     * OpenCalendarClick - the clickevent to open the systemcalendar
     * 
     * @param view
     */
    public void OpenCalendarClick(View view)
    {
    	long time = new GregorianCalendar().getTimeInMillis();
    	Uri uri = Uri.parse("content://com.android.calendar/time/" + time);
    	Intent intent = new Intent(Intent.ACTION_VIEW, uri);
    	startActivity(intent);
    }
    
    public void OpenContactsClick(View view){
    	Intent intent = new Intent(this,ContactListActivity.class);
    	startActivity(intent);
    }
    
    public void OpenGiftClick(View view){
    	//TODO �ffnen der Geschenkeansicht
    	//Intent intent = new Intent(this,GiftListActivity.class);
    	//startActivity(intent);
    }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
			
	// Inflate the menu; this adds items to the action bar if it is present.

		getMenuInflater().inflate(R.menu.main, menu);
			
		return true;
	}

	/**
	* onOptionsItemSelected - get the selected MenuItem and starts the chosen Activity 
	* 
	* @param MenuItem item 
	* @return bool
	* 
	*/
		
	public boolean onOptionsItemSelected(MenuItem item)	
	{		
		switch (item.getItemId()){
			case R.id.contact:
					Intent contactIntent = new Intent(this, ContactCardActivity.class);
					startActivity(contactIntent);
					return true;
			case R.id.group:
					Intent groupIntent = new Intent(this, GroupActivity.class);
					startActivity(groupIntent);
					return true;
			case R.id.gift:
					// TODO Implementing Intent to GiftActivity
					return true;
			case R.id.event:
					// TODO Implementing Intent to EventActivity
					return true;			
			case R.id.action_settings:					
					Intent actionSettingsIntent = new Intent(this, SetupActivity.class);
					startActivity(actionSettingsIntent);
					return true;
			default:
					return super.onOptionsItemSelected(item);
			}
		}
}
