package pes.editor;

//import java.io.File;
import java.io.File;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class PESUtils {
	public final static String xps = "xps";
	
	public final static String psu = "psu";
	
	public final static String max = "max";

	public final static String png = "png";

	public final static String gif = "gif";

	public final static String csv = "csv";

	public final static String[] extraSquad = { "Classic England",
			"Classic France", "Classic Germany", "Classic Italy",
			"Classic Netherlands", "Classic Argentina", "Classic Brazil",
			"<Japan 1>", "<Edited> National 1",
			"<Edited> National 2", "<Edited> National 3",
			"<Edited> National 4", "<Edited> National 5",
			"<Edited> National 6", "<Edited> National 7", "<Edited>",
			"<ML Default>", "<Shop 1>", "<Shop 2>", "<Shop 3>", "<Shop 4>",
			"<Shop 5>" };

	/*
	 * Get the extension of a file.
	 */
	public static String getExtension(File f) {
		String ext = null;
		String s = f.getName();
		int i = s.lastIndexOf('.');

		if (i > 0 && i < s.length() - 1) {
			ext = s.substring(i + 1).toLowerCase();
		}
		return ext;
	}

	public static int swabInt(int v) {
		return (v >>> 24) | (v << 24) | ((v << 8) & 0x00FF0000)
				| ((v >> 8) & 0x0000FF00);
	}
	
	public static void javaUI() {
	try {
		UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (InstantiationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IllegalAccessException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (UnsupportedLookAndFeelException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	
	public static void systemUI() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}

}
