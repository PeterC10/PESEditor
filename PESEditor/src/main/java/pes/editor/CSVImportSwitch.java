package pes.editor;

import java.awt.GridLayout;

import javax.swing.JCheckBox;
import javax.swing.JPanel;

public class CSVImportSwitch extends JPanel {
	JCheckBox extra;

	JCheckBox create;

	JCheckBox updateClubTeams;

	JCheckBox updateNationalTeams;

	JCheckBox updateClassicTeams;

	public CSVImportSwitch() {
		super(new GridLayout(0, 1));
		updateClubTeams = new JCheckBox("Update Club Squads");
		updateNationalTeams = new JCheckBox("Update National Squads");
		updateClassicTeams = new JCheckBox("Update Classic Squads");
		// head.setToolTipText("
		add(updateClubTeams);
		add(updateNationalTeams);
		add(updateClassicTeams);
	}

}
