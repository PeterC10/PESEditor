package pes.editor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class LeaguePanel extends JPanel implements ActionListener,
		ListSelectionListener {
	private OptionFile of;

	// JLabel[] name = new JLabel[11];
	private JTextField editor;

	private JList list;

	public LeaguePanel(OptionFile opf) {
		super();
		of = opf;
		init();
		// refresh();
	}

	public void init() {
		editor = new JTextField(15);
		editor.setToolTipText("Enter new name and press return");
		editor.addActionListener(this);
		list = new JList();
		list.addListSelectionListener(this);
		JPanel pan = new JPanel();
		pan.setBorder(BorderFactory.createTitledBorder("League Names"));
		pan.add(list);
		pan.add(editor);
		add(pan);
	}

	public void refresh() {
		list.setListData(Leagues.get(of));
	}

	public void actionPerformed(ActionEvent evt) {
		// JTextField source = evt.getSource();
		int sn = list.getSelectedIndex();
		String text = editor.getText();
		if (sn != -1 && text.length() <= Leagues.maxLen && text.length() > 0) {
			if (!(text.equals(Leagues.get(of, sn)))) {
				Leagues.set(of, sn, text);
				refresh();
			}
			if (sn < Leagues.total - 1) {
				list.setSelectedIndex(sn + 1);
			}
		}
	}

	public void valueChanged(ListSelectionEvent e) {
		if (!e.getValueIsAdjusting()) {
			int i = list.getSelectedIndex();
			if (i == -1) {
				editor.setText("");
			} else {
				editor.setText(Leagues.get(of, i));
				editor.selectAll();
			}
		}
	}

}
