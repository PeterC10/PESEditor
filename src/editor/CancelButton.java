package editor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;

public class CancelButton extends JButton {

	public CancelButton(final JDialog dialog) {
		super("Cancel");
		this.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent t) {
				dialog.setVisible(false);
			}
		});
	}
}
