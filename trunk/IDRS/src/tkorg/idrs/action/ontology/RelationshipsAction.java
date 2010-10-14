/**
 * 
 */
package tkorg.idrs.action.ontology;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import edu.stanford.smi.protegex.owl.model.OWLNamedClass;
import edu.stanford.smi.protegex.owl.model.OWLObjectProperty;

import tkorg.idrs.core.ontology.OWLModel;
import tkorg.idrs.core.ontology.OWLRelationship;

/**
 * @author DUYVO
 *
 */
public class RelationshipsAction {
	public static ArrayList < OWLRelationship > relationshipArray = null;
	
	public static ArrayList < String > viewRelationships(){
		relationshipArray = new ArrayList < OWLRelationship >();
		ArrayList < String > resultArray = new ArrayList < String >();
		Collection collect = OWLRelationship.getRelationships();
		for(Iterator it = collect.iterator(); it.hasNext();){
			OWLObjectProperty objPro = (OWLObjectProperty)it.next();
			OWLRelationship relationship = new OWLRelationship();
			relationship.setObjProperty(objPro);
			
			relationshipArray.add(relationship);
			resultArray.add(relationship.getName());
			
		}
		return resultArray;
	}
	
	public static ArrayList<String> viewDomains(int index) {
		ArrayList < String > resultArray = new ArrayList < String >();		
		ArrayList < OWLNamedClass > domains = relationshipArray.get(index).getDomains();
		for(int i = 0; i< domains.size(); i++) {
			String name = domains.get(i).getBrowserText();
			resultArray.add(name.substring(name.indexOf(":") + 1));
		}
		return resultArray;
	}
	
	public static ArrayList<String> viewRanges(int index) {
		ArrayList<String> resultArray = new ArrayList<String>();		
		ArrayList<OWLNamedClass> ranges = relationshipArray.get(index).getRanges();
		for(int i = 0; i< ranges.size(); i++) {
			String name = ranges.get(i).getBrowserText();
			resultArray.add(name.substring(name.indexOf(":") + 1));
		}
		return resultArray;
	}
	
	public static String viewInverseProperties(int index) {
		String inversePro;
		try {
		inversePro = relationshipArray.get(index).getInverseProperty().getBrowserText();	
		inversePro.substring(inversePro.indexOf(":") + 1);
		} catch (Exception ex) {
			inversePro = "";
		}
		return inversePro;
	}
	
	public static boolean addRelationship(String name) {
		return OWLRelationship.addRelationship(name);		
	}
	
	public static boolean delRelationship(int index) {
		return OWLRelationship.delRelationship(relationshipArray.get(index));
	}
	
	public static boolean addInverseProperties(int indexRelationshipAdd, int indexRelationshipAdded) {
		return relationshipArray.get(indexRelationshipAdd).addInverseProperty(relationshipArray.get(indexRelationshipAdded).getObjProperty());
	}
	
	public static boolean delDomain(int indexRelationshipsList, int indexDomain) {
		return relationshipArray.get(indexRelationshipsList).delDomain(indexDomain);
	}
	
	public static boolean addDomain(int indexRelationshipsList, String nameClass) {
		return relationshipArray.get(indexRelationshipsList).addDomain(OWLModel.owlModel.getOWLNamedClass(nameClass));
	}
	
	public static boolean addRange(int indexRelationshipsList, String nameClass) {
		return relationshipArray.get(indexRelationshipsList).addRange(OWLModel.owlModel.getOWLNamedClass(nameClass));
	}
}
