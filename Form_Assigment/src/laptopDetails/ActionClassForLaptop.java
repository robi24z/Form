package laptopDetails;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ActionClassForLaptop {
	Connection con;

	ActionClassForLaptop() {
		con = new CommonConnection().getConnection();
	}

	ResultSet onLoadingLaptopName() {
		try {
			String insert = "SELECT LAPTOPNAME FROM LAPTOP ORDER BY LAPTOPNAME";
			ResultSet res = con.createStatement().executeQuery(insert);
			return res;

		} catch (Exception e) {
			System.out.println(e);
		}
		return null;

	}

	ResultSet OnSelectingLaptopName(Object name) {
		int i = 0;
		try {
			// System.out.println("name=" + name);
			String query = "SELECT LAPTOPID FROM LAPTOP WHERE LAPTOPNAME='" + name + "'";
			ResultSet lapid = con.createStatement().executeQuery(query);
			while (lapid.next()) {
				i = lapid.getInt("LAPTOPID");
			}
			// System.out.println("id: "+i);
			String query1 = "SELECT PRICE FROM LAPTOP WHERE LAPTOPID='" + i + "'";
			ResultSet rslt = con.createStatement().executeQuery(query1);
			// System.out.println("return=" + rslt);
			return rslt;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	int onButtonClick(ArrayList datalist) {
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

	ResultSet onLoadingCustomerName() {
		try {
			String insert = "SELECT CUSTOMERNAME FROM CUSTOMERDETAILS ORDER BY CUSTOMERNAME";
			ResultSet res = con.createStatement().executeQuery(insert);
			return res;

		} catch (Exception e) {
			System.out.println(e);
		}
		return null;

	}

	ResultSet tableDisplay(Object cname) {
		try {
			String query = "SELECT CD1.CUSTOMERNAME,CD1.CUSTOMEREMAIL,L1.LAPTOPNAME,L1.PRICE,C1.COMPANYNAME FROM CUSTOMERDETAILS CD1 INNER JOIN LAPTOP L1 ON CD1.LAPTOPID=L1.LAPTOPID INNER JOIN  COMPANY  C1 ON L1.COMPANYID=C1.COMPANYID WHERE CUSTOMERNAME='"
					+ cname + "'";
			//System.out.println("query=" + query);
			ResultSet result = con.createStatement().executeQuery(query);
			return result;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;

	}

	int balaceDisplay(int price, int cprice) {
		if (price > cprice) {
			int balance = price - cprice;
			return balance;
		} else {
			JOptionPane.showMessageDialog(null, "Please enter correct rate!");
		}
		return 0;

	}

}
