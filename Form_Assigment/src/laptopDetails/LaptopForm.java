package laptopDetails;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

class Form implements ActionListener, ItemListener, KeyListener {
	JFrame f;
	JComboBox cb1;
	JButton b1;
	JLabel l7;
	JTextField t1, t2, t3, t4, t5;
	int balance;

	Form() {
		f = new JFrame("Online Laptop Service");

		// Labels//

		JLabel l1, l2, l3, l4, l5, l6;
		l1 = new JLabel("Customer Name : ");
		l1.setBounds(50, 100, 120, 20);
		l2 = new JLabel("Customer Email : ");
		l2.setBounds(50, 150, 120, 20);
		l3 = new JLabel("Laptop Name : ");
		l3.setBounds(50, 200, 120, 20);
		l4 = new JLabel("Price : ");
		l4.setBounds(50, 250, 120, 20);
		l5 = new JLabel("Customer Rate : ");
		l5.setBounds(50, 300, 120, 20);
		l6 = new JLabel("Balance Price : ");
		l6.setBounds(50, 350, 120, 20);
		l7 = new JLabel("");
		l7.setBounds(120, 380, 200, 20);
		f.add(l1);
		f.add(l2);
		f.add(l3);
		f.add(l4);
		f.add(l5);
		f.add(l6);
		f.add(l7);

		// Textfields//

		t1 = new JTextField();
		t1.setBounds(200, 100, 120, 20);
		t2 = new JTextField();
		t2.setBounds(200, 150, 120, 20);
		t3 = new JTextField();
		t3.setBounds(200, 250, 120, 20);
		t3.setEditable(false);
		t4 = new JTextField();
		t4.setBounds(200, 300, 120, 20);
		t4.addKeyListener(this);
		t5 = new JTextField();
		t5.setBounds(200, 350, 120, 20);
		t5.setEditable(false);
		f.add(t1);
		f.add(t2);
		f.add(t3);
		f.add(t4);
		f.add(t5);

		// Combobox//

		cb1 = new JComboBox();
		cb1.setBounds(200, 200, 120, 20);
		cb1.addItem("Select");
		ResultSet res = new ActionClassForLaptop().onLoadingLaptopName();
		// System.out.println(res);
		if (res != null) {
			try {
				while (res.next()) {
					cb1.addItem(res.getString("LAPTOPNAME"));
				}
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		cb1.addItemListener(this);
		f.add(cb1);

		// Button//

		b1 = new JButton("Book");
		b1.setBounds(120, 420, 120, 20);
		b1.addActionListener(this);
		f.add(b1);

		f.setSize(400, 550);
		f.setLayout(null);
		f.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == b1) {
			if ((t1.getText().equals("")) && (t2.getText().equals("")) && (t4.getText().equals(""))
					&& (cb1.getSelectedIndex() == 0)) {
				l7.setText("* Please fill in all the fields");
			} else {
				ArrayList list = new ArrayList();
				list.add(t1.getText());
				list.add(t2.getText());
				list.add(cb1.getSelectedItem());
				list.add(t3.getText());
				new ActionClassForLaptop().onButtonClick(list);
				JOptionPane.showMessageDialog(null, "Submitted Successfully");
				f.setVisible(false);
				new Details();
			}
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			if (e.getSource() == cb1) {
				Object name = cb1.getSelectedItem();
				ResultSet result = new ActionClassForLaptop().OnSelectingLaptopName(name);
				if (result != null) {
					try {
						while (result.next()) {
							t3.setText(result.getString("PRICE"));
						}

					} catch (SQLException ee) {

						ee.printStackTrace();
					}
				}
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == t4) {
			// System.out.println("Key Released===" + t4.getText());
			int cprice = Integer.parseInt(t4.getText());
			int price = Integer.parseInt(t3.getText());
			int balance = new ActionClassForLaptop().balaceDisplay(price, cprice);
			// System.out.println(balance);
			String bal = String.valueOf(balance);
			t5.setText(bal);

		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}

public class LaptopForm {
	public static void main(String[] args) {
		new Form();
	}
}
