package model;

import java.io.*;
import java.util.*;

public class Database {
	
	private LinkedList<Employee> employees;
	
	public Database() {
		employees = new LinkedList<Employee>();
	}
	
	public void addemployee(Employee employee) {
		employees.add(employee);
	}
	
	public List<Employee> getEmployee() {
		return Collections.unmodifiableList(employees);
	}
	
	public void saveToFile(File file) throws IOException {
		FileOutputStream outputStream;
		ObjectOutputStream objectStream;
		outputStream = new FileOutputStream(file);
		objectStream = new ObjectOutputStream(outputStream);
		
		Employee[] anEmployee = employees.toArray(new Employee[employees.size()]);
		
		objectStream.writeObject(anEmployee);
		
		objectStream.close();
	}
	
	public void loadFromFile(File file) throws IOException {
		FileInputStream fileStream = new FileInputStream(file);
		ObjectInputStream objInputStream = new ObjectInputStream(fileStream);
		
		try {
			Employee[] anEmployee = (Employee[]) objInputStream.readObject();
			
			employees.clear();
			
			employees.addAll(Arrays.asList(anEmployee));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		objInputStream.close();
	}
	
	public void removeEmployee(int index) {
		employees.remove(index);
	}
}
