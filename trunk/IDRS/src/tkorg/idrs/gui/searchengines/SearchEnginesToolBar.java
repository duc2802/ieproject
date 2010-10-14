/**
 * 
 */
package tkorg.idrs.gui.searchengines;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

import tkorg.idrs.gui.main.IDRSResourceBundle;
import tkorg.idrs.properties.files.GUIProperties;

/**
 * @author tiendv
 *
 */

public class SearchEnginesToolBar extends JToolBar{
	private JButton searchButton = null;
	private JButton settingButton = null;	
	private JButton helpButton = null;
	public SearchEnginesToolBar(){
		super();
		initComponents();
	};
	
	private void initComponents() {
		add(getSearchButton());
		add(getSettingButton());
		add(getHelpButton());
	}
	
	private JButton getSearchButton(){
		if(searchButton == null) {
			searchButton = new JButton();
			searchButton.setIcon(new ImageIcon(getClass().getResource(GUIProperties.SEARCH_CRAWLER_BUTTON_TOOLBAR)));
			searchButton.setBorder(null);
			searchButton.setToolTipText(IDRSResourceBundle.res.getString("search"));
			searchButton.addMouseListener(new MouseListener(){
				
				public void mouseClicked(MouseEvent e){
					
				};
				
				public void mousePressed(MouseEvent e){
					
				};
				
				public void mouseEntered(MouseEvent e){
					searchButton.setIcon(new ImageIcon(getClass().getResource(GUIProperties.SEARCH_PRESSED_CRAWLER_BUTTON_TOOLBAR)));
				};
				
				public void mouseReleased(MouseEvent e){
					
				};
				
				public void mouseExited(MouseEvent e){
					searchButton.setIcon(new ImageIcon(getClass().getResource(GUIProperties.SEARCH_CRAWLER_BUTTON_TOOLBAR)));
				};
				
			});		
		}
		return searchButton;
	};
	
	private JButton getSettingButton(){
		if(settingButton == null) {
			settingButton = new JButton();
			settingButton.setIcon(new ImageIcon(getClass().getResource(GUIProperties.SETTING_CRAWLER_BUTTON_TOOLBAR)));
			settingButton.setBorder(null);
			settingButton.setToolTipText(IDRSResourceBundle.res.getString("setting"));
			settingButton.addMouseListener(new MouseListener(){
				
				public void mouseClicked(MouseEvent e){
				};
				
				public void mousePressed(MouseEvent e){
					
				};
				
				public void mouseEntered(MouseEvent e){
					settingButton.setIcon(new ImageIcon(getClass().getResource(GUIProperties.SETTING_PRESSED_CRAWLER_BUTTON_TOOLBAR)));
				};
				
				public void mouseReleased(MouseEvent e){
					
				};
				
				public void mouseExited(MouseEvent e){
					settingButton.setIcon(new ImageIcon(getClass().getResource(GUIProperties.SETTING_CRAWLER_BUTTON_TOOLBAR)));
				};
				
			});		
		}
		return settingButton;
	};	
	private JButton getHelpButton(){
		if(helpButton == null) {
			helpButton = new JButton();
			helpButton.setIcon(new ImageIcon(getClass().getResource(GUIProperties.HELP_ONTOLOGY_BUTTON_TOOLBAR)));
			helpButton.setBorder(null);
			helpButton.setToolTipText(IDRSResourceBundle.res.getString("help"));
			helpButton.addMouseListener(new MouseListener(){
				
				public void mouseClicked(MouseEvent e){
					
				};
				
				public void mousePressed(MouseEvent e){
					
				};
				
				public void mouseEntered(MouseEvent e){
					helpButton.setIcon(new ImageIcon(getClass().getResource(GUIProperties.HELP_PRESSED_ONTOLOGY_BUTTON_TOOLBAR)));
				};
				
				public void mouseReleased(MouseEvent e){
					
				};
				
				public void mouseExited(MouseEvent e){
					helpButton.setIcon(new ImageIcon(getClass().getResource(GUIProperties.HELP_ONTOLOGY_BUTTON_TOOLBAR)));
				};
				
			});
		}
		return helpButton;
	};
}
