package com.enere.simplePackage;

import java.util.*;

@SuppressWarnings("serial")
public class FormEvent extends EventObject {
	private String firstName;
	private String lastName;

	public FormEvent(Object source) {
		super(source);
	}
	
	public FormEvent(Object source, String firstName, String lastName) {
		super(source);
		
		this.firstName = firstName;
		this.lastName = lastName;
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

}
