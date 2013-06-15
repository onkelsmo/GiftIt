package com.classes.giftit;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;


/*
 * Kontakt
 */
public class Contact{

	/*
	 * Attribute
	 */
	private int contact_ID;
	private String lastName;
	private String firstName;
	private String address;
	private String houseNumber;
	private int postCode;
	private String city;
	private Date birthday;
	private int telephoneNumber;
	private String eMailAddress;

	/*
	 * Default- Konstruktor
	 */
	public Contact(){
		super();
	}

	/*
	 * Konstruktor
	 */
	public Contact(int contact_ID, String lastName, String firstName,
			String address, String houseNumber, int postCode, String city, Date birthday,
			int telephoneNumber, String eMailAddress) {
		super();
		this.contact_ID = contact_ID;
		this.lastName = lastName;
		this.firstName = firstName;
		this.address = address;
		this.houseNumber = houseNumber;
		this.postCode = postCode;
		this.city = city;
		this.birthday = birthday;
		this.telephoneNumber = telephoneNumber;
		this.eMailAddress = eMailAddress;
	}

	/*
	 * Getter und Setter für die Attribute
	 */
	public int getContact_ID() {
		return contact_ID;
	}

	public void setContact_ID(int contact_ID) {
		this.contact_ID = contact_ID;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public int getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(int telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	public int getPostCode() {
		return postCode;
	}

	public void setPostCode(int postCode) {
		this.postCode = postCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String geteMailAddress() {
		return eMailAddress;
	}

	public void seteMailAddress(String eMailAddress) {
		this.eMailAddress = eMailAddress;
	}
	
	/*
	 * toString()- Methode für den Namen
	 */

	public String nameToString(Contact contact){
		 return contact.getFirstName() + " " + contact.getLastName(); 
	}
	
	public List<String> displayNameToDBName(String displayName){
		String[] s = displayName.split("\\s");
		List<String> nameList = new ArrayList<String>();
		
		for(String element : s){
			nameList.add(element);
		}
		
		if(nameList.size() > 2){
			String string = "";
			for(int i = 0; i <= nameList.size() - 1; i++){
				string = string + nameList.get(i) + " ";
				nameList.remove(i);
			}
			nameList.add(string);
		}
		
		return nameList;
	}
	
	public Date convertStringToDate(String s){
		String[] splitString = s.split("\\.");
		int day = Integer.parseInt(splitString[0]);
		int month = Integer.parseInt(splitString[1]);
		int year = Integer.parseInt(splitString[2]);
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.set(year, month, day);
		Date date = new Date(calendar.getTimeInMillis());
		return date;
	}

}
