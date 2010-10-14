package tkorg.idrs.core.ontology;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import tkorg.idrs.action.ontology.RelationshipsAction;

import edu.stanford.smi.protegex.owl.model.OWLDatatypeProperty;
import edu.stanford.smi.protegex.owl.model.OWLIndividual;
import edu.stanford.smi.protegex.owl.model.OWLNamedClass;
import edu.stanford.smi.protegex.owl.model.OWLObjectProperty;

/**
 * @author danhit.
 *
 */
public class OWLMyIndividual {

	private String name = "";
	private OWLIndividual individual = null;
	private OWLNamedClass type = null;
	private ArrayList < OWLObjectProperty > relationshipList = null;
	private ArrayList < OWLDatatypeProperty > propertyList = null;
	
	public OWLMyIndividual() {
		relationshipList = new ArrayList < OWLObjectProperty >();
		propertyList = new ArrayList < OWLDatatypeProperty >();
	}
	
	public void setRelationshipList(ArrayList<OWLObjectProperty> relationshipList) {
		this.relationshipList = relationshipList;
	}

	public void setPropertyList(ArrayList < OWLDatatypeProperty > propertyList) {
		this.propertyList = propertyList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public OWLIndividual getIndividual() {
		return individual;
	}

	public void setIndividual(OWLIndividual individual) {
		this.individual = individual;
	}

	public OWLNamedClass getType() {
		return type;
	}

	public void setType(OWLNamedClass type) {
		this.type = type;
	}

	public ArrayList<OWLObjectProperty> getRelationshipList() {
		return relationshipList;
	}

	public void setRelationshipList(OWLNamedClass cls) {
		//
		String nameClass = cls.getBrowserText();
		for (int i = 0; i < RelationshipsAction.relationshipArray.size(); i++) {
			OWLRelationship relationship = RelationshipsAction.relationshipArray.get(i);
			
			ArrayList<OWLNamedClass> domains = relationship.getDomains();
			for (int j = 0; j < domains.size(); j++) {
				if (domains.get(j).getBrowserText().equals(nameClass)) {
					relationshipList.add(relationship.getObjProperty());
				}
			}
			ArrayList<OWLNamedClass> ranges = relationship.getRanges();
			for (int j = 0; j < ranges.size(); j++) {
				if (ranges.get(j).getBrowserText().equals(nameClass)) {
					relationshipList.add(relationship.getObjProperty());
				}
			}
		}
	}

	public ArrayList<OWLDatatypeProperty> getPropertyList() {
		return propertyList;
	}

	public void setPropertyList(OWLIndividual individual) {
		if (propertyList.size() != 0) {
			propertyList.removeAll(propertyList);
		}
		Collection properties = OWLModel.owlModel.getUserDefinedOWLDatatypeProperties();
		for (Iterator temp = properties.iterator(); temp.hasNext();) {
			OWLDatatypeProperty dataProperty = (OWLDatatypeProperty) temp.next();
			Object object = individual.getPropertyValue(dataProperty);
			if (object != null) {
				propertyList.add(dataProperty);
			}
		}
	}
}













