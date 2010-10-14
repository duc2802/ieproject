package websphinx.workbench;

import java.io.File;

import javax.swing.JFileChooser;
import tkorg.idrs.gui.main.IDRSResourceBundle;


public class DownloadDocDialog extends JFileChooser {
	public DownloadDocDialog(){
		super();		
		init();
	}
	private void init(){	
		this.setMultiSelectionEnabled(false);
	
		this.setCurrentDirectory(new File("."));
		this.setFileSelectionMode(DIRECTORIES_ONLY);	
		this.setDialogTitle(IDRSResourceBundle.res.getString("download.searchengine.results"));
		this.setAcceptAllFileFilterUsed(false);
	}
}
