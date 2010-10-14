/**
 * 
 */
package tkorg.idrs.action.ontology;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;

import tkorg.idrs.core.ontology.OWLModel;
import tkorg.idrs.core.ontology.OWLMyIndividual;
import tkorg.idrs.gui.main.IDRSApplication;
import tkorg.idrs.gui.ontology.IndividualPanel;
import tkorg.idrs.gui.ontology.LinePanel;
import tkorg.idrs.properties.files.GUIProperties;
import edu.stanford.smi.protegex.owl.model.OWLDatatypeProperty;
import edu.stanford.smi.protegex.owl.model.OWLIndividual;
import edu.stanford.smi.protegex.owl.model.OWLNamedClass;
import edu.stanford.smi.protegex.owl.model.OWLObjectProperty;

/**
 * @author danhit.
 *
 */
public class IndividualsPanelAction {

	public static ArrayList < OWLMyIndividual > myIndividualList = null;
	public static String namespacePrefix = "";
	
	public IndividualsPanelAction() {
		myIndividualList = new ArrayList < OWLMyIndividual >();
	}
	
	/**
	 * 
	 * @param individualTree
	 */
	public void viewIndividualModel(JTree individualTree) {
		//DefaultTreeModel treeModel0 = new DefaultTreeModel(new DefaultMutableTreeNode());
		individualTree.removeAll();
		myIndividualList.removeAll(myIndividualList);
		TreeModel treeModel = null;
		{
			DefaultMutableTreeNode nodeRoot = new DefaultMutableTreeNode("Thing");
			Collection classes = OWLModel.owlModel.getUserDefinedOWLNamedClasses();
			for (Iterator it = classes.iterator(); it.hasNext();) {
				OWLNamedClass cls = (OWLNamedClass) it.next();
				namespacePrefix = cls.getNamespacePrefix();
				Collection instances = cls.getInstances(false);
				DefaultMutableTreeNode nodeClass = new DefaultMutableTreeNode(cls.getBrowserText().substring(cls.getBrowserText().indexOf(":") + 1));
				nodeRoot.add(nodeClass);
				for (Iterator jt = instances.iterator(); jt.hasNext();) {
					OWLIndividual individual = (OWLIndividual) jt.next();
					
					OWLMyIndividual myIndividual = new OWLMyIndividual();
					String nameMyIndividual = individual.getBrowserText().substring(individual.getBrowserText().indexOf(":") + 1);
					myIndividual.setName(nameMyIndividual);
					myIndividual.setIndividual(individual);
					myIndividual.setPropertyList(individual);
					//myIndividual.setRelationshipList(cls);
					myIndividual.setType(cls);
					myIndividualList.add(myIndividual);
					
					DefaultMutableTreeNode nodeIndividual = new DefaultMutableTreeNode(nameMyIndividual);
					nodeClass.add(nodeIndividual);
				}
			}
			treeModel = new DefaultTreeModel(nodeRoot);
		}
		
		Font font = new Font("Dialog", Font.BOLD, 13);
		DefaultTreeCellRenderer render = new DefaultTreeCellRenderer();
		render.setLeafIcon(new ImageIcon(getClass().getResource(GUIProperties.CLASS_ICON)));
		render.setLeafIcon(new ImageIcon(getClass().getResource(GUIProperties.CLASS_ICON)));
		render.setOpenIcon(new ImageIcon(getClass().getResource(GUIProperties.CLASS_ICON)));
		render.setClosedIcon(new ImageIcon(getClass().getResource(GUIProperties.CLASS_ICON)));
		render.setFont(font);
		render.setForeground(Color.ORANGE);
		individualTree.setCellRenderer(render);
		individualTree.setModel(treeModel);
		individualTree.setEnabled(true);
	}
	
	public static DefaultListModel getPropertiesListModel(String nameMyIndividual) {
		OWLMyIndividual myIndividual = getIndividual(nameMyIndividual);
		ArrayList<OWLDatatypeProperty> properties = myIndividual.getPropertyList();
		
		DefaultListModel listModel = new DefaultListModel();
		for (int i = 0; i < properties.size(); i++) {
			OWLDatatypeProperty dataProperty = properties.get(i);
			Object data = myIndividual.getIndividual().getPropertyValue(dataProperty);
			
			String dataPropertyContent = dataProperty.getBrowserText().substring(dataProperty.getBrowserText().indexOf(":") + 1);
			LinePanel panel = new LinePanel(GUIProperties.CLASS_ICON, dataPropertyContent + " ''" + data.toString() + "''");
			listModel.addElement(panel);
		}
		
		return listModel;
	}
	
	public static ArrayList < String > getNameAndValueOfPropertiesOfThisIndividual(String nameMyIndividual) {
		OWLMyIndividual myIndividual = getIndividual(nameMyIndividual);
		ArrayList < OWLDatatypeProperty > properties = myIndividual.getPropertyList();
		ArrayList < String > propertiesList = new ArrayList < String >();
		
		for (int i = 0; i < properties.size(); i++) {
			OWLDatatypeProperty dataProperty = properties.get(i);
			Object data = myIndividual.getIndividual().getPropertyValue(dataProperty);
			
			String dataPropertyContent = dataProperty.getBrowserText().substring(dataProperty.getBrowserText().indexOf(":") + 1);
			propertiesList.add(dataPropertyContent + " ''" + data.toString() + "''");
		}
		
		return propertiesList;
	}
	
	public static DefaultListModel getPropertiesNamesListModel(String nameMyIndividual, ArrayList<String> propertiesNamesList, ArrayList<String> propertiesValuesList) {
		OWLMyIndividual myIndividual = getIndividual(nameMyIndividual);
		ArrayList < OWLDatatypeProperty > properties = myIndividual.getPropertyList();
		
		DefaultListModel listModel = new DefaultListModel();
		for (int i = 0; i < properties.size(); i++) {
			OWLDatatypeProperty dataProperty = properties.get(i);
			
			String propertyName= dataProperty.getBrowserText().substring(dataProperty.getBrowserText().indexOf(":") + 1);
			Object data = myIndividual.getIndividual().getPropertyValue(dataProperty);
			propertiesNamesList.add(propertyName);
			propertiesValuesList.add(data.toString());
			
			listModel.addElement(propertyName);
		}
		
		return listModel;
	}
	
	public static String getPropertyValue(String propertyName, ArrayList<String> propertiesNamesList, ArrayList<String> propertiesValuesList) {
		String value = "";
		for (int i = 0; i < propertiesNamesList.size(); i++) {
			if (propertiesNamesList.get(i).equals(propertyName)) {
				value = propertiesValuesList.get(i).toString();
			}
		}
		
		return value;
	}
	
	public static void setPropertyValue(String nameIndividual, ArrayList < String > propertiesNamesList, ArrayList < String > propertiesValuesList) {
		for (int i = 0; i < myIndividualList.size(); i++) {
			if (nameIndividual.equals(myIndividualList.get(i).getName())) {
				OWLDatatypeProperty data = OWLModel.owlModel.getOWLDatatypeProperty("AuthorName");
			}
		}
	}
	
	public static DefaultListModel getRelationshipListModel(String nameMyIndividual) {
		//
		OWLMyIndividual myIndividual = getIndividual(nameMyIndividual);
		
		DefaultListModel listModel = new DefaultListModel();
		for (int i = 0; i < myIndividual.getRelationshipList().size(); i++) {
			OWLObjectProperty objectProperty = myIndividual.getRelationshipList().get(i);
			String objectPropertyContent = objectProperty.getBrowserText().substring(objectProperty.getBrowserText().indexOf(":") + 1);
			LinePanel panel = new LinePanel(GUIProperties.CLASS_ICON, objectPropertyContent);
			listModel.addElement(panel);
		}
		
		return listModel;
	}
	
	public static ArrayList < String > getRelationshipsListOfThisIndividual(String nameMyIndividual) {
		//
		OWLMyIndividual myIndividual = getIndividual(nameMyIndividual);
		ArrayList < String > relationshipsList = new ArrayList<String>();
		
		for (int i = 0; i < myIndividual.getRelationshipList().size(); i++) {
			OWLObjectProperty relationship = myIndividual.getRelationshipList().get(i);
			String relationshipContent = relationship.getBrowserText().substring(relationship.getBrowserText().indexOf(":") + 1);
			relationshipsList.add(relationshipContent);
		}
		
		return relationshipsList;
	}
	
	public static OWLMyIndividual getIndividual(String nameMyIndividual) {
		for (int i = 0; i < myIndividualList.size(); i++) {
			if (myIndividualList.get(i).getName().equals(nameMyIndividual)) {
				return myIndividualList.get(i);
			}
		}
		
		return null;
	}
	
	public static void addIndividual(String nameMyIndividual, OWLNamedClass typeOfThisIndividual) {
		OWLMyIndividual newIndividual = new OWLMyIndividual();
		
		newIndividual.setName(nameMyIndividual);
		String namespacePrefix = myIndividualList.get(0).getIndividual().getNamespacePrefix();
		newIndividual.setIndividual(typeOfThisIndividual.createOWLIndividual(namespacePrefix + ":" + newIndividual.getName()));
		newIndividual.setType(typeOfThisIndividual);
		for (int i = 0; i < myIndividualList.size(); i++) {
			OWLMyIndividual myIndividual = myIndividualList.get(i);
			if (myIndividual.getType().getBrowserText().equals(newIndividual.getType().getBrowserText())) {
				newIndividual.setPropertyList(myIndividual.getPropertyList());
				newIndividual.setRelationshipList(myIndividual.getRelationshipList());
			}
		}
	}
	
	public static void deleteIndividual(String nameMyIndividual) {
		OWLMyIndividual myIndividual = getIndividual(nameMyIndividual);
		OWLModel.owlModel.deleteInstance(myIndividual.getIndividual());
	}
	
	public void refreshIndividualPanel() {
		viewIndividualModel(IndividualPanel.individualTree);
		IndividualPanel.contentRelationshipsPanel.removeAll();
		IndividualPanel.contentRelationshipsPanel.removeAll();
		IDRSApplication.idrsJFrame.repaint();
	}
	
	public static ArrayList<OWLDatatypeProperty> getPropertiesList(String nameMyIndividual) {
		OWLMyIndividual myIndividual = getIndividual(nameMyIndividual);
		
		return myIndividual.getPropertyList();
	}
}












