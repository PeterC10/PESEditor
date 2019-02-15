package editor;

import java.awt.GridLayout;

import javax.swing.JPanel;

public class WENShopPanel extends JPanel {
	WENPanel wenPanel;

	ShopPanel shopPanel;

	public WENShopPanel(OptionFile opf) {
		super();
		JPanel pan = new JPanel(new GridLayout(0, 1));
		wenPanel = new WENPanel(opf);
		shopPanel = new ShopPanel(opf);
		pan.add(wenPanel);
		pan.add(shopPanel);
		add(pan);
	}

}
