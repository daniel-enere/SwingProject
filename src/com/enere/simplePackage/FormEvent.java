package com.enere.simplePackage;

import java.util.EventObject;

@SuppressWarnings("serial")
public class FormEvent extends EventObject {
	private String firstName;
	private String lastName;
	private int ageCategory;
	private String empType;
	private String taxId;
	private boolean usCitizen;
	private String gender;
	
	/**
	 * 
	 * @param source
	 */
	public FormEvent(Object source) {
		super(source);
	}
	
	/**
	 * 
	 * @param source
	 * @param firstName
	 * @param lastName
	 */
	public FormEvent(Object source, String firstName, String lastName) {
		super(source);
		
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	/**
	 * 
	 * @param source
	 * @param firstName
	 * @param lastName
	 * @param ageCat
	 * Added new constructor to allow use of older constructor
	 */
	public FormEvent(Object source, String firstName, String lastName, int ageCat, String empType, 
			String taxId, boolean usCitizen, String gender) {
		super(source);
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.ageCategory = ageCat;
		this.empType = empType;
		this.taxId = taxId;
		this.usCitizen = usCitizen;
		this.gender = gender;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public int getAgeCategory () {
		return ageCategory;
	}
	
	public String getEmpType () {
		return empType;
	}

	public String getTaxId() {
		return taxId;
	}

	public boolean isUsCitizen() {
		return usCitizen;
	}

	public String getGender() {
		return gender;
	}

}
