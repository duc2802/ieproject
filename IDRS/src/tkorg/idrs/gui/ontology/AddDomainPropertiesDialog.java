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
import java.util.Collection;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

import tkorg.idrs.action.ontology.PropertyPanelAction;

import edu.stanford.smi.protegex.owl.model.OWLNamedClass;
import edu.stanford.smi.protegex.owl.swrl.bridge.OWLDatatypeProperty;

//VS4E -- DO NOT REMOVE THIS LINE!
public class AddDomainPropertiesDialog extends JDialog {

	private static final long serialVersionUID = 1L;
//	private static final String PREFERRED_LOOK_AND_FEEL = "javax.swing.plaf.metal.MetalLookAndFeel";
	
	private JPanel propertiesListPanel;
	private JSplitPane addPropertySplitPane;
	private JPanel buttonsPanel;
	private JList propertiesJList;
	private JScrollPane propertiesListScrollPane;
	private JButton acceptButton;
	private JButton cancelButton;
	
	private ArrayList < String > uncheckedDomainsArrayList = null;
	private String nameProperty = "";
	private int xLocation = 0;
	private int yLocation = 0;
	private int width = 250;
	private int height = 435;

	public AddDomainPropertiesDialog() {
		initComponents();
	}

	public AddDomainPropertiesDialog(Frame parent,  String nameProperty, ArrayList < String > uncheckedDomainsArrayList) {
		super(parent);
		
		xLocation = parent.getX() + ((parent.getWidth() - width) / 2);
		yLocation = parent.getY() + ((parent.getHeight() - height) / 2);
		
		this.uncheckedDomainsArrayList = uncheckedDomainsArrayList;
		this.nameProperty = nameProperty;
		
		initComponents();
		
		updateTextOfComponents();
	}

	public AddDomainPropertiesDialog(Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();
	}

	public AddDomainPropertiesDialog(Frame parent, String title) {
		super(parent, title);
		initComponents();
	}

	public AddDomainPropertiesDialog(Frame parent, String title, boolean modal) {
		super(parent, title, modal);
		initComponents();
	}

	public AddDomainPropertiesDialog(Frame parent, String title, boolean modal,
			GraphicsConfiguration arg) {
		super(parent, title, modal, arg);
		initComponents();
	}

	public AddDomainPropertiesDialog(Dialog parent) {
		super(parent);
		initComponents();
	}

	public AddDomainPropertiesDialog(Dialog parent, boolean modal) {
		super(parent, modal);
		initComponents();
	}

	public AddDomainPropertiesDialog(Dialog parent, String title) {
		super(parent, title);
		initComponents();
	}

	public AddDomainPropertiesDialog(Dialog parent, String title, boolean modal) {
		super(parent, title, modal);
		initComponents();
	}

	public AddDomainPropertiesDialog(Dialog parent, String title,
			boolean modal, GraphicsConfiguration arg) {
		super(parent, title, modal, arg);
		initComponents();
	}

	public AddDomainPropertiesDialog(Window parent) {
		super(parent);
		initComponents();
	}

	public AddDomainPropertiesDialog(Window parent, ModalityType modalityType) {
		super(parent, modalityType);
		initComponents();
	}

	public AddDomainPropertiesDialog(Window parent, String title) {
		super(parent, title);
		initComponents();
	}

	public AddDomainPropertiesDialog(Window parent, String title,
			ModalityType modalityType) {
		super(parent, title, modalityType);
		initComponents();
	}

	public AddDomainPropertiesDialog(Window parent, String title,
			ModalityType modalityType, GraphicsConfiguration arg) {
		super(parent, title, modalityType, arg);
		initComponents();
	}

	private void initComponents() {
		setEnabled(true);
		setFont(new Font("Dialog", Font.PLAIN, 12));
		setBackground(Color.white);
		setForeground(Color.black);
		setLayout(new GroupLayout());
		add(getAddDomainSplitPane(), new Constraints(new Bilateral(0, 0, 202), new Bilateral(0, 0, 28)));
		setSize(width, height);
		setLocation(xLocation, yLocation);
		setTitle(nameProperty);
	}

	private JSplitPane getAddDomainSplitPane() {
		if (addPropertySplitPane == null) {
			addPropertySplitPane = new JSplitPane();
			addPropertySplitPane.setDividerLocation(340);
			addPropertySplitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
			addPropertySplitPane.setEnabled(false);
			addPropertySplitPane.setTopComponent(getDomainsListPanel());
			addPropertySplitPane.setBottomComponent(getButtonsPanel());
		}
		return addPropertySplitPane;
	}
	
	private JPanel getDomainsListPanel() {
		if (propertiesListPanel == null) {
			propertiesListPanel = new JPanel();
			propertiesListPanel.setBorder(BorderFactory.createTitledBorder("Choose a domain"));
			propertiesListPanel.setLayout(new GroupLayout());
			propertiesListPanel.add(getDomainsListScrollPane(), new Constraints(new Bilateral(1, 0, 22), new Bilateral(-1, 0, 22)));
		}
		return propertiesListPanel;
	}
	
	private JScrollPane getDomainsListScrollPane() {
		if (propertiesListScrollPane == null) {
			propertiesListScrollPane = new JScrollPane();
			propertiesListScrollPane.setViewportView(getDomainsJList());
		}
		return propertiesListScrollPane;
	}
	
	private JList getDomainsJList() {
		if (propertiesJList == null) {
			propertiesJList = new JList();
			propertiesJList.setFont(new Font("Dialog", Font.BOLD, 13));
			DefaultListModel listModel = new DefaultListModel();
			for (int i = 0; i < uncheckedDomainsArrayList.size(); i++) {
				listModel.addElement(uncheckedDomainsArrayList.get(i).toString());
			}
			propertiesJList.setModel(listModel);
		}
		return propertiesJList;
	}
	
	private JPanel getButtonsPanel() {
		if (buttonsPanel == null) {
			buttonsPanel = new JPanel();
			buttonsPanel.setLayout(new GroupLayout());
			buttonsPanel.add(getAcceptButton(), new Constraints(new Leading(30, 10, 10), new Leading(12, 12, 12)));
			buttonsPanel.add(getCancelButton(), new Constraints(new Leading(120, 10, 10), new Leading(12, 12, 12)));
		}
		return buttonsPanel;
	}
	
	private JButton getAcceptButton() {
		if (acceptButton == null) {
			acceptButton = new JButton();
			acceptButton.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					if (propertiesJList.getSelectedValue().toString().equals("")) {
						return;
					}
					
					String selectedNameDomain = propertiesJList.getSelectedValue().toString();
					OWLDatatypeProperty property = null;
					Collection domains = tkorg.idrs.core.ontology.OWLModel.owlModel.getUserDefinedOWLNamedClasses();
					for (Iterator it = domains.iterator(); it.hasNext();) {
						OWLNamedClass domain = (OWLNamedClass) it.next();
						if (domain.getBrowserText().substring(domain.getBrowserText().indexOf(":") + 1)
								.equals(selectedNameDomain)) {
							for (int i = 0; i < PropertyPanelAction.propertiesArrayList.size(); i++) {
								if (nameProperty.equals(
										PropertyPanelAction.propertiesArrayList.get(i).getName())) {
									PropertyPanelAction.propertiesArrayList.get(i).getDomains().add(domain);									
									PropertiesPanel.loadDomainsListIntoPanel(nameProperty);
									break;
								}
							}
						}
					}
					nameProperty = "";
					
					dispose();
				}
			});
		}
		return acceptButton;
	}
	
	private JButton getCancelButton() {
		if (cancelButton == null) {
			cancelButton = new JButton();
			cancelButton.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
		}
		return cancelButton;
	}
	
	public void updateTextOfComponents() {
		cancelButton.setText("Cancel");
		acceptButton.setText("OK");
	}
}
