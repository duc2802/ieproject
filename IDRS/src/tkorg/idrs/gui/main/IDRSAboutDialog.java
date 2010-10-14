/**
 * 
 */
package tkorg.idrs.gui.main;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsConfiguration;
import java.awt.Window;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

import tkorg.idrs.properties.files.GUIProperties;

//VS4E -- DO NOT REMOVE THIS LINE!
public class IDRSAboutDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private int width = 510;
	private int height = 370;
	private int width_textarea = 418;
	private int height_textarea = 256;
	private int xLocation;
	private int yLocation;
	private JFrame idrsJFrame;
	private static JTextArea contentTextArea;
	private JScrollPane contentScrollPane;
	private JPanel logoPanel;
	private JLabel logoLabel;

	public IDRSAboutDialog() {
		initComponents();
	}

	public IDRSAboutDialog(JFrame mainFrame) {
		super(mainFrame, true);
		idrsJFrame = mainFrame;
		xLocation = idrsJFrame.getX() + (idrsJFrame.getWidth() - width)/2;
		yLocation = idrsJFrame.getY() + (idrsJFrame.getHeight() - height)/2;
		initComponents();
	}

	public IDRSAboutDialog(JFrame parent, boolean modal) {
		super(parent, modal);
		initComponents();
	}

	public IDRSAboutDialog(JFrame parent, String title) {
		super(parent, title);
		initComponents();
	}

	public IDRSAboutDialog(JFrame parent, String title, boolean modal) {
		super(parent, title, modal);
		initComponents();
	}

	public IDRSAboutDialog(JFrame parent, String title, boolean modal,
			GraphicsConfiguration arg) {
		super(parent, title, modal, arg);
		initComponents();
	}

	public IDRSAboutDialog(Dialog parent) {
		super(parent);
		initComponents();
	}

	public IDRSAboutDialog(Dialog parent, boolean modal) {
		super(parent, modal);
		initComponents();
	}

	public IDRSAboutDialog(Dialog parent, String title) {
		super(parent, title);
		initComponents();
	}

	public IDRSAboutDialog(Dialog parent, String title, boolean modal) {
		super(parent, title, modal);
		initComponents();
	}

	public IDRSAboutDialog(Dialog parent, String title, boolean modal,
			GraphicsConfiguration arg) {
		super(parent, title, modal, arg);
		initComponents();
	}

	public IDRSAboutDialog(Window parent) {
		super(parent);
		initComponents();
	}

	public IDRSAboutDialog(Window parent, ModalityType modalityType) {
		super(parent, modalityType);
		initComponents();
	}

	public IDRSAboutDialog(Window parent, String title) {
		super(parent, title);
		initComponents();
	}

	public IDRSAboutDialog(Window parent, String title,
			ModalityType modalityType) {
		super(parent, title, modalityType);
		initComponents();
	}

	public IDRSAboutDialog(Window parent, String title,
			ModalityType modalityType, GraphicsConfiguration arg) {
		super(parent, title, modalityType, arg);
		initComponents();
	}
	
	public static void updateTextsOfComponents() {
		String textContent = "";
		textContent += IDRSResourceBundle.res.getString("application.name") + "\n\n";
		textContent += IDRSResourceBundle.res.getString("version") + "\n\n";
		textContent += IDRSResourceBundle.res.getString("copyright") + "\n\n";
		
		String authors = IDRSResourceBundle.res.getString("authors");
		String authorsArr[] = authors.split(":");
		if (authorsArr != null) {
			for (int i=0;i<authorsArr.length;i++) {
				textContent += authorsArr[i] + "\n"; 
			}
		}
		
		contentTextArea.setText(textContent);
	}

	private void initComponents() {
		setTitle("About IDRS");
		setFont(new Font("Dialog", Font.PLAIN, 12));
		setBackground(Color.white);
		setResizable(false);
		setForeground(Color.black);
		setLayout(new GroupLayout());
		add(getLogoPanel(), new Constraints(new Leading(0, 116, 10, 10), new Leading(0, 307, 10, 10)));
		add(getContentScrollPane(), new Constraints(new Bilateral(118, 0, 22), new Bilateral(0, 0, 22)));
		setSize(width, height);
		setLocation(xLocation, yLocation);
		
		updateTextsOfComponents();
	}

	private JTextArea getContentTextArea() {
		if (contentTextArea == null) {
			contentTextArea = new JTextArea();
			contentTextArea.setEditable(false);
			contentTextArea.setLineWrap(true);
			contentTextArea.setWrapStyleWord(true);
			contentTextArea.setMinimumSize(new Dimension(width_textarea, height_textarea));
			contentTextArea.setPreferredSize(new Dimension(width_textarea, height_textarea));
			contentTextArea.setDragEnabled(true);			
		}
		return contentTextArea;
	}

	private JLabel getLogoLabel() {
		if (logoLabel == null) {
			logoLabel = new JLabel();
			logoLabel.setIcon(new ImageIcon(getClass().getResource(GUIProperties.ABOUT_LOGO_ICON)));
			logoLabel.setText("");
		}
		return logoLabel;
	}

	private JPanel getLogoPanel() {
		if (logoPanel == null) {
			logoPanel = new JPanel();
			logoPanel.setLayout(new GroupLayout());
			logoPanel.add(getLogoLabel(), new Constraints(new Bilateral(0, 0, 41), new Bilateral(0, 0, 16)));
		}
		return logoPanel;
	}

	private JScrollPane getContentScrollPane() {
		if (contentScrollPane == null) {
			contentScrollPane = new JScrollPane();
			contentScrollPane.setViewportView(getContentTextArea());
		}
		return contentScrollPane;
	}

}
