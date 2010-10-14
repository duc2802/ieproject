/**
 * 
 */
package tkorg.idrs.gui.searchengines;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import tkorg.idrs.core.searchengines.DownloadSearchsResultsFileFilter;
import tkorg.idrs.gui.main.IDRSResourceBundle;

/**
 * @author tiendv
 * Download Dialog for Download searchResults
 */

public class DownLoadDialog extends JFileChooser {
	private DownloadSearchsResultsFileFilter downloadFileFilte;
	public DownLoadDialog(){
		super();		
		init();
	}
	private void init(){	
		this.setMultiSelectionEnabled(false);
		this.setFileFilter(getDownloadSearchResultFileFilter());
		this.setCurrentDirectory(new File("."));
		this.setFileSelectionMode(DIRECTORIES_ONLY);	
		this.setDialogTitle(IDRSResourceBundle.res.getString("download.searchengine.results"));
		this.setAcceptAllFileFilterUsed(false);
	}
	private FileFilter getDownloadSearchResultFileFilter(){
		if(downloadFileFilte == null){
			downloadFileFilte = new DownloadSearchsResultsFileFilter();
		}
		return downloadFileFilte;
	}

}
