package model;

import java.io.*;
import java.sql.*;
import java.util.*;
import java.lang.*;

public class Database {
	Scanner passInput = new Scanner(System.in);

	String dbpass = passInput.nextLine();
	private Connection conn;

	private LinkedList<Employee> employees;

	public Database() {
		employees = new LinkedList<Employee>();
	}

	public void connect() throws Exception {
		if (conn != null) {
			return;
		}
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("connecting to database");
		} catch (ClassNotFoundException e) {
			throw new Exception("Driver not found");
		}

		String connUrl = "jdbc:mysql://localhost:3306/javatestdb";
		conn = DriverManager.getConnection(connUrl, "root", dbpass);

		System.out.println("connected to: " + conn);
	}

	public void disconnect() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.err.println("Can't close connection");
			}
		}

	}

	public void save() throws SQLException {
		String checkSql = "Select count(*) as count from employee where id=?";
		PreparedStatement checkStmt = conn.prepareStatement(checkSql);

		String insertSql = "insert into employee (id, first_name, last_name, age_category, employment_type, tax_id, us_citizen, gender) values (?,?,?,?,?,?,?,?)";
		PreparedStatement insertStmt = conn.prepareStatement(insertSql);

		String updateSql = "update employee set first_name=?, last_name=?, age_category=?, employment_type=?, tax_id=?, us_citizen=?, gender=? where id=?";
		PreparedStatement updateStmt = conn.prepareStatement(updateSql);
		for (Employee employee : employees) {
			int id = employee.getId();

			String empFirstName = employee.getFirstName();
			String empLastName = employee.getLastName();
			AgeCategory empAgeGroup = employee.getAgeCategory();
			EmploymentType empType = employee.getEmpType();
			String empTaxId = employee.getTaxId();
			boolean empWorkStatus = employee.isUsCitizen();
			Gender empGender = employee.getGender();

			checkStmt.setInt(1, id);

			ResultSet checkResult = checkStmt.executeQuery();

			checkResult.next();

			int count = checkResult.getInt(1);

			if (count == 0) {
				System.out.printf("Inserting employee with ID: %d \n", id);
				int col = 1;
				insertStmt.setInt(col++, id);
				insertStmt.setString(col++, empFirstName);
				insertStmt.setString(col++, empLastName);
				insertStmt.setString(col++, empAgeGroup.name());
				insertStmt.setString(col++, empType.name());
				insertStmt.setString(col++, empTaxId);
				insertStmt.setBoolean(col++, empWorkStatus);
				insertStmt.setString(col++, empGender.name());

				insertStmt.executeUpdate();
			} else {
				System.out.printf("Updating employee with ID: %d \n", id);

				int col = 1;
				updateStmt.setString(col++, empFirstName);
				updateStmt.setString(col++, empLastName);
				updateStmt.setString(col++, empAgeGroup.name());
				updateStmt.setString(col++, empType.name());
				updateStmt.setString(col++, empTaxId);
				updateStmt.setBoolean(col++, empWorkStatus);
				updateStmt.setString(col++, empGender.name());
				updateStmt.setInt(col++, id);

				updateStmt.executeUpdate();
			}
		}
		updateStmt.close();
		insertStmt.close();
		checkStmt.close();
	}

	public void load() throws SQLException {
		employees.clear();

		String sqlStmt = "select id, first_name, last_name, age_category, employment_type, tax_id, us_citizen, gender from employee order by last_name";
		Statement selectStmt = conn.createStatement();

		ResultSet results = selectStmt.executeQuery(sqlStmt);

		while (results.next()) {
			int id = results.getInt("id");

			String empFirstName = results.getString("first_name");
			String empLastName = results.getString("last_name");
			String empAge = results.getString("age_category");
			String empType = results.getString("employment_type");
			String empTaxId = results.getString("tax_id");
			boolean empStatus = results.getBoolean("us_citizen");
			String empGender = results.getString("gender");
			
			Employee employee = new Employee(id, empFirstName, empLastName, AgeCategory.valueOf(empAge),
					EmploymentType.valueOf(empType), empTaxId, empStatus, Gender.valueOf(empGender));
			employees.add(employee);
			System.out.println(employee);
		}

		results.close();
		selectStmt.close();
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
