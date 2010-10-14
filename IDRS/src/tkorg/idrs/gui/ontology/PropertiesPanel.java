package tkorg.idrs.gui.ontology;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;
import org.dyno.visual.swing.layouts.Trailing;

import tkorg.idrs.action.ontology.ClassPanelAction;
import tkorg.idrs.action.ontology.PropertyPanelAction;
import tkorg.idrs.core.ontology.OWLModel;
import tkorg.idrs.gui.main.IDRSApplication;
import tkorg.idrs.properties.files.GUIProperties;
import edu.stanford.smi.protegex.owl.model.OWLNamedClass;

//VS4E -- DO NOT REMOVE THIS LINE!
public class PropertiesPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	public static JList propertiesJList;

	ImageIcon editIcon;
	ImageIcon deleteIcon;
	private JSplitPane propertiesSplitPane;
	private JPanel namesPropertiesPanel;
	private JPanel descriptionPanel;
	private JPanel titlePropertiesPanel;
	private JLabel namePropertiesListLabel;
	private JPanel updatePropertiesListPanel;
	private JLabel addLabel;
	private JLabel deleteLabel;
	private JScrollPane propertiesListScrollPane;
	private JSplitPane descriptionSplitPane;
	private JPanel domainPanel;
	private JPanel rangesPanel;
	private JPanel domainsTitlePanel;
	private JLabel domainsTitleLabel;
	private JPanel addDomainsPanel;
	private JLabel addDomainsLabel;
	private JPanel rangesTitlePanel;
	private JLabel rangesTitleLabel;
	private JPanel addRangesPanel;
	private JLabel addRangesLabel;
	private JCheckBox propertiesCheckBox;
	private static JPanel contentRangesPanel;
	private static JPanel contentDomainsPanel;
	
	public static ArrayList < String > checkedDomainsArrayList;
	public static ArrayList < String > uncheckedDomainsArrayList;
	public static String checkedRangesArrayList;
	public static String uncheckedRangesArrayList;
	private int width = 781;
	private int height = 425;
	
	public PropertiesPanel() {
		initComponents();
		
		checkedDomainsArrayList = new ArrayList < String >();
		uncheckedDomainsArrayList = new ArrayList < String >(); 
		
		updateTextOfComponents();
	}

	private void initComponents() {
		setLayout(new GroupLayout());
		add(getPropertiesSplitPane(), new Constraints(new Bilateral(0, 0, 202), new Bilateral(0, 0, 28)));
		setSize(width, height);
	}

	private JPanel getContentDomainsPanel() {
		if (contentDomainsPanel == null) {
			contentDomainsPanel = new JPanel();
			contentDomainsPanel.setBackground(Color.white);
			contentDomainsPanel.setBorder(new LineBorder(Color.black, 1, false));
			contentDomainsPanel.setLayout(new GroupLayout());
		}
		return contentDomainsPanel;
	}

	private JPanel getContentRangesPanel() {
		if (contentRangesPanel == null) {
			contentRangesPanel = new JPanel();
			contentRangesPanel.setBackground(Color.white);
			contentRangesPanel.setBorder(new LineBorder(Color.black, 1, false));
			contentRangesPanel.setLayout(new GroupLayout());
		}
		return contentRangesPanel;
	}

	private JCheckBox getPropertiesCheckBox() {
		if (propertiesCheckBox == null) {
			propertiesCheckBox = new JCheckBox();
			propertiesCheckBox.setFont(new Font("Dialog", Font.BOLD, 13));
			propertiesCheckBox.setSelected(false);
			propertiesCheckBox.addItemListener(new ItemListener() {

				@Override
				public void itemStateChanged(ItemEvent e) {
					Object source = e.getItemSelectable();
					if (source == propertiesCheckBox) {
						for (int i = 0; i < PropertyPanelAction.propertiesArrayList.size(); i++) {
							if (PropertyPanelAction.nameProperty.equals(PropertyPanelAction.propertiesArrayList.get(i).getName())) {
								PropertyPanelAction.propertiesArrayList.get(i).setFunctional(propertiesCheckBox.isSelected());
							}
						}
					}
				}
				
			});
		}
		return propertiesCheckBox;
	}

	private JLabel getAddRangesLabel() {
		if (addRangesLabel == null) {
			addRangesLabel = new JLabel();
			addRangesLabel.setIcon(new ImageIcon(getClass().getResource(GUIProperties.ADD_BUTTON_PRESSED_FILE)));
			addRangesLabel.addMouseListener(new MouseListener() {

				@Override
				public void mouseClicked(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseEntered(MouseEvent arg0) {
					addRangesLabel.setIcon(new ImageIcon(getClass().getResource(GUIProperties.ADD_BUTTON_APPEAR_FILE)));
				}

				@Override
				public void mouseExited(MouseEvent arg0) {
					addRangesLabel.setIcon(new ImageIcon(getClass().getResource(GUIProperties.ADD_BUTTON_PRESSED_FILE)));
				}

				@Override
				public void mousePressed(MouseEvent arg0) {
					
				}

				@Override
				public void mouseReleased(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}
			});
		}
		return addRangesLabel;
	}

	private JPanel getAddRangesPanel() {
		if (addRangesPanel == null) {
			addRangesPanel = new JPanel();
			addRangesPanel.setLayout(new GroupLayout());
//			addRangesPanel.add(getAddRangesLabel(), new Constraints(new Trailing(12, 12, 12), new Leading(4, 10, 10)));
		}
		return addRangesPanel;
	}

	private JLabel getRangesTitleLabel() {
		if (rangesTitleLabel == null) {
			rangesTitleLabel = new JLabel();
			rangesTitleLabel.setFont(new Font("Dialog", Font.BOLD, 13));
		}
		return rangesTitleLabel;
	}

	private JPanel getRangesTitlePanel() {
		if (rangesTitlePanel == null) {
			rangesTitlePanel = new JPanel();
			rangesTitlePanel.setBorder(new LineBorder(Color.lightGray, 1, false));
			rangesTitlePanel.setLayout(new GroupLayout());
			rangesTitlePanel.add(getAddRangesPanel(), new Constraints(new Trailing(0, 77, 10, 10), new Leading(0, 34, 10, 10)));
			rangesTitlePanel.add(getRangesTitleLabel(), new Constraints(new Leading(0, 57, 64, 95), new Leading(7, 10, 10)));
		}
		return rangesTitlePanel;
	}

	private JLabel getAddDomainsLabel() {
		if (addDomainsLabel == null) {
			addDomainsLabel = new JLabel();
			addDomainsLabel.setIcon(new ImageIcon(getClass().getResource(GUIProperties.ADD_BUTTON_PRESSED_FILE)));
			addDomainsLabel.addMouseListener(new MouseListener() {

				@Override
				public void mouseClicked(MouseEvent arg0) {
					
				}

				@Override
				public void mouseEntered(MouseEvent arg0) {
					addDomainsLabel.setIcon(new ImageIcon(getClass().getResource(GUIProperties.ADD_BUTTON_APPEAR_FILE)));
				}

				@Override
				public void mouseExited(MouseEvent arg0) {
					addDomainsLabel.setIcon(new ImageIcon(getClass().getResource(GUIProperties.ADD_BUTTON_PRESSED_FILE)));
				}

				@Override
				public void mousePressed(MouseEvent arg0) {
					if (PropertyPanelAction.nameProperty.equals("")) {
						return;
					}
					AddDomainPropertiesDialog dialog = new AddDomainPropertiesDialog(IDRSApplication.idrsJFrame, PropertyPanelAction.nameProperty, uncheckedDomainsArrayList);
					dialog.setVisible(true);
				}

				@Override
				public void mouseReleased(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}
			});
		}
		return addDomainsLabel;
	}

	private JPanel getAddDomainsPanel() {
		if (addDomainsPanel == null) {
			addDomainsPanel = new JPanel();
			addDomainsPanel.setLayout(new GroupLayout());
			addDomainsPanel.add(getAddDomainsLabel(), new Constraints(new Trailing(12, 12, 12), new Leading(6, 10, 10)));
		}
		return addDomainsPanel;
	}

	private JLabel getDomainsTitleLabel() {
		if (domainsTitleLabel == null) {
			domainsTitleLabel = new JLabel();
			domainsTitleLabel.setFont(new Font("Dialog", Font.BOLD, 13));
		}
		return domainsTitleLabel;
	}

	private JPanel getDomainsTitlePanel() {
		if (domainsTitlePanel == null) {
			domainsTitlePanel = new JPanel();
			domainsTitlePanel.setBorder(new LineBorder(Color.lightGray, 1, false));
			domainsTitlePanel.setLayout(new GroupLayout());
			domainsTitlePanel.add(getAddDomainsPanel(), new Constraints(new Trailing(0, 87, 10, 10), new Leading(0, 36, 10, 10)));
			domainsTitlePanel.add(getDomainsTitleLabel(), new Constraints(new Leading(0, 185, 64, 105), new Leading(8, 10, 10)));
		}
		return domainsTitlePanel;
	}

	private JPanel getRangesPanel() {
		if (rangesPanel == null) {
			rangesPanel = new JPanel();
			rangesPanel.setLayout(new GroupLayout());
			rangesPanel.add(getRangesTitlePanel(), new Constraints(new Bilateral(0, 0, 0), new Leading(0, 44, 32, 32)));
			rangesPanel.add(getContentRangesPanel(), new Constraints(new Bilateral(0, 0, 0), new Bilateral(50, 28, 0)));
			rangesPanel.add(getPropertiesCheckBox(), new Constraints(new Leading(8, 8, 8), new Trailing(0, 80, 80)));
		}
		return rangesPanel;
	}

	private JPanel getDomainPanel() {
		if (domainPanel == null) {
			domainPanel = new JPanel();
			domainPanel.setLayout(new GroupLayout());
			domainPanel.add(getDomainsTitlePanel(), new Constraints(new Bilateral(0, 0, 0), new Leading(0, 43, 12, 12)));
			domainPanel.add(getContentDomainsPanel(), new Constraints(new Bilateral(0, 0, 0), new Bilateral(49, 0, 0)));
		}
		return domainPanel;
	}

	private JSplitPane getDescriptionSplitPane() {
		if (descriptionSplitPane == null) {
			descriptionSplitPane = new JSplitPane();
			descriptionSplitPane.setDividerLocation(190);
			descriptionSplitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
			descriptionSplitPane.setTopComponent(getDomainPanel());
			descriptionSplitPane.setBottomComponent(getRangesPanel());
		}
		return descriptionSplitPane;
	}

	private JScrollPane getPropertiesListScrollPane() {
		if (propertiesListScrollPane == null) {
			propertiesListScrollPane = new JScrollPane();
			propertiesListScrollPane.setViewportView(getPropertiesJList());
		}
		return propertiesListScrollPane;
	}

	private JList getPropertiesJList() {
		if (propertiesJList == null) {
			propertiesJList = new JList();
			propertiesJList.setBorder(new LineBorder(Color.black, 1, false));
			propertiesJList.setFont(new Font("Dialog", Font.BOLD, 13));
			DefaultListModel listModel = new DefaultListModel();
			listModel.addElement("Thing");
			propertiesJList.setEnabled(false);
			propertiesJList.setModel(listModel);
			propertiesJList.addListSelectionListener(new ListSelectionListener() {

				@Override
				public void valueChanged(ListSelectionEvent arg0) {
					PropertyPanelAction.nameProperty = propertiesJList.getSelectedValue().toString();
					if (PropertyPanelAction.nameProperty.equals("")) {
						return;
					}
					
					reset();
									
					loadDomainsListIntoPanel(PropertyPanelAction.nameProperty);
					loadRangesListIntoPanel(PropertyPanelAction.nameProperty);
					getFunctionalByProperty(PropertyPanelAction.nameProperty);
				}
				
			});
		}
		return propertiesJList;
	}

	private JLabel getDeleteLabel() {
		if (deleteLabel == null) {
			deleteLabel = new JLabel();
			deleteLabel.setIcon(new ImageIcon(getClass().getResource(GUIProperties.DELETE_BUTTON_PRESSED_FILE)));
			deleteLabel.addMouseListener(new MouseListener() {

				@Override
				public void mouseClicked(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseEntered(MouseEvent arg0) {
					deleteLabel.setIcon(new ImageIcon(getClass().getResource(GUIProperties.DELETE_BUTTON_APPEAR_FILE)));
				}

				@Override
				public void mouseExited(MouseEvent arg0) {
					deleteLabel.setIcon(new ImageIcon(getClass().getResource(GUIProperties.DELETE_BUTTON_PRESSED_FILE)));
				}

				@Override
				public void mousePressed(MouseEvent arg0) {
					
				}

				@Override
				public void mouseReleased(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}
			});
		}
		return deleteLabel;
	}

	private JLabel getAddLabel() {
		if (addLabel == null) {
			addLabel = new JLabel();
			addLabel.setIcon(new ImageIcon(getClass().getResource(GUIProperties.ADD_BUTTON_PRESSED_FILE)));
			addLabel.addMouseListener(new MouseListener() {

				@Override
				public void mouseClicked(MouseEvent arg0) {
					String nameNewProperty = JOptionPane.showInputDialog("Please enter Property name");
					PropertyPanelAction.addOWLPropertyIntoArrayList(nameNewProperty);
					PropertyPanelAction.convertPropertiesIntoList(propertiesJList);
					contentDomainsPanel.removeAll();
					contentRangesPanel.removeAll();
				}

				@Override
				public void mouseEntered(MouseEvent arg0) {
					addLabel.setIcon(new ImageIcon(getClass().getResource(GUIProperties.ADD_BUTTON_APPEAR_FILE)));
				}

				@Override
				public void mouseExited(MouseEvent arg0) {
					addLabel.setIcon(new ImageIcon(getClass().getResource(GUIProperties.ADD_BUTTON_PRESSED_FILE)));
				}

				@Override
				public void mousePressed(MouseEvent arg0) {
					
				}

				@Override
				public void mouseReleased(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}
			});
		}
		return addLabel;
	}

	private JPanel getUpdatePropertiesListPanel() {
		if (updatePropertiesListPanel == null) {
			updatePropertiesListPanel = new JPanel();
			updatePropertiesListPanel.setLayout(new GroupLayout());
			updatePropertiesListPanel.add(getDeleteLabel(), new Constraints(new Trailing(12, 34, 34), new Leading(0, 12, 12)));
			updatePropertiesListPanel.add(getAddLabel(), new Constraints(new Trailing(46, 12, 12), new Leading(0, 12, 12)));
		}
		return updatePropertiesListPanel;
	}

	private JLabel getNamePropertiesListLabel() {
		if (namePropertiesListLabel == null) {
			namePropertiesListLabel = new JLabel();
			namePropertiesListLabel.setFont(new Font("Dialog", Font.BOLD, 13));
		}
		return namePropertiesListLabel;
	}

	private JPanel getTitlePropertiesPanel() {
		if (titlePropertiesPanel == null) {
			titlePropertiesPanel = new JPanel();
			titlePropertiesPanel.setBorder(new LineBorder(Color.black, 1, false));
			titlePropertiesPanel.setLayout(new GroupLayout());
			titlePropertiesPanel.add(getNamePropertiesListLabel(), new Constraints(new Leading(0, 128, 10, 10), new Leading(8, 20, 12, 12)));
			titlePropertiesPanel.add(getUpdatePropertiesListPanel(), new Constraints(new Trailing(0, 81, 102, 146), new Leading(0, 33, 10, 10)));
		}
		return titlePropertiesPanel;
	}

	private JPanel getDescriptionPanel() {
		if (descriptionPanel == null) {
			descriptionPanel = new JPanel();
			descriptionPanel.setBorder(BorderFactory.createTitledBorder(null, "Description", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, new Font(
					"Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			descriptionPanel.setLayout(new GroupLayout());
			descriptionPanel.add(getDescriptionSplitPane(), new Constraints(new Bilateral(2, 0, 101), new Bilateral(0, 0, 64)));
		}
		return descriptionPanel;
	}

	private JPanel getNamesPropertiesPanel() {
		if (namesPropertiesPanel == null) {
			namesPropertiesPanel = new JPanel();
			namesPropertiesPanel.setBorder(BorderFactory.createTitledBorder(null, "Data Properties", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, new Font(
					"Dialog", Font.BOLD, 13), new Color(51, 51, 51)));
			namesPropertiesPanel.setLayout(new GroupLayout());
			namesPropertiesPanel.add(getTitlePropertiesPanel(), new Constraints(new Bilateral(4, 1, 0), new Leading(0, 38, 28, 409)));
			namesPropertiesPanel.add(getPropertiesListScrollPane(), new Constraints(new Bilateral(3, 0, 22), new Bilateral(43, 0, 22)));
		}
		return namesPropertiesPanel;
	}

	private JSplitPane getPropertiesSplitPane() {
		if (propertiesSplitPane == null) {
			propertiesSplitPane = new JSplitPane();
			propertiesSplitPane.setDividerLocation(312);
			propertiesSplitPane.setLeftComponent(getNamesPropertiesPanel());
			propertiesSplitPane.setRightComponent(getDescriptionPanel());
		}
		return propertiesSplitPane;
	}

	public void updateTextOfComponents() {
		rangesTitleLabel.setText("Ranges");
		namePropertiesListLabel.setText("Properties List");
		propertiesCheckBox.setText("Functional");
		domainsTitleLabel.setText("Domains (Intersection)");
	}
	
	public void reset() {
		propertiesJList.removeAll();
		contentDomainsPanel.removeAll();
		contentRangesPanel.removeAll();
		
		checkedDomainsArrayList.clear();
		uncheckedDomainsArrayList.clear();
		checkedRangesArrayList = "";
		uncheckedRangesArrayList = "";
		
		IDRSApplication.idrsJFrame.repaint();
	}
	
	public static void loadDomainsListIntoPanel(String nameProperty) {
		checkedDomainsArrayList.clear();
		uncheckedDomainsArrayList.clear();
		contentDomainsPanel.removeAll();
		
		checkedDomainsArrayList = PropertyPanelAction.getNameDomainsByProperty(nameProperty);
		for (int i = 0; i < checkedDomainsArrayList.size(); i++) {
			String name = checkedDomainsArrayList.get(i).substring(checkedDomainsArrayList.get(i).indexOf(":") + 1);
			LinePanel linePanel = new LinePanel(name, GUIProperties.PROPERTIES_PANEL, GUIProperties.DOMAINS_PROPERTIES_PANEL);
			linePanel.setNameProperty(nameProperty);
			linePanel.setVisible(true);
			contentDomainsPanel.add(linePanel, linePanel.getPosition(i));
		}
		
		Collection classes = OWLModel.owlModel.getUserDefinedOWLNamedClasses();
		ArrayList < String > domains = new ArrayList < String >();
		
		for (Iterator it = classes.iterator(); it.hasNext();) {
			OWLNamedClass cls = (OWLNamedClass) it.next();
			domains.add(cls.getBrowserText());
		}
		
		for (int i = 0; i < domains.size(); i++) {
			String temp = "unchecked";
			for (int j = 0; j < checkedDomainsArrayList.size(); j++) {
				if (domains.get(i).equals(checkedDomainsArrayList.get(j).toString())) {
					temp = "checked";
				}
			}
			if (temp.equals("unchecked")) {
				uncheckedDomainsArrayList.add(domains.get(i).substring(domains.get(i).indexOf(":") + 1));
			}
		}
		
		boolean isFunctional = PropertyPanelAction.isFunctionalByProperty(nameProperty);
		if (isFunctional == true) {
			
		}
		
		IDRSApplication.idrsJFrame.repaint();
	}
	
	public static void loadRangesListIntoPanel(String nameProperty) {
		checkedRangesArrayList = "";
		uncheckedRangesArrayList = "";
		contentRangesPanel.removeAll();
		
		checkedRangesArrayList = PropertyPanelAction.getNameRangeByProperty(nameProperty);
		String name = checkedRangesArrayList.substring(checkedRangesArrayList.indexOf(":") + 1);
		LinePanel linePanel = new LinePanel(name, GUIProperties.PROPERTIES_PANEL, GUIProperties.RANGES_PROPERTIES_PANEL);
		linePanel.setVisible(true);
		contentRangesPanel.add(linePanel, linePanel.getPosition(0));
		
		IDRSApplication.idrsJFrame.repaint();
	}
	
	private void getFunctionalByProperty (String nameProperty) {
		boolean isFunctional = PropertyPanelAction.isFunctionalByProperty(nameProperty);
		propertiesCheckBox.setSelected(isFunctional);
	}
}



















