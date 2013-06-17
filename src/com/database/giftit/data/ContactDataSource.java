package com.database.giftit.data;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.classes.giftit.Contact;
import com.interfaces.giftit.StringConstants;

public class ContactDataSource implements StringConstants{

	private SQLiteDatabase db;
	private DatabaseHandler dbHandler;
	private String[] allColumns = new String[]{
			CONTACT_ID,
			CONTACT_FIRSTNAME,
			CONTACT_LASTNAME,
			CONTACT_STREET,
			CONTACT_HOUSENUMBER,
			CONTACT_POST_CODE,
			CONTACT_CITY,
			CONTACT_BIRTHDAY,
			CONTACT_PHONE,
			CONTACT_EMAIL
	};
	
	public boolean isOpen(){
		return db.isOpen();
	}
	public ContactDataSource(Context context){
		dbHandler = new DatabaseHandler(context);
	}
	
	public void open() throws SQLException {
	    db = dbHandler.getWritableDatabase();
	  }
	
	public void close() {
	    dbHandler.close();
	  }

	  public void createContact(Contact contact) {
	    ContentValues values = new ContentValues();
	    values.put(CONTACT_FIRSTNAME, contact.getFirstName());
	    values.put(CONTACT_LASTNAME, contact.getLastName());
	    values.put(CONTACT_STREET, contact.getAddress());
	    values.put(CONTACT_POST_CODE, contact.getPostCode());
	    values.put(CONTACT_HOUSENUMBER, contact.getHouseNumber());
	    values.put(CONTACT_CITY, contact.getCity());
	    values.put(CONTACT_BIRTHDAY, contact.getBirthday());
	    values.put(CONTACT_EMAIL, contact.geteMailAddress());
	    values.put(CONTACT_PHONE, contact.getTelephoneNumber());
	    
	    long insertId = 0;
	    try{
	    	insertId = db.insert(CONTACT_TABLE, null,
	    			values);
	    }catch(Exception e){
	    	System.out.print("SQL Fehler " + e.getCause());
	    	System.out.println();
	    	System.out.print("Kontakt ID " + insertId);
	    }
	    
//	    if(insertId <= 0){
//	    	throw new SQLiteException(INSERT_ERROR + CONTACT_TABLE);
	    	
//	    }
	  }
	  

	  public List<Contact> getAllContacts() {
	    List<Contact> contacts = new ArrayList<Contact>();

	    Cursor cursor = db.query(CONTACT_TABLE,
	        allColumns, null, null, null, null, null);

	    cursor.moveToFirst();
	    while (!cursor.isAfterLast()) {
	      Contact contact = cursorToContact(cursor);
	      contacts.add(contact);
	      cursor.moveToNext();
	    }
	    // Make sure to close the cursor
	    cursor.close();
	    return contacts;
	  }
	 
	  public Contact getContact(int contactId){
		  String[] selectionArg = new String[]{Integer.toString(contactId)};
		  Cursor cursor = db.query(CONTACT_TABLE, allColumns, CONTACT_ID + "=?", selectionArg, null, null, null);
		  
		  cursor.moveToFirst();
		  Contact contact = cursorToContact(cursor);
		  cursor.close();
		  return contact;
	  }
	  
	  private Contact cursorToContact(Cursor cursor) {
		    Contact contact = new Contact();
		    contact.setContact_ID(cursor.getInt(0));
		    contact.setFirstName(cursor.getString(1));
		    contact.setLastName(cursor.getString(2));
		    contact.setAddress(cursor.getString(3));
		    contact.setHouseNumber(cursor.getString(4));
		    contact.setPostCode(cursor.getInt(5));
		    contact.setCity(cursor.getString(6));
		    contact.setBirthday(cursor.getString(7));
		    contact.setTelephoneNumber(cursor.getString(8));
		    contact.seteMailAddress(cursor.getString(9));
		    return contact;
		  }
}
