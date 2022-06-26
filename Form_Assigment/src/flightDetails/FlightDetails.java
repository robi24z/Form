package flightDetails;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import laptopDetails.ActionClassForLaptop;

class Flight implements ActionListener, ItemListener {
	JFrame f;
	JComboBox cb1;
	JLabel l3;
	JButton b1;
	JTable table;
	DefaultTableModel model;
	String column[] = { "INDEX", "FLIGHT NAME", "ORIGIN", "DESTINATION", "TIME" };

	Flight() {

		// Labels//

		f = new JFrame("Online Flight Service");
		JLabel l1 = new JLabel("Choose your flight : ");
		l1.setBounds(150, 100, 120, 30);
		JLabel l2 = new JLabel("Flight Details : ");
		l2.setBounds(30, 170, 100, 30);
		l3 = new JLabel();
		l3.setBounds(300, 120, 200, 30);
		f.add(l1);
		f.add(l2);
		f.add(l3);

		// Combobox//

		cb1 = new JComboBox();
		cb1.setBounds(300, 105, 120, 20);
		cb1.addItem("Select");
		ResultSet res = new ActionClassForFlight().onLoadingFlightName();
		//System.out.println(res);
		if (res != null) {
			try {
				while (res.next()) {
					cb1.addItem(res.getString("FLIGHTNAME"));
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
		sp.setBounds(30, 200, 520, 300);
		f.add(sp);

		// Button//

		b1 = new JButton("Book Flight");
		b1.setBounds(230, 520, 100, 20);
		b1.addActionListener(this);
		f.add(b1);

		f.setSize(600, 600);
		f.setLayout(null);
		f.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (cb1.getSelectedIndex() == 0) {
			l3.setText("* Please select a flight name");
		} else {
			if (e.getSource() == b1) {
				if (table.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "Select a Flight");
				} else {
					String flightname = (String) model.getValueAt(table.getSelectedRow(), 1);
					// System.out.println("name===" + flightname);
					new Fname(flightname);
					f.setVisible(false);
				}

			}

		}

	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			Object fname = cb1.getSelectedItem();
			ResultSet result = new ActionClassForFlight().flightDetails(fname);
			model.setRowCount(0);
			int i = 0;
			// System.out.println("result===========" + result);
			if (result != null) {
				try {
					while (result.next()) {
						// System.out.println("result.next()==========="+result.next());
						model.insertRow(i,
								new Object[] { i + "", result.getString("FLIGHTNAME") + "",
										result.getString("ORIGIN") + "", result.getString("DESTINATION") + "",
										result.getString("TIME") + "" });

						i++;

					}
				} catch (SQLException ee) {
					ee.printStackTrace();
				}
			}
		}

	}
}

public class FlightDetails {

	public static void main(String[] args) {
		new Flight();

	}

}
