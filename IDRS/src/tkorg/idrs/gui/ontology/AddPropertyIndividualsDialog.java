/**
 * 
 */
package tkorg.idrs.gui.ontology;

import edu.stanford.smi.protegex.owl.model.OWLDatatypeProperty;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

import tkorg.idrs.action.ontology.IndividualsPanelAction;
import tkorg.idrs.action.ontology.PropertyPanelAction;

//VS4E -- DO NOT REMOVE THIS LINE!
public class AddPropertyIndividualsDialog extends JDialog {
	
	private static final long serialVersionUID = 1L;
	private static final String PREFERRED_LOOK_AND_FEEL = "javax.swing.plaf.metal.MetalLookAndFeel";
	
	private JPanel propertiesListPanel;
	private JSplitPane addPropertySplitPane;
	private JPanel buttonsPanel;
	private JList propertiesJList;
	private JScrollPane propertiesListScrollPane;
	private JButton acceptButton;
	private JButton cancelButton;
	
	private ArrayList < String > uncheckedPropertiesArrayList = null;
	private String nameIndividual = "";
	private int xLocation = 0;
	private int yLocation = 0;
	private int width = 250;
	private int height = 435;
	
	public AddPropertyIndividualsDialog() {
		initComponents();
	}

	public AddPropertyIndividualsDialog(Frame parent, String nameIndividual, ArrayList < String > uncheckedPropertiesArrayList) {
		super(parent);
		
		xLocation = parent.getX() + ((parent.getWidth() - width) / 2);
		yLocation = parent.getY() + ((parent.getHeight() - height) / 2);
		
		this.uncheckedPropertiesArrayList = uncheckedPropertiesArrayList;
		this.nameIndividual = nameIndividual;
		
		initComponents();
	}

	private void initComponents() {
		setEnabled(true);
		setFont(new Font("Dialog", Font.PLAIN, 12));
		setBackground(Color.white);
		setForeground(Color.black);
		setLayout(new GroupLayout());
		add(getAddPropertySplitPane(), new Constraints(new Bilateral(0, 0, 202), new Bilateral(0, 0, 28)));
		setSize(width, height);
		setLocation(xLocation, yLocation);
		setTitle(nameIndividual);
	}

	private JButton getCancelButton() {
		if (cancelButton == null) {
			cancelButton = new JButton();
			cancelButton.setText("Cancel");
			cancelButton.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
		}
		return cancelButton;
	}

	private JButton getAcceptButton() {
		if (acceptButton == null) {
			acceptButton = new JButton();
			acceptButton.setText("OK");
			acceptButton.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					if (propertiesJList.getSelectedValue().toString().equals("") == true) {
						return;
					}
					
					String selectedNameProperty = propertiesJList.getSelectedValue().toString();
					OWLDatatypeProperty property = null;
					for (int i = 0; i < PropertyPanelAction.propertiesArrayList.size(); i++) {
						if (PropertyPanelAction.propertiesArrayList.get(i).getProperty().getBrowserText().substring(
								PropertyPanelAction.propertiesArrayList.get(i).getProperty().getBrowserText().indexOf(":") + 1).equals(selectedNameProperty)) {
							property = (OWLDatatypeProperty) PropertyPanelAction.propertiesArrayList.get(i).getProperty();
							break;
						}
					}
					for (int i = 0; i < IndividualsPanelAction.myIndividualList.size(); i++) {
						if (IndividualsPanelAction.myIndividualList.get(i).getName().equals(nameIndividual)) {
							IndividualsPanelAction.myIndividualList.get(i).getIndividual().setPropertyValue(property, "");
							IndividualsPanelAction.myIndividualList.get(i).setPropertyList(IndividualsPanelAction.myIndividualList.get(i).getIndividual());
							IndividualPanel.loadPropertiesListIntoPanel(nameIndividual);
							break;
						}
					}
					
					dispose();
				}
			});
		}
		return acceptButton;
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
			propertiesJList.setFont(new Font("Dialog", Font.BOLD, 13));
			DefaultListModel listModel = new DefaultListModel();
			for (int i = 0; i < uncheckedPropertiesArrayList.size(); i++) {
				listModel.addElement(uncheckedPropertiesArrayList.get(i).toString());
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

	private JSplitPane getAddPropertySplitPane() {
		if (addPropertySplitPane == null) {
			addPropertySplitPane = new JSplitPane();
			addPropertySplitPane.setDividerLocation(340);
			addPropertySplitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
			addPropertySplitPane.setEnabled(false);
			addPropertySplitPane.setTopComponent(getPropertiesListPanel());
			addPropertySplitPane.setBottomComponent(getButtonsPanel());
		}
		return addPropertySplitPane;
	}

	private JPanel getPropertiesListPanel() {
		if (propertiesListPanel == null) {
			propertiesListPanel = new JPanel();
			propertiesListPanel.setBorder(BorderFactory.createTitledBorder("Choose a property"));
			propertiesListPanel.setLayout(new GroupLayout());
			propertiesListPanel.add(getPropertiesListScrollPane(), new Constraints(new Bilateral(1, 0, 22), new Bilateral(-1, 0, 22)));
		}
		return propertiesListPanel;
	}

	private static void installLnF() {
		try {
			String lnfClassname = PREFERRED_LOOK_AND_FEEL;
			if (lnfClassname == null)
				lnfClassname = UIManager.getCrossPlatformLookAndFeelClassName();
			UIManager.setLookAndFeel(lnfClassname);
		} catch (Exception e) {
			System.err.println("Cannot install " + PREFERRED_LOOK_AND_FEEL
					+ " on this platform:" + e.getMessage());
		}
	}

	/**
	 * Main entry of the class.
	 * Note: This class is only created so that you can easily preview the result at runtime.
	 * It is not expected to be managed by the designer.
	 * You can modify it as you like.
	 */
	public static void main(String[] args) {
		installLnF();
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				AddPropertyIndividualsDialog dialog = new AddPropertyIndividualsDialog();
				dialog
						.setDefaultCloseOperation(AddPropertyIndividualsDialog.DISPOSE_ON_CLOSE);
				dialog.setTitle("AddPropertiesIndividualsDialog");
				dialog.setLocationRelativeTo(null);
				dialog.getContentPane().setPreferredSize(dialog.getSize());
				dialog.pack();
				dialog.setVisible(true);
			}
		});
	}
}
