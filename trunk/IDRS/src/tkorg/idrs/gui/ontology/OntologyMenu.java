package tkorg.idrs.gui.ontology;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import tkorg.idrs.gui.main.IDRSResourceBundle;
import tkorg.idrs.properties.files.GUIProperties;

public class OntologyMenu extends JMenu{
	
	private static final long serialVersionUID = 1L;
	
	private static JMenuItem newOntologyMenuItem;
	private static JMenuItem loadOntologyMenuItem;
	private static JMenuItem saveOntologyMenuItem;
	private static JMenuItem loadXMLMenuItem;
	private static JMenuItem executeMenuItem;
	
	
	
	public OntologyMenu(){
		super();
		add(getNewOntologyMenuItem());
		addSeparator();
		add(getLoadOntologyMenuItem());
		add(getSaveOntologyMenuItem());
		addSeparator();
		add(getLoadXMLMenuItem());
		addSeparator();
		add(getExecuteMenuItem());
	}
	
	/**
	 * 
	 * 
	 */
	private JMenuItem getNewOntologyMenuItem() {
		if(newOntologyMenuItem == null) {
			newOntologyMenuItem = new JMenuItem();
			newOntologyMenuItem.setText(IDRSResourceBundle.res.getString("new.ontology"));
			newOntologyMenuItem.setIcon(new ImageIcon(getClass().getResource(GUIProperties.NEW_ONTOLOGY_MENU_ICON)));
		}
		return newOntologyMenuItem;
	}
	
	/**
	 * 
	 * 
	 */
	private JMenuItem getLoadOntologyMenuItem() {
		if(loadOntologyMenuItem == null) {
			loadOntologyMenuItem = new JMenuItem();
			loadOntologyMenuItem.setText(IDRSResourceBundle.res.getString("open.ontology"));
			loadOntologyMenuItem.setIcon(new ImageIcon(getClass().getResource(GUIProperties.OPEN_ONTOLOGY_MENU_ICON)));
			loadOntologyMenuItem.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					
				}
			});
		}
		return loadOntologyMenuItem;
	}
	
	/**
	 * 
	 * 
	 */
	private JMenuItem getSaveOntologyMenuItem() {
		if(saveOntologyMenuItem == null) {
			saveOntologyMenuItem = new JMenuItem();
			saveOntologyMenuItem.setText(IDRSResourceBundle.res.getString("save.ontology"));
			saveOntologyMenuItem.setIcon(new ImageIcon(getClass().getResource(GUIProperties.SAVE_ONTOLOGY_MENU_ICON)));
		}
		return saveOntologyMenuItem;
	}
	
	/**
	 * 
	 * 
	 */
	private JMenuItem getLoadXMLMenuItem() {
		if(loadXMLMenuItem == null) {
			loadXMLMenuItem = new JMenuItem();
			loadXMLMenuItem.setText(IDRSResourceBundle.res.getString("load.xml"));
		}
		return loadXMLMenuItem;
	}
	
	/**
	 * 
	 * 
	 */
	private JMenuItem getExecuteMenuItem() {
		if(executeMenuItem == null) {
			executeMenuItem = new JMenuItem();
			executeMenuItem.setText(IDRSResourceBundle.res.getString("execute"));
			executeMenuItem.setIcon(new ImageIcon(getClass().getResource(GUIProperties.EXECUTE_ONTOLOGY_MENU_ICON)));
		}
		return executeMenuItem;
	}	
	
	/**
	 * 
	 * 
	 */
	public static void updateTextOfComponents() { 
		newOntologyMenuItem.setText(IDRSResourceBundle.res.getString("new.ontology"));
		loadOntologyMenuItem.setText(IDRSResourceBundle.res.getString("open.ontology"));
		saveOntologyMenuItem.setText(IDRSResourceBundle.res.getString("save.ontology"));
		loadXMLMenuItem.setText(IDRSResourceBundle.res.getString("load.xml"));
		executeMenuItem.setText(IDRSResourceBundle.res.getString("execute"));
	}
}
