package tkorg.idrs.gui.main;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsConfiguration;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

import tkorg.idrs.properties.files.GUIProperties;

//VS4E -- DO NOT REMOVE THIS LINE!
public class IDRSConfigurationDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private int width = 442;
	private int height = 390;
	private int xLocation;
	private int yLocation;
	private JFrame idrsJFrame;
	private JPanel appearanceJPanel;
	private JPanel advancedJPanel;
	private JTabbedPane configJTabbedPane;
	private JButton cancelJButton;
	private JButton okJButton;
	private JPanel actionsJPanel;
	private JPanel lookAndFeelJPanel;
	private JComboBox lookAndFeelJComboBox;
	private JRadioButton textCompJRadioButton;
	private JComboBox fontSizeJComboBox;
	private JCheckBox fontBoldJCheckBox;
	private JCheckBox fontItalicJCheckBox;
	private JPanel fontOptionsJPanel;
	private JComboBox fontNameJComboBox;
	private JRadioButton menuCompJRadioButton;
	private ButtonGroup fontOptionsButtonGroup;
	private JPanel lingualJPanel;
	private JComboBox languagesJComboBox;
	
	public IDRSConfigurationDialog() {
		initComponents();
	}

	public IDRSConfigurationDialog(JFrame mainFrame) {	
		super(mainFrame, true);
		idrsJFrame = mainFrame;
		xLocation = idrsJFrame.getX() + (idrsJFrame.getWidth()-width)/2;
		yLocation = idrsJFrame.getY() + (idrsJFrame.getHeight()-height)/2;
		initComponents();
	}

	public IDRSConfigurationDialog(JFrame parent, boolean modal) {
		super(parent, modal);
		initComponents();
	}

	public IDRSConfigurationDialog(JFrame parent, String title) {
		super(parent, title);
		initComponents();
	}

	public IDRSConfigurationDialog(JFrame parent, String title, boolean modal) {
		super(parent, title, modal);
		initComponents();
	}

	public IDRSConfigurationDialog(JFrame parent, String title, boolean modal,
			GraphicsConfiguration arg) {
		super(parent, title, modal, arg);
		initComponents();
	}

	public IDRSConfigurationDialog(Dialog parent) {
		super(parent);
		initComponents();
	}

	public IDRSConfigurationDialog(Dialog parent, boolean modal) {
		super(parent, modal);
		initComponents();
	}

	public IDRSConfigurationDialog(Dialog parent, String title) {
		super(parent, title);
		initComponents();
	}

	public IDRSConfigurationDialog(Dialog parent, String title, boolean modal) {
		super(parent, title, modal);
		initComponents();
	}

	public IDRSConfigurationDialog(Dialog parent, String title, boolean modal,
			GraphicsConfiguration arg) {
		super(parent, title, modal, arg);
		initComponents();
	}

	public IDRSConfigurationDialog(Window parent) {
		super(parent);
		initComponents();
	}

	public IDRSConfigurationDialog(Window parent, ModalityType modalityType) {
		super(parent, modalityType);
		initComponents();
	}

	public IDRSConfigurationDialog(Window parent, String title) {
		super(parent, title);
		initComponents();
	}

	public IDRSConfigurationDialog(Window parent, String title,
			ModalityType modalityType) {
		super(parent, title, modalityType);
		initComponents();
	}

	public IDRSConfigurationDialog(Window parent, String title,
			ModalityType modalityType, GraphicsConfiguration arg) {
		super(parent, title, modalityType, arg);
		initComponents();
	}

	private void initComponents() {
		setTitle(IDRSResourceBundle.res.getString("idrs.options"));
		setFont(new Font("Dialog", Font.PLAIN, 12));
		setBackground(Color.white);
		setResizable(false);
		setForeground(Color.black);
		setLayout(new GroupLayout());
		add(getconfigJTabbedPane(), new Constraints(new Bilateral(2, 0, 308), new Leading(0, 281, 10, 10)));
		add(getactionsJPanel(), new Constraints(new Bilateral(2, 0, 216), new Bilateral(287, 0, 67)));
		initFontOptionsButtonGroup();
		setSize(width, height);
		setLocation(xLocation, yLocation);
		setResizable(false);
	}

	private JComboBox getLanguagesJComboBox() {
		if (languagesJComboBox == null) {
			languagesJComboBox = new JComboBox();
			languagesJComboBox.setModel(new DefaultComboBoxModel(new Object[] { "English", "Vietnamese" }));
			languagesJComboBox.setDoubleBuffered(false);
			languagesJComboBox.setBorder(null);
			
			if (Locale.getDefault().toString().equalsIgnoreCase("vn_VN")||Locale.getDefault().toString().equalsIgnoreCase("VN"))
				languagesJComboBox.setSelectedItem("Vietnamese");
			else 
				languagesJComboBox.setSelectedItem("English");
		}
		return languagesJComboBox;
	}

	private JPanel getLingualJPanel() {
		if (lingualJPanel == null) {
			lingualJPanel = new JPanel();
			lingualJPanel.setBorder(BorderFactory.createTitledBorder(IDRSResourceBundle.res.getString("languages")));
			lingualJPanel.setLayout(new GroupLayout());
			lingualJPanel.add(getLanguagesJComboBox(), new Constraints(new Leading(5, 195, 10, 10), new Leading(12, 12, 12)));
		}
		return lingualJPanel;
	}

	private void initFontOptionsButtonGroup() {
		fontOptionsButtonGroup = new ButtonGroup();
		fontOptionsButtonGroup.add(getTextCompJRadioButton());
		fontOptionsButtonGroup.add(getMenuCompJRadioButton());
	}

	private ButtonGroup getFontOptionsButtonGroup() {
		if (fontOptionsButtonGroup == null) {
			fontOptionsButtonGroup = new ButtonGroup();
			fontOptionsButtonGroup.add(textCompJRadioButton);
			fontOptionsButtonGroup.add(menuCompJRadioButton);
		}
		return fontOptionsButtonGroup;
	}
	
	private JRadioButton getMenuCompJRadioButton() {
		if (menuCompJRadioButton == null) {
			menuCompJRadioButton = new JRadioButton();
			menuCompJRadioButton.setText(IDRSResourceBundle.res.getString("menu.component.font"));
		}
		return menuCompJRadioButton;
	}

	private JComboBox getFontNameJComboBox() {
		if (fontNameJComboBox == null) {
			fontNameJComboBox = new JComboBox();
			fontNameJComboBox.setModel(new DefaultComboBoxModel(new Object[] { "Times New Romance", "Tahoma" }));
			fontNameJComboBox.setDoubleBuffered(false);
			fontNameJComboBox.setBorder(null);
		}
		return fontNameJComboBox;
	}

	private JPanel getFontOptionsJPanel() {
		if (fontOptionsJPanel == null) {
			fontOptionsJPanel = new JPanel();
			fontOptionsJPanel.setBorder(BorderFactory.createTitledBorder(IDRSResourceBundle.res.getString("font.options")));
			fontOptionsJPanel.setLayout(new GroupLayout());
			fontOptionsJPanel.add(getTextCompJRadioButton(), new Constraints(new Leading(3, 10, 10), new Leading(12, 10, 10)));
			fontOptionsJPanel.add(getMenuCompJRadioButton(), new Constraints(new Leading(168, 10, 10), new Leading(12, 8, 8)));
			fontOptionsJPanel.add(getFontBoldJCheckBox(), new Constraints(new Leading(3, 8, 8), new Leading(88, 8, 8)));
			fontOptionsJPanel.add(getFontItalicJCheckBox(), new Constraints(new Leading(80, 10, 10), new Leading(88, 8, 8)));
			fontOptionsJPanel.add(getFontNameJComboBox(), new Constraints(new Leading(6, 345, 12, 12), new Leading(55, 10, 10)));
			fontOptionsJPanel.add(getFontSizeJComboBox(), new Constraints(new Leading(357, 55, 10, 10), new Leading(55, 12, 12)));
			
			getFontOptionsButtonGroup();
		}
		return fontOptionsJPanel;
	}

	private JCheckBox getFontItalicJCheckBox() {
		if (fontItalicJCheckBox == null) {
			fontItalicJCheckBox = new JCheckBox();
			fontItalicJCheckBox.setText("Italic");
		}
		return fontItalicJCheckBox;
	}

	private JCheckBox getFontBoldJCheckBox() {
		if (fontBoldJCheckBox == null) {
			fontBoldJCheckBox = new JCheckBox();
			fontBoldJCheckBox.setText("Bold");
		}
		return fontBoldJCheckBox;
	}

	private JComboBox getFontSizeJComboBox() {
		if (fontSizeJComboBox == null) {
			fontSizeJComboBox = new JComboBox();
			fontSizeJComboBox.setModel(new DefaultComboBoxModel(new Object[] { "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20" }));
			fontSizeJComboBox.setDoubleBuffered(false);
			fontSizeJComboBox.setBorder(null);
		}
		return fontSizeJComboBox;
	}

	private JRadioButton getTextCompJRadioButton() {
		if (textCompJRadioButton == null) {
			textCompJRadioButton = new JRadioButton();
			textCompJRadioButton.setSelected(true);
			textCompJRadioButton.setText(IDRSResourceBundle.res.getString("text.components.font"));
		}
		return textCompJRadioButton;
	}

	private JComboBox getLookAndFeelJComboBox() {
		if (lookAndFeelJComboBox == null) {
			lookAndFeelJComboBox = new JComboBox();
			lookAndFeelJComboBox.setModel(new DefaultComboBoxModel(
						new Object[] { 
								IDRSResourceBundle.res.getString("windows"), 
								IDRSResourceBundle.res.getString("metal"), 
								IDRSResourceBundle.res.getString("motif"), 
								IDRSResourceBundle.res.getString("windows.classic"), 
								IDRSResourceBundle.res.getString("macOS") 
						}));
			lookAndFeelJComboBox.setDoubleBuffered(false);
			lookAndFeelJComboBox.setBorder(null);
			
			String lookAndFeelStyle = UIManager.getLookAndFeel().getName();
			lookAndFeelJComboBox.setSelectedItem(lookAndFeelStyle);
		}
		return lookAndFeelJComboBox;
	}

	private JPanel getappearanceJPanel() {
		if (appearanceJPanel == null) {
			appearanceJPanel = new JPanel();
			appearanceJPanel.setLayout(new GroupLayout());
			appearanceJPanel.add(getFontOptionsJPanel(), new Constraints(new Leading(2, 424, 12, 12), new Leading(104, 140, 10, 10)));
			appearanceJPanel.add(getLookAndFeelJPanel(), new Constraints(new Leading(2, 213, 10, 10), new Leading(15, 74, 12, 12)));
			appearanceJPanel.add(getLingualJPanel(), new Constraints(new Leading(213, 213, 12, 12), new Leading(15, 74, 12, 12)));
		}
		return appearanceJPanel;
	}

	private JPanel getLookAndFeelJPanel() {
		if (lookAndFeelJPanel == null) {
			lookAndFeelJPanel = new JPanel();
			lookAndFeelJPanel.setBorder(BorderFactory.createTitledBorder(IDRSResourceBundle.res.getString("lookandfeel")));
			lookAndFeelJPanel.setLayout(new GroupLayout());
			lookAndFeelJPanel.add(getLookAndFeelJComboBox(), new Constraints(new Bilateral(3, 0, 128), new Leading(12, 12, 12)));
		}
		return lookAndFeelJPanel;
	}

	private JPanel getactionsJPanel() {
		if (actionsJPanel == null) {
			actionsJPanel = new JPanel();
			actionsJPanel.setBorder(BorderFactory.createTitledBorder(IDRSResourceBundle.res.getString("actions")));
			actionsJPanel.setMinimumSize(new Dimension(320, 50));
			actionsJPanel.setPreferredSize(new Dimension(320, 50));
			actionsJPanel.setLayout(new GroupLayout());
			actionsJPanel.add(getcancelJButton(), new Constraints(new Leading(226, 85, 12, 12), new Leading(6, 12, 12)));
			actionsJPanel.add(getokJButton(), new Constraints(new Leading(117, 81, 10, 10), new Leading(6, 12, 12)));
		}
		return actionsJPanel;
	}

	private JButton getokJButton() {
		if (okJButton == null) {
			okJButton = new JButton();
			okJButton.setText(IDRSResourceBundle.res.getString("ok"));
			okJButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					okJButtonActionActionPerformed(event);
				}
			});
		}
		return okJButton;
	}

	private JButton getcancelJButton() {
		if (cancelJButton == null) {
			cancelJButton = new JButton();
			cancelJButton.setText(IDRSResourceBundle.res.getString("cancel"));
			cancelJButton.addActionListener(new ActionListener() {
	
				public void actionPerformed(ActionEvent event) {
					cancelJButtonActionActionPerformed(event);
				}
			});
		}
		return cancelJButton;
	}

	private JTabbedPane getconfigJTabbedPane() {
		if (configJTabbedPane == null) {
			configJTabbedPane = new JTabbedPane();
			configJTabbedPane.addTab(IDRSResourceBundle.res.getString("appearance"), getappearanceJPanel());
			configJTabbedPane.addTab(IDRSResourceBundle.res.getString("advanced"), getadvancedJPanel());
		}
		return configJTabbedPane;
	}

	private JPanel getadvancedJPanel() {
		if (advancedJPanel == null) {
			advancedJPanel = new JPanel();
			advancedJPanel.setLayout(new GroupLayout());
		}
		return advancedJPanel;
	}
	
	private void okJButtonActionActionPerformed(ActionEvent event) {
		try {
			String lookAndFeel = (String) this.lookAndFeelJComboBox.getSelectedItem();
			if (lookAndFeel.equalsIgnoreCase(IDRSResourceBundle.res.getString("metal"))) {
				UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
			}
			if (lookAndFeel.equalsIgnoreCase(IDRSResourceBundle.res.getString("motif"))){
				UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
			}
			if (lookAndFeel.equalsIgnoreCase(IDRSResourceBundle.res.getString("windows"))){
				UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			}
			if (lookAndFeel.equalsIgnoreCase(IDRSResourceBundle.res.getString("windows.classic"))){
				UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			}
			//if (lookAndFeel.equalsIgnoreCase(IDRSResourceBundle.res.getString("macOS"))){
				//UIManager.setLookAndFeel("com.sun.java.swing.plaf.macos.MacOSLookAndFeel");
			//}
			if (lookAndFeel.equalsIgnoreCase(IDRSResourceBundle.res.getString("macOS"))){
				 UIManager.setLookAndFeel("ch.randelshofer.quaqua.QuaquaLookAndFeel");
			}
			
			String language = (String) this.languagesJComboBox.getSelectedItem();
			if (language.equalsIgnoreCase("Vietnamese")) {
				Locale.setDefault(new Locale("vn", "VN"));
				IDRSResourceBundle.res = IDRSResourceBundle.initResources();
				IDRSApplication.updateTextOfComponents();
				
			}
			if (language.equalsIgnoreCase("English")) {
				Locale.setDefault(Locale.US);
				IDRSResourceBundle.res = IDRSResourceBundle.initResources();
				IDRSApplication.updateTextOfComponents();
				
			}
			
			SwingUtilities.updateComponentTreeUI(idrsJFrame);
			
			this.dispose();
			
		}
		catch (Exception ex){
			ex.printStackTrace();
		}
	}

	private void cancelJButtonActionActionPerformed(ActionEvent event) {
		this.dispose();
	}
	
	public void updateTextOfComponents() {
		
	}

}
