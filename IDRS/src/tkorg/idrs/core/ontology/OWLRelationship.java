package tkorg.idrs.core.ontology;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.JOptionPane;

import edu.stanford.smi.protegex.owl.model.OWLNamedClass;
import edu.stanford.smi.protegex.owl.model.OWLObjectProperty;
import edu.stanford.smi.protegex.owl.model.RDFProperty;

public class OWLRelationship{
	
	String name = "";
	ArrayList<OWLNamedClass> domains = null;
	ArrayList<OWLNamedClass> ranges = null;
	OWLObjectProperty objProperty = null;
	
	public OWLRelationship(){
		domains = new ArrayList<OWLNamedClass>();
		ranges = new ArrayList<OWLNamedClass>();
	}
		
	public OWLObjectProperty getObjProperty() {
		return objProperty;
	}

	public void setObjProperty(OWLObjectProperty objProperty) {
		this.objProperty = objProperty;
		setName(objProperty.getBrowserText());
		setDomains(objProperty.getDomains(false));
		setRanges(objProperty.getRanges(false));
		getInverseProperty();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {		
		this.name = name.substring(name.indexOf(":") + 1);
	}

	public ArrayList<OWLNamedClass> getDomains() {
		return domains;
	}

	public void setDomains(Collection domains) {
		for(Iterator it = domains.iterator(); it.hasNext();){
			this.domains.add((OWLNamedClass)it.next());
		}		
	}
	
	public ArrayList<OWLNamedClass> getRanges() {
		return ranges;
	}

	public void setRanges(Collection ranges) {
		for(Iterator it = ranges.iterator(); it.hasNext();){
			this.ranges.add((OWLNamedClass)it.next());
		}
	}

	public RDFProperty getInverseProperty() {
		RDFProperty inversePro = this.getObjProperty().getInverseProperty();
		if ( inversePro != null) {
			inversePro.setInverseProperty(this.getObjProperty());
		}
		return inversePro;
	}

	public void setInverseProperty(RDFProperty inverseProperty) {
		this.getObjProperty().setInverseProperty(inverseProperty);
	}	
	
	public static boolean addRelationship(String name){
		try{
			OWLModel.owlModel.createOWLObjectProperty(name);
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			return false;
		}		
		return true;
	}
	
	public boolean addDomain(OWLNamedClass classDomain){
		this.getDomains().add(classDomain);
		this.getObjProperty().setDomains(getDomains());
		return true;
	}
	
	public boolean addRange(OWLNamedClass classRange){
		this.getRanges().add(classRange);
		this.getObjProperty().setRanges(getRanges());
		return true;
	}
	
	public boolean addInverseProperty(OWLObjectProperty inverseProperty){
		this.setInverseProperty(inverseProperty);
		return true;
	}
	
	public static boolean delRelationship(OWLRelationship relate){
		relate.getObjProperty().delete();
		return true;
	}
	
	public boolean delDomain(int index){
		if(index < getDomains().size()) {
			this.getDomains().remove(index);
		}		
		this.getObjProperty().setDomains(this.getDomains());
		return true;		
	}
	
	public boolean delRange(int index){
		if(index < getRanges().size()) {
			this.getRanges().remove(index);
		}		
		this.getObjProperty().setRanges(this.getRanges());
		return true;
	}
	
	public boolean delInverseProperty(){
		this.getObjProperty().getInverseProperty().delete();		
		return true;		
	}
	
	public boolean editDomain(int index, OWLNamedClass domain){
		this.getDomains().remove(index);
		this.getDomains().add(index, domain);
		this.getObjProperty().setDomains(this.getDomains());
		return true;
	}
	
	public boolean editRange(int index, OWLNamedClass range){
		this.getRanges().remove(index);
		this.getRanges().add(index, range);
		this.getObjProperty().setRanges(this.getRanges());
		return true;
	}
	
	public boolean editInverseProperty(OWLObjectProperty inverse){
		this.getObjProperty().setInverseProperty(inverse);
		return true;
	}
	
	public static Collection getRelationships(){
		return OWLModel.owlModel.getUserDefinedOWLObjectProperties();			 
	}
}