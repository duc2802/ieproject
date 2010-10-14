
package tkorg.idrs.core.searchengines;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 * @author tiendv
 *	Filter for DownloadDialog . Only dislay file .txt 
 */

public class DownloadSearchsResultsFileFilter extends FileFilter {

	@Override
	public boolean accept(File f) {
		return f.getName ().toLowerCase ().endsWith (".txt")
        || f.isDirectory ();
	}

	@Override
	public String getDescription() {
		return "Text files (*.txt)";
	}

}
