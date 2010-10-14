package tkorg.idrs.core.ontology;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import edu.stanford.smi.protegex.owl.model.OWLDatatypeProperty;
import edu.stanford.smi.protegex.owl.model.RDFSDatatype;

import edu.stanford.smi.protegex.owl.model.OWLNamedClass;

public class OWLProperty {
	
	ArrayList < OWLNamedClass > domains = null;
	RDFSDatatype range = null;
	boolean functional = false;	
	OWLDatatypeProperty property = null;
	String name = "";
		
	public OWLProperty() {
		domains = new ArrayList < OWLNamedClass >();
	}

	/**
	 * 
	 * 
	 * @return
	 */
	public void setAlllValues(OWLDatatypeProperty owlProperty) {
		setRange(owlProperty.getRangeDatatype());
		setDomains(owlProperty);
		setFunctional(owlProperty.isFunctional());
		setProperty(owlProperty);
		setName(owlProperty.getBrowserText().substring(owlProperty.getBrowserText().indexOf(":") + 1));
	}
	
	public Collection getDataProperties() {
		return OWLModel.owlModel.getUserDefinedOWLDatatypeProperties();
	}
	
	public Iterator<RDFSDatatype> getDatatypes(){
		return OWLModel.owlModel.getRDFDatatypes().iterator();
	}

	public RDFSDatatype getRange() {
		return range;
	}

	public void setRange(RDFSDatatype range) {
		this.range = range;
	}

	public boolean isFunctional() {
		return functional;
	}

	public void setFunctional(boolean functional) {
		this.functional = functional;
	}

	public ArrayList < OWLNamedClass > getDomains() {
		return domains;
	}
	
	public void setDomains(OWLDatatypeProperty owlProperty) {
		Collection domainList = owlProperty.getDomains(false);
		for (Iterator it = domainList.iterator(); it.hasNext();) {
			OWLNamedClass domain = (OWLNamedClass) it.next();
			domains.add(domain);
		}
	}

	public void setDomains(Collection propertyDomains) {
		domains.clear();
		for (Iterator it = propertyDomains.iterator(); it.hasNext();) {
			OWLNamedClass domain = (OWLNamedClass) it.next();
			domains.add(domain);
		}
	}
	
	public OWLDatatypeProperty getOWLPropertyByName(String str){
		OWLDatatypeProperty dtProperty = OWLModel.owlModel.getOWLDatatypeProperty(str);
		return dtProperty;
	}

	public OWLDatatypeProperty getProperty() {
		return property;
	}

	public void setProperty(OWLDatatypeProperty property) {
		this.property = property;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDomains(ArrayList<OWLNamedClass> domains) {
		this.domains = domains;
	}
}
