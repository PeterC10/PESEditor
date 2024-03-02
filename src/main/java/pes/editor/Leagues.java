package pes.editor;

import java.io.UnsupportedEncodingException;

public class Leagues {
	static final byte total = 29;

	static final int nameAdr = 11859;

	static final byte maxLen = 61;

	static final byte fieldLen = 84;

	static final String[] def = { "ISS", "England", "French", "German", "Italian",
			"Netherlands", "Spanish", "International", "European", "African",
			"American", "Asia-Oceanian", "Konami", "D2", "D1", "European Masters",
			"European Championship", "Product Placement Cup" };

	static String get(OptionFile of, int l) {
		byte len;
		int a;
		String name;
		a = nameAdr + (l * fieldLen);
		len = 0;
		if (of.data[a] != 0) {
			for (byte i = 0; len == 0 && i < maxLen + 1; i++) {
				if (len == 0 && of.data[a + i] == 0) {
					len = i;
				}
			}
			try {
				name = new String(of.data, a, len, "UTF-8");
				// System.out.println(len);
			} catch (UnsupportedEncodingException e) {
				name = "<" + String.valueOf(l) + ">";
			}
		} else {
			if (l > 10) {
				name = def[l - 11];
				if (l < 27) {
					name = name + " Cup";
				}
			} else {
				name = "<" + String.valueOf(l) + ">";
			}
		}
		//System.out.println(l + "=" + name);
		return name;
	}

	static String[] get(OptionFile of) {
		String[] leagues = new String[total];
		for (byte l = 0; l < total; l++) {
			leagues[l] = get(of, l);
		}
		return leagues;
	}

	static void set(OptionFile of, int ln, String name) {
		if (name.length() <= maxLen && name.length() > 0) {
			int a = nameAdr + (ln * fieldLen);
			byte[] tb = new byte[maxLen + 2];
			byte[] sb;
			try {
				sb = name.getBytes("UTF-8");
			} catch (UnsupportedEncodingException e) {
				sb = new byte[maxLen];
			}
			if (sb.length <= maxLen) {
				System.arraycopy(sb, 0, tb, 0, sb.length);
			} else {
				System.arraycopy(sb, 0, tb, 0, maxLen);
			}
			tb[maxLen + 1] = 1;
			// System.out.println(a);
			System.arraycopy(tb, 0, of.data, a, maxLen + 2);
		}
	}
	
	static void importData(OptionFile ofs, OptionFile ofd) {
		System.arraycopy(ofs.data, nameAdr, ofd.data, nameAdr, fieldLen * total);
	}

}
