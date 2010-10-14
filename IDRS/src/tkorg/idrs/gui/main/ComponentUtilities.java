package tkorg.idrs.gui.main;


import java.awt.Dimension;
import java.awt.Toolkit;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;

import sun.awt.image.URLImageSource;
import tkorg.idrs.properties.files.FileLocationProperties;
import tkorg.idrs.properties.files.GUIProperties;

public class ComponentUtilities {
	
	public static void setFullScreen(JFrame component) {
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		component.setSize(size);
	}
	/**
	 * 
	 * @param component
	 */
	public static void setMiniSize(JFrame component) {
		Dimension size = new Dimension();
		
		size.height = (int) (Toolkit.getDefaultToolkit().getScreenSize().height / 4);
		size.width = (int) (Toolkit.getDefaultToolkit().getScreenSize().width  / 4);
		
		component.setMinimumSize(size);
	}
	
	/**
	 * 
	 * 
	 * @param imageName
	 * @param toolTipText
	 * @param altText
	 * @return
	 */
	public static JButton createButton(String imageName, 
										String toolTipText, 
										String altText) {				
		ImageIcon icon = new ImageIcon(GUIProperties.ABOUT_LOGO_ICON, altText);
		System.out.print(GUIProperties.ABOUT_LOGO_ICON);
		JButton button = new JButton();		
		button.setToolTipText(toolTipText);
		
		if(icon == null) {
			button.setIcon(icon);
		}else button.setText(altText);
		
		return button;
	}
	
	/**
	 * 
	 * 
	 */
	
}
