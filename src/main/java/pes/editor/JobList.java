package pes.editor;

import java.awt.Color;

import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

//import java.util.*;

public class JobList extends JList implements ListSelectionListener {
	private OptionFile of;

	private int offSet;

	private String job;

	int team;

	private boolean ok = false;

	public JobList(OptionFile opf, int os, String j, Color colour) {
		super();
		// System.out.println("team-" + team);
		of = opf;
		offSet = os;
		job = j;
		refresh(-1);
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		setLayoutOrientation(JList.VERTICAL);
		setVisibleRowCount(32);
		setSelectionBackground(colour);
		// setBackground(new Color(255, 255, 224));
		// setFont(new Font("Dialog", Font.BOLD, 12));
		// System.out.println(getFont());
		addListSelectionListener(this);
	}

	public void refresh(int t) {
		ok = false;
		team = t;
		String[] pos = new String[11];
		int p = 0;
		for (int i = 0; i < 11; i++) {
			pos[i] = " ";
		}
		if (t == -1) {
			pos[0] = job;
		} else {
			p = Formations.getJob(of, t, offSet);
			if (p >= 0 && p < 11) {
				pos[p] = job;
			}
		}
		setListData(pos);
		setSelectedIndex(p);
		ok = true;
	}

	public void valueChanged(ListSelectionEvent e) {
		if (!e.getValueIsAdjusting()) {
			if (!isSelectionEmpty() && ok) {
				Formations.setJob(of, team, offSet, of.toByte(getSelectedIndex()));
				refresh(team);
			}
		}
	}

}
