package tkorg.idrs.gui.main;

import javax.swing.JButton;
import javax.swing.JToolBar;

import tkorg.idrs.properties.files.GUIProperties;

public class IDRSToolBar extends JToolBar {
	
	private static final long serialVersionUID = 1L;
	
	private JButton openButton = null;
	
	public JButton getOpenButton() {
		if(openButton == null) {
			openButton = ComponentUtilities.createButton(GUIProperties.ABOUT_LOGO_ICON, 
														IDRSResourceBundle.res.getString("open.ontology"), 
														IDRSResourceBundle.res.getString(""));
		}
		return openButton;
	}
	public IDRSToolBar() {
		super();		
		add(getOpenButton());
		
	}
}
