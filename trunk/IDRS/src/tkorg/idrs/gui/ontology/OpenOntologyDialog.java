package tkorg.idrs.gui.ontology;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileFilter;

import tkorg.idrs.core.ontology.OWLFileFilter;
import tkorg.idrs.gui.main.IDRSResourceBundle;

public class OpenOntologyDialog extends JFileChooser{
	
	private static final long serialVersionUID = 1L;
	
	private JFrame idrsJFrame = null;
	private int xLocation;
	private int yLocation;
	private OWLFileFilter owlFileFilter;
	
	public OpenOntologyDialog(JFrame idrsJFrame){
		super();
		this.idrsJFrame = idrsJFrame;
		xLocation = this.idrsJFrame.getX() + (this.idrsJFrame.getWidth() - this.getWidth())/2;
		yLocation = this.idrsJFrame.getY() + (this.idrsJFrame.getHeight() - this.getHeight())/2;
		init();
	}
	
	/**
	 * 
	 * 
	 */
	private void init(){
		this.setLocation(xLocation,	yLocation);		
		this.setMultiSelectionEnabled(false);
		this.setFileFilter(getOWLFileFilter());
		this.setCurrentDirectory(new File("."));
		this.setFileSelectionMode(FILES_AND_DIRECTORIES);	
		this.setDialogTitle(IDRSResourceBundle.res.getString("open.ontology"));
		this.setAcceptAllFileFilterUsed(false);
	}
	
	/**
	 * 
	 * 
	 * @return
	 */
	private FileFilter getOWLFileFilter(){
		if(owlFileFilter == null){
			owlFileFilter = new OWLFileFilter();
		}
		return owlFileFilter;
	}
	
	public static void updateTextOfComponents(){
		//setDialogTitle(IDRSResourceBundle.res.getString("open.ontolgy"));
	}
}
