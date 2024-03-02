package pes.editor;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

//import java.io.UnsupportedEncodingException;

public class SelectByTeam extends JPanel {
	public SquadList squadList;

	JComboBox teamBox;

	OptionFile of;

	SquadNumList numList;

	PositionList posList;

	boolean normal;

	// NameEditor nameEditor;

	public SelectByTeam(OptionFile opf, boolean n) {
		super(new BorderLayout());
		of = opf;
		normal = n;
		// getTeams();
		teamBox = new JComboBox();
		teamBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent t) {
				// System.out.println(teamBox.getSelectedIndex());
				if (teamBox.getSelectedIndex() != -1) {
					squadList.refresh(teamBox.getSelectedIndex(), true);
					if (normal) {
						posList.refresh(teamBox.getSelectedIndex());
						numList.refresh(teamBox.getSelectedIndex());
					}
				}
			}
		});
		teamBox.setMaximumRowCount(32);
		squadList = new SquadList(of, n);
		add(teamBox, BorderLayout.NORTH);
		if (normal) {
			numList = new SquadNumList(of);
			posList = new PositionList(of, true);
			add(posList, BorderLayout.WEST);
			add(squadList, BorderLayout.CENTER);
			add(numList, BorderLayout.EAST);
		} else {
			JScrollPane scroll = new JScrollPane(
					JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
					JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			setPreferredSize(null);
			scroll.setViewportView(squadList);
			add(scroll, BorderLayout.CENTER);
		}
		if (normal) {
			setPreferredSize(new Dimension(164, 601));
		}
	}

	public void refresh() {
		String[] squads;
		if (normal) {
			squads = new String[57 + PESUtils.extraSquad.length + Clubs.total];
		} else {
			squads = new String[57 + PESUtils.extraSquad.length + Clubs.total + 1];
		}
		System.arraycopy(Stats.nation, 0, squads, 0, 57);
		System.arraycopy(PESUtils.extraSquad, 0, squads, 57, 16);
		System.arraycopy(Clubs.getNames(of), 0, squads, 57 + 16, Clubs.total);
		System
				.arraycopy(PESUtils.extraSquad, 16, squads, 57 + 16 + Clubs.total,
						6);
		if (!normal) {
			squads[57 + PESUtils.extraSquad.length + Clubs.total] = "All Players";
		}
		teamBox.setModel(new DefaultComboBoxModel(squads));
		if (normal) {
			teamBox.setSelectedIndex(57 + 16);
			numList.refresh(teamBox.getSelectedIndex());
			posList.refresh(teamBox.getSelectedIndex());
		} else {
			teamBox.setSelectedIndex(57 + PESUtils.extraSquad.length + Clubs.total);
		}

		squadList.refresh(teamBox.getSelectedIndex(), true);
	}

}
