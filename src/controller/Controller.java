package controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.enere.simplePackage.FormEvent;

import model.AgeCategory;
import model.Database;
import model.Employee;
import model.EmploymentType;
import model.Gender;

public class Controller {
	Database db = new Database();
	
	public List<Employee> getEmployees() {
		return db.getEmployee();
	}
	
	public void connect() throws Exception {
		db.connect();
	}
	
	public void load() throws SQLException {
		db.load();
	}
	
	public void save() throws SQLException {
		db.save();
	}
	
	public void disconnect() {
		db.disconnect();
	}
	
	public void addEmployee(FormEvent event) {
		String firstName = event.getFirstName();
		String lastName = event.getLastName();
		int ageCatId = event.getAgeCategory();
		String empType = event.getEmpType();
		String taxId = event.getTaxId();
		boolean usCitizen = event.isUsCitizen();
		String genderInput = event.getGender();
		
		AgeCategory ageCategory = null;
		
		switch(ageCatId) {
		case 0:
			ageCategory = AgeCategory.child;
			break;
		case 1:
			ageCategory = AgeCategory.adult;
			break;
		case 2:
			ageCategory = AgeCategory.senior;
		}
		
		EmploymentType employmentType;
		if(empType.equals("Hourly")) {
			employmentType = EmploymentType.hourly;
		} else if (empType.equals("Salaried")) {
			employmentType = EmploymentType.salaried;
		} else if (empType.equals("Contractor")) {
			employmentType = EmploymentType.contractor;
		} else {
			employmentType = EmploymentType.other;
			System.err.println(empType);
		}
		
		Gender gender;
		if(genderInput.equals("Male")) {
			gender = Gender.male;
		} else {
			gender = Gender.female;
		}
		
		
		
		Employee employee = new Employee(firstName, lastName, ageCategory, 
				employmentType, taxId, usCitizen, gender);
		db.addemployee(employee);
	}
	
	public void saveToFile(File file) throws IOException {
			db.saveToFile(file);
	}
	
	public void loadFromFile(File file) throws IOException {
		db.loadFromFile(file);
	}
	
	public void removeEmployee(int row) {
		db.removeEmployee(row);
	}
}
