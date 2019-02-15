package editor;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class XportInfo {
	public String gameName;

	public String saveName;

	public String notes;

	public String game;

	public XportInfo() {
		gameName = new String("");
		saveName = new String("");
		notes = new String("");
		game = new String("");
	}

	public boolean getInfo(File f) {
		if (f.isFile()) {
			try {
				RandomAccessFile rf = new RandomAccessFile(f, "r");
				gameName = "";
				saveName = "";
				notes = "";
				byte[] temp;
				game = "";
				String extension = PESUtils.getExtension(f);
				if (extension.equals(PESUtils.xps)) {
					// int gameIdent = 0;
					rf.seek(21);
					int size = PESUtils.swabInt(rf.readInt());
					temp = new byte[size];
					rf.read(temp);
					gameName = new String(temp);
					size = PESUtils.swabInt(rf.readInt());
					temp = new byte[size];
					rf.read(temp);
					saveName = new String(temp);
					size = PESUtils.swabInt(rf.readInt());
					temp = new byte[size];
					rf.read(temp);
					notes = new String(temp);
					rf.skipBytes(6);
					temp = new byte[19];
					rf.read(temp);
					game = new String(temp);
				} else if (extension.equals(PESUtils.max)){
					rf.seek(16);
					temp = new byte[19];
					rf.read(temp);
					game = new String(temp);
					rf.seek(48);
					temp = new byte[32];
					rf.read(temp);
					gameName = new String(temp);
					gameName = gameName.replaceAll("\0", "");
				} else {
					rf.seek(64);
					temp = new byte[19];
					rf.read(temp);
					game = new String(temp);
				}
				rf.close();
				return true;
			} catch (IOException e) {
				// System.out.println("io error");
				return false;
			}
		} else {
			return false;
		}/*
			 * finally { if (rf != null) { System.out.println("Closing rf");
			 * rf.close(); } else { System.out.println("rf not open"); } }
			 */
	}

	/*
	 * private int swabInt(int v) { return (v >>> 24) | (v << 24) | ((v << 8) &
	 * 0x00FF0000) | ((v >> 8) & 0x0000FF00); }
	 */

}
