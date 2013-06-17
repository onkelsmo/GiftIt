package com.example.giftit;

import java.util.ArrayList;
import java.util.List;

import com.classes.giftit.Contact;
import com.interfaces.giftit.StringConstants;

import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Contacts;
import android.provider.ContactsContract.Data;
import android.provider.ContactsContract.RawContacts;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class SetupActivity extends Activity implements StringConstants{
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setup);
	}
	
	public void ImportClick(View view){
		String[]projection = new String[]{RawContacts.CONTACT_ID,RawContacts.DELETED};
		String[]statement = new String[]{};
		
		@SuppressWarnings("deprecation")
		final Cursor contactCursor = managedQuery(RawContacts.CONTENT_URI,projection,"",statement,null);
		
		final int contactIdColumnIndex = contactCursor.getColumnIndex(RawContacts.CONTACT_ID);
		final int contactDeletedColumnIndex = contactCursor.getColumnIndex(RawContacts.DELETED);
		
		if(contactCursor.moveToFirst()){
			while(!contactCursor.isAfterLast()){
				final int contactId = contactCursor.getInt(contactIdColumnIndex);
				final boolean deleted = (contactCursor.getInt(contactDeletedColumnIndex) ==1);
				if (!deleted){
					importContactData(contactId);
				}
				contactCursor.moveToNext();
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	public Contact importContactData(int contactid){
		Contact newContact = new Contact();
		newContact.setContact_ID(contactid);
		final String[] projection = new String[]{
				Contacts.DISPLAY_NAME,
		};
		final Cursor contactCursor = managedQuery(Contacts.CONTENT_URI,projection, Contacts._ID + "=?",
				new String[]{String.valueOf(contactid)}, null);
				
		if(contactCursor.moveToFirst()){
			List<String> list = new ArrayList<String>();
			list = newContact.displayNameToDBName(contactCursor.getString(contactCursor.getColumnIndex(Contacts.DISPLAY_NAME)));
			newContact.setLastName(list.get(1));
			newContact.setFirstName(list.get(2));
		}
		
		final String[] projectionPhone = new String[]{
				Phone.NUMBER
		};
		final Cursor phoneCursor = managedQuery(Phone.CONTENT_URI,projectionPhone,Data.CONTACT_ID + "=?",
				new String[]{String.valueOf(contactid)},null);
		
		if(phoneCursor.moveToFirst()){
			newContact.setTelephoneNumber(phoneCursor.getString(phoneCursor.getColumnIndex(Phone.NUMBER)));
		}
		
		String[] projectionEMail = new String[]{Email.ADDRESS};
		final Cursor mailCursor = managedQuery(Email.CONTENT_URI,projectionEMail,Data.CONTACT_ID + "=?",
				new String[]{String.valueOf(contactid)},null);
		if(mailCursor.moveToFirst()){
			newContact.seteMailAddress(mailCursor.getString(mailCursor.getColumnIndex(Email.ADDRESS)));
		}
		
		String[] projectionAdress = new String[]{};
		return newContact;
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
