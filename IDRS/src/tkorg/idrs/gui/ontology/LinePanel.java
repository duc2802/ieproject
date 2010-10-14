package tkorg.idrs.gui.ontology;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;
import org.dyno.visual.swing.layouts.Trailing;

import edu.stanford.smi.protegex.owl.model.OWLDatatypeProperty;
import edu.stanford.smi.protegex.owl.model.OWLNamedClass;

import tkorg.idrs.action.ontology.ClassPanelAction;
import tkorg.idrs.action.ontology.IndividualsPanelAction;
import tkorg.idrs.action.ontology.PropertyPanelAction;
import tkorg.idrs.core.ontology.OWLModel;
import tkorg.idrs.core.ontology.OWLMyClass;
import tkorg.idrs.core.ontology.OWLMyIndividual;
import tkorg.idrs.core.ontology.OWLProperty;
import tkorg.idrs.gui.main.IDRSApplication;
import tkorg.idrs.properties.files.GUIProperties;

//VS4E -- DO NOT REMOVE THIS LINE!
//KHONG CAN PARENT_CLASS VA PARENT_AREA.
public class LinePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel delJLabel;
	private JLabel editJLabel;
	private JPanel jPanel0;
	private JLabel jLabel0;
	private JPanel jPanel1;
	private JPanel jPanel2;
	private JLabel contentLabel;
	
	//Chua co lop nao goi no.
	private int PARENT_CLASS = 0;
	private int PARENT_AREA = 0;
	private String content = "";
	private String pathPicture = "";
	private String nameSelectedIndividual = "";
	private String nameProperty = "";
	private int indexOfProperty = 0;
	private int heightPane = 0;

	public LinePanel() {
		initComponents();
		
		updateTextsOfComponents();
	}
	
	public LinePanel(String content) {
		this.content = content;
		
		initComponents();
		
		updateTextsOfComponents();
	}
	
	public LinePanel(String content, int parentClass, int parentArea) {
		this.content = content;
		this.PARENT_CLASS = parentClass;
		this.PARENT_AREA = parentArea;
		initComponents();
		
		updateTextsOfComponents();
	}
	
	public LinePanel(String pathPicture, String content) {
		this.pathPicture = pathPicture;
		this.content = content;
		
		initComponents();
		
		updateTextsOfComponents();
	}
	
	private void updateTextsOfComponents() {
		contentLabel.setText(content);
		jPanel1.add(getJLabel0(), new Constraints(new Leading(0, 38, 12, 12), new Leading(0, 24, 12, 12)));
		heightPane = 37;
	}

	private void initComponents() {
		setLayout(new BorderLayout());
		add(getJPanel1(), BorderLayout.WEST);
		add(getJPanel2(), BorderLayout.CENTER);
		add(getJPanel0(), BorderLayout.EAST);
		
		setSize(320, 44);
		
		updateTextsOfComponents();
	}

	private JLabel getContentLabel() {
		if (contentLabel == null) {
			contentLabel = new JLabel();
			contentLabel.setFont(new Font("Dialog", Font.BOLD, 13));
			contentLabel.setHorizontalAlignment(SwingConstants.LEFT);
			contentLabel.setVerticalAlignment(SwingConstants.TOP);
			contentLabel.setAutoscrolls(true);
		}
		return contentLabel;
	}

	private JPanel getJPanel2() {
		if (jPanel2 == null) {
			jPanel2 = new JPanel();
			jPanel2.setLayout(new GroupLayout());
			jPanel2.add(getContentLabel(), new Constraints(new Bilateral(0, 0, 0), new Bilateral(0, 0, 16)));
		}
		return jPanel2;
	}

	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			jPanel1 = new JPanel();
			jPanel1.setLayout(new GroupLayout());
		}
		return jPanel1;
	}

	private JLabel getJLabel0() {
		if (jLabel0 == null) {
			jLabel0 = new JLabel();
			jLabel0.setIcon(new ImageIcon(getClass().getResource(GUIProperties.CLASS_ICON)));
		}
		return jLabel0;
	}

	private JPanel getJPanel0() {
		if (jPanel0 == null) {
			jPanel0 = new JPanel();
			jPanel0.setLayout(new GroupLayout());
			if (PARENT_CLASS != GUIProperties.CLASSES_PANEL && 
					(PARENT_CLASS != GUIProperties.PROPERTIES_PANEL || PARENT_AREA != GUIProperties.DOMAINS_PROPERTIES_PANEL)) {
				jPanel0.add(getEditJLabel(), new Constraints(new Leading(0, 42, 42), new Leading(0, 12, 12)));
			}
			if (PARENT_CLASS != GUIProperties.PROPERTIES_PANEL || PARENT_AREA != GUIProperties.RANGES_PROPERTIES_PANEL) {
				jPanel0.add(getDelJLabel(), new Constraints(new Leading(36, 12, 12), new Leading(0, 12, 12)));
			}
		}
		return jPanel0;
	}

	private JLabel getDelJLabel() {
		if (delJLabel == null) {
			delJLabel = new JLabel();
			delJLabel.setIcon(new ImageIcon(getClass().getResource(GUIProperties.DELETE_BUTTON_PRESSED_FILE)));
			delJLabel.setOpaque(true);
			delJLabel.addMouseListener(new MouseListener() {

				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseEntered(MouseEvent e) {
					delJLabel.setIcon(new ImageIcon(getClass().getResource(GUIProperties.DELETE_BUTTON_APPEAR_FILE)));
				}

				@Override
				public void mouseExited(MouseEvent e) {
					delJLabel.setIcon(new ImageIcon(getClass().getResource(GUIProperties.DELETE_BUTTON_PRESSED_FILE)));
				}

				@Override
				public void mousePressed(MouseEvent e) {
					switch (PARENT_CLASS) {
						case GUIProperties.INDIVIDUAL_PANEL: {
							switch (PARENT_AREA) {
								case GUIProperties.PROPERTIES_INDIVIDUAL_PANEL:
									for (int i = 0; i < IndividualsPanelAction.myIndividualList.size(); i++) {
										OWLMyIndividual myIndividual = IndividualsPanelAction.myIndividualList.get(i);
										if (nameSelectedIndividual.equals(myIndividual.getName())) {
											OWLDatatypeProperty property = 
												IndividualsPanelAction.myIndividualList.get(i).getPropertyList().get(indexOfProperty);
											String propertyValue = (String) myIndividual.getIndividual().getPropertyValue(property);
											myIndividual.getIndividual().removePropertyValue(property, propertyValue);
											IndividualsPanelAction.myIndividualList.get(i).setIndividual(myIndividual.getIndividual());
											IndividualsPanelAction.myIndividualList.get(i).setPropertyList(myIndividual.getIndividual());
											IndividualPanel.loadPropertiesListIntoPanel(nameSelectedIndividual);
											break;
										}
									}
									break;
		
								default:
									break;
							}
						}
						
						case GUIProperties.PROPERTIES_PANEL: {
							switch (PARENT_AREA) {
								case GUIProperties.DOMAINS_PROPERTIES_PANEL:
									OWLProperty property = PropertyPanelAction.getMyPropertyByName(nameProperty);
									for (int i = 0; i < property.getDomains().size(); i++) {
										if (content.equals(ClassPanelAction.convertName(property.getDomains().get(i).getBrowserText()))) {
											property.getDomains().remove(i);
											for (Iterator it = property.getProperty().getDomains(false).iterator(); it.hasNext();) {
												Object temp = it.next();
												if (temp.toString().substring(
														temp.toString().indexOf("#") + 1, 
														temp.toString().length() - 1).
														equals(content)) {
													//property.getProperty().getDomains(false).remove(temp);
												}
											}
										}
									}
									for (int j = 0; j < PropertyPanelAction.propertiesArrayList.size(); j++) {
										if (PropertyPanelAction.propertiesArrayList.get(j).getName().
												equals(property.getName())) {
											PropertyPanelAction.propertiesArrayList.set(j, property);
										}
									}
									PropertiesPanel.loadDomainsListIntoPanel(PropertyPanelAction.nameProperty);
									break;
									
								default:
									break;
							}
							break;
						}
						
						case GUIProperties.CLASSES_PANEL: {
							switch (PARENT_AREA) {
								case GUIProperties.SUPERCLASSES_CLASSES_PANEL:
									OWLMyClass myClass = ClassPanelAction.getMyClassByName(ClassPanelAction.nameClass);
									for (int i = 0; i < myClass.getOwlSuperClassesArrayList().size(); i++) {
										if (ClassPanelAction.convertName(myClass.getOwlSuperClassesArrayList().get(i).getBrowserText()).equals(content)) {
											myClass.getOwlSuperClassesArrayList().remove(i);
											for (Iterator it = myClass.getOwlNamedClass().getSuperclasses(true).iterator(); it.hasNext();) {
												Object temp = it.next();
												if (temp.toString().substring(
														temp.toString().indexOf("#") + 1, 
														temp.toString().length() - 1).
														equals(content)) {
													myClass.getOwlNamedClass().getSuperclasses(true).remove(temp);
												}
											}
											for (int j = 0; j < ClassPanelAction.classesArrayList.size(); j++) {
												if (ClassPanelAction.classesArrayList.get(j).getName().
														equals(myClass.getName())) {
													ClassPanelAction.classesArrayList.set(j, myClass);
												}
											}
											ClassPanel.loadSuperClassesListIntoPanel(ClassPanelAction.nameClass);
											break;
										}
									}
									break;
									
								case GUIProperties.DISJONTCLASSES_CLASSES_PANEL:
									OWLMyClass myClass02 = ClassPanelAction.getMyClassByName(ClassPanelAction.nameClass);
									for (int i = 0; i < myClass02.getOwlDisjontClassesArrayList().size(); i++) {
										if (ClassPanelAction.convertName(
												myClass02.getOwlDisjontClassesArrayList().get(i).getBrowserText()).equals(
												content)) {
											myClass02.getOwlDisjontClassesArrayList().remove(i);
											for (Iterator it02 = myClass02.getOwlNamedClass().getDisjointClasses().iterator(); it02.hasNext();) {
												Object temp02 = it02.next();
												if (temp02.toString().substring(
														temp02.toString().indexOf("#") + 1, 
														temp02.toString().length() - 1).
														equals(content)) {
													//myClass02.getOwlNamedClass().getDisjointClasses().remove(temp02);
												}
											}
											for (int j = 0; j < ClassPanelAction.classesArrayList.size(); j++) {
												if (ClassPanelAction.classesArrayList.get(j).getName().
														equals(myClass02.getName())) {
													ClassPanelAction.classesArrayList.set(j, myClass02);
												}
											}
											ClassPanel.loadDisjontClassesListIntoPanel(ClassPanelAction.nameClass);
											break;
										}
									}
									break;
									
								default:
									break;
							}
							break;
						}
						
						default:
							break;
					}
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
			});
		}
		return delJLabel;
	}

	public JLabel getEditJLabel() {
		if (editJLabel == null) {
			editJLabel = new JLabel();
			editJLabel.setIcon(new ImageIcon(getClass().getResource(GUIProperties.EDIT_BUTTON_APPEAR_FILE)));
			editJLabel.addMouseListener(new MouseListener() {
	
				public void mousePressed(MouseEvent event) {
					switch (PARENT_CLASS) {
						case GUIProperties.INDIVIDUAL_PANEL: {	//Neu la IndividualPanel.
							switch (PARENT_AREA) {
								case GUIProperties.PROPERTIES_INDIVIDUAL_PANEL:	//Neu la vung properties.
									UpdatePropertiesIndividualDialog updatePropertiesIndividualDialog = 
										new UpdatePropertiesIndividualDialog(
											IDRSApplication.idrsJFrame, nameSelectedIndividual);
									updatePropertiesIndividualDialog.setVisible(true);
									break;
		
								default:
									break;
							}
						}
					}
				}
	
				public void mouseClicked(MouseEvent event) {
				}
	
				public void mouseReleased(MouseEvent event) {
					
				}
	
				public void mouseExited(MouseEvent event) {
					editJLabel.setIcon(new ImageIcon(getClass().getResource(GUIProperties.EDIT_BUTTON_APPEAR_FILE)));
				}
	
				public void mouseEntered(MouseEvent event) {
					editJLabel.setIcon(new ImageIcon(getClass().getResource(GUIProperties.EDIT_BUTTON_PRESSED_FILE)));
				}
			});
		}
		return editJLabel;
	}

	public String getNameSelectedIndividual() {
		return nameSelectedIndividual;
	}

	public void setNameSelectedIndividual(String nameSelectedIndividual) {
		this.nameSelectedIndividual = nameSelectedIndividual;
	}

	public int getIndexOfProperty() {
		return indexOfProperty;
	}

	public void setIndexOfProperty(int indexOfProperty) {
		this.indexOfProperty = indexOfProperty;
	}

	public Constraints getPosition(int rank) {
		Constraints constraints = new Constraints(new Bilateral(0, 0, 0), new Leading((heightPane + 5) * rank, heightPane, 1, 1));
		
		return constraints;
	}

	public String getNameProperty() {
		return nameProperty;
	}

	public void setNameProperty(String nameProperty) {
		this.nameProperty = nameProperty;
	}
}
