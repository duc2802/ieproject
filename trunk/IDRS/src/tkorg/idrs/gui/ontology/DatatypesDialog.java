package tkorg.idrs.gui.ontology;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.Window;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

import tkorg.idrs.gui.main.IDRSResourceBundle;

//VS4E -- DO NOT REMOVE THIS LINE!
public class DatatypesDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private JList datatypesJList;
	private JScrollPane jScrollPane0;
	private JButton cancelJButton;
	private JButton okJButton;
	private JFrame propertiesJFrame;
	private int xLocation;
	private int yLocation;
	private int width = 320;
	private int height = 320;
	public DatatypesDialog() {
		initComponents();
	}

	public DatatypesDialog(Frame parent) {
		super(parent);
		initComponents();
	}
	public DatatypesDialog(JFrame parent) {
		super(parent,true);
		propertiesJFrame= parent;
		xLocation = propertiesJFrame.getX() + (propertiesJFrame.getWidth()-width)/2;
		yLocation = propertiesJFrame.getY() + (propertiesJFrame.getHeight()-height)/2;
		initComponents();
	}
	public DatatypesDialog(Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();
	}

	public DatatypesDialog(Frame parent, String title) {
		super(parent, title);
		initComponents();
	}

	public DatatypesDialog(Frame parent, String title, boolean modal) {
		super(parent, title, modal);
		initComponents();
	}

	public DatatypesDialog(Frame parent, String title, boolean modal,
			GraphicsConfiguration arg) {
		super(parent, title, modal, arg);
		initComponents();
	}

	public DatatypesDialog(Dialog parent) {
		super(parent);
		initComponents();
	}

	public DatatypesDialog(Dialog parent, boolean modal) {
		super(parent, modal);
		initComponents();
	}

	public DatatypesDialog(Dialog parent, String title) {
		super(parent, title);
		initComponents();
	}

	public DatatypesDialog(Dialog parent, String title, boolean modal) {
		super(parent, title, modal);
		initComponents();
	}

	public DatatypesDialog(Dialog parent, String title, boolean modal,
			GraphicsConfiguration arg) {
		super(parent, title, modal, arg);
		initComponents();
	}

	public DatatypesDialog(Window parent) {
		super(parent);
		initComponents();
	}

	public DatatypesDialog(Window parent, ModalityType modalityType) {
		super(parent, modalityType);
		initComponents();
	}

	public DatatypesDialog(Window parent, String title) {
		super(parent, title);
		initComponents();
	}

	public DatatypesDialog(Window parent, String title,
			ModalityType modalityType) {
		super(parent, title, modalityType);
		initComponents();
	}

	public DatatypesDialog(Window parent, String title,
			ModalityType modalityType, GraphicsConfiguration arg) {
		super(parent, title, modalityType, arg);
		initComponents();
	}

	private void initComponents() {
		setTitle(IDRSResourceBundle.res.getString("datatypes"));
		setFont(new Font("Dialog", Font.PLAIN, 12));
		setBackground(Color.white);
		setForeground(Color.black);
		setLayout(new GroupLayout());
		add(getJScrollPane0(), new Constraints(new Bilateral(11, 12, 22), new Leading(11, 227, 10, 10)));
		add(getOkJButton(), new Constraints(new Leading(61, 64, 10, 10), new Leading(256, 12, 12)));
		add(getCancelJButton(), new Constraints(new Leading(173, 10, 10), new Leading(256, 12, 12)));
		setSize(width, height);
		setLocation(xLocation, yLocation);
	}

	private JButton getOkJButton() {
		if (okJButton == null) {
			okJButton = new JButton();
			okJButton.setText(IDRSResourceBundle.res.getString("ok"));
		}
		return okJButton;
	}

	private JButton getCancelJButton() {
		if (cancelJButton == null) {
			cancelJButton = new JButton();
			cancelJButton.setText(IDRSResourceBundle.res.getString("cancel"));
		}
		return cancelJButton;
	}

	private JScrollPane getJScrollPane0() {
		if (jScrollPane0 == null) {
			jScrollPane0 = new JScrollPane();
			jScrollPane0.setViewportView(getDatatypesJList());
		}
		return jScrollPane0;
	}

	private JList getDatatypesJList() {
		if (datatypesJList == null) {
			datatypesJList = new JList();
			DefaultListModel listModel = new DefaultListModel();
			listModel.addElement("item0");
			listModel.addElement("item1");
			listModel.addElement("item2");
			listModel.addElement("item3");
			datatypesJList.setModel(listModel);
		}
		return datatypesJList;
	}

}
