package tkorg.idrs.core.ontology;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class OWLFileFilter extends FileFilter{

	@Override
	public boolean accept(File f) {
		return f.getName ().toLowerCase ().endsWith (".owl")
        || f.isDirectory ();
	}

	@Override
	public String getDescription() {
		return "OWL files (*.owl)";
	}

}
