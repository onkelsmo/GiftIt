package com.database.giftit.data;

import com.interfaces.giftit.StringConstants;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper implements StringConstants{

	public static  final String DATABASE_NAME = "giftIt.db";
	public static final int DATABASE_VERSION = 1;
	
	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String createGiftTable = CREATE_TABLE + GIFT_TABLE +"(" +
				GIFT_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "+ 
				GIFT_DESCRIPTION +TEXT + 
				");";
		
		String createContactTable = CREATE_TABLE + CONTACT_TABLE + "(" + 
				CONTACT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
				CONTACT_FIRSTNAME + TEXT  + COMMA+
				CONTACT_LASTNAME + TEXT+ COMMA +
				CONTACT_STREET + TEXT + COMMA + 
				CONTACT_HOUSENUMBER + TEXT + COMMA + 
				CONTACT_POST_CODE + TEXT + COMMA + 
				CONTACT_CITY + TEXT + COMMA + 
				CONTACT_BIRTHDAY + DATETIME + COMMA + 
				CONTACT_PHONE + INTEGER + COMMA +
				CONTACT_EMAIL + TEXT + 
				");";
		
		String createEventTable = CREATE_TABLE + EVENT_TABLE + "(" +
				EVENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
				EVENT_DESCRIPTION + TEXT + 
				");";
		
		String createGroupTable = CREATE_TABLE + GROUP_TABLE + "(" + 
				GROUP_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + 
				GROUP_DESCRIPTION + 
				");";
		
		String createSetupTable = CREATE_TABLE + SETUP_TABLE + "(" +
				SETUP_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+ 
				SETUP_REMINDER + BOOLEAN + COMMA +
				SETUP_DURATION + TEXT +
				");";
		
		String createContactGiftTable = CREATE_TABLE + CONTACT_GIFT_TABLE + "(" +
				CONTACT_GIFT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + 
				CONTACT_GIFT_CONTACT_ID + INTEGER + COMMA +
				CONTACT_GIFT_GIFT_ID + INTEGER + COMMA + 
				CONTACT_GIFT_EVENT_ID + " INTEGER, FOREIGN KEY(" + CONTACT_GIFT_EVENT_ID + ") REFERENCES " + EVENT_TABLE + "(" + EVENT_ID + "), " + 
				"FOREIGN KEY(" + CONTACT_GIFT_CONTACT_ID + ") REFERENCES " + CONTACT_TABLE + "(" + CONTACT_ID + "), " +
				"FOREIGN KEY(" + CONTACT_GIFT_GIFT_ID + ") REFERENCES " + GIFT_TABLE + "(" + GIFT_ID + ")" + 
				");";

		
		String createGroupContactTable = CREATE_TABLE + GROUP_CONTACT_TABLE + "(" +
				GROUP_CONTACT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
				GROUP_CONTACT_CONTACT_ID +  INTEGER + COMMA + 
				GROUP_CONTACT_GROUP_ID + INTEGER + COMMA +
				"FOREIGN KEY(" + GROUP_CONTACT_GROUP_ID + ") REFERENCES " + GROUP_TABLE + "(" + GROUP_ID + "), " + 
				"FOREIGN KEY(" + GROUP_CONTACT_CONTACT_ID + ") REFERENCES " + CONTACT_TABLE + "(" + CONTACT_ID + ")"+
				");";
		
 		db.execSQL(createGiftTable);
 		db.execSQL(createContactTable);
 		db.execSQL(createGroupTable);
 		db.execSQL(createEventTable);
 		db.execSQL(createSetupTable);
 		db.execSQL(createGroupContactTable);
 		db.execSQL(createContactGiftTable);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS "+ GIFT_TABLE);
		onCreate(db);
	}

}
