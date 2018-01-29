package model;

import java.io.Serializable;

public class Employee implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6519599542967958217L;
	
	private static int totalEmployees = 1;
	private int id;
	private String firstName;
	private String lastName;
	private AgeCategory ageCategory;
	private EmploymentType empType;
	private String taxId;
	private boolean usCitizen;
	private Gender gender;
	
	public Employee(String firstName, String lastName, AgeCategory ageCategory,
			EmploymentType emptype, String taxId, boolean usCitizen,
			Gender gender) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.ageCategory = ageCategory;
		this.empType = emptype;
		this.taxId = taxId;
		this.usCitizen = usCitizen;
		this.gender = gender;
		
		this.id = totalEmployees;
		totalEmployees++;
	}
	
	public Employee(int id, String firstName, String lastName, AgeCategory ageCategory,
			EmploymentType emptype, String taxId, boolean usCitizen,
			Gender gender) {
		this(firstName, lastName, ageCategory, emptype, taxId, usCitizen, gender);
		this.id = id;
		totalEmployees++;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public AgeCategory getAgeCategory() {
		return ageCategory;
	}
	public void setAgeCategory(AgeCategory ageCategory) {
		this.ageCategory = ageCategory;
	}
	public EmploymentType getEmpType() {
		return empType;
	}
	public void setEmpType(EmploymentType empType) {
		this.empType = empType;
	}
	public String getTaxId() {
		return taxId;
	}
	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}
	public boolean isUsCitizen() {
		return usCitizen;
	}
	public void setUsCitizen(boolean usCitizen) {
		this.usCitizen = usCitizen;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public static int getTotalEmployees() {
		return totalEmployees;
	}
	
	@Override
	public String toString() {
		return "Employee Name: " + lastName + ", " + firstName + " ID: " + id;
		
	}
}
