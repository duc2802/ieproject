package tkorg.idrs.core.ontology;

import java.io.File;
import java.net.URI;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;

import com.hp.hpl.jena.util.FileUtils;

import sun.util.logging.resources.logging;
import edu.stanford.smi.protege.exception.OntologyLoadException;
import edu.stanford.smi.protegex.owl.ProtegeOWL;
import edu.stanford.smi.protegex.owl.jena.JenaOWLModel;


public class OWLModel {
	
	public static JenaOWLModel owlModel = null;
	
	public OWLModel(){
		
		try {
			owlModel = ProtegeOWL.createJenaOWLModel();
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
		
	}
	
	/**
	 *  
	 * @return
	 */
	public static boolean loadOWLModelFromExistFile(URI uri) throws UnknownHostException {
		try {			
			owlModel = ProtegeOWL.createJenaOWLModelFromURI(uri.toString());
			return true;
		} catch (OntologyLoadException e) {
			System.out.print(e.getMessage());
			return false;
		}catch (NullPointerException e) {
			return false;
		}
		
	}
	
	public boolean saveOWLModelToOWLFile(String fileName, String url){
		Collection errors = new ArrayList();
	    owlModel.save(new File(fileName).toURI(), FileUtils.langXMLAbbrev, errors);
	    System.out.println("File saved with " + errors.size() + " errors.");
		return true;
	}
	
	/**
	 * 
	 * 
	 */
	public JenaOWLModel getOWLModel(){
		return owlModel;
	}
}
