package tkorg.idrs.gui.crawler;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

import tkorg.idrs.gui.main.IDRSResourceBundle;
import tkorg.idrs.properties.files.GUIProperties;

public class CrawlerToolBar extends JToolBar{
	
	private JButton searchButton = null;
	private JButton settingButton = null;	
	/**
	 * 
	 */
	public CrawlerToolBar(){
		super();
		initComponents();
	};
	
	/**
	 * 
	 */
	private void initComponents(){
		
		add(getSearchButton());
		add(getSettingButton());		
	};
	
	/**
	 * 
	 */
	private JButton getSearchButton(){
		if(searchButton == null) {
			searchButton = new JButton();
			searchButton.setIcon(new ImageIcon(getClass().getResource(GUIProperties.SEARCH_CRAWLER_BUTTON_TOOLBAR)));
			//newButton.setPressedIcon(new ImageIcon(getClass().getResource("lkfl")));
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
	
	/**
	 * 
	 */
	private JButton getSettingButton(){
		if(settingButton == null) {
			settingButton = new JButton();
			settingButton.setIcon(new ImageIcon(getClass().getResource(GUIProperties.SETTING_CRAWLER_BUTTON_TOOLBAR)));
			//settingButton.setPressedIcon(new ImageIcon(getClass().getResource("lkfl")));
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
	
}
