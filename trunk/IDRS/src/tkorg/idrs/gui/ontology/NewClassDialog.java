package tkorg.idrs.gui.ontology;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

import tkorg.idrs.action.ontology.ClassPanelAction;
import tkorg.idrs.gui.main.IDRSApplication;
import tkorg.idrs.gui.main.IDRSResourceBundle;

//VS4E -- DO NOT REMOVE THIS LINE!
public class NewClassDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private int locationX;
	private int locationY;
	private int width = 300;
	private int height = 130;
	
	private JFrame idrsJFrame = null;
	private JLabel jLabel0;
	private JButton okNameClass;
	private JButton cancelNameClass;
	private JTextField nameClassTextField;
	
	private String nameNewClass;
	private String nameSuperClass;
	
	public NewClassDialog() {
		initComponents();
	}

	public NewClassDialog(JFrame parent, String nameSuperClass) {
		super(parent, true);
		
		idrsJFrame = parent;
		locationX = idrsJFrame.getX() + (idrsJFrame.getWidth() - width)/2;
		locationY = idrsJFrame.getY() + (idrsJFrame.getHeight() - height)/2;
		
		this.nameSuperClass = nameSuperClass;
		
		initComponents();
	}

	public NewClassDialog(Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();
	}

	public NewClassDialog(Frame parent, String title) {
		super(parent, title);
		initComponents();
	}

	public NewClassDialog(Frame parent, String title, boolean modal) {
		super(parent, title, modal);
		initComponents();
	}

	public NewClassDialog(Frame parent, String title, boolean modal,
			GraphicsConfiguration arg) {
		super(parent, title, modal, arg);
		initComponents();
	}

	public NewClassDialog(Dialog parent) {
		super(parent);
		initComponents();
	}

	public NewClassDialog(Dialog parent, boolean modal) {
		super(parent, modal);
		initComponents();
	}

	public NewClassDialog(Dialog parent, String title) {
		super(parent, title);
		initComponents();
	}

	public NewClassDialog(Dialog parent, String title, boolean modal) {
		super(parent, title, modal);
		initComponents();
	}

	public NewClassDialog(Dialog parent, String title, boolean modal,
			GraphicsConfiguration arg) {
		super(parent, title, modal, arg);
		initComponents();
	}

	public NewClassDialog(Window parent) {
		super(parent);
		initComponents();
	}

	public NewClassDialog(Window parent, ModalityType modalityType) {
		super(parent, modalityType);
		initComponents();
	}

	public NewClassDialog(Window parent, String title) {
		super(parent, title);
		initComponents();
	}

	public NewClassDialog(Window parent, String title, ModalityType modalityType) {
		super(parent, title, modalityType);
		initComponents();
	}

	public NewClassDialog(Window parent, String title,
			ModalityType modalityType, GraphicsConfiguration arg) {
		super(parent, title, modalityType, arg);
		initComponents();
	}

	private void initComponents() {
		setFont(new Font("Dialog", Font.PLAIN, 12));
		setBackground(Color.white);
		setForeground(Color.black);
		setLayout(new GroupLayout());
		add(getJLabel0(), new Constraints(new Leading(25, 10, 10), new Leading(12, 12, 12)));
		add(getCancelNameClass(), new Constraints(new Leading(146, 12, 12), new Leading(74, 12, 12)));
		add(getNameClassTextField(), new Constraints(new Leading(25, 250, 10, 10), new Leading(40, 12, 12)));
		add(getOKNameClass(), new Constraints(new Leading(83, 12, 12), new Leading(74, 12, 12)));
		setSize(300, 145);
		setLocation(locationX, locationY);
		setTitle(IDRSResourceBundle.res.getString("create.new.class"));
	}

	private JTextField getNameClassTextField() {
		if (nameClassTextField == null) {
			nameClassTextField = new JTextField();
			nameClassTextField.addFocusListener(new FocusListener(){
				public void focusLost(FocusEvent e){
					nameNewClass = nameClassTextField.getText();
				}
				public void focusGained(FocusEvent e){
					
				}
			});
		}
		return nameClassTextField;
	}

	private JButton getCancelNameClass() {
		if (cancelNameClass == null) {
			cancelNameClass = new JButton();
			cancelNameClass.setText(IDRSResourceBundle.res.getString("cancel"));
			cancelNameClass.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					if(e.getSource() == cancelNameClass){
						setVisible(false);
					}
				}
			});
		}
		return cancelNameClass;
	}

	private JButton getOKNameClass() {
		if (okNameClass == null) {
			okNameClass = new JButton();
			okNameClass.setText(IDRSResourceBundle.res.getString("ok"));
			okNameClass.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					if(e.getSource() == okNameClass){
						if(nameClassTextField.getText().equals("")){
							JOptionPane.showMessageDialog(IDRSApplication.idrsJFrame, IDRSResourceBundle.res.getString("please.enter.name.class"));
						}
						else{
							ClassPanelAction action  = new ClassPanelAction();
							action.createNewClass(nameSuperClass, nameNewClass);
							
							DefaultTreeModel model = (DefaultTreeModel)ClassPanel.classTree.getModel();
							DefaultMutableTreeNode superNode = (DefaultMutableTreeNode)ClassPanel.classTree.getLastSelectedPathComponent();
													
							DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(nameNewClass);
							model.insertNodeInto(newNode, superNode, superNode.getChildCount());
							
							TreeNode[] nodes = model.getPathToRoot(newNode);
							TreePath path = new TreePath(nodes);
							ClassPanel.classTree.scrollPathToVisible(path);
							
							setVisible(false);
						}
					}
				}
			});
		}
		return okNameClass;
	}

	private JLabel getJLabel0() {
		if (jLabel0 == null) {
			jLabel0 = new JLabel();
			jLabel0.setText(IDRSResourceBundle.res.getString("please.enter.name.class"));
			//jLabel0.setText(IDRSResourceBundle.res.getString(arg0));
		}
		return jLabel0;
	}

}
