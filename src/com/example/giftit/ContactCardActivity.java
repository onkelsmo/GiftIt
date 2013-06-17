package com.example.giftit;
 
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.classes.giftit.Contact;
import com.database.giftit.data.ContactDataSource;
import com.interfaces.giftit.StringConstants;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class ContactCardActivity extends Activity implements StringConstants{

	private ContactDataSource dataSource;
	private static final String TAG = ContactCardActivity.class.getSimpleName();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contact_card);
		
		dataSource = new ContactDataSource(this);
		dataSource.open();
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
	
	public void saveContactClick(View view){
		
		EditText tfContactName = (EditText)findViewById(R.id.tfContactName);
		EditText tfBirthday = (EditText)findViewById(R.id.tfBirthday);
		EditText tfStreet = (EditText)findViewById(R.id.tfStreet);
		EditText tfHouseNo = (EditText)findViewById(R.id.tfHouseNo);
		EditText tfPLZ = (EditText)findViewById(R.id.tfPostCode);
		EditText tfCity = (EditText)findViewById(R.id.tfCity);
		EditText tfPhoneNo = (EditText)findViewById(R.id.tfPhone);
		EditText tfeMail = (EditText)findViewById(R.id.tfMail);
		Spinner spGroup = (Spinner)findViewById(R.id.spGroup);
		Spinner spGift = (Spinner)findViewById(R.id.spGift);
		
		
		Contact contact = new Contact();
		List<String> name = contact.displayNameToDBName(tfContactName.getText().toString());
		
		contact.setLastName(name.get(0));
		if(name.size() > 1){
			contact.setFirstName(name.get(1));
		}
		contact.setBirthday(tfBirthday.getText().toString());
		contact.setAddress(tfStreet.getText().toString());
		contact.setHouseNumber(tfHouseNo.getText().toString());
		contact.setPostCode(Integer.parseInt(tfPLZ.getText().toString().trim()));
		contact.setCity(tfCity.getText().toString());
		String s = tfPhoneNo.getText().toString();
		if(!s.equals(null)){
			contact.setTelephoneNumber(s);
		}
		contact.seteMailAddress(tfeMail.getText().toString());
		
		// jsmolka - 20130616 - setting the birthday in the systemcalendar
		// Beginn und Ende eines Termins
		Calendar cal = Calendar.getInstance();
		Date from = cal.getTime();
		cal.add(Calendar.HOUR_OF_DAY, 1);
		Date to = cal.getTime();
		// Termin anlegen
		createEntry(contact.getFirstName() + " " + contact.getLastName() + "'s Birthday", "", from, to, true);
		
		
		try{
			if(!dataSource.isOpen()){
				dataSource.open();
			}
			dataSource.createContact(contact);
			Toast toast = Toast.makeText(this, CONTACT_SAVED, Toast.LENGTH_SHORT);
			toast.show();
		}catch(Exception e){
			System.out.print(e.getCause());
			Toast toast = Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG);
			toast.show();
		}finally{
			dataSource.close();
			finish();
		}
		
	}
	
	/**
	 * 
	 * createEntry - creates the calendar entry 
	 * 
	 * @param title String - the title of the event
	 * @param description String - the description of the event
	 * @param from Date - start date
	 * @param to Date - end date
	 * @param allDay boolean - lasts the event the whole day
	 */
	private void createEntry(String title, String description, Date from,
			Date to, boolean allDay) {
		Intent intent = new Intent(Intent.ACTION_EDIT);
		intent.setType("vnd.android.cursor.item/event");
		intent.putExtra("title", title);
		intent.putExtra("description", description);
		intent.putExtra("beginTime", from.getTime());
		intent.putExtra("endTime", to.getTime());
		intent.putExtra("allDay", allDay);
		try {
			startActivity(intent);
		} catch (ActivityNotFoundException e) {
			Log.e(TAG, "keine passende Activity", e);
		}
	}
}
