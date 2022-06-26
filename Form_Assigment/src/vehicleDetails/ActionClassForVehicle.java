package vehicleDetails;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ActionClassForVehicle {
	Connection con;

	ActionClassForVehicle() {
		con = new CommonConnection().getConnection();
	}

	ResultSet onLoadingVehicleName() {
		try {
			String insert = "SELECT VEHICLENAME FROM VEHICLEDEPARTMENT ORDER BY VEHICLENAME";
			ResultSet res = con.createStatement().executeQuery(insert);
			return res;

		} catch (Exception e) {
			System.out.println(e);
		}
		return null;

	}

	ResultSet OnSelectingVehicleName(Object name) {
		int i = 0;
		try {
			// System.out.println("name=" + name);
			String query = "SELECT VEHICLEID FROM VEHICLEDEPARTMENT WHERE VEHICLENAME='" + name + "'";
			ResultSet vehicleid = con.createStatement().executeQuery(query);
			while (vehicleid.next()) {
				i = vehicleid.getInt("VEHICLEID");
			}
			// System.out.println("id: "+i);
			String query1 = "SELECT VEHICLEPRICE FROM VEHICLEDEPARTMENT WHERE VEHICLEID='" + i + "'";
			ResultSet rslt = con.createStatement().executeQuery(query1);
			// System.out.println("return=" + rslt);
			return rslt;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	int onBooking(ArrayList datalist) {
		int i = 0;
		int idd = 0;
		try {
			Object lname = datalist.get(2);
			// System.out.println(lname);
			String id = "SELECT LAPTOPID FROM LAPTOP WHERE LAPTOPNAME='" + lname + "'";
			ResultSet lid = con.createStatement().executeQuery(id);
			while (lid.next()) {
				idd = lid.getInt("LAPTOPID");

			}
			String sql = "INSERT INTO CUSTOMERDETAILS VALUES('" + datalist.get(0) + "','" + datalist.get(1) + "','"
					+ idd + "')";
			// System.out.println(sql);
			i = con.createStatement().executeUpdate(sql);

		} catch (Exception e) {
			System.out.println(e);
		}
		return i;
	}

}
