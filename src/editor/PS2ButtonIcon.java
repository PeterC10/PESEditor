package editor;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import javax.swing.Icon;
import javax.swing.SwingConstants;

public class PS2ButtonIcon implements Icon, SwingConstants {
	private int type = 0;

	private int width = 17;

	private int height = 17;

	public PS2ButtonIcon(int t) {
		type = t;
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
		g2.fill(new Ellipse2D.Double(1, 1, 15, 15));
		if (type == 3) {
			g2.setPaint(Color.pink);
			g2.draw(new Rectangle2D.Double(4, 4, 8, 8));
		}
		if (type == 2) {
			g2.setPaint(Color.green);
			g2.draw(new Line2D.Double(8, 4, 12, 12));
			g2.draw(new Line2D.Double(4, 12, 12, 12));
			g2.draw(new Line2D.Double(8, 4, 4, 12));
		}
		if (type == 0) {
			g2.setPaint(Color.red);
			g2.draw(new Ellipse2D.Double(4, 4, 8, 8));
		}
		if (type == 1) {
			g2.setPaint(Color.cyan);
			g2.draw(new Line2D.Double(4, 4, 12, 12));
			g2.draw(new Line2D.Double(4, 12, 12, 4));
		}
		g2.translate(-x, -y); // Restore graphics object
	}
}
