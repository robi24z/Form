package vehicleDetails;

import java.sql.Connection;
import java.sql.DriverManager;

public class CommonConnection {
	public Connection getConnection() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:frsp", "test", "test");
			return con;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;

	}
}
