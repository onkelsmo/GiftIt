package com.example.giftit;
 
import java.util.Calendar;
import java.util.List;

import com.classes.giftit.Contact;
import com.database.giftit.data.ContactDataSource;
import com.interfaces.giftit.StringConstants;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class ContactCardActivity extends Activity implements StringConstants{

	private ContactDataSource dataSource;
	
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
		contact.setBirthday(contact.convertStringToDate(tfBirthday.getText().toString()));
		contact.setAddress(tfStreet.getText().toString());
		contact.setHouseNumber(tfHouseNo.getText().toString());
		contact.setPostCode(Integer.parseInt(tfPLZ.getText().toString()));
		contact.setCity(tfCity.getText().toString());
		String s = tfPhoneNo.getText().toString();
		if(!s.equals(null)){
			contact.setTelephoneNumber(Integer.parseInt(s));
		}
		contact.seteMailAddress(tfeMail.getText().toString());
		
		// jsmolka - 20130616 - setting the birthday in the systemcalendar
		/*
		Calendar cal = Calendar.getInstance();
		Intent intent = new Intent(Intent.ACTION_EDIT);
		intent.setType("vnd.android.cursor.item/event");
		intent.putExtra("beginTime", cal.getTimeInMillis());
		intent.putExtra("alldDay", true);
		//intent.putExtra("rrule", "FREQ=YEARLY");
		intent.putExtra("entTime", cal.getTimeInMillis()+60*60*1000);
		intent.putExtra("title", "A Test Event");
		startActivity(intent);
		*/
		
		try{
			dataSource.createContact(contact);
			Toast.makeText(this, CONTACT_SAVED, Toast.LENGTH_SHORT);
		}catch(SQLiteException e){
		    AlertDialog ad = new AlertDialog.Builder(this).create();  
		    ad.setCancelable(false); // This blocks the 'BACK' button  
		    ad.setMessage(e.getMessage());  
		    ad.setButton(1, "OK", new DialogInterface.OnClickListener() {  
		        @Override  
		        public void onClick(DialogInterface dialog, int which) {  
		            dialog.dismiss();                      
		        }  
		    }); 
		    ad.show();  
		}finally{
			dataSource.close();
		}
	}
	
	
}
