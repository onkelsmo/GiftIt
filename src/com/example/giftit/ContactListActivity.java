package com.example.giftit;

import java.util.ArrayList;
import java.util.List;

import com.classes.giftit.Contact;
import com.database.giftit.data.ContactDataSource;

import android.os.Bundle;
import android.app.ListActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

public class ContactListActivity extends ListActivity {

	private ContactDataSource dataSource;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contact_list);
		
		dataSource = new ContactDataSource(this);
		dataSource.open();
		
		List<Contact> contacts = dataSource.getAllContacts();
		
		ArrayAdapter<Contact> adapter = new ArrayAdapter<Contact>(this, android.R.layout.simple_list_item_1, contacts);
		setListAdapter(adapter);
	}
	
	 @Override
	  protected void onResume() {
	    dataSource.open();
	    super.onResume();
	  }

	  @Override
	  protected void onPause() {
	    dataSource.close();
	    super.onPause();
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
//					Intent giftIntent = new Intent(this, GiftCardActivity.class);
//					startActivity(giftIntent);
					return true;
			case R.id.event:
//					Intent eventIntent = new Intent(this, EventActivity.class);
//					startActivity(eventIntent);
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
