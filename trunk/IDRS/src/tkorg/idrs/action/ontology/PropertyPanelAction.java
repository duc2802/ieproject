package tkorg.idrs.action.ontology;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListModel;
import javax.swing.tree.DefaultMutableTreeNode;

import tkorg.idrs.core.ontology.OWLModel;
import tkorg.idrs.core.ontology.OWLMyClass;
import tkorg.idrs.core.ontology.OWLProperty;
import tkorg.idrs.gui.ontology.PropertiesPanel;

import edu.stanford.smi.protegex.owl.model.OWLDatatypeProperty;
import edu.stanford.smi.protegex.owl.model.OWLNamedClass;
import edu.stanford.smi.protegex.owl.model.RDFSDatatype;

public class PropertyPanelAction {
	
//	OWLProperty owlPropertyIndividual = null;
	public static ArrayList < OWLProperty > propertiesArrayList = null;
	public static String namespacePrefix = "";
	public static String nameProperty = "";
//	public ArrayList < RDFSDatatype > datatypeList = null;
	
	public PropertyPanelAction(){
//		owlPropertyIndividual = new OWLProperty();
		propertiesArrayList = new ArrayList < OWLProperty >();
//		datatypeList = new ArrayList < RDFSDatatype >();
	}
	
	public void viewPropertiesPanel() {
		Collection properties = OWLModel.owlModel.getUserDefinedOWLDatatypeProperties();
		DefaultListModel listModel = new DefaultListModel();
		
		for (Iterator it = properties.iterator(); it.hasNext();) {
			OWLDatatypeProperty owlProperty = (OWLDatatypeProperty) it.next();
			OWLProperty property = new OWLProperty();
			property.setAlllValues(owlProperty);
			namespacePrefix = property.getProperty().getNamespacePrefix();
			propertiesArrayList.add(property);
			
			listModel.addElement(owlProperty.getBrowserText().substring(
					owlProperty.getBrowserText().indexOf(":") + 1));
		}
		
		PropertiesPanel.propertiesJList.setModel(listModel);
		PropertiesPanel.propertiesJList.setEnabled(true);
	}
	
	public static void convertPropertiesIntoList(JList propertiesJList) {
		DefaultListModel listModel = new DefaultListModel();
		
		for (int i = 0; i < PropertyPanelAction.propertiesArrayList.size(); i++) {
			OWLProperty property = PropertyPanelAction.propertiesArrayList.get(i);
			listModel.addElement(property.getName());
		}
		propertiesJList.setModel(listModel);
		propertiesJList.setEnabled(true);
	}
	
	public static ArrayList < String > getNameDomainsByProperty(String nameProperty) {
		ArrayList < String > nameDomains = new ArrayList < String >();
		
		for (int i = 0; i < propertiesArrayList.size(); i++) {
			if (nameProperty.equals(propertiesArrayList.get(i).getName())) {
				for (int j = 0; j < propertiesArrayList.get(i).getDomains().size(); j++) {
					OWLNamedClass domain = propertiesArrayList.get(i).getDomains().get(j);
					nameDomains.add(domain.getBrowserText());
				}
			}
		}
		
		return nameDomains;
	}
	
	public static String getNameRangeByProperty(String nameProperty) {
		String nameRange = "";
		
		//String nameOWLProperty = namespacePrefix + ":" + nameProperty;
		for (int i = 0; i < propertiesArrayList.size(); i++) {
			if (nameProperty.equals(propertiesArrayList.get(i).getName())) {
				nameRange = propertiesArrayList.get(i).getRange().getBrowserText();
				break;
			}
		}
		
		return nameRange;
	}
	
	public static boolean isFunctionalByProperty(String nameProperty) {
		boolean isFunctional = false;
		
		for (int i = 0; i < propertiesArrayList.size(); i++) {
			if (nameProperty.equals(propertiesArrayList.get(i).getName())) {
				isFunctional = propertiesArrayList.get(i).isFunctional();
				break;
			}
		}
		
		return isFunctional;
	}
	
	public static OWLProperty getMyPropertyByName(String nameProperty) {
		OWLProperty property = null;
		
		for (int i = 0; i < PropertyPanelAction.propertiesArrayList.size(); i++) {
			if (nameProperty.equals(PropertyPanelAction.propertiesArrayList.get(i).getName())) {
				property = (OWLProperty) PropertyPanelAction.propertiesArrayList.get(i);
			}
		}
		
		return property;
	}
	
	public static void addOWLPropertyIntoArrayList(String nameNewProperty) {
		OWLProperty property = new OWLProperty();
		
		OWLDatatypeProperty dataProperty = OWLModel.owlModel.createOWLDatatypeProperty(nameNewProperty);
		property.setAlllValues(dataProperty);
		propertiesArrayList.add(property);
	}
	
//	public ArrayList < RDFSDatatype > getDatatypesList(){
//		for (Iterator it = owlPropertyIndividual.getDatatypes(); it.hasNext();) {
//			RDFSDatatype datatype = (RDFSDatatype) it.next();
//			datatypeList.add(datatype);
//		}
//		return datatypeList;
//		}
}
