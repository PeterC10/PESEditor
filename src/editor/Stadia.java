package editor;

import java.io.UnsupportedEncodingException;

public class Stadia {
	static final byte total = 36;

	static final int nameAdr = 9544;

	static final byte maxLen = 61;

	static final int switchAdr = nameAdr + (maxLen * total);

	static String get(OptionFile of, int s) {
		byte len;
		int a;
		String name;
		a = nameAdr + (s * maxLen);
		len = 0;
		if (of.data[a] != 0) {
			for (byte i = 0; len == 0 && i < maxLen; i++) {
				if (len == 0 && of.data[a + i] == 0) {
					len = i;
				}
			}
			try {
				name = new String(of.data, a, len, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				name = "<" + String.valueOf(s) + ">";
			}
		} else {
			name = "<" + String.valueOf(s) + ">";
		}
		return name;
	}

	static String[] get(OptionFile of) {
		String[] stadia = new String[total];
		for (byte s = 0; s < total; s++) {
			stadia[s] = get(of, s);
		}
		return stadia;
	}

	static void set(OptionFile of, int sn, String name) {
		if (name.length() < maxLen && name.length() > 0) {
			int a = nameAdr + (sn * maxLen);
			int sa = switchAdr + sn;
			byte[] tb = new byte[maxLen];
			byte[] sb;
			try {
				sb = name.getBytes("UTF-8");
			} catch (UnsupportedEncodingException e) {
				sb = new byte[maxLen - 1];
			}
			if (sb.length <= maxLen - 1) {
				System.arraycopy(sb, 0, tb, 0, sb.length);
			} else {
				System.arraycopy(sb, 0, tb, 0, maxLen - 1);
			}
			System.arraycopy(tb, 0, of.data, a, maxLen);
			of.data[sa] = 1;
		}
	}
	
	static void importData(OptionFile ofs, OptionFile ofd) {
		System.arraycopy(ofs.data, nameAdr, ofd.data, nameAdr, Leagues.nameAdr - nameAdr);
	}

}
