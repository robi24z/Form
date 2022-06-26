package vehicleDetails;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

class Customer {
	JFrame f;
	JComboBox cb1;
	JButton b1;
	JTable table;
	DefaultTableModel model;
	String column[] = { "INDEX", "CUSTOMERNAME", "CUSTOMEREMAIL", "CUSTOMERADDRESS", "CUSTOMERPHONE", "VEHICLENAME",
			"PRICE", "SHOWROOM" };

	Customer() {

		f = new JFrame("Customer Details");

		// Labels//
		JLabel l1 = new JLabel("Customer Name : ");
		l1.setBounds(350, 50, 150, 30);
		f.add(l1);

		// Combobox//
		cb1 = new JComboBox();
		cb1.setBounds(500, 55, 150, 20);
		cb1.addItem("Select");
		f.add(cb1);

		// Table//

		String data[][] = {};
		model = new DefaultTableModel(data, column);
		table = new JTable(model);
		JScrollPane sp = new JScrollPane(table);
		sp.setBounds(10, 150, 950, 100);
		f.add(sp);

		// Button//

		b1 = new JButton("BACK");
		b1.setBounds(860, 300, 100, 20);
		f.add(b1);

		f.setSize(1000, 400);
		f.setLayout(null);
		f.setVisible(true);

	}
}

public class CustomerDetails {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Customer();

	}

}
