package tkorg.idrs.gui.ontology;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;

import edu.stanford.smi.protegex.owl.model.OWLNamedClass;

import tkorg.idrs.action.ontology.ClassPanelAction;
import tkorg.idrs.action.ontology.IndividualsPanelAction;
import tkorg.idrs.core.ontology.OWLModel;
import tkorg.idrs.core.ontology.OWLMyClass;
import tkorg.idrs.gui.main.IDRSApplication;
import tkorg.idrs.gui.main.IDRSResourceBundle;
import tkorg.idrs.properties.files.GUIProperties;

//VS4E -- DO NOT REMOVE THIS LINE!
public class ClassPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	public static JTree classTree;
	public static JPanel contentSuperClassPanel;
	public static JList disjointClassList;
	public static JPanel contentDisjontClassPanel;
	
	private static JPanel modelBrowerPanel;
	private static JPanel descriptionPanel;
	private JSplitPane classJSplitPane;
	private static JLabel jLabel0;
	private JPanel jPanel0;
	private JScrollPane jScrollPane0;
	private JLabel jLabel1;
	private JLabel addClassLabel;
	private JPanel jPanel1;
	private JPanel jPanel2;
	private JPanel jPanel3;
	private JSplitPane jSplitPane0;
	private static JLabel jLabel3;
	private JPanel jPanel4;
	private JLabel addSuperClassLabel;
	private static JPanel jPanel5;
	private JPanel jPanel6;
	private static JLabel jLabel5;
	private JLabel addDisjointClassLabel;
	private JPanel jPanel7;
	private JLabel deleteClassLabel;
	
	public ClassPanel() {
		initComponents();
	}

	private void initComponents() {
		setLayout(new GroupLayout());
		add(getClassJSplitPane(), new Constraints(new Bilateral(0, 0, 12), new Bilateral(3, 0, 3)));
		setSize(800, 435);
	}

	private JLabel getDeleteClassLabel() {
		if (deleteClassLabel == null) {
			deleteClassLabel = new JLabel();
	
			deleteClassLabel.setIcon(new ImageIcon(getClass().getResource(GUIProperties.DELETE_BUTTON_PRESSED_FILE)));
			deleteClassLabel.addMouseListener(new MouseListener(){
				public void mouseClicked(MouseEvent e){
					if (OWLModel.owlModel != null) {
						int result = JOptionPane.showConfirmDialog(IDRSApplication.idrsJFrame, 
								IDRSResourceBundle.res.getString("delete.class"), 
								IDRSResourceBundle.res.getString("delete.class"), 
								JOptionPane.YES_NO_OPTION, 
								JOptionPane.QUESTION_MESSAGE);
						if(result == JOptionPane.YES_OPTION){
							
							ClassPanelAction action = new ClassPanelAction();
							DefaultMutableTreeNode nodeSelected = (DefaultMutableTreeNode)ClassPanel.classTree.getLastSelectedPathComponent();
							boolean rs = action.deleteClass(nodeSelected.toString());
							if(rs == true){
								if(nodeSelected != null && nodeSelected.getParent() != null){
									DefaultTreeModel model = (DefaultTreeModel)classTree.getModel();
									model.removeNodeFromParent(nodeSelected);
								}
							}else{
								JOptionPane.showMessageDialog(IDRSApplication.idrsJFrame, IDRSResourceBundle.res.getString("error.delete.class"));
							}
							
						}
					}else JOptionPane.showMessageDialog(IDRSApplication.idrsJFrame, 
														IDRSResourceBundle.res.getString("please.open.or.new.a.ontology.before"),
														IDRSResourceBundle.res.getString("message"),
														JOptionPane.OK_OPTION);
				};
				
				public void mousePressed(MouseEvent e){
					
				};
				
				public void mouseEntered(MouseEvent e){
					deleteClassLabel.setIcon(new ImageIcon(getClass().getResource(GUIProperties.DELETE_BUTTON_FILE)));
				};
				
				public void mouseReleased(MouseEvent e){
					
				};
				
				public void mouseExited(MouseEvent e){					
					deleteClassLabel.setIcon(new ImageIcon(getClass().getResource(GUIProperties.DELETE_BUTTON_PRESSED_FILE)));
				};
			});
		}
		return deleteClassLabel;
	}

	private JPanel getJPanel7() {
		if (jPanel7 == null) {
			jPanel7 = new JPanel();
			jPanel7.add(getAddDisjointClassLabel());
		}
		return jPanel7;
	}

	private JLabel getAddDisjointClassLabel() {
		if (addDisjointClassLabel == null) {
			addDisjointClassLabel = new JLabel();
			addDisjointClassLabel.setIcon(new ImageIcon(getClass().getResource(GUIProperties.ADD_BUTTON_PRESSED_FILE)));
			addDisjointClassLabel.addMouseListener(new MouseListener(){
				public void mouseClicked(MouseEvent e){
					if(!ClassPanelAction.nameClass.equals("")){
						AddDisjontClass addDisjontClass = new AddDisjontClass(IDRSApplication.idrsJFrame);
						addDisjontClass.setVisible(true);
					} else {
						JOptionPane.showMessageDialog(IDRSApplication.idrsJFrame,
							IDRSResourceBundle.res.getString("please.open.or.new.a.ontology.before"),
							IDRSResourceBundle.res.getString("message"),
							JOptionPane.OK_OPTION);
					}
				};
				
				public void mousePressed(MouseEvent e){
					
				};
				
				public void mouseEntered(MouseEvent e){
					addDisjointClassLabel.setIcon(new ImageIcon(getClass().getResource(GUIProperties.ADD_BUTTON_FILE)));
				};
				
				public void mouseReleased(MouseEvent e){
					
				};
				
				public void mouseExited(MouseEvent e){					
					addDisjointClassLabel.setIcon(new ImageIcon(getClass().getResource(GUIProperties.ADD_BUTTON_PRESSED_FILE)));
				};
			});
		}
		return addDisjointClassLabel;
	}

	private JLabel getJLabel5() {
		if (jLabel5 == null) {
			jLabel5 = new JLabel();
			jLabel5.setFont(new Font("Dialog", Font.BOLD, 13));
			jLabel5.setText(IDRSResourceBundle.res.getString("disjoint.class"));
			//jLabel5.setText("DisjointClass");
		}
		return jLabel5;
	}

	private JPanel getJPanel6() {
		if (jPanel6 == null) {
			jPanel6 = new JPanel();
			//jPanel6.setBackground(new Color(238, 238, 238));
			jPanel6.setBorder(new LineBorder(Color.lightGray, 1, false));
			jPanel6.setLayout(new BorderLayout());
			jPanel6.add(getJLabel5(), BorderLayout.CENTER);
			jPanel6.add(getJPanel7(), BorderLayout.EAST);
		}
		return jPanel6;
	}

//	private JList getSuperClassList() {
//		if (superClassList == null) {
//			superClassList = new JList();
//			superClassList.setBorder(new LineBorder(Color.lightGray, 1, false));
//			superClassList.setCellRenderer(new CustomCellRenderer());
//			superClassList.setFont(new Font("Dialog", Font.BOLD, 13));
//			superClassList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//			DefaultListModel listModel = new DefaultListModel();
//			
//			superClassList.setModel(listModel);
//			superClassList.addMouseListener(new MouseListener(){
//				public void mouseClicked(MouseEvent e){
//					if(e.getClickCount() == 2){
//						int rs = JOptionPane.showConfirmDialog(IDRSApplication.idrsJFrame, 
//								IDRSResourceBundle.res.getString("do.you.delete.this.class"),
//								IDRSResourceBundle.res.getString("message"),
//								JOptionPane.OK_OPTION);
//						if(rs == JOptionPane.OK_OPTION){
//							String nameSuperClass = ((LinePanel)superClassList.getSelectedValue()).getTextJLabel().getText();
//							String nameSelectedClass = ((DefaultMutableTreeNode)classTree.getLastSelectedPathComponent()).toString();
//							ClassPanelAction action = new ClassPanelAction();
//							
//							action.deleteSuperClass(nameSelectedClass, nameSuperClass);
//							
//							superClassList.setModel(action.viewSuperClassToListModel(nameSelectedClass));
////							action.viewTreeClassModelAction(classTree);							
//						}
//					}
//				};
//				
//				public void mousePressed(MouseEvent e){
//					
//				};
//				
//				public void mouseEntered(MouseEvent e){
//					
//				};
//				
//				public void mouseReleased(MouseEvent e){
//					
//				};
//				
//				public void mouseExited(MouseEvent e){					
//					
//				};
//			});
//			//superClassList.setCellRenderer(new CustomCellRenderer());
//		}
//		return superClassList;
//	}

	private JPanel getJPanel5() {
		if (jPanel5 == null) {
			jPanel5 = new JPanel();
			jPanel5.add(getAddSuperClassLabel());
		}
		return jPanel5;
	}

	private JLabel getAddSuperClassLabel() {
		if (addSuperClassLabel == null) {
			addSuperClassLabel = new JLabel();
			addSuperClassLabel.setIcon(new ImageIcon(getClass().getResource(GUIProperties.ADD_BUTTON_PRESSED_FILE)));
			addSuperClassLabel.addMouseListener(new MouseListener(){
				public void mouseClicked(MouseEvent e){
					if(!ClassPanelAction.nameClass.equals("")){
						AddSuperClass addSuperClass = new AddSuperClass(IDRSApplication.idrsJFrame);
						addSuperClass.setVisible(true);
					} else {
						JOptionPane.showMessageDialog(IDRSApplication.idrsJFrame,
							IDRSResourceBundle.res.getString("please.open.or.new.a.ontology.before"),
							IDRSResourceBundle.res.getString("message"),
							JOptionPane.OK_OPTION);
					}
				};
				
				public void mousePressed(MouseEvent e){
					
				};
				
				public void mouseEntered(MouseEvent e){
					addSuperClassLabel.setIcon(new ImageIcon(getClass().getResource(GUIProperties.ADD_BUTTON_FILE)));
				};
				
				public void mouseReleased(MouseEvent e){
					
				};
				
				public void mouseExited(MouseEvent e){					
					addSuperClassLabel.setIcon(new ImageIcon(getClass().getResource(GUIProperties.ADD_BUTTON_PRESSED_FILE)));
				};
			});
		}
		return addSuperClassLabel;
	}

	private JPanel getJPanel4() {
		if (jPanel4 == null) {
			jPanel4 = new JPanel();
			//jPanel4.setBackground(new Color(238, 238, 238));
			jPanel4.setBorder(new LineBorder(Color.lightGray, 1, false));
			jPanel4.setLayout(new BorderLayout());
			jPanel4.add(getJLabel3(), BorderLayout.CENTER);
			jPanel4.add(getJPanel5(), BorderLayout.EAST);
		}
		return jPanel4;
	}

	private JLabel getJLabel3() {
		if (jLabel3 == null) {
			jLabel3 = new JLabel();
			jLabel3.setFont(new Font("Dialog", Font.BOLD, 13));
			//jLabel3.setText("SuperClass");
			jLabel3.setText(IDRSResourceBundle.res.getString("super.class"));
		}
		return jLabel3;
	}

	private JPanel getDescriptionPanel() {
		if (descriptionPanel == null) {
			descriptionPanel = new JPanel();
			descriptionPanel.setBorder(BorderFactory.createTitledBorder(null, IDRSResourceBundle.res.getString("description"), TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, new Font(
					"Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			descriptionPanel.setLayout(new GroupLayout());
			descriptionPanel.add(getJSplitPane0(), new Constraints(new Bilateral(0, 0, 101), new Bilateral(0, 0, 64)));
		}
		return descriptionPanel;
	}

	private JSplitPane getJSplitPane0() {
		if (jSplitPane0 == null) {
			jSplitPane0 = new JSplitPane();
			jSplitPane0.setDividerLocation(240);
			jSplitPane0.setOrientation(JSplitPane.VERTICAL_SPLIT);
			jSplitPane0.setTopComponent(getJPanel2());
			jSplitPane0.setBottomComponent(getJPanel3());
		}
		return jSplitPane0;
	}

	private JPanel getJPanel3() {
		if (jPanel3 == null) {
			jPanel3 = new JPanel();
			jPanel3.setLayout(new BorderLayout());
			jPanel3.add(getJPanel6(), BorderLayout.NORTH);
			//jPanel3.add(getJScrollPane2(), BorderLayout.CENTER)
			jPanel3.add(getContentDisjontClassPanel(), BorderLayout.CENTER);
		}
		return jPanel3;
	}
	
	private JPanel getContentDisjontClassPanel() {
		if (contentDisjontClassPanel == null) {
			contentDisjontClassPanel = new JPanel();
			contentDisjontClassPanel.setBorder(new LineBorder(Color.BLACK, 1, false));
			contentDisjontClassPanel.setLayout(new GroupLayout());
			contentDisjontClassPanel.setBackground(Color.WHITE);
		}
		
		return contentDisjontClassPanel;
	}

	private JPanel getJPanel2() {
		if (jPanel2 == null) {
			jPanel2 = new JPanel();
			jPanel2.setLayout(new BorderLayout());
			jPanel2.add(getJPanel4(), BorderLayout.NORTH);
			jPanel2.add(getContentSuperClassPanel(), BorderLayout.CENTER);
		}
		return jPanel2;
	}
	
	private JPanel getContentSuperClassPanel() {
		if (contentSuperClassPanel == null) {
			contentSuperClassPanel = new JPanel();
			contentSuperClassPanel.setBackground(Color.WHITE);
			contentSuperClassPanel.setBorder(new LineBorder(Color.BLACK, 1, false));
			contentSuperClassPanel.setLayout(new GroupLayout());
		}
		return contentSuperClassPanel;
	}

	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			jPanel1 = new JPanel();
			jPanel1.add(getJLabel1());
			jPanel1.add(getAddClassLabel());
			jPanel1.add(getDeleteClassLabel());
		}
		return jPanel1;
	}

	private JLabel getAddClassLabel() {
		if (addClassLabel == null) {
			addClassLabel = new JLabel();
			addClassLabel.setIcon(new ImageIcon(getClass().getResource(GUIProperties.ADD_BUTTON_PRESSED_FILE)));
			addClassLabel.addMouseListener(new MouseListener(){
				public void mouseClicked(MouseEvent e){
					DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) classTree.getLastSelectedPathComponent();
					if (selectedNode == null) {
						JOptionPane.showMessageDialog(null, "You must choose a class");
						return;
					}
					ClassPanelAction.nameClass = (String) selectedNode.getUserObject();
					String nameSubClass = JOptionPane.showInputDialog("Please enter SubClass name");
					
					//Add a owlMyClass into OWLModel.
					ClassPanelAction.addOWLMyClassIntoArrayList(ClassPanelAction.nameClass, nameSubClass);
					//Refresh.
					ClassPanelAction.convertClassesIntoTree(classTree);
					contentDisjontClassPanel.removeAll();
					contentSuperClassPanel.removeAll();
				};
				
				public void mousePressed(MouseEvent e){
					
				};
				
				public void mouseEntered(MouseEvent e){
					addClassLabel.setIcon(new ImageIcon(getClass().getResource(GUIProperties.ADD_BUTTON_FILE)));
				};
				
				public void mouseReleased(MouseEvent e){
					
				};
				
				public void mouseExited(MouseEvent e){
					
					addClassLabel.setIcon(new ImageIcon(getClass().getResource(GUIProperties.ADD_BUTTON_PRESSED_FILE)));
				};
			});
		}
		return addClassLabel;
	}

	private JLabel getJLabel1() {
		if (jLabel1 == null) {
			jLabel1 = new JLabel();
			//jLabel1.setText("jLabel1");
			
		}
		return jLabel1;
	}

	private JPanel getModelBrowerPanel() {
		if (modelBrowerPanel == null) {
			modelBrowerPanel = new JPanel();
			modelBrowerPanel.setBorder(BorderFactory.createTitledBorder(null, IDRSResourceBundle.res.getString("brower.model"), TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, new Font(
					"Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			modelBrowerPanel.setLayout(new BorderLayout());
			modelBrowerPanel.add(getJPanel0(), BorderLayout.NORTH);
			modelBrowerPanel.add(getJScrollPane0(), BorderLayout.CENTER);
		}
		return modelBrowerPanel;
	}

	private JScrollPane getJScrollPane0() {
		if (jScrollPane0 == null) {
			jScrollPane0 = new JScrollPane();
			jScrollPane0.setViewportView(getClassTree());
		}
		return jScrollPane0;
	}

	private JTree getClassTree() {
		if (classTree == null) {
			classTree = new JTree();
			classTree.setBorder(new LineBorder(Color.lightGray, 1, false));
			classTree.setFont(new Font("Dialog", Font.BOLD, 13));
//			classTree.setForeground(new Color(255, 128, 0));
			DefaultTreeModel treeModel = null;
			{
				DefaultMutableTreeNode node0 = new DefaultMutableTreeNode("Thing");
				treeModel = new DefaultTreeModel(node0);
			}			
			classTree.setModel(treeModel);
			classTree.setEditable(false);
			classTree.addTreeSelectionListener(new TreeSelectionListener() {

				@Override
				public void valueChanged(TreeSelectionEvent arg0) {
					DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) classTree.getLastSelectedPathComponent();
					
					//Neu rong hoac khong phai la nut con thi thoat.
					if (selectedNode == null)
						return;
					
					reset();
					ClassPanelAction.nameClass = (String) selectedNode.getUserObject();
					//Load du lieu ve SuperClass.
					loadSuperClassesListIntoPanel(ClassPanelAction.nameClass);
					
					//Load du lieu ve DisjontClass.
					loadDisjontClassesListIntoPanel(ClassPanelAction.nameClass);
				}
				
			});
		}
		return classTree;
	}

	private JPanel getJPanel0() {
		if (jPanel0 == null) {
			jPanel0 = new JPanel();
			//jPanel0.setBackground(new Color(238, 238, 238));
			jPanel0.setBorder(new LineBorder(Color.lightGray, 1, false));
			jPanel0.setLayout(new BorderLayout());
			jPanel0.add(getJLabel0(), BorderLayout.CENTER);
			jPanel0.add(getJPanel1(), BorderLayout.EAST);
		}
		return jPanel0;
	}

	private JLabel getJLabel0() {
		if (jLabel0 == null) {
			jLabel0 = new JLabel();
			jLabel0.setFont(new Font("Dialog", Font.BOLD, 13));
			//jLabel0.setText("Class model");
			jLabel0.setText(IDRSResourceBundle.res.getString("class.model"));
		}
		return jLabel0;
	}

	private JSplitPane getClassJSplitPane() {
		if (classJSplitPane == null) {
			classJSplitPane = new JSplitPane();
			classJSplitPane.setDividerLocation(312);
			classJSplitPane.setLeftComponent(getModelBrowerPanel());
			classJSplitPane.setRightComponent(getDescriptionPanel());
		}
		return classJSplitPane;
	}
	
	/**
	 * 
	 * 
	 * @author Huynh Minh Duc
	 */
	public void updateTextOfComponents(){
		jLabel5.setText(IDRSResourceBundle.res.getString("disjoint.class"));
		jLabel3.setText(IDRSResourceBundle.res.getString("super.class"));
		jLabel0.setText(IDRSResourceBundle.res.getString("class.model"));
		
		descriptionPanel.setBorder(BorderFactory.createTitledBorder(null, IDRSResourceBundle.res.getString("description"), TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, new Font(
				"Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
		modelBrowerPanel.setBorder(BorderFactory.createTitledBorder(null, IDRSResourceBundle.res.getString("brower.model"), TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, new Font(
				"Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
	}
	
	public static void loadSuperClassesListIntoPanel(String nameSelectedClass) {
		//Delete all last data.
		if (ClassPanelAction.checkedSuperClassesArrayList != null) {
			ClassPanelAction.checkedSuperClassesArrayList.clear();
			ClassPanelAction.unCheckedSuperClassesArrayList.clear();
			contentSuperClassPanel.removeAll();
		}
		
		//Set info into checkedClassArrayList.
		ClassPanelAction.checkedSuperClassesArrayList = ClassPanelAction.getNameSuperClassesByClass(nameSelectedClass);
		for (int i = 0; i < ClassPanelAction.checkedSuperClassesArrayList.size(); i++) {
			LinePanel linePanel = new LinePanel(
					ClassPanelAction.checkedSuperClassesArrayList.get(i).toString(), 
					GUIProperties.CLASSES_PANEL, 
					GUIProperties.SUPERCLASSES_CLASSES_PANEL);
			linePanel.setVisible(true);
			contentSuperClassPanel.add(linePanel, linePanel.getPosition(i));
		}
		
		//Set info into unCheckedClassArrayList.
		for (int i = 0; i < ClassPanelAction.classesArrayList.size(); i++) {
			Boolean isChecked = false;
			for (int j = 0; j < ClassPanelAction.checkedSuperClassesArrayList.size(); j++) {
			if (ClassPanelAction.classesArrayList.get(i).getName().equals(ClassPanelAction.checkedSuperClassesArrayList.get(j))) {
					isChecked = true;
				}
				
				if (isChecked == false) {
					ClassPanelAction.unCheckedSuperClassesArrayList.add(ClassPanelAction.classesArrayList.get(i).getName());
				}
			}
		}
		IDRSApplication.idrsJFrame.repaint();
	}
	
	public static void loadDisjontClassesListIntoPanel(String nameSelectedClass) {
		//Delete all last data.
		if (ClassPanelAction.checkedDisjontClassesArrayList != null) {
			ClassPanelAction.checkedDisjontClassesArrayList.clear();
			ClassPanelAction.unCheckedDisjontClassesArrayList.clear();
			contentDisjontClassPanel.removeAll();
		}
		
		//Set info into checkedClassArrayList.
		ClassPanelAction.checkedDisjontClassesArrayList = ClassPanelAction.getNameDisjontClassesByClass(nameSelectedClass);
		for (int i = 0; i < ClassPanelAction.checkedDisjontClassesArrayList.size(); i++) {
			LinePanel linePanel = new LinePanel(
					ClassPanelAction.checkedDisjontClassesArrayList.get(i).toString(), 
					GUIProperties.CLASSES_PANEL, 
					GUIProperties.DISJONTCLASSES_CLASSES_PANEL);
			linePanel.setVisible(true);
			contentDisjontClassPanel.add(linePanel, linePanel.getPosition(i));
		}
		
		//Set info into unCheckedClassArrayList.
		for (int i = 0; i < ClassPanelAction.classesArrayList.size(); i++) {
			Boolean isChecked = false;
			for (int j = 0; j < ClassPanelAction.checkedDisjontClassesArrayList.size(); j++) {
			if (ClassPanelAction.classesArrayList.get(i).getName().equals(ClassPanelAction.checkedDisjontClassesArrayList.get(j))) {
					isChecked = true;
				}
				
				if (isChecked == false) {
					ClassPanelAction.unCheckedDisjontClassesArrayList.add(ClassPanelAction.classesArrayList.get(i).getName());
				}
			}
		}
		IDRSApplication.idrsJFrame.repaint();
	}
	
	public static void reset() {
		contentSuperClassPanel.removeAll();
//		contentRelationshipsPanel.removeAll();
		IDRSApplication.idrsJFrame.repaint();
		//if (ClassPanelAction.checkedClassesArrayList != null && 
		//		ClassPanelAction.unCheckedClassesArrayList != null) {
			ClassPanelAction.checkedDisjontClassesArrayList.clear();
			ClassPanelAction.checkedSuperClassesArrayList.clear();
			ClassPanelAction.unCheckedDisjontClassesArrayList.clear();
			ClassPanelAction.unCheckedSuperClassesArrayList.clear();
		//}
		ClassPanelAction.maxRank = 0;
		ClassPanelAction.nameClass = "";
		ClassPanelAction.namespacePrefix = "";
	}
}

