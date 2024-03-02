package pes.editor;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import javax.swing.Icon;
import javax.swing.SwingConstants;

public class CopySwapIcon implements Icon, SwingConstants {
	private boolean swap;

	private int width = 10;

	private int height = 20;

	public CopySwapIcon(boolean s) {
		swap = s;
	}

	public int getIconHeight() {
		return height;
	}

	public int getIconWidth() {
		return width;
	}

	public void paintIcon(Component c, Graphics g, int x, int y) {
		Graphics2D g2 = (Graphics2D) g;
		g2.translate(x, y);
		g2.setPaint(Color.black);
		g2.draw(new Line2D.Double(5, 0, 5, 20));

		g2.draw(new Line2D.Double(5, 20, 0, 15));
		g2.draw(new Line2D.Double(5, 20, 10, 15));
		if (swap) {
			g2.draw(new Line2D.Double(5, 0, 0, 5));
			g2.draw(new Line2D.Double(5, 0, 10, 5));
		}

		g2.translate(-x, -y); // Restore graphics object
	}
}
