package pes.editor;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LogoImportDialog extends JDialog {
	private JButton[] flagButton;// = new JButton[64];

	private boolean trans = true;

	private OptionFile of;

	private OptionFile of2;

	JLabel fileLabel;

	boolean of2Open;

	int slot;

	int replacement;

	byte max;

	int adr;

	int size;

	public LogoImportDialog(Frame owner, OptionFile opt, OptionFile opf2) {
		super(owner, true);// "Import Flag / Emblem"
		of = opt;
		of2 = opf2;
		fileLabel = new JLabel("From:");
		JPanel flagPanel;// = new JPanel(new GridLayout(8, 8));

		// if (logoType) {
		max = Logos.total;
		flagPanel = new JPanel(new GridLayout(8, 10));
		/*
		 * } else { max = Flags.total; emptyFlag = of2.emptyFlag; flag =
		 * of2.flag; tranFlag = of2.tranFlag; flagPanel = new JPanel(new
		 * GridLayout(8, 8)); }
		 */
		flagButton = new JButton[max];
		
		PESUtils.javaUI();
		
		for (int l = 0; l < max; l++) {
			flagButton[l] = new JButton(new ImageIcon(Logos.get(of, -1, false)));
			flagButton[l].setMargin(new Insets(0, 0, 0, 0));
			flagButton[l].setActionCommand(Integer.toString(l));
			flagButton[l].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent b) {
					replacement = (Integer.parseInt(((JButton) b.getSource())
							.getActionCommand()));
					importFlag();
					setVisible(false);
				}
			});
			flagPanel.add(flagButton[l]);
		}
		
		PESUtils.systemUI();
		
		JButton transButton = new JButton("Transparency");
		transButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent t) {
				trans = !trans;
				updateFlags();
			}
		});

		CancelButton cancelButton = new CancelButton(this);
		JPanel topPan = new JPanel(new GridLayout(0, 1));
		topPan.add(fileLabel);
		topPan.add(transButton);
		getContentPane().add(topPan, BorderLayout.NORTH);
		getContentPane().add(cancelButton, BorderLayout.SOUTH);
		getContentPane().add(flagPanel, BorderLayout.CENTER);
		of2Open = false;
		slot = 0;
		replacement = 0;
		setResizable(false);
		pack();
	}

	private void updateFlags() {
		for (int f = 0; f < Logos.total; f++) {
			flagButton[f].setIcon(new ImageIcon(Logos.get(of2, f, !trans)));
		}
	}

	public void refresh() {
		updateFlags();
		of2Open = true;
		slot = 0;
		replacement = 0;

		fileLabel.setText("  From:  " + of2.fileName);
	}

	public void show(int i, String title) {
		setTitle(title);
		slot = i;
		setVisible(true);
	}

	private void importFlag() {
		Logos.importLogo(of2, replacement, of, slot);
	}

}
