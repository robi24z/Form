package laptopDetails;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

class Details implements ItemListener {
	JFrame f;
	JTable table;
	JComboBox cb1;
	DefaultTableModel model;
	String column[] = { "INDEX", "CUSTOMER NAME", "CUSTOMER EMAIL", "LAPTOP", "PRICE", "COMPANY NAME" };

	Details() {
		f = new JFrame("Customer Details");

		// Labels//

		JLabel l1;
		l1 = new JLabel("Customer Name : ");
		l1.setBounds(350, 100, 120, 20);
		f.add(l1);

		// Combobox//

		cb1 = new JComboBox();
		cb1.setBounds(550, 100, 120, 20);
		cb1.addItem("Select");
		ResultSet res = new ActionClassForLaptop().onLoadingCustomerName();
		// System.out.println(res);
		if (res != null) {
			try {
				while (res.next()) {
					cb1.addItem(res.getString("CUSTOMERNAME"));
				}
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		cb1.addItemListener(this);
		f.add(cb1);

		// Table//

		String data[][] = {};
		model = new DefaultTableModel(data, column);
		table = new JTable(model);
		JScrollPane sp = new JScrollPane(table);
		sp.setBounds(30, 200, 920, 300);
		f.add(sp);

		f.setSize(1000, 600);
		f.setLayout(null);
		f.setVisible(true);
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			Object cname = cb1.getSelectedItem();
			ResultSet result = new ActionClassForLaptop().tableDisplay(cname);
			model.setRowCount(0);
			int i = 0;
			// System.out.println("result===========" + result);
			if (result != null) {
				try {
					while (result.next()) {
						// System.out.println("result.next()==========="+result.next());
						model.insertRow(i,
								new Object[] { i + "", result.getString("CUSTOMERNAME") + "",
										result.getString("CUSTOMEREMAIL") + "", result.getString("LAPTOPNAME") + "",
										result.getInt("PRICE") + "", result.getString("COMPANYNAME") + "" });

						i++;

					}
				} catch (SQLException ee) {
					ee.printStackTrace();
				}
			}
		}

	}
}

public class CustomerDetails {
	public static void main(String[] args) {
		new Details();
	}
}
