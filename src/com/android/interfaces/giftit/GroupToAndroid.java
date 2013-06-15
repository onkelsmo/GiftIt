package com.android.interfaces.giftit;

import java.util.List;

import com.classes.giftit.Contact;
import com.classes.giftit.Group;

public interface GroupToAndroid {

	/*
	 * Methode zum Importieren der Gruppen aus dem Adressbuch
	 */
	public List<Group> importGroupFromAddressBook();
	
	/*
	 * Methode zum Einfügen eines neuen Gruppe
	 */
	
	public void createNewGroup(Group newGroup);
	
	/*
	 * Methode zum Zuordnen einer Gruppe zu einem Kontakt
	 */
	public void createRelationshipContactGroup(Contact contact, Group group);
}
