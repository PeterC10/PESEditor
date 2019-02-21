package editor;

import java.awt.GridLayout;

import javax.swing.JCheckBox;
import javax.swing.JPanel;

public class CSVSwitch extends JPanel {
	JCheckBox head;

	JCheckBox extra;

	JCheckBox create;

	public CSVSwitch() {
		super(new GridLayout(0, 1));
		// PeterC10 MOD: Remove Column Headings Option
		// head = new JCheckBox("Column Headings");
		extra = new JCheckBox("Extra Players");
		create = new JCheckBox("Created Players");
		// head.setToolTipText("
		// add(head);
		// PeterC10 MOD: Check options by default
		extra.setSelected(true);
		create.setSelected(true);
		add(extra);
		add(create);
	}

}
