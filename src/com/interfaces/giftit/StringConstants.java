package com.interfaces.giftit;

public interface StringConstants {

	//Interface für das Hinterlegen von Stringkonstanten
	
	/*
	 * Allgemeine String Konstanten
	 */
	public static final String EMPTY_STRING = "";
	public static final String COMMA = ", ";
	public static final String LEERZEICHEN = " ";
	public static final String GROUP_SAVED = "Neue Gruppe wurde gespeichert";
	public static final String CONTACT_SAVED = "Neuer Kontakt wurde gespeichert";
	
	
	/*
	 * Datenbank- Strings
	 */
	
	//String Konstanten für Tabellen
		
	public final static String GIFT_TABLE = "gift";
	public final static String GIFT_ID = "_id";
	public final static String GIFT_DESCRIPTION= "description";
	
	public final static String CONTACT_TABLE = "contact";
	public final static String CONTACT_ID = "contact_id";
	public final static String CONTACT_FIRSTNAME = "firstname";
	public final static String CONTACT_LASTNAME = "lastname";
	public final static String CONTACT_STREET = "street";
	public final static String CONTACT_HOUSENUMBER = "housenumber";
	public final static String CONTACT_POST_CODE = "post_code";
	public final static String CONTACT_CITY = "city";
	public final static String CONTACT_BIRTHDAY = "birthday";
	public final static String CONTACT_PHONE = "phone";
	public final static String CONTACT_EMAIL = "email";
	
	public final static String EVENT_TABLE = "event";
	public final static String EVENT_ID = "event_id";
	public final static String EVENT_DESCRIPTION = "description";
	
	public final static String GROUP_TABLE = "groupG";
	public final static String GROUP_ID = "groupG_id";
	public final static String GROUP_DESCRIPTION = "description";
	
	public final static String SETUP_TABLE = "setup";
	public final static String SETUP_ID = "id";
	public final static String SETUP_REMINDER = "reminder";
	public final static String SETUP_DURATION = "duration";
	
	public final static String CONTACT_GIFT_TABLE = "contact_gift";
	public final static String CONTACT_GIFT_ID = "contact_gift_id";
	public final static String CONTACT_GIFT_CONTACT_ID = "contact_id";
	public final static String CONTACT_GIFT_GIFT_ID = "gift_id";
	public final static String CONTACT_GIFT_EVENT_ID = "event_id";
	public final static String CONTACT_GIFT_YEAR = "year";
	
	public final static String GROUP_CONTACT_TABLE ="groupG_contact";
	public final static String GROUP_CONTACT_ID = "groupG_contact_id";
	public final static String GROUP_CONTACT_CONTACT_ID = "contact_id";
	public final static String GROUP_CONTACT_GROUP_ID = "groupGC_id";
	
	public final static String TEXT = " TEXT";
	public final static String DATETIME = " DATETIME";
	public final static String INTEGER = " INTEGER";
	public static final String BOOLEAN = " BOOLEAN";
	
	//SQL- Statements
	
	public final static String CREATE_TABLE ="CREATE TABLE IF NOT EXISTS ";
	
	//SQL Fehler
	public final static String INSERT_ERROR = "Fehler beim Einfügen in: "; 
}
