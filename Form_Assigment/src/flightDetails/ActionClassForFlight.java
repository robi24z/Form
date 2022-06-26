package flightDetails;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import laptopDetails.CommonConnection;

public class ActionClassForFlight {
	Connection con;

	ActionClassForFlight() {
		con = new CommonConnection().getConnection();
	}

	ResultSet onLoadingFlightName() {
		try {
			String insert = "SELECT FLIGHTNAME FROM FLIGHT ORDER BY FLIGHTNAME";
			ResultSet res = con.createStatement().executeQuery(insert);
			return res;

		} catch (Exception e) {
			System.out.println(e);
		}
		return null;

	}

	ResultSet flightDetails(Object fname) {
		try {
			String query = "SELECT F1.FLIGHTNAME,F1.ORIGIN,F1.DESTINATION,F1.TIME FROM FLIGHT F1 WHERE FLIGHTNAME='"
					+ fname + "'";
			// System.out.println("query=" + query);
			ResultSet result = con.createStatement().executeQuery(query);
			return result;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;

	}

	int onBookingFlight(ArrayList datalist) {
		int i = 0;
		int idd = 0;
		try {
			Object fname = datalist.get(2);
			System.out.println(fname);
			String id = "SELECT FLIGHTID FROM FLIGHT WHERE FLIGHTNAME='" + fname + "'";
			ResultSet fid = con.createStatement().executeQuery(id);
			while (fid.next()) {
				idd = fid.getInt("FLIGHTID");

			}
			String sql = "INSERT INTO FLIGHTCUSTOMER VALUES('" + datalist.get(0) + "','" + datalist.get(1) + "','" + idd
					+ "')";
			// System.out.println(sql);
			i = con.createStatement().executeUpdate(sql);

		} catch (Exception e) {
			System.out.println(e);
		}
		return i;
	}

	ResultSet tableDisplay(String cname, String cemail) {
		try {
			String query = "SELECT FC1.CUSTOMERNAME,FC1.CUSTOMEREMAIL,F1.FLIGHTNAME,F1.ORIGIN,F1.DESTINATION,F1.TIME FROM FLIGHTCUSTOMER FC1 INNER JOIN FLIGHT F1 ON FC1.FLIGHTID=F1.FLIGHTID WHERE FC1.CUSTOMERNAME='"
					+ cname + "'" + "AND FC1.CUSTOMEREMAIL='" + cemail + "'";
			// System.out.println("query=" + query);
			ResultSet result = con.createStatement().executeQuery(query);
			return result;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;

	}
}
