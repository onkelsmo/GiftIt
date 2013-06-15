package com.database.giftit.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.interfaces.giftit.StringConstants;

public class GroupContactDataSource implements StringConstants{


	private SQLiteDatabase db;
	private DatabaseHandler dbHandler;
	private String[] allColumns = new String[]{
			GROUP_CONTACT_ID,
			GROUP_CONTACT_CONTACT_ID,
			GROUP_CONTACT_GROUP_ID
	};
	
	public GroupContactDataSource(Context context){
		dbHandler = new DatabaseHandler(context);
	}
	
	public void open() throws SQLException {
	    db = dbHandler.getWritableDatabase();
	  }
	
	public void close() {
	    dbHandler.close();
	  }

	  public void createGroupContact(int contactId, int groupId) {
	    ContentValues values = new ContentValues();
	    values.put(GROUP_CONTACT_CONTACT_ID, contactId);
	    values.put(GROUP_CONTACT_GROUP_ID, groupId);
	    long insertId = db.insert(GROUP_CONTACT_TABLE, null,
	        values);
	    
	    if(insertId < 0){
	    	throw new SQLiteException(INSERT_ERROR + GROUP_CONTACT_TABLE);
	    }
	  }
	 
	  public int getGroupID(int contactId){
		  String[] selectionArg = new String[]{Integer.toString(contactId)};
		  Cursor cursor = db.query(GROUP_CONTACT_TABLE, allColumns, GROUP_CONTACT_GROUP_ID + "=?", selectionArg, null, null, null);
		  
		  cursor.moveToFirst();
		  int groupId = cursor.getInt(3);
		  cursor.close();
		  return groupId;
	  }
	  
}
