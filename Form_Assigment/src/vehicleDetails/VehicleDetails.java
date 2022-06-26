package vehicleDetails;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

class Vehicle implements ActionListener, ItemListener {
	JFrame f;
	JTextField t1, t2, t3, t4, t5;
	JComboBox cb1;
	JButton b1, b2;

	Vehicle() {

		f = new JFrame("Vehicle Sale");

		// Labels//

		JLabel l1, l2, l3, l4, l5, l6;
		l1 = new JLabel("Customer Name : ");
		l1.setBounds(100, 100, 150, 30);
		l2 = new JLabel("Customer Email : ");
		l2.setBounds(100, 150, 150, 30);
		l3 = new JLabel("Customer Address : ");
		l3.setBounds(100, 200, 150, 30);
		l4 = new JLabel("Vehicle : ");
		l4.setBounds(100, 250, 150, 30);
		l5 = new JLabel("Price : ");
		l5.setBounds(100, 300, 150, 30);
		l6 = new JLabel("Customer Phone : ");
		l6.setBounds(100, 350, 150, 30);
		f.add(l1);
		f.add(l2);
		f.add(l3);
		f.add(l4);
		f.add(l5);
		f.add(l6);

		// Textfield//

		t1 = new JTextField();
		t1.setBounds(250, 105, 150, 20);
		t2 = new JTextField();
		t2.setBounds(250, 155, 150, 20);
		t3 = new JTextField();
		t3.setBounds(250, 205, 150, 20);
		t4 = new JTextField();
		t4.setBounds(250, 305, 150, 20);
		t4.setEditable(false);
		t5 = new JTextField();
		t5.setBounds(250, 355, 150, 20);
		f.add(t1);
		f.add(t2);
		f.add(t3);
		f.add(t4);
		f.add(t5);

		// Combobox//

		cb1 = new JComboBox();
		cb1.setBounds(250, 255, 150, 20);
		cb1.addItem("Select");
		ResultSet result = new ActionClassForVehicle().onLoadingVehicleName();
		if (result != null) {
			try {
				while (result.next()) {
					cb1.addItem(result.getString("VEHICLENAME"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		cb1.addActionListener(this);
		cb1.addItemListener(this);
		f.add(cb1);

		// Button//

		b1 = new JButton("Book");
		b1.setBounds(100, 450, 130, 20);
		b2 = new JButton("Show Customer");
		b2.setBounds(250, 450, 130, 20);
		f.add(b1);
		f.add(b2);

		f.setSize(500, 600);
		f.setLayout(null);
		f.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == b1) {
			if (t1.getText().equals("") && t2.getText().equals("") && t3.getText().equals("") && t4.getText().equals("")
					&& t5.getText().equals("") && cb1.getSelectedIndex() == 0) {

			} else {
				ArrayList list = new ArrayList();
				list.add(t1.getText());
				list.add(t2.getText());
				list.add(cb1.getSelectedItem());
				list.add(t3.getText());
				new ActionClassForVehicle().onBooking(list);
				JOptionPane.showMessageDialog(null, "Submitted Successfully");
			}
		}

	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			if (e.getSource() == cb1) {
				Object name = cb1.getSelectedItem();
				ResultSet result = new ActionClassForVehicle().OnSelectingVehicleName(name);
				if (result != null) {
					try {
						while (result.next()) {
							t4.setText(result.getString("VEHICLEPRICE"));
						}

					} catch (SQLException ee) {

						ee.printStackTrace();
					}
				}
			}
		}
	}
}

public class VehicleDetails {

	public static void main(String[] args) {
		new Vehicle();
	}

}
