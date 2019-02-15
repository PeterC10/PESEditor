package editor;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ShopPanel extends JPanel {
	OptionFile of;

	JLabel status;

	// JButton lock;
	JButton unlock;

	public ShopPanel(OptionFile opf) {
		super();
		of = opf;
		JPanel pan = new JPanel(new GridLayout(0, 1));
		pan.setBorder(BorderFactory.createTitledBorder("Shop Items"));
		JPanel bpan = new JPanel();
		status = new JLabel("");
		/*
		 * lock = new JButton("Lock"); lock.addActionListener(new
		 * ActionListener() { public void actionPerformed (ActionEvent l) {
		 * of.data[5144] = 0; of.data[5145] = 0; of.data[5146] = 0;
		 * of.data[5148] = 0; of.data[5149] = 0; of.data[5150] = 0;
		 * of.data[5151] = 0; of.data[5152] = 0; of.data[5153] = 0;
		 * of.data[5154] = 0; of.data[5155] = 0; of.data[5156] = 0;
		 * of.data[5157] = 0; of.data[5158] = 0; of.data[5159] = 0;
		 * of.data[5160] = 0; of.data[5161] = 0; of.data[5162] = 0;
		 * of.data[5163] = 0; of.data[5164] = 0; of.data[52] = 0;
		 * status.setText("Locked"); } });
		 */
		unlock = new JButton("Unlock");
		unlock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent u) {
				for (int i = 0; i < 20; i++) {
					of.data[5144 + i] = -1;
				}
				of.data[5164] = -6;
				of.data[5165] = 11;
				of.data[5166] = -2;
				of.data[5167] = -1;
				of.data[5168] = -17;
				of.data[5169] = 127;
				of.data[52] = 100;
				status.setText("Unlocked");
			}
		});
		// bpan.add(lock);
		bpan.add(unlock);
		pan.add(bpan);
		pan.add(status);
		add(pan);
	}

}
