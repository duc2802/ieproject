/**
 * 
 */
package tkorg.idrs.gui.ontology;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTree;
import javax.swing.border.LineBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;
import org.dyno.visual.swing.layouts.Trailing;

import tkorg.idrs.action.ontology.ClassPanelAction;
import tkorg.idrs.core.ontology.OWLMyClass;
import tkorg.idrs.gui.main.IDRSResourceBundle;

/**
 * 
 * @author Tran Cong Danh.
 *
 */

//VS4E -- DO NOT REMOVE THIS LINE!
public class AddDisjontClass extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel hierarchiClassPanel;
	
	private JTabbedPane jTabbedPane0;
	private JButton okButton;
	private JButton cancelButton;
	private JTree addDisjontClassTree;
	private JScrollPane jScrollPane0;
	
	private int locationX;
	private int locationY;
	
	private int width = 500;
	private int height = 440;
	
	private String selectedDisjontClass;

	public AddDisjontClass() {
		initComponents();
	}

	public AddDisjontClass(Frame parent) {
		super(parent);
		
		locationX = parent.getX() + (parent.getWidth() - width)/2;
		locationY = parent.getY() + (parent.getHeight() - height)/2;
		
		initComponents();
	}

	public AddDisjontClass(Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();
	}

	public AddDisjontClass(Frame parent, String title) {
		super(parent, title);
		initComponents();
	}

	public AddDisjontClass(Frame parent, String title, boolean modal) {
		super(parent, title, modal);
		initComponents();
	}

	public AddDisjontClass(Frame parent, String title, boolean modal,
			GraphicsConfiguration arg) {
		super(parent, title, modal, arg);
		initComponents();
	}

	public AddDisjontClass(Dialog parent) {
		super(parent);
		initComponents();
	}

	public AddDisjontClass(Dialog parent, boolean modal) {
		super(parent, modal);
		initComponents();
	}

	public AddDisjontClass(Dialog parent, String title) {
		super(parent, title);
		initComponents();
	}

	public AddDisjontClass(Dialog parent, String title, boolean modal) {
		super(parent, title, modal);
		initComponents();
	}

	public AddDisjontClass(Dialog parent, String title, boolean modal,
			GraphicsConfiguration arg) {
		super(parent, title, modal, arg);
		initComponents();
	}

	public AddDisjontClass(Window parent) {
		super(parent);
		initComponents();
	}

	public AddDisjontClass(Window parent, ModalityType modalityType) {
		super(parent, modalityType);
		initComponents();
	}

	public AddDisjontClass(Window parent, String title) {
		super(parent, title);
		initComponents();
	}

	public AddDisjontClass(Window parent, String title,
			ModalityType modalityType) {
		super(parent, title, modalityType);
		initComponents();
	}

	public AddDisjontClass(Window parent, String title,
			ModalityType modalityType, GraphicsConfiguration arg) {
		super(parent, title, modalityType, arg);
		initComponents();
	}

	private void initComponents() {
		setFont(new Font("Dialog", Font.PLAIN, 12));
		setBackground(Color.white);
		setForeground(Color.black);
		setLayout(new GroupLayout());
		add(getJTabbedPane0(), new Constraints(new Bilateral(0, 0, 5), new Leading(0, 371, 10, 10)));
		add(getOKButton(), new Constraints(new Leading(160, 10, 10), new Leading(377, 12, 12)));
		add(getCancelButton(), new Constraints(new Leading(256, 10, 10), new Trailing(12, 78, 383)));
		setSize(width, height);
		setLocation(locationX, locationY);
		setTitle(IDRSResourceBundle.res.getString("hierarchy.class"));
		
		updateTextOfComponent();
	}
	
	private JPanel getHierarchiClassPanel() {
		if (hierarchiClassPanel == null) {
			hierarchiClassPanel = new JPanel();
			hierarchiClassPanel.setLayout(new GroupLayout());
			hierarchiClassPanel.add(getJScrollPane0(), new Constraints(new Bilateral(-1, 0, 22), new Bilateral(1, 0, 22)));
			
		}
		return hierarchiClassPanel;
	}

	private JScrollPane getJScrollPane0() {
		if (jScrollPane0 == null) {
			jScrollPane0 = new JScrollPane();
			jScrollPane0.setViewportView(getAddDisjontClassTree());
		}
		return jScrollPane0;
	}

	private JTree getAddDisjontClassTree() {
		if (addDisjontClassTree == null) {
			addDisjontClassTree = new JTree();		
			
			addDisjontClassTree.setBorder(new LineBorder(Color.black, 1, false));
			addDisjontClassTree.setFont(new Font("Tahoma", Font.BOLD, 16));
			DefaultMutableTreeNode nodeThing = new DefaultMutableTreeNode("Thing");
			DefaultTreeModel treeModel = new DefaultTreeModel(nodeThing);
			addDisjontClassTree.setModel(treeModel);
			
			ClassPanelAction.convertClassesIntoTree(addDisjontClassTree);
			
			addDisjontClassTree.addTreeSelectionListener(new TreeSelectionListener(){
				
				public void valueChanged(TreeSelectionEvent e) {
					DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) addDisjontClassTree.getLastSelectedPathComponent();
					selectedDisjontClass = selectedNode.toString();
				}
			});
		}
		return addDisjontClassTree;
	}

	private JButton getCancelButton() {
		if (cancelButton == null) {
			cancelButton = new JButton();
			cancelButton.setText(IDRSResourceBundle.res.getString("cancel"));
			cancelButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					if(arg0.getSource() == cancelButton){
						dispose();
					}
				}
			});
		}
		return cancelButton;
	}

	public JButton getOKButton() {
		if (okButton == null) {
			okButton = new JButton();
			okButton.setText(IDRSResourceBundle.res.getString("ok"));
			okButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					if(arg0.getSource() == okButton) {
						for (int i = 0; i < ClassPanelAction.classesArrayList.size(); i++) {
							if (ClassPanelAction.nameClass.equals(
									ClassPanelAction.classesArrayList.get(i).getName())) {
								for (int j = 0; j < ClassPanelAction.classesArrayList.get(i).getOwlDisjontClassesArrayList().size(); j++) {
									String temp = ClassPanelAction.convertName(
											ClassPanelAction.classesArrayList.get(i).getOwlDisjontClassesArrayList().get(j).getBrowserText());
									if (temp.equals(selectedDisjontClass)) {
										JOptionPane.showMessageDialog(null, "It is already in this");
										return;
									}
								}
								
								OWLMyClass myClass = ClassPanelAction.getMyClassByName(selectedDisjontClass);
								ClassPanelAction.classesArrayList.get(i).addSuperClassIntoArrayList(myClass.getOwlNamedClass());
								ClassPanelAction.classesArrayList.get(i).addDisjontClassIntoArrayList(myClass.getOwlNamedClass());
								ClassPanelAction.classesArrayList.get(i).getOwlNamedClass().addDisjointClass(myClass.getOwlNamedClass());
								ClassPanel.loadDisjontClassesListIntoPanel(ClassPanelAction.nameClass);
							}
						}
						dispose();
					}			
				}
				
			});
		}
		return okButton;
	}

	private JTabbedPane getJTabbedPane0() {
		if (jTabbedPane0 == null) {
			jTabbedPane0 = new JTabbedPane();
			jTabbedPane0.addTab(IDRSResourceBundle.res.getString("hierarchy.class"), getHierarchiClassPanel());
			//jTabbedPane0.addTab("jPanel1", getJPanel1());
			//jTabbedPane0.addTab("jPanel2", getJPanel2());
		}
		return jTabbedPane0;
	}
	
	/**
	 * 
	 * @return
	 * @author Tran Cong Danh
	 */
	public String getSelectedClass(){
		return selectedDisjontClass;
	}
	
	/**
	 * 
	 * 
	 * @author Tran Cong Danh
	 */
	public void updateTextOfComponent(){
		cancelButton.setText(IDRSResourceBundle.res.getString("cancel"));
		okButton.setText(IDRSResourceBundle.res.getString("ok"));
		
		jTabbedPane0.setTitleAt(0, IDRSResourceBundle.res.getString("hierarchy.class"));
	}

}
