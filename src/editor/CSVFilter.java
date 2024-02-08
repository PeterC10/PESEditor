package editor;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class CSVFilter extends FileFilter {

	// Accept all directories and all csv files.
	public boolean accept(File f) {
		if (f.isDirectory()) {
			return true;
		}

		var extension = PESUtils.getExtension(f);
        return extension != null && extension.equals(PESUtils.csv);

    }

	// The description of this filter
	public String getDescription() {
		return ".csv";
	}
}
