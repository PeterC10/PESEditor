package pes.editor;

import java.awt.Dimension;

import javax.swing.JList;
import javax.swing.ListSelectionModel;

public class SquadNumList extends JList {
	private OptionFile of;

	public SquadNumList(OptionFile opf) {
		super();
		// System.out.println("team-" + team);
		of = opf;
		// refresh(team);
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		setLayoutOrientation(JList.VERTICAL);
		setVisibleRowCount(1);
		//setFont(new Font("Dialog", Font.PLAIN, 12));
		// System.out.println(getFont());
		setPreferredSize(new Dimension(20, 576));
	}

	public void refresh(int team) {
		String[] num;
		int size;
		int firstAdr;
		int a;
		int n;
		int ft;
		if (team < 71) {
			size = 23;
			firstAdr = Squads.num23 + (team * size);
			ft = team;
		} else if (team == 71) {
			size = 14;
			firstAdr = Squads.num23 + (team * 23);
			ft = team;
		} else {
			size = 32;
			firstAdr = Squads.num32 + ((team - 73) * size);
			ft = team - 9;
		}
		num = new String[size];
		for (int p = 0; p < size; p++) {
			a = firstAdr + p;

			if ((team >= 0 && team < 64) || (team >= 73 && team < 213)) {
				//a = 670512 + (628 * ft) + 6232 + p;
				a = firstAdr + Formations.getSlot(of, ft, p);
			}

			n = of.toInt(of.data[a]) + 1;
			if (n == 256) {
				num[p] = "...";
			} else {
				num[p] = String.valueOf(n);
			}
		}
		setListData(num);
		// System.out.println(getWidth() + ", " + getHeight());
	}

}
