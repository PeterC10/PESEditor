package pes.editor;

import javax.swing.JButton;
import javax.swing.JDialog;

public class CancelButton extends JButton {

	public CancelButton(final JDialog dialog) {
		super("Cancel");
		this.addActionListener(t -> dialog.setVisible(false));
	}
}
