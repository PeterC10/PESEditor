package editor;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import javax.swing.filechooser.FileFilter;

public class OptionFilter extends FileFilter {

	public boolean accept(File f) {

		if (f == null) {
			return false;
		}
		if (f.isDirectory()) {
			return true;
		}

		String extension = PESUtils.getExtension(f);
		if (extension != null) {
			if (extension.equals(PESUtils.xps) && fileIsXPSOption(f)) {
				return true;
			}
			if (extension.equals(PESUtils.psu) && fileIsPSUOption(f)) {
				return true;
			}
			if (extension.equals(PESUtils.max) && fileIsMAXOption(f)) {
				return true;
			}
		} else if (fileIsPCOption(f)) {
			return true;
		}
		return false;
	}

	private boolean fileIsPSUOption(File f) {
		boolean result = false;
		try {
			if (f.canRead()) {
				byte[] identBytes = new byte[19];
				String identCheck = "";
				RandomAccessFile rf = new RandomAccessFile(f, "r");
				rf.seek(64);
				rf.read(identBytes);
				identCheck = new String(identBytes);
				rf.close();
				result = OptionFile.isPS2pes(identCheck);
			}
		} catch (IOException e) {
			result = false;
		}
		return result;
	}
	
	private boolean fileIsXPSOption(File f) {
		boolean result = false;
		try {
			if (f.canRead()) {
				byte[] identBytes = new byte[19];
				String identCheck = "";
				RandomAccessFile rf = new RandomAccessFile(f, "r");
				rf.seek(21);
				int skip = PESUtils.swabInt(rf.readInt());
				if (rf.skipBytes(skip) == skip) {
					skip = PESUtils.swabInt(rf.readInt());
					if (rf.skipBytes(skip) == skip) {
						skip = PESUtils.swabInt(rf.readInt()) + 6;
						if (rf.skipBytes(skip) == skip) {
							rf.read(identBytes);
							identCheck = new String(identBytes);
						}
					}
				}
				rf.close();
				result = OptionFile.isPS2pes(identCheck);
			}
		} catch (IOException e) {
			result = false;
		}
		return result;
	}
	
	private boolean fileIsMAXOption(File f) {
		boolean result = false;
		try {
			if (f.canRead()) {
				byte[] identBytes = new byte[19];
				String identCheck = "";
				RandomAccessFile rf = new RandomAccessFile(f, "r");
				rf.seek(16);
				rf.read(identBytes);
				identCheck = new String(identBytes);
				rf.close();
				result = OptionFile.isPS2pes(identCheck);
			}
		} catch (IOException e) {
			result = false;
		}
		return result;
	}

	private boolean fileIsPCOption(File f) {
		boolean result = false;
		try {
			if (f.canRead() && f.length() == OptionFile.LENGTH) {
				byte[] id = new byte[4];
				RandomAccessFile rf = new RandomAccessFile(f, "r");
				rf.read(id);
				rf.close();
				if (id[0] == -97 && id[1] == 114 && id[2] == -31 && id[3] == -58) {
					result = true;
				}
			}
		} catch (IOException e) {
			result = false;
		}
		return result;
	}
	
	public String getDescription() {
		return "PES6 Option File";
	}

}
