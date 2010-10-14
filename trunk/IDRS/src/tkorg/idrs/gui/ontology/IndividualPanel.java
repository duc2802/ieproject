/**
 * 
 */
package tkorg.idrs.gui.ontology;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;
import org.dyno.visual.swing.layouts.Trailing;

import tkorg.idrs.action.ontology.IndividualsPanelAction;
import tkorg.idrs.action.ontology.PropertyPanelAction;
import tkorg.idrs.core.ontology.OWLModel;
import tkorg.idrs.core.ontology.OWLMyIndividual;
import tkorg.idrs.gui.main.IDRSApplication;
import tkorg.idrs.properties.files.GUIProperties;
import edu.stanford.smi.protegex.owl.model.OWLNamedClass;

//VS4E -- DO NOT REMOVE THIS LINE!
/**
 * @author danhit.
 * 
 */
public class IndividualPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	public static JTree individualTree;
	public static JPanel contentRelationshipsPanel;
	public static JPanel contentPropertiesPanel;
	
	private JSplitPane individualsSplitPanel;
	private JSplitPane informationSplitPane;
	private JPanel modelBroswerPanel;
	private JPanel informationPanel;
	private static JLabel nameModelBrowserLabel;
	private JLabel deleteLabel;
	private JLabel addLabel;
	private JScrollPane individualScrollPane;
	private JPanel hierarchyPanel;
	private JPanel jPanel1;
	private JPanel relationshipsPanel;
	private JPanel propertiesPanel;
	private JLabel nameRelationshipsLabel;
	private JPanel nameRelationshipsPanel;
	private JLabel addRelationshipLabel;
	private JPanel addRelationshipPanel;
	private JPanel namePropertiesPanel;
	private JLabel namePropertiesLabel;
	private JLabel addPropertiesLabel;
	private JPanel addPropertiesPanel;
	
	private static String nameSelectedIndividual = "";
	private static ArrayList < String > checkedPropertiesArrayList = null;
	private static ArrayList < String > uncheckedPropertiesArrayList = null;

	public IndividualPanel() {
		initComponents();
	}

	private void initComponents() {
		setBackground(Color.white);
		setLayout(new GroupLayout());
		add(getIndividualsSplitPanel(), new Constraints(new Bilateral(0, 0, 202), new Bilateral(0, 0, 28)));
		setSize(781, 425);
		
		checkedPropertiesArrayList = new ArrayList < String >();
		uncheckedPropertiesArrayList = new ArrayList < String >();
	}

	private JPanel getContentPropertiesPanel() {
		if (contentPropertiesPanel == null) {
			contentPropertiesPanel = new JPanel();
			contentPropertiesPanel.setBackground(Color.white);
			contentPropertiesPanel.setBorder(new LineBorder(Color.black, 1, false));
			contentPropertiesPanel.setLayout(new GroupLayout());
		}
		return contentPropertiesPanel;
	}

	private JPanel getContentRelationshipsPanel() {
		if (contentRelationshipsPanel == null) {
			contentRelationshipsPanel = new JPanel();
			contentRelationshipsPanel.setBackground(Color.white);
			contentRelationshipsPanel.setBorder(new LineBorder(Color.black, 1, false));
			contentRelationshipsPanel.setLayout(new GroupLayout());
		}
		return contentRelationshipsPanel;
	}

	private JPanel getAddPropertiesPanel() {
		if (addPropertiesPanel == null) {
			addPropertiesPanel = new JPanel();
			addPropertiesPanel.setLayout(new GroupLayout());
			addPropertiesPanel.add(getAddPropertiesLabel(), new Constraints(new Trailing(12, 12, 12), new Leading(4, 10, 10)));
		}
		return addPropertiesPanel;
	}

	private JLabel getAddPropertiesLabel() {
		if (addPropertiesLabel == null) {
			addPropertiesLabel = new JLabel();
			addPropertiesLabel.setIcon(new ImageIcon(getClass().getResource(GUIProperties.ADD_BUTTON_PRESSED_FILE)));
			addPropertiesLabel.addMouseListener(new MouseListener() {
				
				public void mouseEntered(MouseEvent e) {
					addPropertiesLabel.setIcon(new ImageIcon(getClass().getResource(GUIProperties.ADD_BUTTON_FILE)));
				}
				
				public void mouseExited(MouseEvent e) {
					addPropertiesLabel.setIcon(new ImageIcon(getClass().getResource(GUIProperties.ADD_BUTTON_PRESSED_FILE)));
				}
				
				public void mouseClicked(MouseEvent e) {
			
				}
				
				public void mousePressed(MouseEvent e) {
					if (!nameSelectedIndividual.equals("")) {
						AddPropertyIndividualsDialog addPropertyIndividualsDialog = new AddPropertyIndividualsDialog(
								IDRSApplication.idrsJFrame, nameSelectedIndividual, uncheckedPropertiesArrayList);
						addPropertyIndividualsDialog.setVisible(true);
					}
				}
				
				public void mouseReleased(MouseEvent e) {
					
				}
			});
		}
		return addPropertiesLabel;
	}

	private JLabel getNamePropertiesLabel() {
		if (namePropertiesLabel == null) {
			namePropertiesLabel = new JLabel();
			namePropertiesLabel.setFont(new Font("Dialog", Font.BOLD, 13));
			namePropertiesLabel.setText("Properties");
		}
		return namePropertiesLabel;
	}

	private JPanel getNamePropertiesPanel() {
		if (namePropertiesPanel == null) {
			namePropertiesPanel = new JPanel();
			namePropertiesPanel.setBorder(new LineBorder(Color.lightGray, 1, false));
			namePropertiesPanel.setLayout(new GroupLayout());
			namePropertiesPanel.add(getNamePropertiesLabel(), new Constraints(new Leading(0, 79, 12, 12), new Leading(6, 31, 10, 10)));
			namePropertiesPanel.add(getAddPropertiesPanel(), new Constraints(new Trailing(0, 79, 83, 97), new Leading(0, 39, 12, 12)));
		}
		return namePropertiesPanel;
	}

	private JPanel getAddRelationshipPanel() {
		if (addRelationshipPanel == null) {
			addRelationshipPanel = new JPanel();
			addRelationshipPanel.setLayout(new GroupLayout());
			addRelationshipPanel.add(getAddRelationshipLabel(), new Constraints(new Trailing(12, 12, 12), new Leading(4, 29, 10, 10)));
		}
		return addRelationshipPanel;
	}

	private JLabel getAddRelationshipLabel() {
		if (addRelationshipLabel == null) {
			addRelationshipLabel = new JLabel();
			addRelationshipLabel.setIcon(new ImageIcon(getClass().getResource(GUIProperties.ADD_BUTTON_PRESSED_FILE)));
			addRelationshipLabel.setVisible(false);
			addRelationshipLabel.addMouseListener(new MouseListener() {
				
				public void mouseEntered(MouseEvent e) {
					addRelationshipLabel.setIcon(new ImageIcon(getClass().getResource(GUIProperties.ADD_BUTTON_FILE)));
				}
				
				public void mouseExited(MouseEvent e) {
					addRelationshipLabel.setIcon(new ImageIcon(getClass().getResource(GUIProperties.ADD_BUTTON_PRESSED_FILE)));
				}
				
				public void mouseClicked(MouseEvent e) {
					
				}
				
				public void mousePressed(MouseEvent e) {
					
				}
				
				public void mouseReleased(MouseEvent e) {
					
				}
			});
		}
		return addRelationshipLabel;
	}

	private JPanel getNameRelationshipsPanel() {
		if (nameRelationshipsPanel == null) {
			nameRelationshipsPanel = new JPanel();
			nameRelationshipsPanel.setBorder(new LineBorder(Color.lightGray, 1, false));
			nameRelationshipsPanel.setLayout(new GroupLayout());
			nameRelationshipsPanel.add(getNameRelationshipsLabel(), new Constraints(new Leading(0, 110, 10, 10), new Leading(9, 10, 10)));
			nameRelationshipsPanel.add(getAddRelationshipPanel(), new Constraints(new Trailing(0, 56, 103, 128), new Leading(0, 38, 12, 12)));
		}
		return nameRelationshipsPanel;
	}

	private JLabel getNameRelationshipsLabel() {
		if (nameRelationshipsLabel == null) {
			nameRelationshipsLabel = new JLabel();
			nameRelationshipsLabel.setFont(new Font("Dialog", Font.BOLD, 13));
			nameRelationshipsLabel.setText("Relationships");
		}
		return nameRelationshipsLabel;
	}

	private JPanel getPropertiesPanel() {
		if (propertiesPanel == null) {
			propertiesPanel = new JPanel();
			propertiesPanel.setLayout(new BorderLayout());
			propertiesPanel.add(getNamePropertiesPanel(), BorderLayout.NORTH);
			propertiesPanel.add(getContentPropertiesPanel(), BorderLayout.CENTER);
		}
		return propertiesPanel;
	}

	private JPanel getRelationshipsPanel() {
		if (relationshipsPanel == null) {
			relationshipsPanel = new JPanel();
			relationshipsPanel.setLayout(new BorderLayout());
			relationshipsPanel.add(getNameRelationshipsPanel(), BorderLayout.NORTH);
			relationshipsPanel.add(getContentRelationshipsPanel(), BorderLayout.CENTER);
		}
		return relationshipsPanel;
	}

	private JSplitPane getInformationSplitPane() {
		if (informationSplitPane == null) {
			informationSplitPane = new JSplitPane();
			informationSplitPane.setDividerLocation(190);
			informationSplitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
			informationSplitPane.setTopComponent(getRelationshipsPanel());
			informationSplitPane.setBottomComponent(getPropertiesPanel());
		}
		return informationSplitPane;
	}

	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			jPanel1 = new JPanel();
			jPanel1.setLayout(new GroupLayout());
			jPanel1.add(getAddLabel(), new Constraints(new Leading(15, 10, 10), new Leading(2, 10, 10)));
			jPanel1.add(getDeleteLabel(), new Constraints(new Leading(51, 12, 12), new Leading(2, 12, 12)));
		}
		return jPanel1;
	}

	private JPanel getHierarchyPanel() {
		if (hierarchyPanel == null) {
			hierarchyPanel = new JPanel();
			hierarchyPanel.setBorder(new LineBorder(Color.black, 1, false));
			hierarchyPanel.setLayout(new GroupLayout());
			hierarchyPanel.add(getJPanel1(), new Constraints(new Trailing(0, 83, 10, 10), new Leading(0, 34, 12, 12)));
			hierarchyPanel.add(getNameModelBrowserLabel(), new Constraints(new Leading(0, 128, 10, 10), new Leading(8, 20, 12, 12)));
		}
		return hierarchyPanel;
	}

	private JLabel getNameModelBrowserLabel() {
		if (nameModelBrowserLabel == null) {
			nameModelBrowserLabel = new JLabel();
			nameModelBrowserLabel.setFont(new Font("Dialog", Font.BOLD, 13));
			nameModelBrowserLabel.setText("Class Hierarchy");
		}
		return nameModelBrowserLabel;
	}

	private JSplitPane getIndividualsSplitPanel() {
		if (individualsSplitPanel == null) {
			individualsSplitPanel = new JSplitPane();
			individualsSplitPanel.setDividerLocation(312);
			individualsSplitPanel.setLeftComponent(getModelBroswerPanel());
			individualsSplitPanel.setRightComponent(getInformationPanel());
		}
		return individualsSplitPanel;
	}

	private JScrollPane getIndividualScrollPane() {
		if (individualScrollPane == null) {
			individualScrollPane = new JScrollPane();
			individualScrollPane.setViewportView(getIndividualTree());
		}
		return individualScrollPane;
	}

	private JTree getIndividualTree() {
		if (individualTree == null) {
			individualTree = new JTree();
			individualTree.setBorder(new LineBorder(Color.black, 1, false));
			individualTree.setFont(new Font("Dialog", Font.BOLD, 13));
			DefaultTreeModel treeModel = null;
			{
				DefaultMutableTreeNode node0 = new DefaultMutableTreeNode("Thing");
				treeModel = new DefaultTreeModel(node0);
			}
			individualTree.setModel(treeModel);
			individualTree.setEnabled(false);
			individualTree.addTreeSelectionListener(new TreeSelectionListener() {
	
				public void valueChanged(TreeSelectionEvent event) {
					DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) individualTree.getLastSelectedPathComponent();
					
					//Neu rong hoac khong phai la nut con thi thoat.
					if (selectedNode == null || selectedNode.isLeaf() == false)
						return;
					
					reset();
					nameSelectedIndividual = (String) selectedNode.getUserObject();
					
					//Load du lieu ve properties.
					loadPropertiesListIntoPanel(nameSelectedIndividual);

					//Load du lieu ve relationships.
					//loadRelationshipsListIntoPanel(nameSelectedIndividual);
				}
			});
		}
		
		return individualTree;
	}

	private JLabel getAddLabel() {
		if (addLabel == null) {
			addLabel = new JLabel();
			addLabel.setIcon(new ImageIcon(getClass().getResource(GUIProperties.ADD_BUTTON_PRESSED_FILE)));
			addLabel.addMouseListener(new MouseListener() {
				
				@Override
				public void mouseEntered(MouseEvent e) {
					addLabel.setIcon(new ImageIcon(getClass().getResource(GUIProperties.ADD_BUTTON_FILE)));
				};
				
				@Override
				public void mousePressed(MouseEvent e) {
					DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) individualTree.getLastSelectedPathComponent();
					if (selectedNode == null || selectedNode.isLeaf() == true) {
						JOptionPane.showMessageDialog(null, "You must choose a class to contain the Individual");
						return;
					}
					String typeNameOfThisIndividual = (String) selectedNode.getUserObject();
					typeNameOfThisIndividual = typeNameOfThisIndividual.substring(0, typeNameOfThisIndividual.indexOf(" "));
					OWLNamedClass typeOfThisIndividual = OWLModel.owlModel.getOWLNamedClass(IndividualsPanelAction.namespacePrefix + ":" + typeNameOfThisIndividual);
					String nameIndividual = JOptionPane.showInputDialog("Please enter Individual name");
					//Add individual into OWLModel.
					IndividualsPanelAction.addIndividual(nameIndividual, typeOfThisIndividual);
					//Refresh.
					IndividualsPanelAction individualPanelAction = new IndividualsPanelAction();
					individualPanelAction.refreshIndividualPanel();
				};
				
				public void mouseClicked(MouseEvent e) {
					
				};
				
				public void mouseReleased(MouseEvent e) {

				};
				
				@Override
				public void mouseExited(MouseEvent e) {
					addLabel.setIcon(new ImageIcon(getClass().getResource(GUIProperties.ADD_BUTTON_PRESSED_FILE)));
				};
			});
		}
		return addLabel;
	}
	
	private JPanel getInformationPanel() {
		if (informationPanel == null) {
			informationPanel = new JPanel();
			informationPanel.setBorder(BorderFactory.createTitledBorder(null, "Information", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, new Font(
					"Dialog", Font.BOLD, 13), new Color(51, 51, 51)));
			informationPanel.setLayout(new GroupLayout());
			informationPanel.add(getInformationSplitPane(), new Constraints(new Bilateral(0, 0, 101), new Bilateral(0, 0, 64)));
		}
		return informationPanel;
	}

	private JPanel getModelBroswerPanel() {
		if (modelBroswerPanel == null) {
			modelBroswerPanel = new JPanel();
			modelBroswerPanel.setBorder(BorderFactory.createTitledBorder(null, "Model Browser", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, new Font(
					"Dialog", Font.BOLD, 13), new Color(51, 51, 51)));
			modelBroswerPanel.setLayout(new GroupLayout());
			modelBroswerPanel.add(getIndividualScrollPane(), new Constraints(new Bilateral(4, 0, 22), new Bilateral(44, 0, 22)));
			modelBroswerPanel.add(getHierarchyPanel(), new Constraints(new Bilateral(4, 1, 0), new Leading(0, 38, 28, 409)));
		}
		return modelBroswerPanel;
	}

	private JLabel getDeleteLabel() {
		if (deleteLabel == null) {
			deleteLabel = new JLabel();
			deleteLabel.setIcon(new ImageIcon(getClass().getResource(GUIProperties.DELETE_BUTTON_PRESSED_FILE)));
			deleteLabel.addMouseListener(new MouseListener() {
				
				public void mouseEntered(MouseEvent e) {
					deleteLabel.setIcon(new ImageIcon(getClass().getResource(GUIProperties.DELETE_BUTTON_FILE)));
				};
				
				public void mouseExited(MouseEvent e) {
					deleteLabel.setIcon(new ImageIcon(getClass().getResource(GUIProperties.DELETE_BUTTON_PRESSED_FILE)));
				};
				
				public void mousePressed(MouseEvent e) {
					DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) individualTree.getLastSelectedPathComponent();
					if (selectedNode == null || selectedNode.isLeaf() == false) {
						JOptionPane.showMessageDialog(null, "You must choose a Individual You want to delete");
						return;
					}
					nameSelectedIndividual = (String) selectedNode.getUserObject();
					IndividualsPanelAction.deleteIndividual(nameSelectedIndividual);
					IndividualsPanelAction individualsPanelAction = new IndividualsPanelAction();
					individualsPanelAction.refreshIndividualPanel();
				};
				
				public void mouseReleased(MouseEvent e) {

				};
				
				public void mouseClicked(MouseEvent e) {
					
				};
			});
		}
		return deleteLabel;
	}
	
	private void reset() {
		contentPropertiesPanel.removeAll();
		contentRelationshipsPanel.removeAll();
		IDRSApplication.idrsJFrame.repaint();
		if (checkedPropertiesArrayList != null && uncheckedPropertiesArrayList != null) {
			checkedPropertiesArrayList.removeAll(checkedPropertiesArrayList);
			uncheckedPropertiesArrayList.removeAll(uncheckedPropertiesArrayList);
		}
	}

	public static void resetValues() {
		nameSelectedIndividual = "";
	}
	
	public static void loadPropertiesListIntoPanel(String nameSelectedIndividual) {
		if (checkedPropertiesArrayList != null) {
			checkedPropertiesArrayList.removeAll(checkedPropertiesArrayList);
			uncheckedPropertiesArrayList.removeAll(uncheckedPropertiesArrayList);
			contentPropertiesPanel.removeAll();
		}
		
		ArrayList < String > namePropertiesArrayList = 
			IndividualsPanelAction.getNameAndValueOfPropertiesOfThisIndividual(nameSelectedIndividual);
		OWLMyIndividual owlMyIndividual = 
			IndividualsPanelAction.getIndividual(nameSelectedIndividual);
		
		for (int i = 0; i < owlMyIndividual.getPropertyList().size(); i++) {			
			checkedPropertiesArrayList.add(
					owlMyIndividual.getPropertyList().get(i).getBrowserText());
		}
			
		for (int i = 0; i < namePropertiesArrayList.size(); i++) {
			LinePanel linePanel = new LinePanel(
					namePropertiesArrayList.get(i).toString(), 
					GUIProperties.INDIVIDUAL_PANEL, 
					GUIProperties.PROPERTIES_INDIVIDUAL_PANEL);
			linePanel.setNameSelectedIndividual(nameSelectedIndividual);
			linePanel.setIndexOfProperty(i);
			contentPropertiesPanel.add(linePanel, linePanel.getPosition(i));
		}
		
		for (int i = 0; i < PropertyPanelAction.propertiesArrayList.size(); i++) {
			String temp = "unchecked";
			for (int j = 0; j < checkedPropertiesArrayList.size(); j++) {
				if (PropertyPanelAction.propertiesArrayList.get(i).getProperty().getBrowserText().equals(
						checkedPropertiesArrayList.get(j).toString())) {
					temp = "checked";
				}
			}
			if (temp.equals("unchecked")) {
				uncheckedPropertiesArrayList.add(
						PropertyPanelAction.propertiesArrayList.get(i).getProperty().getBrowserText().substring(
								PropertyPanelAction.propertiesArrayList.get(i).getProperty().getBrowserText().indexOf(":") + 1));
			}
		}
		
		IDRSApplication.idrsJFrame.repaint();
	}
	
	public void loadRelationshipsListIntoPanel(String nameSelected) {
		ArrayList < String > relationshipsList = IndividualsPanelAction.getRelationshipsListOfThisIndividual(nameSelected);
		for (int i = 0; i < relationshipsList.size(); i++) {
			LinePanel linePanel = new LinePanel(relationshipsList.get(i).toString(), GUIProperties.INDIVIDUAL_PANEL, GUIProperties.RELATIONSHIPS_INDIVIDUAL_PANEL);
			linePanel.setNameSelectedIndividual(nameSelected);
			contentRelationshipsPanel.add(linePanel, linePanel.getPosition(i));
		}
		IDRSApplication.idrsJFrame.repaint();
	}
	
	public static void updateTextOfComponents() {
		nameModelBrowserLabel.setText("Class Hierarchy");
	}
}
