package tkorg.idrs.gui.ontology;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;

import javax.swing.JTree;


import javax.swing.border.LineBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;


import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

import tkorg.idrs.action.ontology.ClassPanelAction;
import tkorg.idrs.action.ontology.RelationshipsAction;
import tkorg.idrs.gui.main.IDRSResourceBundle;
import tkorg.idrs.properties.files.GUIProperties;

//VS4E -- DO NOT REMOVE THIS LINE!
public class ClassHierarchyDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private JButton cancelJButton;
	private JButton okJButton;
	private JFrame propertiesJFrame;
	private JTree classTree;
	private JDialog mainDialog;
	
	private int xLocation;
	private int yLocation;
	private int width = 320;
	private int height = 300;
	private int index = -1;
	private String flag = ""; //domain or range
	private static final String PREFERRED_LOOK_AND_FEEL = "javax.swing.plaf.metal.MetalLookAndFeel";
	
	public ClassHierarchyDialog() {
		initComponents();
	}

	public ClassHierarchyDialog(Frame parent) {
		super(parent);
		initComponents();
	}
	public ClassHierarchyDialog(JFrame parent) {
		super(parent,true);
		 propertiesJFrame= parent;
		xLocation = propertiesJFrame.getX() + (propertiesJFrame.getWidth()-width)/2;
		yLocation = propertiesJFrame.getY() + (propertiesJFrame.getHeight()-height)/2;
		initComponents();
	}
	public ClassHierarchyDialog(JFrame parent, String flag, int index) {
		super(parent,true);
		propertiesJFrame= parent;
		xLocation = propertiesJFrame.getX() + (propertiesJFrame.getWidth()-width)/2;
		yLocation = propertiesJFrame.getY() + (propertiesJFrame.getHeight()-height)/2;
		this.flag = flag;
		this.index = index;
		initComponents();
	}
	public ClassHierarchyDialog(Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();
	}

	public ClassHierarchyDialog(Frame parent, String title) {
		super(parent, title);
		initComponents();
	}

	public ClassHierarchyDialog(Frame parent, String title, boolean modal) {
		super(parent, title, modal);
		initComponents();
	}

	public ClassHierarchyDialog(Frame parent, String title, boolean modal,
			GraphicsConfiguration arg) {
		super(parent, title, modal, arg);
		initComponents();
	}

	public ClassHierarchyDialog(Dialog parent) {
		super(parent);
		initComponents();
	}

	public ClassHierarchyDialog(Dialog parent, boolean modal) {
		super(parent, modal);
		initComponents();
	}

	public ClassHierarchyDialog(Dialog parent, String title) {
		super(parent, title);
		initComponents();
	}

	public ClassHierarchyDialog(Dialog parent, String title, boolean modal) {
		super(parent, title, modal);
		initComponents();
	}

	public ClassHierarchyDialog(Dialog parent, String title, boolean modal,
			GraphicsConfiguration arg) {
		super(parent, title, modal, arg);
		initComponents();
	}

	public ClassHierarchyDialog(Window parent) {
		super(parent);
		initComponents();
	}

	public ClassHierarchyDialog(Window parent, ModalityType modalityType) {
		super(parent, modalityType);
		initComponents();
	}

	public ClassHierarchyDialog(Window parent, String title) {
		super(parent, title);
		initComponents();
	}

	public ClassHierarchyDialog(Window parent, String title, ModalityType modalityType) {
		super(parent, title, modalityType);
		initComponents();
	}

	public ClassHierarchyDialog(Window parent, String title,
			ModalityType modalityType, GraphicsConfiguration arg) {
		super(parent, title, modalityType, arg);
		initComponents();
	}

	private void initComponents() {
		mainDialog = this;
		setTitle(IDRSResourceBundle.res.getString("class.hierarchy"));
		setFont(new Font("Dialog", Font.PLAIN, 12));
		setBackground(Color.white);
		setResizable(false);
		setForeground(Color.black);
		setLayout(new GroupLayout());
		add(getClassTree(), new Constraints(new Leading(30, 265, 10, 10), new Leading(38, 144, 10, 10)));
		add(getCancelJButton(), new Constraints(new Leading(179, 10, 10), new Leading(220, 10, 10)));
		add(getOkJButton(), new Constraints(new Leading(80, 67, 10, 10), new Leading(220, 12, 12)));
		setSize(width, height);
		setLocation(xLocation, yLocation);
	}

	private JButton getOkJButton() {
		if (okJButton == null) {
			okJButton = new JButton();
			okJButton.setText(IDRSResourceBundle.res.getString("ok"));
			okJButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {		
					DefaultMutableTreeNode node = (DefaultMutableTreeNode)getClassTree().getAnchorSelectionPath().getLastPathComponent();
					if(flag == "domain") {
						RelationshipsAction.addDomain(index, node.toString());
					}
					else if(flag == "range") {
						RelationshipsAction.addRange(index, node.toString());						
					}
					RelationshipsPanel.updateData(index);
					RelationshipsPanel.refresh();
					mainDialog.dispose();
										
				}
				
			});
		}
		return okJButton;
	}

	private JButton getCancelJButton() {
		if (cancelJButton == null) {
			cancelJButton = new JButton();
			cancelJButton.setText(IDRSResourceBundle.res.getString("cancel"));
			cancelJButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					mainDialog.dispose();
				}
				
			});
		}
		return cancelJButton;
	}

	private JTree getClassTree() {
		if (classTree == null) {
			classTree = new JTree();			
			
			classTree.setBorder(new LineBorder(Color.black, 1, false));
			classTree.setFont(new Font("Tahoma", Font.BOLD, 16));
			
			DefaultTreeCellRenderer render = new DefaultTreeCellRenderer();
			render.setLeafIcon(new ImageIcon(getClass().getResource(GUIProperties.CLASS_ICON)));
			render.setOpenIcon(new ImageIcon(getClass().getResource(GUIProperties.CLASS_ICON)));
			render.setClosedIcon(new ImageIcon(getClass().getResource(GUIProperties.CLASS_ICON)));
			classTree.setCellRenderer(render);
			
			ClassPanelAction classPanelAction = new ClassPanelAction();
			
			classTree.setModel(classPanelAction.viewClassModel());
		}
		return classTree;
	}
}
