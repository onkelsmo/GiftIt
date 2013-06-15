package com.android.interfaces.giftit;

import com.classes.giftit.Contact;

public interface ContactsToAndroid {

	/*
	 * Methode zum Abrufen eines Kontakts aus Datenbank
	 */
	public Contact getContactFromDB(int Contact_ID);
	
	/*
	 * Methode zum Einfügen eines neuen Kontakts
	 */
	
	public void createNewContact(Contact newContact);
}
