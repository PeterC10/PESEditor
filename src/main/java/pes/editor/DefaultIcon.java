package pes.editor;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.Icon;
import javax.swing.SwingConstants;

public class DefaultIcon implements Icon, SwingConstants {
	private int width = 64;

	private int height = 64;

	public int getIconHeight() {
		return height;
	}

	public int getIconWidth() {
		return width;
	}

	public void paintIcon(Component c, Graphics g, int x, int y) {
		Graphics2D g2 = (Graphics2D) g;
		g2.translate(x, y);
		g2.setFont(new Font("Dialog", Font.BOLD, 18));
		g2.setPaint(Color.black);
		g2.drawString("Default", 0, 38);
		g2.translate(-x, -y); // Restore graphics object
	}
}
