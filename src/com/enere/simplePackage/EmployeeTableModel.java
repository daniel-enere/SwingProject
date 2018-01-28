package com.enere.simplePackage;

import java.util.List;
import javax.swing.table.AbstractTableModel;

import model.Employee;

public class EmployeeTableModel extends AbstractTableModel {
	
	private List<Employee> db;
	
	public EmployeeTableModel() {
	}
	
	private String[] colNames = {"ID", "First Name", "Last Name", "Age Category",
			"Employment Type", "US Citizen", "Tax Id"};
	
	/** (non-Javadoc)
	 * @see javax.swing.table.AbstractTableModel#getColumnName(int)
	 */
	@Override
	public String getColumnName(int column) {
		return colNames[column];
	}

	public void setData(List<Employee> db) {
		this.db = db;
	}

	@Override
	public int getColumnCount() {
		return 7;
	}

	@Override
	public int getRowCount() {
		return db.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		Employee emp = db.get(row);
		
		switch(col) {
		case 0:
			return emp.getId();
		case 1:
			return emp.getFirstName();
		case 2:
			return emp.getLastName();
		case 3: 
			return emp.getAgeCategory();
		case 4:
			return emp.getEmpType();
		case 5:
			return emp.isUsCitizen();
		case 6:
			return emp.getTaxId();
		
		}
		return null;
	}

}
