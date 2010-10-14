package tkorg.idrs.gui.ontology;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableCellRenderer;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;
import org.dyno.visual.swing.layouts.Trailing;

import tkorg.idrs.action.ontology.RelationshipsAction;
import tkorg.idrs.gui.main.IDRSApplication;
import tkorg.idrs.gui.main.IDRSResourceBundle;
import tkorg.idrs.properties.files.GUIProperties;

//VS4E -- DO NOT REMOVE THIS LINE!
public class RelationshipsPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private static JSplitPane relationDescriptionJSplitPane;
	private static JPanel relationshipsJPanel;
	private static JPanel descriptionJPanel;
	private static JLabel addJLabel;
	private static JLabel delJLabel;
	private static JList relationshipsJList;
	private static JScrollPane relationshipsJScrollPane;
	private static JSplitPane domainRangeJSplitPane;
	private static JPanel domainsJPanel;
	private static JPanel rangesJPanel;
	private static JPanel inversePropertiesJPanel;
	private static JSplitPane rangeInverseJSplitPane;
	private static JLabel domainsJLabel;
	private static JTable domainsJTable;
	private static JScrollPane domainsJScrollPane;
	private static JLabel addDomainsJLabel;
	private static JTable rangesJTable;
	private static JScrollPane rangesJScrollPane;
	private static JLabel rangesJLabel;
	private static JLabel addRangesJLabel;
	private static JLabel inversePropertiesLabel;
	private static JLabel addInversePropertiesJLabel;
	private static JTable inversePropertiesJTable;
	private static JScrollPane inversePropertiesJScrollPane;
	
	private static ArrayList<String> domains = null;
	private static ArrayList<String> ranges = null;
	private static String inversePro = null;
	
	private static ImageIcon editIcon;
	private static ImageIcon deleteIcon;
	
	public RelationshipsPanel() {
		initComponents();
	}

	private void initComponents() {
		setLayout(new GroupLayout());
		add(getRelationDescriptionJSplitPane(), new Constraints(new Bilateral(0, 4, 386), new Bilateral(0, 0, 28)));
		setSize(522, 356);
		addComponentListener(new ComponentAdapter() {
			
			public void componentShown(ComponentEvent event) {
				componentComponentShown(event);
			}
		});
		editIcon = new ImageIcon(getClass().getResource(
				GUIProperties.EDIT_BUTTON_APPEAR_FILE));
		deleteIcon = new ImageIcon(getClass().getResource(
				GUIProperties.DELETE_BUTTON_PRESSED_FILE));		
	}

	private JScrollPane getInversePropertiesJScrollPane() {
		if (inversePropertiesJScrollPane == null) {
			inversePropertiesJScrollPane = new JScrollPane();
			inversePropertiesJScrollPane.setViewportView(getInversePropertiesJTable());
		}
		return inversePropertiesJScrollPane;
	}

	private static JTable getInversePropertiesJTable() {
		
		if (inversePropertiesJTable == null) {
			inversePropertiesJTable = new JTable();						
		}
		else {
			Vector inverseProData = new Vector();
			if (inversePro != "") {
					inverseProData.add(addRow(inversePro));
			}
			Vector columnNames = new Vector();
			columnNames.add("");
			columnNames.add("");
			columnNames.add("");
			TableCellRenderer defaultRenderer;
			inversePropertiesJTable.setModel(new JTableLabelModel(inverseProData, columnNames));
			defaultRenderer = rangesJTable.getDefaultRenderer(JLabel.class);
			inversePropertiesJTable.setDefaultRenderer(JLabel.class, new JTableLabelRenderer(
					defaultRenderer));
			inversePropertiesJTable
					.setPreferredScrollableViewportSize(new Dimension(400, 200));
			inversePropertiesJTable.addMouseListener(new JTableModelListener(
					inversePropertiesJTable));
			inversePropertiesJTable.setBorder(null);
			inversePropertiesJTable.setShowHorizontalLines(false);
			inversePropertiesJTable.setShowVerticalLines(false);

			inversePropertiesJTable.setRowHeight(editIcon.getIconHeight());
			inversePropertiesJTable.getColumnModel().getColumn(0).setPreferredWidth(
					inversePropertiesJTable.getWidth());

			inversePropertiesJTable.getColumnModel().getColumn(1).setPreferredWidth(
					editIcon.getIconWidth() + 2);
			inversePropertiesJTable.getColumnModel().getColumn(2).setPreferredWidth(
					deleteIcon.getIconWidth() + 2);
			inversePropertiesJTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);							
			inversePropertiesJTable.repaint();
			inversePropertiesJScrollPane.setViewportView(inversePropertiesJTable);
		}
		return inversePropertiesJTable;
	}

	private JLabel getAddInversePropertiesJLabel() {
		if (addInversePropertiesJLabel == null) {
			addInversePropertiesJLabel = new JLabel();
			addInversePropertiesJLabel.setIcon(new ImageIcon(getClass().getResource(GUIProperties.ADD_BUTTON_PRESSED_FILE)));
			addInversePropertiesJLabel.setToolTipText(IDRSResourceBundle.res.getString("add") +" "+ IDRSResourceBundle.res.getString("inverse.properties"));
			addInversePropertiesJLabel.addMouseListener(new MouseAdapter() {
	
				public void mouseExited(MouseEvent event) {
					addInversePropertiesJLabel.setIcon(new ImageIcon(getClass().getResource(GUIProperties.ADD_BUTTON_PRESSED_FILE)));
				}
	
				public void mouseReleased(MouseEvent event) {
				}
	
				public void mousePressed(MouseEvent event) {
					RelationshipsDialog myDialog = new RelationshipsDialog(IDRSApplication.idrsJFrame, getRelationshipsJList().getAnchorSelectionIndex());
					myDialog.setVisible(true);
				}
	
				public void mouseEntered(MouseEvent event) {
					addInversePropertiesJLabel.setIcon(new ImageIcon(getClass().getResource(GUIProperties.ADD_BUTTON_FILE)));
				}
	
				public void mouseClicked(MouseEvent event) {
				}
			});
		}
		return addInversePropertiesJLabel;
	}

	private JLabel getInversePropertiesLabel() {
		if (inversePropertiesLabel == null) {
			inversePropertiesLabel = new JLabel();
			inversePropertiesLabel.setText(IDRSResourceBundle.res.getString("inverse.properties"));
		}
		return inversePropertiesLabel;
	}

	private JLabel getAddRangesJLabel() {
		if (addRangesJLabel == null) {
			addRangesJLabel = new JLabel();
			addRangesJLabel.setIcon(new ImageIcon(getClass().getResource(GUIProperties.ADD_BUTTON_PRESSED_FILE)));
			addRangesJLabel.setToolTipText(IDRSResourceBundle.res.getString("add.range"));
			addRangesJLabel.addMouseListener(new MouseAdapter() {
	
				public void mouseExited(MouseEvent event) {
					addRangesJLabel.setIcon(new ImageIcon(getClass().getResource(GUIProperties.ADD_BUTTON_PRESSED_FILE)));
				}
	
				public void mouseReleased(MouseEvent event) {
				}
	
				public void mousePressed(MouseEvent event) {
					ClassHierarchyDialog myHierarchy = new ClassHierarchyDialog(IDRSApplication.idrsJFrame, "range", getRelationshipsJList()
							.getAnchorSelectionIndex());
					myHierarchy.setVisible(true);
				}
	
				public void mouseEntered(MouseEvent event) {
					addRangesJLabel.setIcon(new ImageIcon(getClass().getResource(GUIProperties.ADD_BUTTON_FILE)));
				}
	
				public void mouseClicked(MouseEvent event) {
				}
			});
		}
		return addRangesJLabel;
	}

	private JLabel getRangesJLabel() {
		if (rangesJLabel == null) {
			rangesJLabel = new JLabel();
			rangesJLabel.setText(IDRSResourceBundle.res.getString("ranges.intersection"));
		}
		return rangesJLabel;
	}

	private JScrollPane getRangesJScrollPane() {
		if (rangesJScrollPane == null) {
			rangesJScrollPane = new JScrollPane();
			rangesJScrollPane.setViewportView(getRangesJTable());
		}
		return rangesJScrollPane;
	}

	private static JTable getRangesJTable() {
		
		if (rangesJTable == null) {
			rangesJTable = new JTable();
		}
		else {
			Vector rangesData = new Vector();
			if (ranges.size() > 0) {
				for (int i = 0; i < ranges.size(); i++) {
					rangesData.add(addRow(ranges.get(i)));
				}
			}
			Vector columnNames = new Vector();
			columnNames.add("");
			columnNames.add("");
			columnNames.add("");
			TableCellRenderer defaultRenderer;
			rangesJTable.setModel(new JTableLabelModel(rangesData, columnNames));
			defaultRenderer = rangesJTable.getDefaultRenderer(JLabel.class);
			rangesJTable.setDefaultRenderer(JLabel.class, new JTableLabelRenderer(
					defaultRenderer));
			rangesJTable
					.setPreferredScrollableViewportSize(new Dimension(400, 200));
			rangesJTable.addMouseListener(new JTableModelListener(
					rangesJTable));
			rangesJTable.setBorder(null);
			rangesJTable.setShowHorizontalLines(false);
			rangesJTable.setShowVerticalLines(false);

			rangesJTable.setRowHeight(editIcon.getIconHeight());
			rangesJTable.getColumnModel().getColumn(0).setPreferredWidth(
					rangesJTable.getWidth());

			rangesJTable.getColumnModel().getColumn(1).setPreferredWidth(
					editIcon.getIconWidth() + 2);
			rangesJTable.getColumnModel().getColumn(2).setPreferredWidth(
					deleteIcon.getIconWidth() + 2);
			rangesJTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);							
			rangesJTable.repaint();
			rangesJScrollPane.setViewportView(rangesJTable);
		}
		return rangesJTable;
	}

	private JLabel getAddDomainsJLabel() {
		if (addDomainsJLabel == null) {
			addDomainsJLabel = new JLabel();
			addDomainsJLabel.setIcon(new ImageIcon(getClass().getResource(GUIProperties.ADD_BUTTON_PRESSED_FILE)));
			addDomainsJLabel.setToolTipText(IDRSResourceBundle.res.getString("add.domain"));
			addDomainsJLabel.addMouseListener(new MouseAdapter() {
	
				public void mouseExited(MouseEvent event) {
					addDomainsJLabel.setIcon(new ImageIcon(getClass().getResource(GUIProperties.ADD_BUTTON_PRESSED_FILE)));
				}
	
				public void mouseReleased(MouseEvent event) {
				}
	
				public void mousePressed(MouseEvent event) {
					ClassHierarchyDialog myHierarchy = new ClassHierarchyDialog(IDRSApplication.idrsJFrame, "domain", getRelationshipsJList()
							.getAnchorSelectionIndex());
					myHierarchy.setVisible(true);
				}
	
				public void mouseEntered(MouseEvent event) {
					addDomainsJLabel.setIcon(new ImageIcon(getClass().getResource(GUIProperties.ADD_BUTTON_FILE)));
				}
	
				public void mouseClicked(MouseEvent event) {
				}
			});
		}
		return addDomainsJLabel;
	}

	private JScrollPane getDomainsJScrollPane() {
		if (domainsJScrollPane == null) {
			domainsJScrollPane = new JScrollPane();
			domainsJScrollPane.setViewportView(getDomainsJTable());
		}
		return domainsJScrollPane;
	}

	private static JTable getDomainsJTable() {
		if (domainsJTable == null) {
			domainsJTable = new JTable();					
		}
		else {
			Vector domainsData = new Vector();
			if(domains.size() > 0) {
				for (int i = 0; i < domains.size(); i++) {
					domainsData.add(addRow(domains.get(i)));
				}
			}			
			Vector columnNames = new Vector();
			columnNames.add("");
			columnNames.add("");
			columnNames.add("");
			TableCellRenderer defaultRenderer;
			domainsJTable.setModel(new JTableLabelModel(domainsData, columnNames));
			defaultRenderer = domainsJTable.getDefaultRenderer(JLabel.class);
			domainsJTable.setDefaultRenderer(JLabel.class, new JTableLabelRenderer(
					defaultRenderer));
			domainsJTable
					.setPreferredScrollableViewportSize(new Dimension(400, 200));
			inversePropertiesJTable.addMouseListener(new JTableModelListener(domainsJTable));
			domainsJTable.setBorder(null);
			domainsJTable.setShowHorizontalLines(false);
			domainsJTable.setShowVerticalLines(false);

			domainsJTable.setRowHeight(editIcon.getIconHeight());
			domainsJTable.getColumnModel().getColumn(0).setPreferredWidth(
					domainsJTable.getWidth());

			domainsJTable.getColumnModel().getColumn(1).setPreferredWidth(
					editIcon.getIconWidth() + 2);
			domainsJTable.getColumnModel().getColumn(2).setPreferredWidth(
					deleteIcon.getIconWidth() + 2);
			domainsJTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);	
			domainsJScrollPane.setViewportView(domainsJTable);
			domainsJTable.repaint();			
						
		}
		return domainsJTable;
	}

	private JLabel getDomainsJLabel() {
		if (domainsJLabel == null) {
			domainsJLabel = new JLabel();
			domainsJLabel.setText(IDRSResourceBundle.res.getString("domains.intersection"));
		}
		return domainsJLabel;
	}

	private JSplitPane getRangeInverseJSplitPane() {
		if (rangeInverseJSplitPane == null) {
			rangeInverseJSplitPane = new JSplitPane();
			rangeInverseJSplitPane.setDividerLocation(130);
			rangeInverseJSplitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
			rangeInverseJSplitPane.setFont(new Font("Dialog", Font.BOLD, 12));
			rangeInverseJSplitPane.setTopComponent(getRangesJPanel());
			rangeInverseJSplitPane.setBottomComponent(getInversePropertiesJPanel());
		}
		return rangeInverseJSplitPane;
	}

	private JPanel getInversePropertiesJPanel() {
		if (inversePropertiesJPanel == null) {
			inversePropertiesJPanel = new JPanel();
			inversePropertiesJPanel.setLayout(new GroupLayout());
			inversePropertiesJPanel.add(getInversePropertiesLabel(), new Constraints(new Leading(12, 12, 12), new Leading(12, 12, 12)));
			inversePropertiesJPanel.add(getAddInversePropertiesJLabel(), new Constraints(new Leading(135, 12, 12), new Leading(5, 10, 10)));
			inversePropertiesJPanel.add(getInversePropertiesJScrollPane(), new Constraints(new Bilateral(12, 12, 22), new Bilateral(46, 9, 10, 57)));
		}
		return inversePropertiesJPanel;
	}

	private JPanel getRangesJPanel() {
		if (rangesJPanel == null) {
			rangesJPanel = new JPanel();
			rangesJPanel.setLayout(new GroupLayout());
			rangesJPanel.add(getRangesJLabel(), new Constraints(new Leading(12, 12, 12), new Leading(12, 12, 12)));
			rangesJPanel.add(getAddRangesJLabel(), new Constraints(new Leading(140, 12, 12), new Leading(5, 12, 12)));
			rangesJPanel.add(getRangesJScrollPane(), new Constraints(new Bilateral(11, 12, 22), new Bilateral(41, 12, 22)));
		}
		return rangesJPanel;
	}

	private JPanel getDomainsJPanel() {
		if (domainsJPanel == null) {
			domainsJPanel = new JPanel();
			domainsJPanel.setLayout(new GroupLayout());
			domainsJPanel.add(getDomainsJLabel(), new Constraints(new Leading(12, 12, 12), new Leading(7, 12, 12)));
			domainsJPanel.add(getAddDomainsJLabel(), new Constraints(new Leading(151, 10, 10), new Leading(0, 12, 12)));
			domainsJPanel.add(getDomainsJScrollPane(), new Constraints(new Bilateral(13, 12, 22), new Bilateral(39, 12, 22)));
		}
		return domainsJPanel;
	}

	private JSplitPane getDomainRangeJSplitPane() {
		if (domainRangeJSplitPane == null) {
			domainRangeJSplitPane = new JSplitPane();
			domainRangeJSplitPane.setDividerLocation(130);
			domainRangeJSplitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
			domainRangeJSplitPane.setFont(new Font("Dialog", Font.BOLD, 12));
			domainRangeJSplitPane.setTopComponent(getDomainsJPanel());
			domainRangeJSplitPane.setBottomComponent(getRangeInverseJSplitPane());
		}
		return domainRangeJSplitPane;
	}

	private JPanel getDescriptionJPanel() {
		if (descriptionJPanel == null) {
			descriptionJPanel = new JPanel();
			descriptionJPanel.setBorder(BorderFactory.createTitledBorder(IDRSResourceBundle.res.getString("description")));
			descriptionJPanel.setFont(new Font("Dialog", Font.BOLD, 12));
			descriptionJPanel.setLayout(new GroupLayout());
			descriptionJPanel.add(getDomainRangeJSplitPane(), new Constraints(new Bilateral(2, 0, 103), new Bilateral(-6, 0, 102)));
		}
		return descriptionJPanel;
	}

	private JScrollPane getRelationshipsJScrollPane() {
		if (relationshipsJScrollPane == null) {
			relationshipsJScrollPane = new JScrollPane();
			relationshipsJScrollPane.setViewportView(getRelationshipsJList());
		}
		return relationshipsJScrollPane;
	}

	private static JList getRelationshipsJList() {
		DefaultListModel listModel = new DefaultListModel();
		if (relationshipsJList == null) {
			relationshipsJList = new JList();
			relationshipsJList.setModel(listModel);			
		} else {
			try {	
				ArrayList<String> relations = RelationshipsAction.viewRelationships();
				for(int i = 0; i < relations.size(); i++){
					listModel.addElement(relations.get(i));			
				}
				relationshipsJList.setModel(listModel);
				relationshipsJList.addListSelectionListener(new ListSelectionListener(){

					@Override
					public void valueChanged(ListSelectionEvent e) {
						if(relationshipsJList.getSelectedIndex() >= 0) {
							updateData(relationshipsJList.getSelectedIndex());
							refresh();
						}
					}					
				});
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage());
			}	
		}
		return relationshipsJList;
	}

	private JLabel getDelJLabel() {
		if (delJLabel == null) {
			delJLabel = new JLabel();
			delJLabel.setIcon(new ImageIcon(getClass().getResource(GUIProperties.DELETE_BUTTON_PRESSED_FILE)));
			delJLabel.setToolTipText(IDRSResourceBundle.res.getString("delete") + " " + IDRSResourceBundle.res.getString("relationship"));
			delJLabel.addMouseListener(new MouseAdapter() {
	
				public void mouseExited(MouseEvent event) {
					delJLabel.setIcon(new ImageIcon(getClass().getResource(GUIProperties.DELETE_BUTTON_PRESSED_FILE)));
				}
	
				public void mouseReleased(MouseEvent event) {
				}
	
				public void mousePressed(MouseEvent event) {
					RelationshipsAction.delRelationship(relationshipsJList.getAnchorSelectionIndex());
					clearData();
					refresh();
				}
	
				public void mouseEntered(MouseEvent event) {
					delJLabel.setIcon(new ImageIcon(getClass().getResource(GUIProperties.DELETE_BUTTON_FILE)));
				}
	
				public void mouseClicked(MouseEvent event) {
				}
			});
		}
		return delJLabel;
	}

	private JPanel getRelationshipsJPanel() {
		if (relationshipsJPanel == null) {
			relationshipsJPanel = new JPanel();
			relationshipsJPanel.setBorder(BorderFactory.createTitledBorder(IDRSResourceBundle.res.getString("relationships")));
			relationshipsJPanel.setFont(new Font("Dialog", Font.BOLD, 12));
			relationshipsJPanel.setLayout(new GroupLayout());
			relationshipsJPanel.add(getRelationshipsJScrollPane(), new Constraints(new Bilateral(0, 0, 129), new Bilateral(54, -2, 10, 190)));
			relationshipsJPanel.add(getDelJLabel(), new Constraints(new Trailing(4, 10, 114), new Leading(9, 29, 26, 135)));
			relationshipsJPanel.add(getAddJLabel(), new Constraints(new Trailing(40, 31, 12, 12), new Leading(7, 33, 26, 135)));
		}
		return relationshipsJPanel;
	}

	private JLabel getAddJLabel() {
		if (addJLabel == null) {
			addJLabel = new JLabel();
			addJLabel.setIcon(new ImageIcon(getClass().getResource(GUIProperties.ADD_BUTTON_PRESSED_FILE)));
			addJLabel.setToolTipText(IDRSResourceBundle.res.getString("add") +" "+ IDRSResourceBundle.res.getString("relationship"));
			addJLabel.setOpaque(true);
			addJLabel.addMouseListener(new MouseAdapter() {
	
				public void mouseExited(MouseEvent event) {
					addJLabel.setIcon(new ImageIcon(getClass().getResource(GUIProperties.ADD_BUTTON_PRESSED_FILE)));
				}
	
				public void mouseReleased(MouseEvent event) {
				}
	
				public void mousePressed(MouseEvent event) {
					String name = JOptionPane.showInputDialog(IDRSResourceBundle.res.getString("please.enter.name.relationship"));
					RelationshipsAction.addRelationship(name);
					getRelationshipsJList();
				}
	
				public void mouseEntered(MouseEvent event) {
					addJLabel.setIcon(new ImageIcon(getClass().getResource(GUIProperties.ADD_BUTTON_FILE)));
				}
	
				public void mouseClicked(MouseEvent event) {
				}
			});
		}
		return addJLabel;
	}

	private JSplitPane getRelationDescriptionJSplitPane() {
		if (relationDescriptionJSplitPane == null) {
			relationDescriptionJSplitPane = new JSplitPane();
			relationDescriptionJSplitPane.setDividerLocation(240);
			relationDescriptionJSplitPane.setLeftComponent(getRelationshipsJPanel());
			relationDescriptionJSplitPane.setRightComponent(getDescriptionJPanel());
		}
		return relationDescriptionJSplitPane;
	}

	private void componentComponentShown(ComponentEvent event) {
		getRelationshipsJList();
	}
	
	public static void refresh() {
		getRelationshipsJList();
		getDomainsJTable();
		getRangesJTable();
		getInversePropertiesJTable();
	}
	
	public static Vector addRow(String str) {

		JLabel addJLabel = new JLabel("", editIcon, JLabel.RIGHT);
		JLabel editJLabel = new JLabel("", deleteIcon, JLabel.RIGHT);

		Vector row = new Vector();
		row.add(str);
		row.add(addJLabel);
		row.add(editJLabel);
		return row;
	}
	
	public void clearData() {
		domains.clear();
		ranges.clear();
		inversePro = "";
	}
	
	public static void updateData(int indexRelationships) {
		domains = RelationshipsAction.viewDomains(indexRelationships);
		ranges = RelationshipsAction.viewRanges(indexRelationships);
		inversePro = RelationshipsAction.viewInverseProperties(indexRelationships);
	}
	
}
