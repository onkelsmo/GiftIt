package com.database.giftit.data;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.classes.giftit.Group;
import com.interfaces.giftit.StringConstants;

public class GroupDataSource implements StringConstants{

	private SQLiteDatabase db;
	private DatabaseHandler dbHandler;
	private String[] allColumns = new String[]{
			GROUP_ID,
			GROUP_DESCRIPTION
	};
	
	public GroupDataSource(Context context){
		dbHandler = new DatabaseHandler(context);
	}
	
	public void open() throws SQLException {
	    db = dbHandler.getWritableDatabase();
	  }
	
	public void close() {
	    dbHandler.close();
	  }

	  public void createGroup(Group group) {
	    ContentValues values = new ContentValues();
	    values.put(GROUP_ID, group.getGroup_ID());
	    values.put(GROUP_DESCRIPTION, group.getDescription());
	    
	    long insertId = db.insert(GROUP_TABLE, null,
	        values);
	    
	    if(insertId < 0){
	    	throw new SQLiteException(INSERT_ERROR + GROUP_TABLE);
	    }
	  }
	  

	  public List<Group> getAllGroups() {
	    List<Group> groups = new ArrayList<Group>();

	    Cursor cursor = db.query(GROUP_TABLE,
	        allColumns, null, null, null, null, null);

	    cursor.moveToFirst();
	    while (!cursor.isAfterLast()) {
	      Group group = cursorToGroup(cursor);
	      groups.add(group);
	      cursor.moveToNext();
	    }
	    // Make sure to close the cursor
	    cursor.close();
	    return groups;
	  }
	 
	  public Group getGroup(int groupId){
		  String[] selectionArg = new String[]{Integer.toString(groupId)};
		  Cursor cursor = db.query(GROUP_TABLE, allColumns, GROUP_ID + "=?", selectionArg, null, null, null);
		  
		  cursor.moveToFirst();
		  Group group = cursorToGroup(cursor);
		  cursor.close();
		  return group;
	  }
	  
	  private Group cursorToGroup(Cursor cursor) {
		    Group group = new Group();
		    group.setGroup_ID(cursor.getInt(0));
		    group.setDescription(cursor.getString(1));
		    return group;
		  }

}
