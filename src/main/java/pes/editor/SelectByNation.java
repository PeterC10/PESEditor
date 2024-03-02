package pes.editor;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class SelectByNation extends JPanel {
	public NationalityList freeList;

	JComboBox nationBox;

	JButton sort;

	boolean alpha;

	public SelectByNation(OptionFile opf) {
		super(new BorderLayout());
		JScrollPane scroll = new JScrollPane(
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		freeList = new NationalityList(opf);
		alpha = true;
		sort = new JButton("Alpha Order");
		sort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				if (alpha) {
					sort.setText("Index Order");
					alpha = false;
				} else {
					sort.setText("Alpha Order");
					alpha = true;
				}
				int i = nationBox.getSelectedIndex();
				freeList.refresh(i, alpha);
			}
		});
		String[] boxChoice = new String[Stats.nation.length + 6];
		boxChoice[boxChoice.length - 6] = "Unused";
		boxChoice[boxChoice.length - 5] = "ML Old";
		boxChoice[boxChoice.length - 4] = "ML Young";
		boxChoice[boxChoice.length - 3] = "Duplicates?";
		boxChoice[boxChoice.length - 2] = "Free Agents";
		boxChoice[boxChoice.length - 1] = "All Players";
		System.arraycopy(Stats.nation, 0, boxChoice, 0, Stats.nation.length);
		nationBox = new JComboBox(boxChoice);
		nationBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {

				int i = nationBox.getSelectedIndex();
				// System.out.println(i);
				if (i != -1) {
					// if (i == 0) {
					// freeList.refresh(999);
					// } else {
					freeList.refresh(i, alpha);
					// }
				}
			}
		});
		nationBox.setMaximumRowCount(32);
		freeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		freeList.setLayoutOrientation(JList.VERTICAL);
		freeList.setVisibleRowCount(11);
		scroll.setViewportView(freeList);
		add(nationBox, BorderLayout.NORTH);
		add(scroll, BorderLayout.CENTER);
		add(sort, BorderLayout.SOUTH);
		// refresh();
		setPreferredSize(new Dimension(164, 601));
	}

	public void refresh() {
		nationBox.setSelectedIndex(nationBox.getItemCount() - 1);
		freeList.refresh(nationBox.getSelectedIndex(), alpha);
	}

}
