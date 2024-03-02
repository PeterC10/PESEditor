package pes.editor;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class WENPanel extends JPanel implements ActionListener {
	OptionFile of;
	JLabel current;
	JTextField field;

	public WENPanel(OptionFile opf) {
		super();
		of = opf;
		JPanel pan = new JPanel(new GridLayout(0, 1));
		pan.setBorder(BorderFactory.createTitledBorder("PES Points"));
		current = new JLabel("");
		field = new JTextField(8);
		field.setToolTipText("Enter an amount (0-99999) and press return");
		field.addActionListener(this);
		pan.add(field);
		pan.add(current);
		add(pan);
		refresh();
	}

	public void refresh() {
		int wen = (of.toInt(of.data[50]) << 16) + (of.toInt(of.data[49]) << 8)
				+ of.toInt(of.data[48]);
		current.setText("Current:  " + wen);
		field.setText("");
	}

	public void setWEN(int newWen) {
		if (newWen >= 0 && newWen <= 99999) {
			of.data[48] = of.toByte(newWen & 0xFF);
			of.data[49] = of.toByte((newWen & 0xFF00) >>> 8);
			of.data[50] = of.toByte((newWen & 0xFF0000) >>> 16);
			of.data[5208] = of.toByte(newWen & 0xFF);
			of.data[5209] = of.toByte((newWen & 0xFF00) >>> 8);
			of.data[5210] = of.toByte((newWen & 0xFF0000) >>> 16);
			refresh();
		} else {
			field.setText("");
			JOptionPane.showMessageDialog(null,
					"Amount must be in the range 0-99999", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void actionPerformed(ActionEvent evt) {
		try {
			setWEN(Integer.parseInt(field.getText()));
		} catch (NumberFormatException exception) {
			EditorLogger.Log(exception);
		}
	}

}
