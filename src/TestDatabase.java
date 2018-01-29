import java.sql.SQLException;

import model.AgeCategory;
import model.Database;
import model.Employee;
import model.EmploymentType;
import model.Gender;

public class TestDatabase {

	public static void main(String[] args) {
		
		Database db = new Database();
		
		try {
			db.connect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		db.addemployee(new Employee("Barack", "Obama4", AgeCategory.adult, EmploymentType.hourly, "0019",true, Gender.male));
		db.addemployee(new Employee("Sally", "Smith3", AgeCategory.senior, EmploymentType.contractor, null, false, Gender.female));
		
		try {
			db.save();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			db.load();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		db.disconnect();

	}

}
