package com.classes.giftit;

import java.util.List;

import com.android.interfaces.giftit.GroupToAndroid;

public class Group implements GroupToAndroid{
	
	/*
	 * Attribute
	 */

	private int group_ID;
	private String description;
	

	/*
	 * Default- Konstruktor
	 */
	public Group() {
		super();
	}
	
	/*
	 * Konstruktor
	 */
	public Group(int group_ID, String description) {
		super();
		this.group_ID = group_ID;
		this.description = description;
	}
	
	/*
	 * Getter und Setter
	 */
	public int getGroup_ID() {
		return group_ID;
	}
	
	public void setGroup_ID(int group_ID) {
		this.group_ID = group_ID;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	/*
	 * Methoden aus Interface
	 */
	@Override
	public List<Group> importGroupFromAddressBook() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createNewGroup(Group newGroup) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void createRelationshipContactGroup(Contact contact, Group group) {
		// TODO Auto-generated method stub
		
	}
	
	
}
