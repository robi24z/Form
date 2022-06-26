package flightDetails;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import laptopDetails.ActionClassForLaptop;

class Fname extends Customer {
	Fname(String fname) {
		t3.setText(fname);
	}
}

class Customer implements ActionListener, ItemListener {
	String fname;
	JFrame f;
	JLabel l4;
	JTextField t1, t2, t3;
	JButton b1;

	Customer() {

		// Labels//

		f = new JFrame("Customer Details");
		JLabel l1 = new JLabel("Customer Name : ");
		l1.setBounds(100, 100, 150, 30);
		JLabel l2 = new JLabel("Customer Email : ");
		l2.setBounds(100, 150, 150, 30);
		JLabel l3 = new JLabel("Flight Name : ");
		l3.setBounds(100, 200, 150, 30);
		l4 = new JLabel();
		l4.setBounds(250, 230, 150, 30);
		f.add(l1);
		f.add(l2);
		f.add(l3);
		f.add(l4);

		// TextField//

		t1 = new JTextField();
		t1.setBounds(250, 105, 120, 20);
		t2 = new JTextField();
		t2.setBounds(250, 155, 120, 20);
		t3 = new JTextField();
		t3.setBounds(250, 205, 120, 20);
		t3.setEditable(false);
		f.add(t1);
		f.add(t2);
		f.add(t3);

		// Button//

		b1 = new JButton("Confirm Booking");
		b1.setBounds(150, 280, 150, 20);
		b1.addActionListener(this);
		f.add(b1);

		f.setSize(500, 500);
		f.setLayout(null);
		f.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == b1) {
			if (t1.getText().equals("") && t2.getText().equals("")) {
				l4.setText("* Please fill in all the fields");

				// new Fname(fname);
			} else {
				String cname = t1.getText();
				String cemail = t2.getText();
				ArrayList list = new ArrayList();
				list.add(t1.getText());
				list.add(t2.getText());
				list.add(t3.getText());
				new ActionClassForFlight().onBookingFlight(list);
				//new ActionClassForFlight().tableDisplay(cname, cemail);
				JOptionPane.showMessageDialog(null, "Submitted Successfully");
				f.setVisible(false);
				new Booking(cname,cemail);
			}

		}

	}

	@Override
	public void itemStateChanged(ItemEvent e) {
	}
}

public class CustomerDetails {

	public static void main(String[] args) {
		new Customer();
	}

}
