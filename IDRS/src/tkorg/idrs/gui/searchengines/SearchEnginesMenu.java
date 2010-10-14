/**
 * 
 */
package tkorg.idrs.gui.searchengines;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import tkorg.idrs.gui.main.IDRSResourceBundle;
import tkorg.idrs.properties.files.GUIProperties;

/**
 * @author tiendv
 *
 */

public class SearchEnginesMenu extends JMenu{
	private static JMenuItem newSeachMenuItem;
	private static JMenuItem settingSeachMenuItem;
	private static JMenuItem helpSeachMenuItem;
	public SearchEnginesMenu(){
		super();
		add(getNewSeachMenuItem());
		addSeparator();
		add(getSettingSeachMenuItem());
		addSeparator();
		add(getHelpSeachMenuItem());
		addSeparator();
	}

	private JMenuItem getNewSeachMenuItem() {
		if(newSeachMenuItem == null) {
			newSeachMenuItem = new JMenuItem();
			newSeachMenuItem.setText(IDRSResourceBundle.res.getString("search"));
			newSeachMenuItem.setIcon(new ImageIcon(getClass().getResource(GUIProperties.SEARCH_MENU_ICON)));
			newSeachMenuItem.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					
				}
			});
		
		}
		return newSeachMenuItem;
	}

	private JMenuItem getSettingSeachMenuItem() {
		if(settingSeachMenuItem == null) {
			settingSeachMenuItem = new JMenuItem();
			settingSeachMenuItem.setText(IDRSResourceBundle.res.getString("setting"));
			settingSeachMenuItem.setIcon(new ImageIcon(getClass().getResource(GUIProperties.SETTING_SEARCH_MENU_ICON)));
			settingSeachMenuItem.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
				}
			});
		}
		return settingSeachMenuItem;
	}

	private JMenuItem getHelpSeachMenuItem() {
		if(helpSeachMenuItem == null) {
			helpSeachMenuItem = new JMenuItem();
			helpSeachMenuItem.setText(IDRSResourceBundle.res.getString("help"));
			//helpSeachMenuItem.setIcon(new ImageIcon(getClass().getResource(GUIProperties.HELP_MENU_ICON)));
		}
		return helpSeachMenuItem;
	}
}
