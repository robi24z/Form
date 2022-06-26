package flightDetails;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

class Booking implements ActionListener {
	JFrame f;
	JButton b1;
	JTable table;
	DefaultTableModel model;
	String column[] = { "INDEX", "CUSTOMER NAME", "CUSTOMER EMAIL", "FLIGHT NAME", "ORIGIN", "DESTINATION", "TIME" };
	
	Booking(String cname, String cemail) {

		// Table//
		f = new JFrame("Booking Details");
		String data[][] = {};
		model = new DefaultTableModel(data, column);
		table = new JTable(model);
		JScrollPane sp = new JScrollPane(table);
		sp.setBounds(10, 50, 750, 100);
		f.add(sp);

		ResultSet result = new ActionClassForFlight().tableDisplay(cname, cemail);
		model.setRowCount(0);
		int i = 0;
		// System.out.println("result===========" + result);
		if (result != null) {
			try {
				while (result.next()) {
					// System.out.println("result.next()==========="+result.next());
					model.insertRow(i,
							new Object[] { i + "", result.getString("CUSTOMERNAME") + "",
									result.getString("CUSTOMEREMAIL") + "", result.getString("FLIGHTNAME") + "",
									result.getString("ORIGIN") + "", result.getString("DESTINATION") + "",
									result.getString("TIME") });

				}
				i++;

			} catch (Exception e) {
				System.out.println(e);
			}
		}

		// Button//
		b1 = new JButton("Back");
		b1.setBounds(610, 200, 80, 30);
		b1.addActionListener(this);
		f.add(b1);

		f.setSize(800, 300);
		f.setLayout(null);
		f.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == b1) {
			f.setVisible(false);
			new Customer();
		}

	}
}

public class BookingDetails {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Booking(null, null);
	}

}
