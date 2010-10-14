/**
 * 
 */
package tkorg.idrs.gui.ontology;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

import edu.stanford.smi.protegex.owl.model.OWLDatatypeProperty;

import tkorg.idrs.action.ontology.IndividualsPanelAction;
import tkorg.idrs.core.ontology.OWLMyIndividual;

//VS4E -- DO NOT REMOVE THIS LINE!
public class UpdatePropertiesIndividualDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private JSplitPane rootSplitPane;
	private JPanel propertiesListPanel;
	private JSplitPane jSplitPane0;
	private JPanel valuePanel;
	private JPanel buttonPanel;
	private JButton cancelButton;
	private JButton acceptButton;
	private JFrame idrsJFrame;
	
	private int xLocation;
	private int yLocation;
	private int width = 662;
	private int height = 480;
	
	private String nameMyIndividual = "";
	private String nameSelected = "";
	private ArrayList < String > propertiesNamesList;
	private ArrayList < String > propertiesValuesList;
	private JList propertiesJList;
	private JScrollPane propertiesScrollPane;
	private JTextArea propertiesTextArea;
	private JScrollPane jScrollPane0;
	

	public UpdatePropertiesIndividualDialog() {
		initComponents();
	}

	public UpdatePropertiesIndividualDialog(JFrame mainFrame, String nameMyIndividual) {
		super(mainFrame);
		
		idrsJFrame = mainFrame;
		xLocation = idrsJFrame.getX() + ((idrsJFrame.getWidth() - width) / 2);
		yLocation = idrsJFrame.getY() + ((idrsJFrame.getHeight() - height) / 2);
		
		this.nameMyIndividual = nameMyIndividual;
		propertiesNamesList = new ArrayList<String>();
		propertiesValuesList = new ArrayList<String>();
		
		initComponents();
	}

	private void initComponents() {
		setTitle("Paper1");
		setFont(new Font("Dialog", Font.PLAIN, 12));
		setBackground(Color.white);
		setResizable(false);
		setForeground(Color.black);
		setLayout(new GroupLayout());
		add(getRootSplitPane(), new Constraints(new Bilateral(0, 0, 202), new Bilateral(0, 0, 64)));
		setSize(width, height);
		setLocation(xLocation, yLocation);
	}

	private JScrollPane getJScrollPane0() {
		if (jScrollPane0 == null) {
			jScrollPane0 = new JScrollPane();
			jScrollPane0.setViewportView(getPropertiesTextArea());
		}
		return jScrollPane0;
	}

	private JTextArea getPropertiesTextArea() {
		if (propertiesTextArea == null) {
			propertiesTextArea = new JTextArea();
			propertiesTextArea.setLineWrap(true);
		}
		return propertiesTextArea;
	}

	private JScrollPane getPropertiesScrollPane() {
		if (propertiesScrollPane == null) {
			propertiesScrollPane = new JScrollPane();
			propertiesScrollPane.setViewportView(getPropertiesJList());
		}
		return propertiesScrollPane;
	}

	private JList getPropertiesJList() {
		if (propertiesJList == null) {
			propertiesJList = new JList();
			propertiesJList.setFont(new Font("Dialog", Font.BOLD, 13));
			propertiesJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			DefaultListModel listModel = IndividualsPanelAction.getPropertiesNamesListModel(nameMyIndividual, propertiesNamesList, propertiesValuesList);
			propertiesJList.setModel(listModel);
			propertiesJList.addListSelectionListener(new ListSelectionListener() {
				
				public void valueChanged(ListSelectionEvent event) {
					if (nameSelected.equals("") == false) {
						for (int i = 0; i < propertiesNamesList.size(); i++) {
							if (nameSelected.equals(propertiesNamesList.get(i))) {
								propertiesValuesList.set(i, propertiesTextArea.getText());
							}
						}
					}
					nameSelected = propertiesJList.getSelectedValue().toString();
					String value = IndividualsPanelAction.getPropertyValue(nameSelected, propertiesNamesList, propertiesValuesList);
					propertiesTextArea.setText(value);
				}
			});
		}
		
		return propertiesJList;
	}

	private JButton getAcceptButton() {
		if (acceptButton == null) {
			acceptButton = new JButton();
			acceptButton.setText("OK");
			acceptButton.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					if (nameSelected.equals("")) {
						dispose();
					}
					for (int i = 0; i < propertiesNamesList.size(); i++) {
						if (nameSelected.equals(propertiesNamesList.get(i))) {
							propertiesValuesList.set(i, propertiesTextArea.getText());
						}
					}
					for (int i = 0; i < IndividualsPanelAction.myIndividualList.size(); i++) {
						if (IndividualsPanelAction.myIndividualList.get(i).getName().equals(nameMyIndividual)) {
							OWLMyIndividual owlMyIndividual = IndividualsPanelAction.myIndividualList.get(i);
							for (int j = 0; j < owlMyIndividual.getPropertyList().size(); j++) {
								OWLDatatypeProperty dataProperty = owlMyIndividual.getPropertyList().get(j);
								owlMyIndividual.getIndividual().setPropertyValue(dataProperty, propertiesValuesList.get(j));
							}
							owlMyIndividual.setPropertyList(owlMyIndividual.getIndividual());
							IndividualsPanelAction.myIndividualList.set(i, owlMyIndividual);
							break;
						}
					}
					IndividualPanel.loadPropertiesListIntoPanel(nameMyIndividual);
					
					dispose();
				}
			});
		}
		return acceptButton;
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

	private JPanel getPropertiesListPanel() {
		if (propertiesListPanel == null) {
			propertiesListPanel = new JPanel();
			propertiesListPanel.setBorder(BorderFactory.createTitledBorder(null, "Properties", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, new Font(
					"Dialog", Font.BOLD, 13), new Color(51, 51, 51)));
			propertiesListPanel.setLayout(new GroupLayout());
			propertiesListPanel.add(getPropertiesScrollPane(), new Constraints(new Bilateral(1, 0, 22), new Bilateral(1, 0, 22)));
		}
		return propertiesListPanel;
	}

	private JSplitPane getRootSplitPane() {
		if (rootSplitPane == null) {
			rootSplitPane = new JSplitPane();
			rootSplitPane.setDividerLocation(400);
			rootSplitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
			rootSplitPane.setTopComponent(getJSplitPane0());
			rootSplitPane.setBottomComponent(getButtonPanel());
		}
		return rootSplitPane;
	}

	private JPanel getButtonPanel() {
		if (buttonPanel == null) {
			buttonPanel = new JPanel();
			buttonPanel.setLayout(new GroupLayout());
			buttonPanel.add(getAcceptButton(), new Constraints(new Leading(247, 78, 12, 12), new Leading(7, 10, 10)));
			buttonPanel.add(getCancelButton(), new Constraints(new Leading(338, 77, 10, 10), new Leading(7, 10, 10)));
		}
		return buttonPanel;
	}

	private JPanel getValuePanel() {
		if (valuePanel == null) {
			valuePanel = new JPanel();
			valuePanel.setBorder(BorderFactory.createTitledBorder(null, "Value", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD,
					13), new Color(51, 51, 51)));
			valuePanel.setLayout(new GroupLayout());
			valuePanel.add(getJScrollPane0(), new Constraints(new Bilateral(-1, 0, 22), new Bilateral(-1, 0, 22)));
		}
		return valuePanel;
	}

	private JSplitPane getJSplitPane0() {
		if (jSplitPane0 == null) {
			jSplitPane0 = new JSplitPane();
			jSplitPane0.setDividerLocation(200);
			jSplitPane0.setLeftComponent(getPropertiesListPanel());
			jSplitPane0.setRightComponent(getValuePanel());
		}
		return jSplitPane0;
	}

}
