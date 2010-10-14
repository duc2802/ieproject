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
import java.util.ArrayList;

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

import tkorg.idrs.action.ontology.RelationshipsAction;
import tkorg.idrs.gui.main.IDRSResourceBundle;

//VS4E -- DO NOT REMOVE THIS LINE!
public class RelationshipsDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private JFrame mainFrame;
	private JDialog mainDialog;
	private JList relationshipsJList;
	private JScrollPane jScrollPane0;
	private JButton okJButton;
	private JButton cancelJButton;
	
	private int xLocation;
	private int yLocation;
	private int width = 320;
	private int heigh = 270;
	
	private int index;

	public RelationshipsDialog() {
		initComponents();
	}

	public RelationshipsDialog(JFrame frame, int index) {
		super(frame, true);
		mainFrame = frame;
		xLocation = mainFrame.getX() + (mainFrame.getWidth() - width)/2;
		yLocation = mainFrame.getY() + (mainFrame.getHeight() - heigh)/2;
		initComponents();
		this.index = index;
	}
	
	public RelationshipsDialog(Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();
	}

	public RelationshipsDialog(Frame parent, String title) {
		super(parent, title);
		initComponents();
	}

	public RelationshipsDialog(Frame parent, String title, boolean modal) {
		super(parent, title, modal);
		initComponents();
	}

	public RelationshipsDialog(Frame parent, String title, boolean modal,
			GraphicsConfiguration arg) {
		super(parent, title, modal, arg);
		initComponents();
	}

	public RelationshipsDialog(Dialog parent) {
		super(parent);
		initComponents();
	}

	public RelationshipsDialog(Dialog parent, boolean modal) {
		super(parent, modal);
		initComponents();
	}

	public RelationshipsDialog(Dialog parent, String title) {
		super(parent, title);
		initComponents();
	}

	public RelationshipsDialog(Dialog parent, String title, boolean modal) {
		super(parent, title, modal);
		initComponents();
	}

	public RelationshipsDialog(Dialog parent, String title, boolean modal,
			GraphicsConfiguration arg) {
		super(parent, title, modal, arg);
		initComponents();
	}

	public RelationshipsDialog(Window parent) {
		super(parent);
		initComponents();
	}

	public RelationshipsDialog(Window parent, ModalityType modalityType) {
		super(parent, modalityType);
		initComponents();
	}

	public RelationshipsDialog(Window parent, String title) {
		super(parent, title);
		initComponents();
	}

	public RelationshipsDialog(Window parent, String title,
			ModalityType modalityType) {
		super(parent, title, modalityType);
		initComponents();
	}

	public RelationshipsDialog(Window parent, String title,
			ModalityType modalityType, GraphicsConfiguration arg) {
		super(parent, title, modalityType, arg);
		initComponents();
	}

	private void initComponents() {
		mainDialog = this;
		setTitle(IDRSResourceBundle.res.getString("relationships"));
		setFont(new Font("Dialog", Font.PLAIN, 12));
		setBackground(Color.white);
		setForeground(Color.black);
		setLayout(new GroupLayout());
		add(getJScrollPane0(), new Constraints(new Bilateral(11, 12, 22), new Leading(12, 170, 12, 12)));
		add(getOkJButton(), new Constraints(new Leading(94, 12, 12), new Leading(194, 12, 12)));
		add(getCancelJButton(), new Constraints(new Leading(163, 12, 12), new Leading(194, 12, 12)));
		setLocation(xLocation, yLocation);
		setSize(width, heigh);
	}

	private JButton getCancelJButton() {
		if (cancelJButton == null) {
			cancelJButton = new JButton();
			cancelJButton.setText(IDRSResourceBundle.res.getString("cancel"));
			cancelJButton.setMnemonic('C');
			cancelJButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					mainDialog.dispose();
				}
				
			});
		}
		return cancelJButton;
	}

	private JButton getOkJButton() {
		if (okJButton == null) {
			okJButton = new JButton();
			okJButton.setText(IDRSResourceBundle.res.getString("ok"));
			okJButton.setMnemonic('O');
			okJButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {					
					RelationshipsAction.addInverseProperties(index, getRelationshipsJList().getAnchorSelectionIndex());
					RelationshipsPanel.refresh();
					mainDialog.dispose();
										
				}
				
			});
		}
		return okJButton;
	}

	private JScrollPane getJScrollPane0() {
		if (jScrollPane0 == null) {
			jScrollPane0 = new JScrollPane();
			jScrollPane0.setViewportView(getRelationshipsJList());
		}
		return jScrollPane0;
	}

	public JList getRelationshipsJList() {
		DefaultListModel model = new DefaultListModel();
		if (relationshipsJList == null) {
			relationshipsJList = new JList();
			ArrayList<String> relations = RelationshipsAction.viewRelationships();
			for (int i = 0; i < relations.size(); i ++) {
				model.addElement(relations.get(i));		
			}
		}
		relationshipsJList.setModel(model);
		return relationshipsJList;
	}

}
